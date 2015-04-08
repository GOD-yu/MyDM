package data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataColumnCollection {
	private ArrayList<DataColumn> columnsL;// 列
	private HashMap<Integer, DataColumn> columnsMI;// 列索引 数字
	private HashMap<String, DataColumn> columnsMS;// 列索引 列名

	/**
	 * 构造函数
	 */
	public DataColumnCollection() {
		this.columnsL = new ArrayList<DataColumn>();
		this.columnsMI = new HashMap<Integer, DataColumn>();
		this.columnsMS = new HashMap<String, DataColumn>();
	}

	/**
	 * 获取长度
	 * 
	 * @return 长度
	 */
	public int size() {
		return this.columnsL.size();
	}

	/**
	 * 添加列
	 * 
	 * @param column
	 *            新列
	 * @return 是否添加成功
	 */
	public boolean add(DataColumn column) {
		if (columnsMS.get(column.getColumnName()) == null) {
			columnsMS.put(column.getColumnName(), column);
			column.setColumnIndex(columnsMI.size());
			columnsMI.put(columnsMI.size(), column);
			columnsL.add(column);
			return true;
		}
		return false;
	}

	/**
	 * 删除列
	 * 
	 * @param columnindex
	 *            列索引
	 * @return 是否删除成功
	 */
	public boolean remove(int columnindex) {
		DataColumn column = columnsMI.get(columnindex);
		if (column != null) {
			return remove(column);
		}
		return false;
	}

	/**
	 * 删除列
	 * 
	 * @param columnname
	 *            列名
	 * @return 是否删除成功
	 */
	public boolean remove(String columnname) {
		DataColumn column = columnsMS.get(columnname);
		if (column != null) {
			return remove(column);
		}
		return false;
	}

	/**
	 * 删除列
	 * 
	 * @param column
	 *            列
	 * @return 是否删除成功
	 */
	public boolean remove(DataColumn column) {
		if (columnsL.remove(column)) {
			columnsMS.remove(column.getColumnName());
			DataColumn temp;
			for (int i = 0, j = 0; i < this.size(); i++, j++) {
				temp = columnsMI.get(i);
				if (temp == column) {
					columnsMI.remove(i);
					i++;
					temp = columnsMI.get(i);
				}
				temp.setColumnIndex(j);
				columnsMI.put(j, temp);
			}
			return true;
		}
		return false;
	}

	/**
	 * 获取所有列
	 * 
	 * @return 所有列
	 */
	public ArrayList<DataColumn> getColumns() {
		return new ArrayList<DataColumn>(this.columnsL);
	}

	/**
	 * 获取列
	 * 
	 * @param columnindex
	 *            列索引
	 * @return 列索引对应列
	 */
	public DataColumn getColumn(int columnindex) {
		return this.columnsMI.get(columnindex);
	}

	/**
	 * 获取列
	 * 
	 * @param columnname
	 *            列名
	 * @return 列名对应列
	 */
	public DataColumn getColumn(String columnname) {
		return this.columnsMS.get(columnname);
	}

	/**
	 * 获取最后一列
	 * 
	 * @return 最后一列
	 */
	public DataColumn getLastColumn() {
		return this.columnsMI.get(this.columnsL.size() - 1);
	}
}
