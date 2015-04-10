package classify.decisiontree;

import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTreeNode {
	private DecisionTreeNode parent;// 父节点
	private Object parent_value;// 父节点 路径
	private String name;// 名称 列名
	private HashMap<Object, DecisionTreeNode> nextM;// 子节点
	private ArrayList<DecisionTreeNode> nextL;// 子节点

	/**
	 * 构造函数
	 * 
	 * @param parent
	 *            父节点
	 * @param parent_value
	 *            父节点 路径
	 * @param name
	 *            名称 列名
	 */
	public DecisionTreeNode(DecisionTreeNode parent, Object parent_value,
			String name) {
		this.parent = parent;
		this.parent_value = parent_value;
		this.name = name;
		this.nextM = new HashMap<Object, DecisionTreeNode>();
		this.nextL = new ArrayList<DecisionTreeNode>();
		if (this.parent != null) {
			this.parent.add(this.parent_value, this);
		}
	}

	private void add(Object value, DecisionTreeNode node) {
		this.nextM.put(value, node);
		this.nextL.add(node);
	}

	/**
	 * 获取子节点
	 * 
	 * @param value
	 *            路径
	 * @return 子节点
	 */
	public DecisionTreeNode getNext(Object value) {
		return this.nextM.get(value);
	}

	/**
	 * 获取父节点
	 * 
	 * @return 父节点
	 */
	public DecisionTreeNode getParent() {
		return this.parent;
	}

	/**
	 * 获取父节点 路径
	 * 
	 * @return 路径
	 */
	public Object getParent_value() {
		return this.parent_value;
	}

	/**
	 * 获取子节点
	 * 
	 * @param index
	 *            索引
	 * @return 子节点
	 */
	public DecisionTreeNode getNext(int index) {
		return this.nextL.get(index);
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 获取子节点
	 * 
	 * @return 子节点
	 */
	public HashMap<Object, DecisionTreeNode> getNexts() {
		return new HashMap<Object, DecisionTreeNode>(nextM);
	}

	/**
	 * 获取子节点长度
	 * 
	 * @return 长度
	 */
	public int size() {
		return this.nextM.size();
	}
}
