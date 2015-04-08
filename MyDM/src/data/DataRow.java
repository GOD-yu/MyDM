package data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataRow {
	private int rowIndex;// 行索引
	private DataColumnCollection columns;// 列
	private ArrayList<DataCell> cellsL;// 单元格
	private HashMap<DataColumn, DataCell> cellsM;// 单元格

	/**
	 * 构造函数
	 * 
	 * @param index
	 *            行索引
	 * @param columns
	 *            列
	 */
	public DataRow(int index, DataColumnCollection columns) {
		this.cellsL = new ArrayList<DataCell>();
		this.cellsM = new HashMap<DataColumn, DataCell>();
		this.columns = columns;
		this.rowIndex = index;
		this.setColumns(columns);
	}

	/**
	 * 构造函数
	 * 
	 * @param columns
	 *            列
	 */
	public DataRow(DataColumnCollection columns) {
		this(-1, columns);
	}

	/**
	 * 构造函数
	 */
	public DataRow() {
		this(-1, null);
	}

	/**
	 * 设置单元格
	 * 
	 * @param column
	 *            列
	 * @param cell
	 *            单元格
	 */
	public void setCell(DataColumn column, DataCell cell) {
		if (this.cellsM.containsKey(column)) {
			cell.bind(this, column);
		}
	}

	/**
	 * 绑定单元格
	 * 
	 * @param column
	 *            列
	 * @param cell
	 *            单元格
	 */
	public void bindCell(DataColumn column, DataCell cell) {
		this.cellsL.add(cell);
		this.cellsM.put(column, cell);
	}

	/**
	 * 获取单元格
	 * 
	 * @param columnname
	 *            列名
	 * @return 单元格
	 */
	public DataCell getCell(String columnname) {
		return this.cellsM.get(this.getColumn(columnname));
	}

	/**
	 * 获取单元格
	 * 
	 * @param columnindex
	 *            列索引
	 * @return 单元格
	 */
	public DataCell getCell(int columnindex) {
		return this.cellsM.get(this.getColumn(columnindex));
	}

	/**
	 * 获取该行所有单元格
	 * 
	 * @return 所有单元格
	 */
	public ArrayList<DataCell> getCells() {
		return new ArrayList<DataCell>(this.cellsL);
	}

	/**
	 * 获取列
	 * 
	 * @param columnname
	 *            列名
	 * @return 列
	 */
	public DataColumn getColumn(String columnname) {
		return this.columns.getColumn(columnname);
	}

	/**
	 * 获取列
	 * 
	 * @param columnindex
	 *            列索引
	 * @return 列
	 */
	public DataColumn getColumn(int columnindex) {
		return this.columns.getColumn(columnindex);
	}

	/**
	 * 获取长度
	 * 
	 * @return 长度
	 */
	public int size() {
		return this.cellsL.size();
	}

	/**
	 * 获取行索引
	 * 
	 * @return 行索引
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	public String toString() {
		return cellsL.toString();
	}

	/**
	 * 设置行索引
	 * 
	 * @param rowIndex
	 *            行索引
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * 设置列
	 * 
	 * @param columns
	 *            列
	 */
	public void setColumns(DataColumnCollection columns) {
		this.columns = columns;
		if (this.columns != null) {
			ArrayList<DataColumn> columnss = this.columns.getColumns();
			for (DataColumn column : columnss) {
				this.cellsM.put(column, null);
			}
		}
	}

}