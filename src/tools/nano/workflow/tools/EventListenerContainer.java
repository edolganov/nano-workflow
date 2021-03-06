package tools.nano.workflow.tools;

import java.util.ArrayList;
import java.util.List;

import tools.nano.workflow.model.execution.EventListener;

public class EventListenerContainer {
	
	@SuppressWarnings("unchecked")
	private List<EventListener> lisnteners;


	@SuppressWarnings("unchecked")
	public void addListener(EventListener eventListener){
		if(lisnteners == null){
			lisnteners = new ArrayList<EventListener>();
		}
		lisnteners.add(eventListener);
	}
	
	@SuppressWarnings("unchecked")
	public void fireNotify(Object context) throws Exception{
		if(lisnteners != null){
			for (EventListener listener : lisnteners) {
				listener.notify(context);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<EventListener> getListeners() {
		return lisnteners;
	}

	@Override
	public String toString() {
		return "EventListenerContainer [lisnteners=" + lisnteners + "]";
	}

}
