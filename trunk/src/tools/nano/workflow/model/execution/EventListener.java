package tools.nano.workflow.model.execution;

public interface EventListener<T extends ExecutionContext> {
	
	void notify(T context) throws Exception;


}
