package data;

import java.util.ArrayList;
import java.util.HashMap;

public class DataColumnCollection {
	private ArrayList<DataColumn> columnsL;// ��
	private HashMap<Integer, DataColumn> columnsMI;// ������ ����
	private HashMap<String, DataColumn> columnsMS;// ������ ����

	/**
	 * ���캯��
	 */
	public DataColumnCollection() {
		this.columnsL = new ArrayList<DataColumn>();
		this.columnsMI = new HashMap<Integer, DataColumn>();
		this.columnsMS = new HashMap<String, DataColumn>();
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public int size() {
		return this.columnsL.size();
	}

	/**
	 * �����
	 * 
	 * @param column
	 *            ����
	 * @return �Ƿ���ӳɹ�
	 */
	public boolean add(DataColumn column) {
		if (columnsMS.get(column.getColumnName()) == null) {
			columnsMS.put(column.getColumnName(), column);
			column.setColumnIndex(columnsMI.size());
			columnsMI.put(columnsMI.size(), column);
			columnsL.add(column);
			return true;
		}
		return false;
	}

	/**
	 * ɾ����
	 * 
	 * @param columnindex
	 *            ������
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean remove(int columnindex) {
		DataColumn column = columnsMI.get(columnindex);
		if (column != null) {
			return remove(column);
		}
		return false;
	}

	/**
	 * ɾ����
	 * 
	 * @param columnname
	 *            ����
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean remove(String columnname) {
		DataColumn column = columnsMS.get(columnname);
		if (column != null) {
			return remove(column);
		}
		return false;
	}

	/**
	 * ɾ����
	 * 
	 * @param column
	 *            ��
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean remove(DataColumn column) {
		if (columnsL.remove(column)) {
			columnsMS.remove(column.getColumnName());
			DataColumn temp;
			for (int i = 0, j = 0; i < this.size(); i++, j++) {
				temp = columnsMI.get(i);
				if (temp == column) {
					columnsMI.remove(i);
					i++;
					temp = columnsMI.get(i);
				}
				temp.setColumnIndex(j);
				columnsMI.put(j, temp);
			}
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ������
	 * 
	 * @return ������
	 */
	public ArrayList<DataColumn> getColumns() {
		return new ArrayList<DataColumn>(this.columnsL);
	}

	/**
	 * ��ȡ��
	 * 
	 * @param columnindex
	 *            ������
	 * @return ��������Ӧ��
	 */
	public DataColumn getColumn(int columnindex) {
		return this.columnsMI.get(columnindex);
	}

	/**
	 * ��ȡ��
	 * 
	 * @param columnname
	 *            ����
	 * @return ������Ӧ��
	 */
	public DataColumn getColumn(String columnname) {
		return this.columnsMS.get(columnname);
	}

	/**
	 * ��ȡ���һ��
	 * 
	 * @return ���һ��
	 */
	public DataColumn getLastColumn() {
		return this.columnsMI.get(this.columnsL.size() - 1);
	}
}
