package tools.nano.bpm.tools;

import java.util.ArrayList;
import java.util.List;

import tools.nano.bpm.model.execution.EventListener;
import tools.nano.bpm.model.execution.ExecutionContext;

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
	public void fireNotify(ExecutionContext context) throws Exception{
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
