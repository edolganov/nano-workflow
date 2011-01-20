package ru.dz.tools.bpm.xml;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ru.dz.tools.bpm.model.AbstractInProcessElement;
import ru.dz.tools.bpm.model.AbstractProcessElement;
import ru.dz.tools.bpm.model.Decision;
import ru.dz.tools.bpm.model.End;
import ru.dz.tools.bpm.model.Process;
import ru.dz.tools.bpm.model.State;
import ru.dz.tools.bpm.model.Transition;
import ru.dz.tools.bpm.model.execution.DecisionHandler;
import ru.dz.tools.bpm.model.execution.EventListener;
import ru.dz.tools.bpm.model.execution.HasStartEventListener;
import ru.dz.tools.bpm.xml.model.DecisionElem;
import ru.dz.tools.bpm.xml.model.EndCancelElem;
import ru.dz.tools.bpm.xml.model.EndElem;
import ru.dz.tools.bpm.xml.model.EventListenerElem;
import ru.dz.tools.bpm.xml.model.HandlerElem;
import ru.dz.tools.bpm.xml.model.OnElem;
import ru.dz.tools.bpm.xml.model.ProcessElem;
import ru.dz.tools.bpm.xml.model.StartElem;
import ru.dz.tools.bpm.xml.model.StateElem;
import ru.dz.tools.bpm.xml.model.TransitionElem;

public class ProcessBuilder {
	
	public static void main(String[] args) throws Exception {
		InputStream in = Parser.class.getResourceAsStream("/ru/dz/metashop/service/common/order/bpm/process-order.jpdl.xml");
		
		Process process = new ProcessBuilder().build(in);
		System.out.println(process);
	}
	
	
	public Process build(InputStream in) throws Exception{
		ProcessElem processElem = new Parser().parse(in);

		//анализ
		StartElem start = processElem.getStart();
		if(start == null) throw new IllegalStateException("no start elem");
		
		List<TransitionElem> transitions = start.getTransitions();
		if(transitions == null || transitions.size() != 1) throw new IllegalStateException("start.getTransitions(): transitions == null || transitions.size() != 1");
			
		TransitionElem elem = transitions.get(0);
		check(elem);
		String firstElem = elem.getTo();
		
		HashMap<String, AbstractProcessElement> elems = new HashMap<String, AbstractProcessElement>();
		HashMap<String, List<TransitionElem>> transitionsElems = new HashMap<String,List<TransitionElem>>();
		
		
		List<DecisionElem> decisions = processElem.getDecisions();
		if(decisions != null){
			for (DecisionElem decisionElem : decisions) {
				check(decisionElem);
				String name = decisionElem.getName();
				Decision decision = new Decision(name);
				elems.put(name, decision);
				
				HandlerElem handler = decisionElem.getHandler();
				if(handler != null){
					check(handler);
					String clazzName = handler.getClazz();
					Class<?> clazz = Class.forName(clazzName);
					if( ! DecisionHandler.class.isAssignableFrom(clazz)) throw new IllegalStateException(" ! DecisionHandler.class.isAssignableFrom(clazz): "+clazz);
					DecisionHandler<?> ob = (DecisionHandler<?>)clazz.newInstance();
					decision.setHandler(ob);
				}
				
				List<TransitionElem> trs  = decisionElem.getTransitions();
				if(trs != null){
					check(trs);
					transitionsElems.put(name, trs);
				}
				
				processOnElem(decision, decisionElem.getOns());
			}
		}
		
		List<StateElem> states = processElem.getStates();
		if(states != null){
			for (StateElem stateElem : states) {
				check(stateElem);
				String name = stateElem.getName();
				State state = new State(name);
				elems.put(name, state);
				
				List<TransitionElem> trs = stateElem.getTransitions();
				if(trs != null){
					check(trs);
					transitionsElems.put(name, trs);
				}
				
				processOnElem(state, stateElem.getOns());
			}
		}
		
		List<EndElem> ends = processElem.getEnds();
		if(ends != null){
			for (EndElem endElem : ends) {
				check(endElem);
				String name = endElem.getName();
				End end = new End(name);
				elems.put(name, end);
				
				processOnElem(end, endElem.getOns());
			}
		}
		
		List<EndCancelElem> endCancels = processElem.getEndCancels();
		if(endCancels != null){
			for (EndCancelElem endElem : endCancels) {
				check(endElem);
				String name = endElem.getName();
				End end = new End(name);
				elems.put(name, end);
				
				processOnElem(end, endElem.getOns());
			}
		}
		
		
		
		
		
		//сбока
		Process process = new Process();
		AbstractProcessElement first = elems.get(firstElem);
		if(first == null) throw new IllegalStateException("map.get(firstElem) is null");
		if(! (first instanceof AbstractInProcessElement)) throw new IllegalStateException(" ! (first instanceof AbstractInProcessElement)");
		
		process.addFirst((AbstractInProcessElement)first);
		
		Collection<AbstractProcessElement> values = elems.values();
		for (AbstractProcessElement abstractProcessElement : values) {
			if(first != abstractProcessElement){
				process.add(abstractProcessElement);
			}
		}
		
		Set<String> keySet = transitionsElems.keySet();
		for (String fromKey : keySet) {
			AbstractProcessElement from = elems.get(fromKey);
			if(from == null) throw new IllegalStateException("no \"from\" elem by key "+fromKey);
			if(! (from instanceof AbstractInProcessElement)) throw new IllegalStateException(" ! (from instanceof AbstractInProcessElement) : "+from);
			
			List<TransitionElem> list = transitionsElems.get(fromKey);
			for (TransitionElem transitionElem : list) {
				String toKey = transitionElem.getTo();
				String signalName = transitionElem.getName();
				
				AbstractProcessElement to = elems.get(toKey);
				if(to == null) throw new IllegalStateException("no \"to\" elem by key "+toKey);
				
				Transition transition = new Transition(signalName, (AbstractInProcessElement)from, to);
				process.add(transition);
				
				addStartEventListeners(transition, transitionElem.getEventListeners());
			}
		}
		
		
		
		
		
		return process;
		
	}
	
	
	
	
	
	
	
	private void processOnElem(HasStartEventListener hasStartEventListener,List<OnElem> ons) throws Exception{
		if(ons != null){
			for (OnElem onElem : ons) {
				check(onElem);
				String event = onElem.getEvent();
				if("start".equals(event)){
					addStartEventListeners(hasStartEventListener, onElem.getEventListeners());
				}
			}
		}
	}



	private void addStartEventListeners(HasStartEventListener hasStartEventListener, List<EventListenerElem> eventListeners) throws Exception{
		if(eventListeners != null){
			checkListeners(eventListeners);
			for (EventListenerElem l : eventListeners) {
				String clazzName = l.getClazz();
				Class<?> clazz = Class.forName(clazzName);
				if( ! EventListener.class.isAssignableFrom(clazz)) throw new IllegalStateException(" ! EventListener.class.isAssignableFrom(clazz): "+clazz);
				EventListener<?> ob = (EventListener<?>)clazz.newInstance();
				hasStartEventListener.addStartListener(ob);
			}
		}
	}






	private void check(OnElem elem) {
		if(elem == null) throw new IllegalStateException("check(OnElem elem): elem is null");
		if(elem.getEvent() == null)throw new IllegalStateException("check(OnElem elem): elem.getEvent() == null");
	}


	private void checkListeners(List<EventListenerElem> eventListeners) {
		for (EventListenerElem eventListenerElem : eventListeners) {
			check(eventListenerElem);
		}
		
	}


	private void check(EventListenerElem elem) {
		if(elem == null) throw new IllegalStateException("check(EventListenerElem elem): elem is null");
		if(elem.getClazz() == null)throw new IllegalStateException("check(EventListenerElem elem): elem.getClazz() == null");
		
	}


	private void check(HandlerElem elem) {
		if(elem == null) throw new IllegalStateException("check(HandlerElem elem): elem is null");
		if(elem.getClazz() == null)throw new IllegalStateException("check(HandlerElem elem): elem.getClazz() == null");
		
	}


	private void check(EndCancelElem elem) {
		if(elem == null) throw new IllegalStateException("check(EndCancelElem elem): elem is null");
		if(elem.getName() == null)throw new IllegalStateException("check(EndCancelElem elem): elem.getName() == null");
	}

	private void check(EndElem elem) {
		if(elem == null) throw new IllegalStateException("check(EndElem endElem): elem is null");
		if(elem.getName() == null)throw new IllegalStateException("check(EndElem endElem): elem.getName() == null");
		
	}

	private void check(StateElem elem) {
		if(elem == null) throw new IllegalStateException("check(StateElem elem): elem is null");
		if(elem.getName() == null)throw new IllegalStateException("check(StateElem elem): elem.getName() == null");
		
	}

	private void check(DecisionElem elem) {
		if(elem == null) throw new IllegalStateException("check(DecisionElem elem): elem is null");
		if(elem.getName() == null)throw new IllegalStateException("check(DecisionElem elem): elem.getName() == null");
	}

	private void check(List<TransitionElem> trs) {
		for (TransitionElem transitionElem : trs) {
			check(transitionElem);
		}
	}
	
	private void check(TransitionElem elem) {
		if(elem == null) throw new IllegalStateException("check(TransitionElem elem): elem is null");
		if(elem.getTo() == null)throw new IllegalStateException("check(TransitionElem elem): elem.getTo() == null");
	}

}
