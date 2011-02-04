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

@XStreamAlias("on")
//@XmlAccessorType(XmlAccessType.FIELD)
public class OnElem {
	
	@XStreamAlias("event")@XStreamAsAttribute
	//@XmlAttribute(name="event",required=false)
	private String event;
	
	@XStreamImplicit(itemFieldName="event-listener")
	//@XmlElements({@XmlElement(name = "event-listener",required=false)})
	private List<EventListenerElem> eventListeners;
	
	
	

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public List<EventListenerElem> getEventListeners() {
		return eventListeners;
	}

	public void setEventListeners(List<EventListenerElem> eventListeners) {
		this.eventListeners = eventListeners;
	}

	@Override
	public String toString() {
		return "OnElem [event=" + event + ", eventListeners=" + eventListeners
				+ "]";
	}

	
	

	
	

}
