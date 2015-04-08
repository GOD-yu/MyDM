package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class DataColumn {
	private String columnName; // 列名
	private int columnIndex;// 列索引
	private DataRowCollection rows;// 行
	private HashMap<Object, ArrayList<Integer>> columnValues;// 列属性值索引
	private ArrayList<DataCell> cellsL;// 单元格
	private HashMap<DataRow, DataCell> cellsM;// 单元格

	/**
	 * 构造函数
	 * 
	 * @param columnname
	 *            列名
	 * @param rows
	 *            行
	 */
	public DataColumn(String columnname, DataRowCollection rows) {
		this.cellsL = new ArrayList<DataCell>();
		this.cellsM = new HashMap<DataRow, DataCell>();
		this.columnValues = new HashMap<Object, ArrayList<Integer>>();
		this.rows = rows;
		this.columnName = columnname;
		this.columnIndex = -1;
	}

	/**
	 * 构造函数 默认列名"default"
	 * 
	 * @param rows
	 *            行
	 */
	public DataColumn(DataRowCollection rows) {
		this("default", rows);
	}

	/**
	 * 设置单元格
	 * 
	 * @param row
	 *            行
	 * @param cell
	 *            单元格
	 */
	public void setCell(DataRow row, DataCell cell) {
		if (this.cellsM.containsKey(row)) {
			cell.bind(row, this);
		}
	}

	/**
	 * 绑定单元格
	 * 
	 * @param row
	 *            行
	 * @param cell
	 *            单元格
	 */
	public void bindCell(DataRow row, DataCell cell) {
		ArrayList<Integer> columnvalue = columnValues.get(cell.getValue());
		if (columnvalue == null) {
			columnvalue = new ArrayList<Integer>();
			columnValues.put(cell.getValue(), columnvalue);
		}
		columnvalue.add(row.getRowIndex());
		this.cellsL.add(cell);
		this.cellsM.put(row, cell);
	}

	/**
	 * 获取列名
	 * 
	 * @return 列名
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * 设置列索引
	 * 
	 * @param columnIndex
	 *            列索引
	 */
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	/**
	 * 获取单元格
	 * 
	 * @param rowindex
	 *            行索引
	 * @return 单元格
	 */
	public DataCell getCell(int rowindex) {
		return this.cellsM.get(this.getRow(rowindex));
	}

	/**
	 * 获取该列下所有单元格
	 * 
	 * @return 所有单元格
	 */
	public ArrayList<DataCell> getCells() {
		return new ArrayList<DataCell>(this.cellsL);
	}

	/**
	 * 获取行
	 * 
	 * @param rowindex
	 *            行索引
	 * @return 行
	 */
	public DataRow getRow(int rowindex) {
		return this.rows.getRow(rowindex);
	}

	/**
	 * 获取列索引
	 * 
	 * @return 列索引
	 */
	public int getColumnIndex() {
		return columnIndex;
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
	 * 获取所有属性值
	 * 
	 * @return 所有属性值
	 */
	public ArrayList<Object> getColumnValues() {
		return new ArrayList<Object>(columnValues.keySet());
	}

	/**
	 * 获取属性值
	 * 
	 * @param index
	 *            索引
	 * @return 属性值
	 */
	public Object getColumnValue(int index) {
		int i = 0;
		for (Object obj : columnValues.keySet()) {
			if (i == index) {
				return obj;
			}
			i++;
		}
		return null;
	}

	/**
	 * 获取某属性全部单元格索引
	 * 
	 * @param columnvalue
	 *            属性值
	 * @return 全部单元格行索引
	 */
	public ArrayList<Integer> getCellsIndex(Object columnvalue) {
		ArrayList<Integer> res = columnValues.get(columnvalue);
		if (res != null) {
			return new ArrayList<Integer>(res);
		} else {
			return null;
		}
	}

	/**
	 * 获取某属性全部单元格索引
	 * 
	 * @param columnvalueindex
	 *            属性值索引
	 * @return 全部单元格索引
	 */
	public ArrayList<Integer> getCellsIndex(int columnvalueindex) {
		ArrayList<Integer> res = columnValues.get(this
				.getColumnValue(columnvalueindex));
		if (res != null) {
			return new ArrayList<Integer>(res);
		} else {
			return null;
		}
	}

	public String toString() {
		return cellsL.toString();
	}
}
