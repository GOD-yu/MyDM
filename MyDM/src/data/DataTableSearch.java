package data;

import java.util.ArrayList;

public class DataTableSearch {
	private DataTable dataTable;

	public DataTableSearch(DataTable dt) {
		dataTable = dt;
	}

	public DataTable GetChildDataTable(String columnname, Object value) {
		DataColumn column = dataTable.columns.getColumn(columnname);
		return GetChildDataTable(column.getColumnIndex(), value);
	}

	public DataTable GetChildDataTable(String columnname, int value_index) {
		DataColumn column = dataTable.columns.getColumn(columnname);
		return GetChildDataTable(column.getColumnIndex(), value_index);
	}

	public DataTable GetChildDataTable(int columnindex, int value_index) {
		return GetChildDataTable(
				columnindex,
				dataTable.columns.getColumn(columnindex).getColumnValue(
						value_index));
	}

	public DataTable GetChildDataTable(int columnindex, Object value) {
		DataColumn column = dataTable.columns.getColumn(columnindex);
		if (column == null) {
			return null;
		}
		DataTable dt = new DataTable(String.format("%s_%s=%s",
				dataTable.getTableName(), column.getColumnName(), value));
		for (int i = 0; i < dataTable.columns.size(); i++) {
			if (i != columnindex) {
				dt.addColumn(dataTable.columns.getColumn(i).getColumnName());
			}
		}

		ArrayList<Integer> cells = column.getCellsIndex(value);
		DataRow row;
		for (int cell_index : cells) {
			dt.addRow();
			row = dataTable.rows.getRow(cell_index);
			for (int i = 0, j = 0; i < row.getCells().size(); i++) {
				if (i != columnindex) {
					row.getCell(i).bind(dt.rows.getLastRow(),
							dt.columns.getColumn(j));
					j++;
				}
			}
		}

		return dt;
	}
}
