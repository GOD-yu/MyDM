package classify.decisiontree;

import java.util.HashMap;
import java.util.TreeMap;

import data.DataColumnValue;
import data.DataTable;
import data.DataTableSearch;

public class ID3 extends DecisionTree {
	private DecisionTreeRoot root;

	@Override
	public DecisionTreeRoot create() {
		root = new DecisionTreeRoot(null, null, "root");
		run(dataTable, root, "root");
		return root;
	}

	private void run(DataTable parent, DecisionTreeRoot root_parent,
			String value_parent) {
		if (parent == null) {
			return;
		}
		DataTable dt = null;
		if (parent.getColumns().size() != 1 && this.getEntropy(parent) != 0) {
			TreeMap<Double, Integer> entropys_sort = new TreeMap<Double, Integer>();
			HashMap<Integer, HashMap<Integer, DataTable>> entropys_tables = new HashMap<Integer, HashMap<Integer, DataTable>>();
			DataTableSearch dataTableSearch = new DataTableSearch(parent);
			int length = parent.getColumns().size() - 1;
			for (int i = 0; i < length; i++) {// 属性列
				HashMap<Integer, DataTable> col_table = new HashMap<Integer, DataTable>();
				double entropys_one = 0;
				for (int ii = 0; ii < parent.getColumn(i).getColumnValues()
						.size(); ii++) {// 每个属性的所有属性值
					col_table.put(
							ii,
							dataTableSearch.GetChildDataTable(i, parent
									.getColumn(i).getColumnValues().get(ii)
									.toString()));
					entropys_one += (((double) parent.getColumn(i)
							.getColumnValues().get(ii).getCount() / (double) parent
							.getColumn(i).getSize()) * getEntropy(col_table
							.get(ii)));
				}
				entropys_tables.put(i, col_table);
				entropys_sort.put(entropys_one, i);
			}
			int next_col = entropys_sort.get(entropys_sort.firstKey());

			HashMap<Integer, DataTable> next_tables = entropys_tables
					.get(next_col);
			DecisionTreeRoot root_now = new DecisionTreeRoot(root_parent,
					value_parent, parent.getColumn(next_col).getColumnName());
			for (int next_next_col : next_tables.keySet()) {
				DataTable next_dt = next_tables.get(next_next_col);
				run(next_dt, root_now, parent.getColumn(next_col)
						.getColumnValues().get(next_next_col).toString());
			}
		} else {
			DecisionTreeRoot root_now = new DecisionTreeRoot(root_parent,
					value_parent, parent.getLastColumn().getColumnValues()
							.get(0).toString());
		}
	}
}
