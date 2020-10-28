import java.io.*;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
public class nfa {
	public static List<String> combinations = new ArrayList<>();
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
    		List<String> Q = new ArrayList<>();
    		List<String> Qid = new ArrayList<>();
		List<String> Sigma = new ArrayList<>();
		Map<String, Map<String, List<String>>> delta;
		String q0;
		List<String> accept = new ArrayList<>();
		List<String> epsilonFrom = new ArrayList<>();
		List<String> epsilonTo = new ArrayList<>();
        	try {
        	Scanner sc = new Scanner(System.in);
        	String url = sc.nextLine();
        	File input = new File(url);
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder builder = factory.newDocumentBuilder();
        	Document doc = builder.parse(input);
        	doc.getDocumentElement().normalize();
        	NodeList nodes = doc.getElementsByTagName("state");
        	for (int i = 0; i < nodes.getLength(); i++) {
        	    Element element = (Element) nodes.item(i);
        	    Qid.add(element.getAttribute("id"));
        	    Q.add(element.getAttribute("name"));
        	}
        	if (Q.size() == 1)
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
        	if (q0 == accept.get(0) && accept.size() == 1)
        	{
        		System.out.println();
        	}
        	nfadata nfa = new nfadata(); //(Q, Sigma, delta, q0, accept);
        	nfa.Q = Qid;
        	nfa.Sigma = Sigma; 
        	nfa.delta = delta;
        	nfa.q0 = q0;
        	nfa.accept = accept;
        	String strcomb = "";
        	String str = "";
        	for (int i = 0; i < Sigma.size(); i++)
        	{
        		str += Sigma.get(i);
        	}
        	int n = str.length();
        	int k = 5;
        	for (int i = 0; i < 5; i++)
        	{
        		permute(str, strcomb,n, k - i);
        	}	
        	for (int j = 0; j < combinations.size(); j++)
        	{
        		if (nfa.run(combinations.get(j)) ==  true)
        		{
        		System.out.println(combinations.get(j));
        		}
        	}
     
        	sc.close();
        	}
        	catch (Exception e) {
        	    e.printStackTrace();
        	}
    	}	

}
