package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class DataColumn {
	private DataTable dataTable;// 表
	private String columnName; // 列名
	private Vector<DataColumnValue> columnValues; // 列属性值
	private int columnIndex;// 列索引
	private DataAdapter dataAdapter;// 适配器

	private ArrayList<DataCell> cellsL;// 单元格
	private HashMap<DataRow, DataCell> cellsM;// 单元格

	public DataColumn(DataTable datatable, int index, String columnname,
			DataAdapter adapter) {
		this.dataTable = datatable;
		this.columnName = columnname;
		this.dataAdapter = adapter;
		this.columnValues = new Vector<DataColumnValue>();
		this.cellsL = new ArrayList<DataCell>();
		this.cellsM = new HashMap<DataRow, DataCell>();
		this.columnIndex = index;
	}

	public DataColumn(DataTable datatable, int index, DataAdapter adapter) {
		this(datatable, index, "default", adapter);
	}

	public DataColumn(DataTable datatable, String columnname,
			DataAdapter adapter) {
		this(datatable, -1, columnname, adapter);
	}

	public DataColumn(DataTable datatable, DataAdapter adapter) {
		this(datatable, -1, "default", adapter);
	}

	public void setCell(DataRow row, DataCell cell) {
		if (this.cellsM.containsKey(row)) {
			if (cell.bind(row, this) != -1) {
				this.dataTable.getCells().add(cell);
			}
		}
	}

	public void bindCell(DataRow row, DataCell cell) {
		this.cellsL.add(cell);
		this.cellsM.put(row, cell);
	}

	public int getValueIndex(Object value) {
		DataColumnValue newValue = new DataColumnValue(this, value);
		int index = this.columnValues.indexOf(newValue);
		if (index == -1) {
			this.columnValues.add(newValue);
			index = this.columnValues.size() - 1;
		} else {
			this.columnValues.get(index).addCount();
		}
		return index;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Vector<DataColumnValue> getColumnValues() {
		return this.columnValues;
	}

	public int getColumnIndex() {
		return this.columnIndex;
	}

	public DataAdapter getDataAdapter() {
		return this.dataAdapter;
	}

	public DataCell getCell(int rowindex) {
		return this.cellsM.get(this.dataTable.getRow(rowindex));
	}

	public ArrayList<DataCell> getCells() {
		return this.cellsL;
	}

	public String toString() {
		return this.cellsL.toString();
	}

	public int getSize() {
		return this.cellsL.size();
	}
}
