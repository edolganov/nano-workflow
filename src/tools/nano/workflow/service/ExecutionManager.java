package tools.nano.workflow.service;

import java.util.Collection;

import org.apache.commons.logging.Log;

import tools.nano.workflow.model.AbstractProcessElement;
import tools.nano.workflow.model.Decision;
import tools.nano.workflow.model.End;
import tools.nano.workflow.model.Process;
import tools.nano.workflow.model.State;
import tools.nano.workflow.model.Transition;

public class ExecutionManager {
	
	Process process;
	Object context;
	Log log;
	StringBuilder processLog = null;
	String logHeader;
	
	public ExecutionManager(Process process, Object context) {
		this(process, context, null,null);
	}
	
	public ExecutionManager(Process process, Object context, Log log, String logHeader) {
		super();
		this.process = process;
		this.context = context;
		
		if(log != null && log.isInfoEnabled()){
			this.log = log;
			processLog = new StringBuilder();
			this.logHeader = logHeader;
		}
	}

	
	/**
	 * Запусить инстанцию процесса
	 * @throws Exception
	 */
	public void startProcessInstance() throws Exception{
		startProcessInstance(true, null, null);
	}


	/**
	 * Запустить инстанцию процесса с определенного состояния
	 * @param parentState родительское состояние
	 * @param signal переход из родительского состояния
	 * @throws Exception 
	 */
	public void startProcessInstance(String parentState, String signal) throws Exception {
		startProcessInstance(false, parentState, signal);
	}
	
	
	private void startProcessInstance(boolean fromBegin, String parentState, String signal) throws Exception {
		long curTime = 0;
		if(canLog()) {
			curTime = System.currentTimeMillis();
			if(logHeader != null) logMain("\n\nInfo: "+logHeader);
			logMain("Start process instance...");
			
		}
		
		
		boolean errorBreak = false;
		String errorMsg = null;
		try {
			if(fromBegin){
				
				AbstractProcessElement element = process.getFirst();
				loopInstance(element);
				
			} else {
				if(canLog()) log("parentState="+parentState+", signal="+signal);
				
				AbstractProcessElement parent = process.get(parentState);
				if(parent == null) throw new IllegalStateException("no state by id "+parentState);
				
				AbstractProcessElement element = processingTransition(parentState, signal);
				loopInstance(element);
			}
		}catch (Exception e) {
			errorMsg = e.getMessage();
			errorBreak = true;
			throw e;
		}
		finally {
			if(canLog()) {
				if(!errorBreak){
					logMain("Stop process instance.");
				} else {
					logMain("Unexpected error while excecuting process: "+errorMsg);
				}
				
				double workTime = (System.currentTimeMillis() - curTime) / 1000.;
				logMain("work time: "+workTime+" sec");
				printLog();
			}
		}
	}
	
	


	private void loopInstance(AbstractProcessElement element) throws Exception {
		while(element != null){
			element = processing(element);
		}
	}
	

	private AbstractProcessElement processing(AbstractProcessElement element) throws Exception {
		if(canLog()) log("processing "+element.getClass().getSimpleName()+" with id="+element.getId());
		if(element instanceof Decision){
			Decision decision = (Decision)element;
			
			if(canLog()) logCall(decision.getHandler());
			decision.fireStartNotify(context);
			
			String signalName = decision.getTransition(context);
			return processingTransition(decision.getId(), signalName);
		}
		
		if(element instanceof State){
			State state = (State) element;
			
			if(canLog()) logCall(state.getListeners());
			state.fireStartNotify(context);
		}
		
		if(element instanceof End){
			End end = (End) element;
			
			if(canLog()) logCall(end.getListeners());
			end.fireStartNotify(context);
		}
		
		
		return null;
	}

	private AbstractProcessElement processingTransition(String from, String signalName) throws Exception {
		if(canLog()) log("\ttransition from="+from+", signalName="+signalName);
		
		Transition transition = process.getTransition(from,signalName);
		if(transition == null) throw new IllegalStateException("Unknow signal name "+signalName+" for element "+from);
		
		if(canLog()) logCall(transition.getListeners());
		transition.fireStartNotify(context);
		return transition.getTo();
	}
	
	
	
	
	
	
	
	private boolean canLog(){
		return processLog != null;
	}
	
	private void logMain(String line) {
		processLog.append(line).append("\n");
	}
	
	private void log(String line) {
		processLog.append("\t").append(line).append("\n");
	}
	
	private void logCall(Object ob) {
		if(ob == null) return;
		if(ob instanceof Collection<?> && ((Collection<?>)ob).size() ==0) return;
		
		processLog.append("\t\t\tcall ").append(ob).append("\n");
	}
	
	private void printLog() {
		
		if(log != null && log.isInfoEnabled() && processLog != null) log.info(processLog.toString());
	}



}
