package data;

public class DataTable {
	private String tableName;// ����
	public DataRowCollection rows;// ��
	public DataColumnCollection columns;// ��

	/**
	 * ���캯��
	 * 
	 * @param tablename
	 *            ����
	 */
	public DataTable(String tablename) {
		this.rows = new DataRowCollection();
		this.columns = new DataColumnCollection();
		this.tableName = tablename;
	}

	/**
	 * ���캯��
	 */
	public DataTable() {
		this.rows = new DataRowCollection();
		this.columns = new DataColumnCollection();
		this.tableName = "default";
	}

	/**
	 * �������
	 * 
	 * @return ����
	 */
	public DataRow addRow() {
		DataRow row = new DataRow(this.rows.size(), columns);
		this.rows.add(row);
		return row;
	}

	/**
	 * �������
	 * 
	 * @param columnname
	 *            ����
	 * @return �Ƿ�ɹ�
	 */
	public boolean addColumn(String columnname) {
		DataColumn column = new DataColumn(columnname, rows);
		return addColumn(column);
	}

	/**
	 * �������
	 * 
	 * @param column
	 *            ��
	 * @return �Ƿ�ɹ�
	 */
	public boolean addColumn(DataColumn column) {
		return columns.add(column);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getTableName() {
		return this.tableName;
	}

	public String toString() {
		return rows.toString();
	}
}
