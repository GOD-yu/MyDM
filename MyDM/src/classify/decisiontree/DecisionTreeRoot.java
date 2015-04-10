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


	
}
