package data;

public class DataTableSearch {
	private DataTable dataTable;

	public DataTableSearch(DataTable dt) {
		dataTable = dt;
	}

	public DataTable GetChildDataTable(String columnname, Object value) {
		DataColumn column = dataTable.getColumn(columnname);
		return GetChildDataTable(column.getColumnIndex(), value);
	}

	public DataTable GetChildDataTable(String columnname, int value_index) {
		DataColumn column = dataTable.getColumn(columnname);
		return GetChildDataTable(column.getColumnIndex(), value_index);
	}

	public DataTable GetChildDataTable(int columnindex, int value_index) {
		return GetChildDataTable(columnindex, dataTable.getColumn(columnindex)
				.getColumnValues().get(value_index));
	}

	public DataTable GetChildDataTable(int columnindex, Object value) {
		DataColumn column = dataTable.getColumn(columnindex);
		if (column == null) {
			return null;
		}
		DataTable dt = new DataTable(String.format("%s_%s=%s",
				dataTable.getTableName(), column.getColumnName(), value));
		for (int i = 0; i < dataTable.getColumns().size(); i++) {
			if (i != columnindex) {
				dt.addColumn(dataTable.getColumn(i).getColumnName(), dataTable
						.getColumn(i).getDataAdapter());
			}
		}
		for (DataRow row : dataTable.getRows()) {
			if (!row.getCell(columnindex).getValue().equals(value)) {
				continue;
			}
			dt.addRow();
			for (int i = 0, j = 0; i < row.size(); i++) {
				if (i != columnindex) {
					row.getCell(i).bind(dt.getLastRow(), dt.getColumn(j));
					j++;
				}
			}
		}

		return dt;
	}
}
