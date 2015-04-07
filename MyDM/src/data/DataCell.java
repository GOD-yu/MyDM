package data;

public class DataCell {
	private Object value;
	private DataAdapter dataAdapter;

	public DataCell(Object obj, DataAdapter adapter) {
		this.dataAdapter = adapter;
		this.value = dataAdapter.Adaptate(obj);
	}

	public int bind(DataRow row, DataColumn column) {
		if (this.value == null) {
			return -1;
		} else if (!column.getDataAdapter().equals(this.dataAdapter)) {
			return -1;
		} else {
			if (column != null && row != null) {
				int index = column.getValueIndex(this.value);
				row.bindCell(column, this);
				column.bindCell(row, this);
				return index;
			} else {
				return -1;
			}
		}
	}

	public String toString() {
		if (value == null) {
			return "null";
		} else {
			return this.value.toString();
		}
	}

	public Object getValue() {
		return value;
	}

}
