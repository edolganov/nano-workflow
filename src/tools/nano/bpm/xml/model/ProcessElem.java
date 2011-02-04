package tools.nano.bpm.xml.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("process")
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name = "process")
public class ProcessElem {

	@XStreamAlias("start")
	//@XmlElement(name="start",required=true)
	private StartElem start;
	
	@XStreamImplicit(itemFieldName="state")
	//@XmlElements({@XmlElement(name = "state",required=false)})
	private List<StateElem> states;
	
	@XStreamImplicit(itemFieldName="decision")
	//@XmlElements({@XmlElement(name = "decision",required=false)})
	private List<DecisionElem> decisions;
	
	@XStreamImplicit(itemFieldName="end")
	//@XmlElements({@XmlElement(name = "end",required=false)})
	private List<EndElem> ends;
	
	@XStreamImplicit(itemFieldName="end-cancel")
	//@XmlElements({@XmlElement(name = "end-cancel",required=false)})
	private List<EndCancelElem> endCancels;
	
	
	
	
	public List<EndCancelElem> getEndCancels() {
		return endCancels;
	}

	public void setEndCancels(List<EndCancelElem> endCancels) {
		this.endCancels = endCancels;
	}

	public StartElem getStart() {
		return start;
	}

	public void setStart(StartElem start) {
		this.start = start;
	}
	

	public List<StateElem> getStates() {
		return states;
	}

	public void setStates(List<StateElem> states) {
		this.states = states;
	}

	public List<DecisionElem> getDecisions() {
		return decisions;
	}

	public void setDecisions(List<DecisionElem> decisions) {
		this.decisions = decisions;
	}

	public List<EndElem> getEnds() {
		return ends;
	}

	public void setEnds(List<EndElem> ends) {
		this.ends = ends;
	}

	@Override
	public String toString() {
		return "ProcessElem [decisions=" + decisions + ", endCancels="
				+ endCancels + ", ends=" + ends + ", start=" + start
				+ ", states=" + states + "]";
	}
	

}
