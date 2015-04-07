package data;

public class DataColumnValue {
	private DataColumn dataColumn;// ¡–
	private Object dataColumnValue;//  Ù–‘÷µ
	private int count;

	public DataColumnValue(DataColumn column, Object value) {
		this.count = 1;
		this.dataColumn = column;
		this.dataColumnValue = value;
	}

	public void addCount() {
		this.count++;
	}

	public String toString() {
		return this.dataColumnValue.toString();
	}

	public boolean equals(Object obj) {
		if (obj instanceof DataColumnValue) {
			DataColumnValue o = (DataColumnValue) obj;
			return this.dataColumnValue.equals(o.dataColumnValue)
					&& this.dataColumn == o.dataColumn;
		} else {
			return this.toString().equals(obj.toString());
		}
	}

	public int getCount() {
		return count;
	}
}
