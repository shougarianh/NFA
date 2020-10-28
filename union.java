import java.io.*;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
import org.w3c.dom.Document;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 
public class union {
	public static List<String> combinations = new ArrayList<>();
	public static String statenamechanger(String s)
	{
		int i = Integer.parseInt(s) + 1000;
		return Integer.toString(i);
	}
	public static List<String> permute(String str, String strcomb, int n, int k)
	{
		if (k == 0)
		{
			combinations.add(strcomb);
			return combinations;
		}
		for (int i = 0; i < n; i++)
		{
			String temp = "";
			temp = temp + strcomb;
			temp += str.charAt(i);
			permute(str, temp, n, k - 1);
		}
		return combinations;
	}
	public static void main(String [] args) {
    		System.out.println("<automaton>");
    		List<String> Q = new ArrayList<>();
    		List<String> Qid = new ArrayList<>();
		List<String> Sigma = new ArrayList<>();
		Map<String, Map<String, List<String>>> delta;
		String q0;
		List<String> accept = new ArrayList<>();
		List<String> Q2 = new ArrayList<>();
    		List<String> Qid2 = new ArrayList<>();
		List<String> Sigma2 = new ArrayList<>();
		Map<String, Map<String, List<String>>> delta2;
		String q02;
		List<String> accept2 = new ArrayList<>();
		List<String> epsilonFrom = new ArrayList<>();
		List<String> epsilonTo = new ArrayList<>();
		List<String> epsilonFrom2 = new ArrayList<>();
		List<String> epsilonTo2 = new ArrayList<>();
		List<String> outputArray = new ArrayList<>();
        	try {
        	Scanner sc = new Scanner(System.in);
        	String urls = sc.nextLine();
        	String url[] = urls.split(" ");
        	File input = new File(url[0]); // input 1
        	File input2 = new File(url[1]);
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // input 1
        	DocumentBuilder builder = factory.newDocumentBuilder(); // input 1
        	Document doc = builder.parse(input); // input 1
        	doc.getDocumentElement().normalize(); // input 1
        	NodeList nodes = doc.getElementsByTagName("state");
        	for (int i = 0; i < nodes.getLength(); i++) {
        	    Element element = (Element) nodes.item(i);
        	    Qid.add(element.getAttribute("id"));
        	    Q.add(element.getAttribute("name"));
        	}
        	if (Q.size() < 1)
        	{
        		return;
        	}        	
        	NodeList first = doc.getElementsByTagName("initial");
        	Element f = (Element) first.item(0).getParentNode();
        	q0 = f.getAttribute("id");
        	NodeList last = doc.getElementsByTagName("final");
        	for (int i = 0; i < last.getLength(); i++) { 
        	    Element l = (Element) last.item(i).getParentNode();
        	    accept.add(l.getAttribute("id"));
        	}
        	
        	NodeList transition = doc.getElementsByTagName("transition");
        	delta = new HashMap<String, Map<String, List<String>>>();
        	if (accept.size() > 0)
        	{
        		for (int j = 0; j < transition.getLength(); j++)
        		{
        			Node tnode = transition.item(j);
        			Element t = (Element) tnode;
        
        	
        			String from = t.getElementsByTagName("from").item(0).getTextContent();
        			String read = t.getElementsByTagName("read").item(0).getTextContent();
        			String to = t.getElementsByTagName("to").item(0).getTextContent();
        			if (!Sigma.contains(read) && read != "")
        			{
        				Sigma.add(read);
        			}
        		
        			if (read != "")
        			{
        				if (delta.containsKey(from) != true)
        				{
        					delta.put(from, new HashMap<String, List<String>>());
        				}
        				if (delta.get(from).containsKey(read) != true)
        				{
        					delta.get(from).put(read, new ArrayList<String>());
        				}
        				delta.get(from).get(read).add(to);
        			}
        			else
        			{
					epsilonFrom.add(from);
        				epsilonTo.add(to); 
        			}
        			outputArray.add("<transition><from>" + from + "</from><to>" + to + "</to><read>" + read + "</read></transition>");
        		}
        	}
        	DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance(); // input 2
        	DocumentBuilder builder2 = factory2.newDocumentBuilder(); // input 2
        	Document doc2 = builder2.parse(input2); // input 2
        	doc2.getDocumentElement().normalize(); // input 2
        	NodeList nodes2 = doc2.getElementsByTagName("state");
        	for (int i = 0; i < nodes2.getLength(); i++) {
        	    Element element2 = (Element) nodes2.item(i);
        	    Qid2.add(statenamechanger(element2.getAttribute("id")));
        	    Q2.add(element2.getAttribute("name"));
        	}
        	if (Q2.size() < 1)
        	{
        		return;
        	}       		
        	NodeList first2 = doc2.getElementsByTagName("initial");
        	Element f2 = (Element) first2.item(0).getParentNode();
        	q02 = statenamechanger(f2.getAttribute("id"));
        	NodeList last2 = doc2.getElementsByTagName("final");
        	for (int i = 0; i < last2.getLength(); i++) { 
        	    Element l2 = (Element) last2.item(i).getParentNode();
        	    accept2.add(statenamechanger(l2.getAttribute("id")));
        	}
        	String q217 = "q217";
        	System.out.println("<state id=\"" + q217 + "\" " + "name=\"" + q217 + "\">" + "<initial/></state>");
        	for (int i = 0; i < Qid.size(); i++)
        	{
        		if (accept.contains(Qid.get(i)))
        		{
        			System.out.println("<state id=\"" + Qid.get(i) + "\" " + "name=\"" + Q.get(i) + "\">" +"<final/></state>");
        		}
        		else
        		{
        			System.out.println("<state id=\"" + Qid.get(i) + "\" " + "name=\"" + Q.get(i) + "\">" +"</state>");
        		}
        	}
        	for (int i = 0; i < Qid2.size(); i++)
        	{
        		if (accept2.contains(Qid2.get(i)))
        		{
        			System.out.println("<state id=\"" + Qid2.get(i) + "\" " + "name=\"" + Q2.get(i) + "\">" +"<final/></state>");
        		}
        		else
        		{
        			System.out.println("<state id=\"" + Qid2.get(i) + "\" " + "name=\"" + Q2.get(i) + "\">" +"</state>");
        		}
        	}
        	System.out.println("<transition><from>" + q217 + "</from><to>" + q0 + "</to><read/></transition>");
        	System.out.println("<transition><from>" + q217 + "</from><to>" + q02 + "</to><read/></transition>");
        	NodeList transition2 = doc2.getElementsByTagName("transition");
        	delta2 = new HashMap<String, Map<String, List<String>>>();
        	if (accept2.size() > 0)
        	{
        		for (int j = 0; j < transition2.getLength(); j++)
        		{
        			Node tnode2 = transition2.item(j);
        			Element t2 = (Element) tnode2;
        
        	
        			String from2 = statenamechanger(t2.getElementsByTagName("from").item(0).getTextContent());
        			String read2 = t2.getElementsByTagName("read").item(0).getTextContent();
        			String to2 = statenamechanger(t2.getElementsByTagName("to").item(0).getTextContent());
        			if (!Sigma2.contains(read2) && read2 != "")
        			{
        				Sigma2.add(read2);
        			}
        		
        			if (read2 != "")
        			{
        				if (delta2.containsKey(from2) != true)
        				{
        					delta2.put(from2, new HashMap<String, List<String>>());
        				}
        				if (delta2.get(from2).containsKey(read2) != true)
        				{
        					delta2.get(from2).put(read2, new ArrayList<String>());
        				}
        				delta2.get(from2).get(read2).add(to2);
        			}
        			else
        			{
        				epsilonFrom2.add(from2);
        				epsilonTo2.add(to2); 
        			}
        			outputArray.add("<transition><from>" + from2 + "</from><to>" + to2 + "</to><read>" + read2 + "</read></transition>");
        		
        		}
        	}
        	
        	for (int i = 0; i < epsilonFrom.size(); i++)
        	{
        		for (String key : delta.keySet())
        		{
        			for (String key2 : delta.get(key).keySet())
        			{
        				if(delta.get(key).get(key2).contains(epsilonFrom.get(i)))
        				{
        						delta.get(key).get(key2).add(epsilonTo.get(i));
        				}
        			}
        		}
        	}
        	for (int i = 0; i < epsilonFrom2.size(); i++)
        	{
        		for (String key : delta2.keySet())
        		{
        			for (String key2 : delta2.get(key).keySet())
        			{
        				if(delta2.get(key).get(key2).contains(epsilonFrom2.get(i)))
        				{
        						delta2.get(key).get(key2).add(epsilonTo2.get(i));
        				}
        			}
        		}
        	}
        	for (int i = 0; i < outputArray.size(); i++)
        	{
        		System.out.println(outputArray.get(i));
        	}
        	System.out.println("</automaton>");
        	sc.close();
        	}
        	catch (Exception e) {
        	    e.printStackTrace();
        	}
        }
}

     
