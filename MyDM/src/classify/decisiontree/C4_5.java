package classify.decisiontree;

import java.util.HashMap;
import java.util.TreeMap;
import data.DataTable;
import data.DataTableSearch;

public class C4_5 extends DecisionTreeAlgorithm {
	private DecisionTree tree;

	@Override
	public DecisionTree create() {
		tree = new DecisionTree();
		run(dataTable, tree.getRoot(), "root");
		return tree;
	}

	private void run(DataTable parent, DecisionTreeNode root_parent,
			String value_parent) {
		if (parent == null) {
			return;
		}
		if (parent.columns.getColumns().size() != 1
				&& this.getEntropy(parent) != 0) {
			TreeMap<Double, Integer> entropys_sort = new TreeMap<Double, Integer>();
			HashMap<Integer, HashMap<Integer, DataTable>> entropys_tables = new HashMap<Integer, HashMap<Integer, DataTable>>();
			DataTableSearch dataTableSearch = new DataTableSearch(parent);
			int length = parent.columns.getColumns().size() - 1;
			for (int i = 0; i < length; i++) {// 属性列
				HashMap<Integer, DataTable> col_table = new HashMap<Integer, DataTable>();
				double entropys_one = 0;
				double splite_one = 1;
				for (int ii = 0; ii < parent.columns.getColumn(i)
						.getColumnValues().size(); ii++) {// 每个属性的所有属性值
					col_table.put(
							ii,
							dataTableSearch.GetChildDataTable(i, parent.columns
									.getColumn(i).getColumnValues().get(ii)
									.toString()));
					entropys_one += (((double) parent.columns.getColumn(i)
							.getCellsIndex(ii).size() / (double) parent.columns
							.getColumn(i).size()) * getEntropy(col_table
							.get(ii)));

				}

				splite_one = getEntropy(parent, i);
				entropys_tables.put(i, col_table);
				entropys_sort.put((double) entropys_one / splite_one, i);

			}
			int next_col = entropys_sort.get(entropys_sort.firstKey());

			HashMap<Integer, DataTable> next_tables = entropys_tables
					.get(next_col);
			DecisionTreeNode root_now = new DecisionTreeNode(root_parent,
					value_parent, parent.columns.getColumn(next_col)
							.getColumnName());
			for (int next_next_col : next_tables.keySet()) {
				DataTable next_dt = next_tables.get(next_next_col);
				run(next_dt, root_now, parent.columns.getColumn(next_col)
						.getColumnValues().get(next_next_col).toString());
			}
		} else {
			DecisionTreeNode root_now = new DecisionTreeNode(root_parent,
					value_parent, parent.columns.getLastColumn()
							.getColumnValues().get(0).toString());
			root_now.size();
		}
	}
}
