package data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataRow {
	private DataTable dataTable;// 表
	private int rowIndex;// 行索引

	private ArrayList<DataCell> cellsL;// 单元格
	private HashMap<DataColumn, DataCell> cellsM;// 单元格

	public DataRow(DataTable datatable, int index, ArrayList<DataColumn> columns) {
		this.dataTable = datatable;
		this.cellsL = new ArrayList<DataCell>();
		this.cellsM = new HashMap<DataColumn, DataCell>();
		this.rowIndex = index;
		if (columns != null) {
			for (DataColumn column : columns) {
				this.cellsM.put(column, null);
			}
		}
	}

	public DataRow(DataTable datatable, int index) {
		this(datatable, index, null);
	}

	public DataRow(DataTable datatable) {
		this(datatable, -1, null);
	}

	public void addColumn(DataColumn column) {
		cellsM.put(column, null);
	}

	public void setCell(DataColumn column, DataCell cell) {
		if (this.cellsM.containsKey(column)) {
			if (cell.bind(this, column) != -1) {
				this.dataTable.getCells().add(cell);
			}
		}
	}

	public void bindCell(DataColumn column, DataCell cell) {
		this.cellsL.add(cell);
		this.cellsM.put(column, cell);
	}

	public DataCell getCell(String columnname) {
		return this.cellsM.get(this.dataTable.getColumn(columnname));
	}

	public DataCell getCell(int columnindex) {
		return this.cellsM.get(this.dataTable.getColumn(columnindex));
	}

	public ArrayList<DataCell> getCells() {
		return this.cellsL;
	}

	public int size() {
		return this.cellsL.size();
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public String toString() {
		return cellsL.toString();
	}
}