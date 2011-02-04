package tools.nano.workflow.model.execution;

public interface DecisionHandler<T extends ExecutionContext> {
	
	String decide(T context) throws Exception;

}
