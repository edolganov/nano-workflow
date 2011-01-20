package ru.dz.tools.bpm.model;

public class State extends AbstractInProcessElement {

	public State(String id) {
		super(id);
	}

	@Override
	public String toString() {
		return "State [\n" +
		"\t\tid="+id+",\n"+
		"\t\teventListenerContainer=" + eventListenerContainer + "]";
	}
	
	

}
