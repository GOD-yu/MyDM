package data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataRowCollection {
	private ArrayList<DataRow> rowsL;// 行
	private HashMap<Integer, DataRow> rowsM;// 行

	/**
	 * 构造函数
	 */
	public DataRowCollection() {
		this.rowsL = new ArrayList<DataRow>();
		this.rowsM = new HashMap<Integer, DataRow>();
	}

	/**
	 * 添加行
	 * 
	 * @param row
	 *            行
	 * @return 是否成功
	 */
	public boolean add(DataRow row) {
		row.setRowIndex(rowsM.size());
		rowsM.put(rowsM.size(), row);
		rowsL.add(row);
		return true;
	}

	/**
	 * 删除行
	 * 
	 * @param rowindex
	 *            行号
	 * @return 是否成功
	 */
	public boolean remove(int rowindex) {
		DataRow row = rowsM.get(rowindex);
		if (row != null) {
			return remove(row);
		}
		return false;
	}

	/**
	 * 删除行
	 * 
	 * @param row
	 *            行
	 * @return 是否成功
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
	 * 获取长度
	 * 
	 * @return 长度
	 */
	public int size() {
		return rowsL.size();
	}

	/**
	 * 获取行
	 * 
	 * @param rowindex
	 *            行号
	 * @return 行
	 */
	public DataRow getRow(int rowindex) {
		return this.rowsM.get(rowindex);
	}

	/**
	 * 获取最后一行
	 * 
	 * @return 行
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
