package tools.nano.workflow.model;

public class End extends AbstractProcessElement {

	public End(String id) {
		super(id);
	}

	@Override
	public String toString() {
		return "End [\n" +
		"\t\tid="+id+",\n"+
		"\t\teventListenerContainer=" + eventListenerContainer + "]";
	}


	
	

}
