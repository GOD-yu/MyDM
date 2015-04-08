package data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataRowCollection {
	private ArrayList<DataRow> rowsL;// ��
	private HashMap<Integer, DataRow> rowsM;// ��

	/**
	 * ���캯��
	 */
	public DataRowCollection() {
		this.rowsL = new ArrayList<DataRow>();
		this.rowsM = new HashMap<Integer, DataRow>();
	}

	/**
	 * �����
	 * 
	 * @param row
	 *            ��
	 * @return �Ƿ�ɹ�
	 */
	public boolean add(DataRow row) {
		row.setRowIndex(rowsM.size());
		rowsM.put(rowsM.size(), row);
		rowsL.add(row);
		return true;
	}

	/**
	 * ɾ����
	 * 
	 * @param rowindex
	 *            �к�
	 * @return �Ƿ�ɹ�
	 */
	public boolean remove(int rowindex) {
		DataRow row = rowsM.get(rowindex);
		if (row != null) {
			return remove(row);
		}
		return false;
	}

	/**
	 * ɾ����
	 * 
	 * @param row
	 *            ��
	 * @return �Ƿ�ɹ�
	 */
	public boolean remove(DataRow row) {
		if (rowsL.remove(row)) {
			DataRow temp;
			for (int i = 0, j = 0; i < this.size(); i++, j++) {
				temp = rowsM.get(i);
				if (temp == row) {
					rowsM.remove(i);
					i++;
					temp = rowsM.get(i);
				}
				temp.setRowIndex(j);
				rowsM.put(j, temp);
			}
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public int size() {
		return rowsL.size();
	}

	/**
	 * ��ȡ��
	 * 
	 * @param rowindex
	 *            �к�
	 * @return ��
	 */
	public DataRow getRow(int rowindex) {
		return this.rowsM.get(rowindex);
	}

	/**
	 * ��ȡ���һ��
	 * 
	 * @return ��
	 */
	public DataRow getLastRow() {
		return this.rowsM.get(this.rowsL.size() - 1);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (DataRow row : rowsL) {
			sb.append(row.getRowIndex()).append(" : ").append(row.toString())
					.append("\n");
		}
		return sb.toString();
	}
}
