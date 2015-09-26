package xslt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ModifyNGXML {

	public static void main(String argv[]) {

		try {
			File file = new File("test-output\\testng-results.xml");
			System.out.println(file.getAbsolutePath());
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			InputStream inputStream= new FileInputStream(file.getAbsolutePath());
			Reader reader = new InputStreamReader(inputStream,"UTF-8");
			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");
			Document doc = docBuilder.parse(is);
			NodeList testmethods = doc.getElementsByTagName("test-method");

			for(int j=0; j<testmethods.getLength();j++){
				NodeList list1 =testmethods.item(j).getChildNodes();
				for (int i = 0; i < list1.getLength(); i++) {

					Node node = list1.item(i);
					if ("exception".equals(node.getNodeName())) {
						NamedNodeMap exception_attr =  node.getAttributes();
						Node exception_node_attr = exception_attr.getNamedItem("class");
						if("java.lang.AssertionError".equals(exception_node_attr.getNodeValue())){}else{
							NamedNodeMap attr = testmethods.item(j).getAttributes();
							Node nodeAttr = attr.getNamedItem("status");
							nodeAttr.setTextContent("SKIP");
							Element ro = doc.createElement("reporter-output");
							String act ="%3c![CDATA[[Status]:TestFour_SKIPPED_RUNTIME]]%3e";
							String result = java.net.URLDecoder.decode(act, "UTF8");
							ro.appendChild(doc.createTextNode(result));
							testmethods.item(j).appendChild(ro);
							System.out.println("modified and::"+exception_node_attr.getNodeValue());
							break;
						}
					}
				}
			}


			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file.getAbsolutePath());
			transformer.transform(source, result);

			System.out.println("Done");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
}