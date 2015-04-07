package data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataTable {
	private String tableName;// 表名
	private ArrayList<DataColumn> columnsL;// 列
	private HashMap<Integer, DataColumn> columnsMI;// 列
	private HashMap<String, DataColumn> columnsMS;// 列
	private ArrayList<DataRow> rowsL;// 行
	private HashMap<Integer, DataRow> rowsM;// 行

	public DataTable(String tablename) {
		this.tableName = tablename;
		this.columnsL = new ArrayList<DataColumn>();
		this.columnsMI = new HashMap<Integer, DataColumn>();
		this.columnsMS = new HashMap<String, DataColumn>();
		this.rowsL = new ArrayList<DataRow>();
		this.rowsM = new HashMap<Integer, DataRow>();
	}

	public DataRow addRow() {
		DataRow row = new DataRow(this, this.getRows().size(), columnsL);
		this.rowsL.add(row);
		this.rowsM.put(row.getRowIndex(), row);
		return row;
	}

	public boolean addColumn(String columnname, DataAdapter adapter) {
		DataColumn column = new DataColumn(this, this.columnsL.size(),
				columnname, adapter);
		return addColumn(column);
	}

	public boolean addColumn(DataColumn column) {
		if (!this.columnsMS.containsKey(column.getColumnName())) {
			this.columnsL.add(column);
			this.columnsMI.put(column.getColumnIndex(), column);
			this.columnsMS.put(column.getColumnName(), column);
			return true;
		}
		return false;
	}

	public String getTableName() {
		return this.tableName;
	}

	public ArrayList<DataColumn> getColumns() {
		return this.columnsL;
	}

	public DataColumn getColumn(int columnindex) {
		return this.columnsMI.get(columnindex);
	}

	public DataColumn getColumn(String columnname) {
		return this.columnsMS.get(columnname);
	}

	public DataColumn getLastColumn() {
		return this.columnsMI.get(this.columnsL.size() - 1);
	}

	public ArrayList<DataRow> getRows() {
		return this.rowsL;
	}

	public DataRow getRow(int rowindex) {
		return this.rowsM.get(rowindex);
	}

	public DataRow getLastRow() {
		return this.rowsM.get(this.rowsL.size() - 1);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("-----").append(this.tableName).append("-----").append("\n");
		for (int row_num : this.rowsM.keySet()) {
			sb.append(row_num).append(" : ").append(this.rowsM.get(row_num))
					.append("\n");
		}
		return sb.toString();
	}

	public ArrayList<DataCell> getCells() {
		ArrayList<DataCell> res = new ArrayList<DataCell>();
		for (int row_num : this.rowsM.keySet()) {
			res.addAll(this.rowsM.get(row_num).getCells());
		}
		return res;
	}
}
