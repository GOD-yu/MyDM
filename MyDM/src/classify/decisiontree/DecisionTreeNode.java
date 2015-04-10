package classify.decisiontree;

import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTreeNode {
	private DecisionTreeNode parent;// ���ڵ�
	private Object parent_value;// ���ڵ� ·��
	private String name;// ���� ����
	private HashMap<Object, DecisionTreeNode> nextM;// �ӽڵ�
	private ArrayList<DecisionTreeNode> nextL;// �ӽڵ�

	/**
	 * ���캯��
	 * 
	 * @param parent
	 *            ���ڵ�
	 * @param parent_value
	 *            ���ڵ� ·��
	 * @param name
	 *            ���� ����
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
	 * ��ȡ�ӽڵ�
	 * 
	 * @param value
	 *            ·��
	 * @return �ӽڵ�
	 */
	public DecisionTreeNode getNext(Object value) {
		return this.nextM.get(value);
	}

	/**
	 * ��ȡ���ڵ�
	 * 
	 * @return ���ڵ�
	 */
	public DecisionTreeNode getParent() {
		return this.parent;
	}

	/**
	 * ��ȡ���ڵ� ·��
	 * 
	 * @return ·��
	 */
	public Object getParent_value() {
		return this.parent_value;
	}

	/**
	 * ��ȡ�ӽڵ�
	 * 
	 * @param index
	 *            ����
	 * @return �ӽڵ�
	 */
	public DecisionTreeNode getNext(int index) {
		return this.nextL.get(index);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ��ȡ�ӽڵ�
	 * 
	 * @return �ӽڵ�
	 */
	public HashMap<Object, DecisionTreeNode> getNexts() {
		return new HashMap<Object, DecisionTreeNode>(nextM);
	}

	/**
	 * ��ȡ�ӽڵ㳤��
	 * 
	 * @return ����
	 */
	public int size() {
		return this.nextM.size();
	}
}
