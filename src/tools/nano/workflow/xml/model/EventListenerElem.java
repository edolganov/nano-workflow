package tools.nano.workflow.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("event-listener")
//@XmlAccessorType(XmlAccessType.FIELD)
public class EventListenerElem {
	
	@XStreamAlias("class")@XStreamAsAttribute
	//@XmlAttribute(name="class",required=true)
	private String clazz;

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	@Override
	public String toString() {
		return "EventListenerElem [class=" + clazz + "]";
	}
	
	

}
