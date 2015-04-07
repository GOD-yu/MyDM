package classify.decisiontree;

import data.DataColumn;
import data.DataTable;

public abstract class DecisionTree {
	public DataTable dataTable;

	public void load(DataTable datatable) {
		this.dataTable = datatable;
	}

	public double log2(double x) {
		return Math.log(x) / Math.log(2);
	}

	public double getEntropy(DataTable dataTable) {
		double entropy = 0;
		DataColumn classClolumn = dataTable.getLastColumn();
		int column_length = classClolumn.getColumnValues().size();
		for (int i = 0; i < column_length; i++) {
			double temp = (double) classClolumn.getColumnValues().get(i)
					.getCount()
					/ (double) classClolumn.getSize();
			entropy += (-log2(temp) * temp);
		}
		return entropy;
	}

	public abstract DecisionTreeRoot create();
}
