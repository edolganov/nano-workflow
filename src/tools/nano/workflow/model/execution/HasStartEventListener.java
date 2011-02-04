package tools.nano.workflow.model.execution;

import java.util.List;

public interface HasStartEventListener {
	
	@SuppressWarnings("unchecked")
	void addStartListener(EventListener eventListener);
	
	
	void fireStartNotify(Object context) throws Exception;
	
	@SuppressWarnings("unchecked")
	List<EventListener> getListeners();
}
