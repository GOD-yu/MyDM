package classify.decisiontree;

import java.util.HashMap;

import data.DataRow;
import data.DataTable;

/**
 * @author Administrator
 * 
 */
public class DecisionTree {
	private DecisionTreeNode root;// ���ڵ�
	private StringBuilder sb_tostring;// toString����

	/**
	 * ���캯��
	 */
	public DecisionTree() {
		root = new DecisionTreeNode(null, "root", "root");
	}

	/**
	 * ��ȡ���ڵ�
	 * 
	 * @return
	 */
	public DecisionTreeNode getRoot() {
		return root;
	}

	/**
	 * ����
	 * 
	 * @param dt
	 *            ���Լ�
	 * @return ���
	 */
	public String test(DataTable dt) {
		int count_all = 0;
		int count_right = 0;
		int count_error = 0;
		for (int i = 0; i < dt.rows.size(); i++) {
			DataRow row = dt.rows.getRow(i);
			DecisionTreeNode node = root.getNext(0);
			while (node != null && node.size() > 0) {
				node = node.getNext(row.getCell(node.getName()).getValue());
			}
			if (node == null) {
				count_error++;
			} else {
				if (node.getName().equals(
						row.getCell(row.size() - 1).getValue())) {
					count_right++;
				} else {
					count_error++;
				}
			}
			count_all++;
		}
		return String.format("all:%s\nright:%s\nerror:%s\npersent:%s",
				count_all, count_right, count_error, (double) count_right
						/ count_all);
	}

	public String toString() {
		sb_tostring = new StringBuilder();
		toString_bulid(root, new StringBuilder(""));
		return sb_tostring.toString();
	}

	private void toString_bulid(DecisionTreeNode root, StringBuilder line) {
		line.append(root.getParent_value()).append("->").append(root.getName())
				.append(":");
		if (root.size() == 0) {
			sb_tostring.append(line.substring(0, line.length() - 1)).append(
					"\n");
		}
		HashMap<Object, DecisionTreeNode> nodes = root.getNexts();
		for (Object v : nodes.keySet()) {
			toString_bulid(root.getNext(v), line);
		}
	}
}
