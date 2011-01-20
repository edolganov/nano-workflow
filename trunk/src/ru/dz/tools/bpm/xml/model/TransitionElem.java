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

@XStreamAlias("transition")
//@XmlAccessorType(XmlAccessType.FIELD)
public class TransitionElem {
	
	@XStreamAlias("to")@XStreamAsAttribute
	//@XmlAttribute(name="to",required=true)
	private String to;
	
	@XStreamAlias("name")@XStreamAsAttribute
	//@XmlAttribute(name="name",required=true)
	private String name;
	
	@XStreamImplicit(itemFieldName="event-listener")
	//@XmlElements({@XmlElement(name = "event-listener",required=false)})
	private List<EventListenerElem> eventListeners;
	
	

	public List<EventListenerElem> getEventListeners() {
		return eventListeners;
	}

	public void setEventListeners(List<EventListenerElem> eventListeners) {
		this.eventListeners = eventListeners;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "TransitionElem [eventListeners=" + eventListeners + ", name="
				+ name + ", to=" + to + "]";
	}
	
	
	

}
