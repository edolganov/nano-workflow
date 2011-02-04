package tools.nano.bpm.model;

import java.util.List;

import tools.nano.bpm.model.execution.DecisionHandler;
import tools.nano.bpm.model.execution.EventListener;
import tools.nano.bpm.model.execution.ExecutionContext;


public class Decision extends AbstractInProcessElement {
	
	@SuppressWarnings("unchecked")
	private DecisionHandler handler;

	public Decision(String id) {
		super(id);
	}

	@SuppressWarnings("unchecked")
	public void setHandler(DecisionHandler handler) {
		this.handler = handler;
	}

	@SuppressWarnings("unchecked")
	public String getTransition(ExecutionContext context) throws Exception {
		if(handler == null) throw new IllegalStateException("no handler for decision "+this);
		return handler.decide(context);
	}

	@Override
	public String toString() {
		return "Decision [\n" +
				"\t\tid="+id+",\n"+
				"\t\thandler=" + handler +",\n"+
				"\t\teventListenerContainer=" + eventListenerContainer + "]";
	}


	public DecisionHandler<?> getHandler(){
		return handler;
	}
	
	


}
