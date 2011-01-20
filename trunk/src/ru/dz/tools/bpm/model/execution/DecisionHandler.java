package ru.dz.tools.bpm.model.execution;

public interface DecisionHandler<T extends ExecutionContext> {
	
	String decide(T context) throws Exception;

}
