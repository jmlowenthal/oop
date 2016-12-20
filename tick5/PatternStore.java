package oop.tick5;

import java.io.*;
import java.net.*;
import java.util.*;

public class PatternStore {
	private List<Pattern> patterns = new LinkedList<>();
	private Map<String, List<Pattern>> authorMap = new HashMap<>();
	private Map<String, Pattern> nameMap = new HashMap<>();
	
	public PatternStore(String source) throws IOException {
		if (source.startsWith("http://") || source.startsWith("https://")) {
			loadFromURL(source);
		}
		else {
			loadFromDisk(source);
		}
	}
	
	public PatternStore(Reader source) throws IOException {
		load(source);
	}
	
	private void load(Reader r) throws IOException {
		BufferedReader b = new BufferedReader(r);
		String line;
		while ((line = b.readLine()) != null) {
			try {
				Pattern p = new Pattern(line);
				if (patterns.contains(p)) continue;
				if (!authorMap.containsKey(p.getAuthor())) {
					authorMap.put(p.getAuthor(), new LinkedList<Pattern>());
				}
				authorMap.get(p.getAuthor()).add(p);
				nameMap.put(p.getName(), p);
				patterns.add(p);
			}
			catch (PatternFormatException e) {
				System.out.println("Could not parse \"" + line + "\": " + e.getMessage());
			}
		}
	}
	
	private void loadFromURL(String url) throws IOException {
		URL dest = new URL(url);
		URLConnection conn = dest.openConnection();
		Reader r = new InputStreamReader(conn.getInputStream());
		load(r);
	}
	
	private void loadFromDisk(String path) throws IOException {
		load(new FileReader(path));
	}

	public List<Pattern> getPatternsNameSorted() {
		List<Pattern> l = new LinkedList<Pattern>(patterns);
		Collections.sort(l);
		return l;
	}

	public List<Pattern> getPatternsAuthorSorted() {
		List<Pattern> l = new LinkedList<Pattern>(patterns);
		Collections.sort(l, new Comparator<Pattern>() {
			public int compare(Pattern a, Pattern b) {
				int r = a.getAuthor().compareTo(b.getAuthor());
				if (r == 0) return a.compareTo(b);
				else return r;
			}
		});
		return l;
	}

	public List<Pattern> getPatternsByAuthor(String author) throws PatternNotFound {
		List<Pattern> p = new LinkedList<Pattern>(authorMap.get(author));
		if (p == null) throw new PatternNotFound("No such author");
		Collections.sort(p);
		return p;
	}

	public Pattern getPatternByName(String name) throws PatternNotFound {
		Pattern p = nameMap.get(name);
		if (p == null) throw new PatternNotFound("No such name");
		return p;
	}

	public List<String> getPatternAuthors() {
		List<String> authors = new LinkedList<String>(authorMap.keySet());
		Collections.sort(authors);
		return authors;
	}

	public List<String> getPatternNames() {
		List<String> names = new LinkedList<String>(nameMap.keySet());
		Collections.sort(names);
		return names;
	}
	
	public static void main(String[] args) throws IOException {
		PatternStore p = new PatternStore("http://www.cl.cam.ac.uk/teaching/current/OOProg/ticks/lifetest.txt");
	}
}

