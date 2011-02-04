package tools.nano.workflow.xml.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("start")
//@XmlAccessorType(XmlAccessType.FIELD)
public class StartElem {
	
	@XStreamImplicit(itemFieldName="transition")
	//@XmlElements({@XmlElement(name = "transition",required=false)})
	private List<TransitionElem> transitions;

	public List<TransitionElem> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<TransitionElem> transitions) {
		this.transitions = transitions;
	}

	@Override
	public String toString() {
		return "StartElem [transitions=" + transitions + "]";
	}
	
	

}
