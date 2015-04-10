package data;

public class DataTable {
	private String tableName;// 表名
	public DataRowCollection rows;// 行
	public DataColumnCollection columns;// 行

	/**
	 * 构造函数
	 * 
	 * @param tablename
	 *            表名
	 */
	public DataTable(String tablename) {
		this.rows = new DataRowCollection();
		this.columns = new DataColumnCollection();
		this.tableName = tablename;
	}

	/**
	 * 构造函数
	 */
	public DataTable() {
		this.rows = new DataRowCollection();
		this.columns = new DataColumnCollection();
		this.tableName = "default";
	}

	/**
	 * 添加新行
	 * 
	 * @return 新行
	 */
	public DataRow addRow() {
		DataRow row = new DataRow(this.rows.size(), columns);
		this.rows.add(row);
		return row;
	}

	/**
	 * 添加新列
	 * 
	 * @param columnname
	 *            列名
	 * @return 是否成功
	 */
	public boolean addColumn(String columnname) {
		DataColumn column = new DataColumn(columnname, rows);
		return addColumn(column);
	}

	/**
	 * 添加新列
	 * 
	 * @param column
	 *            列
	 * @return 是否成功
	 */
	public boolean addColumn(DataColumn column) {
		return columns.add(column);
	}

	/**
	 * 获取表名
	 * 
	 * @return 表名
	 */
	public String getTableName() {
		return this.tableName;
	}

	public String toString() {
		return rows.toString();
	}
}
