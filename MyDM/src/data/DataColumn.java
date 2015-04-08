package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class DataColumn {
	private String columnName; // ����
	private int columnIndex;// ������
	private DataRowCollection rows;// ��
	private HashMap<Object, ArrayList<Integer>> columnValues;// ������ֵ����
	private ArrayList<DataCell> cellsL;// ��Ԫ��
	private HashMap<DataRow, DataCell> cellsM;// ��Ԫ��

	/**
	 * ���캯��
	 * 
	 * @param columnname
	 *            ����
	 * @param rows
	 *            ��
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
	 * ���캯�� Ĭ������"default"
	 * 
	 * @param rows
	 *            ��
	 */
	public DataColumn(DataRowCollection rows) {
		this("default", rows);
	}

	/**
	 * ���õ�Ԫ��
	 * 
	 * @param row
	 *            ��
	 * @param cell
	 *            ��Ԫ��
	 */
	public void setCell(DataRow row, DataCell cell) {
		if (this.cellsM.containsKey(row)) {
			cell.bind(row, this);
		}
	}

	/**
	 * �󶨵�Ԫ��
	 * 
	 * @param row
	 *            ��
	 * @param cell
	 *            ��Ԫ��
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
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * ����������
	 * 
	 * @param columnIndex
	 *            ������
	 */
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	/**
	 * ��ȡ��Ԫ��
	 * 
	 * @param rowindex
	 *            ������
	 * @return ��Ԫ��
	 */
	public DataCell getCell(int rowindex) {
		return this.cellsM.get(this.getRow(rowindex));
	}

	/**
	 * ��ȡ���������е�Ԫ��
	 * 
	 * @return ���е�Ԫ��
	 */
	public ArrayList<DataCell> getCells() {
		return new ArrayList<DataCell>(this.cellsL);
	}

	/**
	 * ��ȡ��
	 * 
	 * @param rowindex
	 *            ������
	 * @return ��
	 */
	public DataRow getRow(int rowindex) {
		return this.rows.getRow(rowindex);
	}

	/**
	 * ��ȡ������
	 * 
	 * @return ������
	 */
	public int getColumnIndex() {
		return columnIndex;
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
	 * ��ȡ��������ֵ
	 * 
	 * @return ��������ֵ
	 */
	public ArrayList<Object> getColumnValues() {
		return new ArrayList<Object>(columnValues.keySet());
	}

	/**
	 * ��ȡ����ֵ
	 * 
	 * @param index
	 *            ����
	 * @return ����ֵ
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
	 * ��ȡĳ����ȫ����Ԫ������
	 * 
	 * @param columnvalue
	 *            ����ֵ
	 * @return ȫ����Ԫ��������
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
	 * ��ȡĳ����ȫ����Ԫ������
	 * 
	 * @param columnvalueindex
	 *            ����ֵ����
	 * @return ȫ����Ԫ������
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
