package ru.dz.tools.bpm.model.execution;

public interface EventListener<T extends ExecutionContext> {
	
	void notify(T context) throws Exception;


}
