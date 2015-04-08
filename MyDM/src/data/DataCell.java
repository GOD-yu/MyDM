package data;

public class DataCell {
	private Object value;

	public DataCell(Object value) {
		this.value = value;
	}

	public boolean bind(DataRow row, DataColumn column) {
		if (this.value == null) {
			return false;
		} else {
			if (column != null && row != null) {
				// int index = column.getValueIndex(this.value);
				row.bindCell(column, this);
				column.bindCell(row, this);
				return true;
			} else {
				return false;
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
