package tools.nano.bpm.xml;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.thoughtworks.xstream.XStream;

import tools.nano.bpm.xml.model.DecisionElem;
import tools.nano.bpm.xml.model.EndCancelElem;
import tools.nano.bpm.xml.model.EndElem;
import tools.nano.bpm.xml.model.EventListenerElem;
import tools.nano.bpm.xml.model.HandlerElem;
import tools.nano.bpm.xml.model.OnElem;
import tools.nano.bpm.xml.model.ProcessElem;
import tools.nano.bpm.xml.model.StartElem;
import tools.nano.bpm.xml.model.StateElem;
import tools.nano.bpm.xml.model.TransitionElem;

public class Parser {
	
	
	
	public static void main(String[] args) {
		InputStream in = Parser.class.getResourceAsStream("/ru/dz/metashop/service/common/order/bpm/process-order.jpdl.xml");
		ProcessElem parse = new Parser().parse(in);
		System.out.println(parse.toString());
	}
	
	
	public ProcessElem parse(InputStream in){
		boolean usingJavaxParser = false; //на модуле покупателя JavaxParser не читал атрибуты у тегов
		
		if(usingJavaxParser){
			try {
				SAXParserFactory mParserFactory = SAXParserFactory.newInstance();
				mParserFactory.setNamespaceAware(false);
				mParserFactory.setValidating(false);
	
				SAXParser lSaxParser = mParserFactory.newSAXParser();
				XMLReader lReader = lSaxParser.getXMLReader();
	//			lReader.setFeature(EXTERNAL_DTD_LOADING_FEATURE, false);
	
				JAXBContext context = JAXBContext.newInstance("ru.dz.tools.bpm.xml.model");
				Unmarshaller unm = context.createUnmarshaller();
				ProcessElem process = (ProcessElem) unm.unmarshal(new SAXSource(lReader, new InputSource(in)));
				
				return process;
				
				
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		else {
			try {
				ObjectToXMLConverter<ProcessElem> converter = new ObjectToXMLConverter<ProcessElem>();
				XStream xStream = converter.getXStream();
				xStream.processAnnotations(new Class[]{
					DecisionElem.class,
					EndCancelElem.class,
					EndElem.class,
					EventListenerElem.class,
					HandlerElem.class,
					OnElem.class,
					ProcessElem.class,
					StartElem.class,
					StateElem.class,
					TransitionElem.class	
				});
				xStream.aliasSystemAttribute("java-class", "class");
				
				ProcessElem process = converter.toObject(in);
				return process;
				
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
	}

}
