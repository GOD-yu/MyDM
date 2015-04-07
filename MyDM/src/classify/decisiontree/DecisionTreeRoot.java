package classify.decisiontree;

import java.util.HashMap;

public class DecisionTreeRoot {
	private String name;
	private DecisionTreeRoot parent;
	private String parent_value;
	private HashMap<String, DecisionTreeRoot> next;

	public DecisionTreeRoot(DecisionTreeRoot parent, String parent_value,
			String name) {
		this.parent = parent;
		this.parent_value = parent_value;
		this.name = name;
		this.next = new HashMap<String, DecisionTreeRoot>();

		if (this.parent != null) {
			this.parent.addNode(parent_value, this);
		}
	}

	public void addNode(String value, DecisionTreeRoot node) {
		this.next.put(value, node);
	}

	static public void print1(DecisionTreeRoot root) {
		System.out.println(String.format("%s:%s->%s",
				root.parent == null ? "null" : root.parent.name,
				root.parent_value, root.name));
		for (String v : root.next.keySet()) {
			print1(root.next.get(v));
		}
	}

	static public void print2(DecisionTreeRoot root) {
		print2(root, "");
	}

	static public void print2(DecisionTreeRoot root, String line) {
		line = line + String.format("%s->%s:", root.parent_value, root.name);
		if (root.next.size() == 0) {
			System.out.println(line.substring(0, line.length() - 1));
		}
		DecisionTreeRoot node;
		for (String v : root.next.keySet()) {
			print2(root.next.get(v), line);
		}
	}
}
