package ru.dz.tools.bpm.xml.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("decision")
//@XmlAccessorType(XmlAccessType.FIELD)
public class DecisionElem {
	
	
	@XStreamAlias("handler")@XStreamAsAttribute
	//@XmlElement(name="handler",required=false)
	private HandlerElem handler;
	
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

	public HandlerElem getHandler() {
		return handler;
	}

	public void setHandler(HandlerElem handler) {
		this.handler = handler;
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
		return "DecisionElem [handler=" + handler + ", name=" + name + ", ons="
				+ ons + ", transitions=" + transitions + "]";
	}
	
	

}
