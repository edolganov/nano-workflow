package tools.nano.workflow.model.execution;

public interface EventListener<T> {
	
	void notify(T context) throws Exception;


}
