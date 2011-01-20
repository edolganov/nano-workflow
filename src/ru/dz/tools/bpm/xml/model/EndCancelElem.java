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

@XStreamAlias("end-cancel")
//@XmlAccessorType(XmlAccessType.FIELD)
public class EndCancelElem {
	
	@XStreamAlias("name")@XStreamAsAttribute
	//@XmlAttribute(name="name",required=true)
	private String name;
	
	@XStreamImplicit(itemFieldName="on")
	//@XmlElements({@XmlElement(name = "on",required=false)})
	private List<OnElem> ons;
	
	

	public List<OnElem> getOns() {
		return ons;
	}

	public void setOns(List<OnElem> ons) {
		this.ons = ons;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EndCancelElem [name=" + name + ", ons=" + ons + "]";
	}
	
	

}
