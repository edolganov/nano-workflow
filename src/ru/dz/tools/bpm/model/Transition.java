package ru.dz.tools.bpm.model;

import java.util.List;

import ru.dz.tools.bpm.model.execution.EventListener;
import ru.dz.tools.bpm.model.execution.ExecutionContext;
import ru.dz.tools.bpm.model.execution.HasStartEventListener;
import ru.dz.tools.bpm.tools.EventListenerContainer;


public class Transition implements HasStartEventListener{
	
	private String signalName;


	private AbstractInProcessElement from;
	private AbstractProcessElement to;
	private EventListenerContainer eventListenerContainer = new EventListenerContainer();

	public Transition(String signalName, AbstractInProcessElement from, AbstractProcessElement to) {
		this.from = from;
		this.to = to;
		this.signalName = signalName;
	}

	public AbstractInProcessElement getFrom() {
		return from;
	}

	public AbstractProcessElement getTo() {
		return to;
	}

	public String getSignalName() {
		return signalName;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void addStartListener(EventListener eventListener){
		eventListenerContainer.addListener(eventListener);
	}
	
	
	public void fireStartNotify(ExecutionContext context) throws Exception{
		eventListenerContainer.fireNotify(context);
	}

	@Override
	public String toString() {
		return "Transition [eventListenerContainer=" + eventListenerContainer
				+ ", signalName=" + signalName + ", to=" + to.getId() + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventListener> getListeners() {
		return eventListenerContainer.getListeners();
	}
	
	
	


}
