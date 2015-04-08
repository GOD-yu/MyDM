package data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataRow {
	private int rowIndex;// ������
	private DataColumnCollection columns;// ��
	private ArrayList<DataCell> cellsL;// ��Ԫ��
	private HashMap<DataColumn, DataCell> cellsM;// ��Ԫ��

	/**
	 * ���캯��
	 * 
	 * @param index
	 *            ������
	 * @param columns
	 *            ��
	 */
	public DataRow(int index, DataColumnCollection columns) {
		this.cellsL = new ArrayList<DataCell>();
		this.cellsM = new HashMap<DataColumn, DataCell>();
		this.columns = columns;
		this.rowIndex = index;
		this.setColumns(columns);
	}

	/**
	 * ���캯��
	 * 
	 * @param columns
	 *            ��
	 */
	public DataRow(DataColumnCollection columns) {
		this(-1, columns);
	}

	/**
	 * ���캯��
	 */
	public DataRow() {
		this(-1, null);
	}

	/**
	 * ���õ�Ԫ��
	 * 
	 * @param column
	 *            ��
	 * @param cell
	 *            ��Ԫ��
	 */
	public void setCell(DataColumn column, DataCell cell) {
		if (this.cellsM.containsKey(column)) {
			cell.bind(this, column);
		}
	}

	/**
	 * �󶨵�Ԫ��
	 * 
	 * @param column
	 *            ��
	 * @param cell
	 *            ��Ԫ��
	 */
	public void bindCell(DataColumn column, DataCell cell) {
		this.cellsL.add(cell);
		this.cellsM.put(column, cell);
	}

	/**
	 * ��ȡ��Ԫ��
	 * 
	 * @param columnname
	 *            ����
	 * @return ��Ԫ��
	 */
	public DataCell getCell(String columnname) {
		return this.cellsM.get(this.getColumn(columnname));
	}

	/**
	 * ��ȡ��Ԫ��
	 * 
	 * @param columnindex
	 *            ������
	 * @return ��Ԫ��
	 */
	public DataCell getCell(int columnindex) {
		return this.cellsM.get(this.getColumn(columnindex));
	}

	/**
	 * ��ȡ�������е�Ԫ��
	 * 
	 * @return ���е�Ԫ��
	 */
	public ArrayList<DataCell> getCells() {
		return new ArrayList<DataCell>(this.cellsL);
	}

	/**
	 * ��ȡ��
	 * 
	 * @param columnname
	 *            ����
	 * @return ��
	 */
	public DataColumn getColumn(String columnname) {
		return this.columns.getColumn(columnname);
	}

	/**
	 * ��ȡ��
	 * 
	 * @param columnindex
	 *            ������
	 * @return ��
	 */
	public DataColumn getColumn(int columnindex) {
		return this.columns.getColumn(columnindex);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public int size() {
		return this.cellsL.size();
	}

	/**
	 * ��ȡ������
	 * 
	 * @return ������
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	public String toString() {
		return cellsL.toString();
	}

	/**
	 * ����������
	 * 
	 * @param rowIndex
	 *            ������
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * ������
	 * 
	 * @param columns
	 *            ��
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