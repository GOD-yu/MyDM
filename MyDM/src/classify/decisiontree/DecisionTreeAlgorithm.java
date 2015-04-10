package classify.decisiontree;

import data.DataColumn;
import data.DataTable;

public abstract class DecisionTreeAlgorithm {
	public DataTable dataTable;

	public void load(DataTable datatable) {
		this.dataTable = datatable;
	}

	public double log2(double x) {
		return Math.log(x) / Math.log(2);
	}

	public double getEntropy(DataTable dataTable) {
		return getEntropy(dataTable, dataTable.columns.size() - 1);
	}

	public double getEntropy(DataTable dataTable, int columnindex) {
		double entropy = 0;
		DataColumn classClolumn = dataTable.columns.getColumn(columnindex);
		int column_length = classClolumn.getColumnValues().size();
		for (int i = 0; i < column_length; i++) {
			double temp = (double) classClolumn.getCellsIndex(i).size()
					/ (double) classClolumn.size();
			entropy += (-log2(temp) * temp);
		}
		return entropy;
	}

	public abstract DecisionTree create();
}
