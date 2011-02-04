package tools.nano.bpm.model.execution;

public interface EventListener<T extends ExecutionContext> {
	
	void notify(T context) throws Exception;


}
