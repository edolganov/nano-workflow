package tools.nano.bpm.xml.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("state")
//@XmlAccessorType(XmlAccessType.FIELD)
public class StateElem {
	
	@XStreamAlias("name")@XStreamAsAttribute
	//@XmlAttribute(name="name",required=true)
	private String name;
	
	@XStreamImplicit(itemFieldName="transition")
	//@XmlElements({@XmlElement(name = "transition",required=false)})
	private List<TransitionElem> transitions;
	
	@XStreamImplicit(itemFieldName="on")
	//@XmlElements({@XmlElement(name = "on",required=false)})
	private List<OnElem> ons;
	
	

	public List<OnElem> getOns() {
		return ons;
	}

	public void setOns(List<OnElem> ons) {
		this.ons = ons;
	}

	public List<TransitionElem> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<TransitionElem> transitions) {
		this.transitions = transitions;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StateElem [name=" + name + ", ons=" + ons + ", transitions="
				+ transitions + "]";
	}
	
	

}
