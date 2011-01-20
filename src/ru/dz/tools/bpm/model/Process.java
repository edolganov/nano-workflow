package ru.dz.tools.bpm.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Process {
	
	private AbstractProcessElement firstElement;
	
	private Map<String, AbstractProcessElement> elementsCache = new LinkedHashMap<String, AbstractProcessElement>();
	private Map<String, Map<String,Transition>> fromTransitions = new LinkedHashMap<String, Map<String,Transition>>();
	
	

	public void addFirst(AbstractInProcessElement element) {
		if(firstElement != null) throw new IllegalStateException("Process already has first element by id: "+firstElement.getId());
		firstElement = element;
		put(element);
	}
	


	public void add(AbstractProcessElement element){
		put(element);
	}
	
	public void add(Transition transition){
		AbstractInProcessElement from = transition.getFrom();
		String fromId = from.getId();
		checkExist(fromId);
		
		AbstractProcessElement to = transition.getTo();
		checkExist(to.getId());
		
		if(from.equals(to)) throw new IllegalStateException("Can't transition from element to himself: "+firstElement.getId());
		
		Map<String, Transition> transtitons = fromTransitions.get(fromId);
		if(transtitons == null){
			transtitons = new LinkedHashMap<String, Transition>();
			fromTransitions.put(fromId, transtitons);
		}
		transtitons.put(transition.getSignalName(), transition);
		
	}
	
	public AbstractProcessElement get(String id){
		AbstractProcessElement element = elementsCache.get(id);
		return element;
	}
	
	public Transition getTransition(String elemId, String signalName){
		Map<String, Transition> map = fromTransitions.get(elemId);
		if(map == null) return null;
		
		return map.get(signalName);
	}
	
	

	public AbstractProcessElement getFirst() {
		return firstElement;
	}
	
	
	
	
	
	
	

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Process [elements=[\n");
	
		
		Set<Entry<String, AbstractProcessElement>> entrySet = elementsCache.entrySet();
		for (Entry<String, AbstractProcessElement> entry : entrySet) {
			AbstractProcessElement elem = entry.getValue();
			sb.append("\t");
			sb.append(elem);
			
			Map<String, Transition> map = fromTransitions.get(elem.getId());
			if(map != null){
				sb.append("\n\t\tTrancitions ");
				Collection<Transition> trs = map.values();
				sb.append(trs);
			}
			sb.append(",\n");
		}
		
		sb.append("]");
		sb.append(", firstElement=");
		sb.append(firstElement);
		sb.append("]");
		
		return sb.toString();
	}



	private void put(AbstractProcessElement element){
		if(element == null) throw new IllegalStateException("element is null");
		
		String id = element.getId();
		if(id == null)throw new IllegalStateException("element's id is null");
		if(elementsCache.containsKey(id)) throw new IllegalStateException("id is already in process: "+id);
		
		elementsCache.put(element.getId(), element);
	}
	
	private void checkExist(String id){
		if(!elementsCache.containsKey(id)) throw new IllegalStateException("no element by id: "+id);
	}




	


}
