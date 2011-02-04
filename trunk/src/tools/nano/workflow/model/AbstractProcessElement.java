package tools.nano.workflow.model;

import java.util.List;

import tools.nano.workflow.model.execution.EventListener;
import tools.nano.workflow.model.execution.ExecutionContext;
import tools.nano.workflow.model.execution.HasStartEventListener;
import tools.nano.workflow.tools.EventListenerContainer;

public abstract class AbstractProcessElement implements HasStartEventListener {

	protected String id;
	protected EventListenerContainer eventListenerContainer = new EventListenerContainer();


	public AbstractProcessElement(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void addStartListener(EventListener eventListener){
		eventListenerContainer.addListener(eventListener);
	}
	
	@Override
	public void fireStartNotify(ExecutionContext context) throws Exception{
		eventListenerContainer.fireNotify(context);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventListener> getListeners() {
		return eventListenerContainer.getListeners();
	}
	
	
	@Override
	public String toString() {
		return "AbstractProcessElement [id=" + id + ", eventListenerContainer="
				+ eventListenerContainer + "]";
	}
	

}