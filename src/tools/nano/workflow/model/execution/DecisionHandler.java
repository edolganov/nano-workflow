package tools.nano.workflow.model.execution;

public interface DecisionHandler<T> {
	
	String decide(T context) throws Exception;

}
