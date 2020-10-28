import java.util.*;

class nfadata
{
	public List<String> Q = new ArrayList<>();
	public List<String> Sigma = new ArrayList<>();
	public Map<String, Map<String, List<String>>> delta;
	public String q0;
	public List<String> accept = new ArrayList<>();
	
	public void nfadata(List<String> Q, List<String> Sigma, Map<String, Map<String, List<String>>> delta, String q0, List<String> accept)
	{
		this.Q = Q;
		this.Sigma = Sigma;
		this.delta = delta;
		this.q0 = q0;
		this.accept = accept;
	}
	
	public boolean run(String input)
	{
		
		List<String> currents = new ArrayList<String>(); // this will house all current states
		currents.add(q0); // add first state to currents
		char[] s = input.toCharArray(); // this way we can itterate by character
		for (char c : s) // itterate through all of the current states
		{
			String currentinput = String.valueOf(c);
			List<String> readstates = new ArrayList<String>(); // this stores all of the next possible states
			// System.out.println(currents);
			for (int i = 0; i < currents.size(); i++) // iterate through all current states
			{
				// System.out.println(delta.get(currents.get(i)).containsKey(currentinput));
				if (delta.get(currents.get(i)).containsKey(currentinput))
				{
					readstates.addAll(delta.get(currents.get(i)).get(currentinput));
				}
				//System.out.println("YES");
			}
			currents = readstates;
		}
		for (int i = 0; i < currents.size(); i++) // iterate through all current states
		{
			if (accept.contains(currents.get(i))) // if any current state is an accept state
			{
				return true; // return true
			}
		}
		return false; // no curret state is an accept state
		
	}
	/*

	public static void main(String[] args)
	{
		/*
		char read = '0';
		String from = "q1";
		String to = "q2";
		String tot = "q3";
		string
		delta = new TreeMap<String, Map<Character, List<String>>>();
		delta.put(from, new TreeMap<Character, List<String>>());
		delta.get(from).put(read, new ArrayList<String>());
		delta.get(from).get(read).add(to);
	
		//System.out.println(delta);}
	}
	*/
	
}
