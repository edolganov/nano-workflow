package tools.nano.workflow.model;

import java.util.List;

import tools.nano.workflow.model.execution.DecisionHandler;
import tools.nano.workflow.model.execution.EventListener;


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
	public String getTransition(Object context) throws Exception {
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
