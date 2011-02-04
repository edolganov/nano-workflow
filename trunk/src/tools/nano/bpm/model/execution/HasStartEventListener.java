package tools.nano.bpm.model.execution;

import java.util.List;

public interface HasStartEventListener {
	
	@SuppressWarnings("unchecked")
	void addStartListener(EventListener eventListener);
	
	
	void fireStartNotify(ExecutionContext context) throws Exception;
	
	@SuppressWarnings("unchecked")
	List<EventListener> getListeners();
}
