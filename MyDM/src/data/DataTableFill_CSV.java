package data;

import java.util.Vector;

import file.FileReader;

public class DataTableFill_CSV {
	private String dirName;

	public DataTableFill_CSV(String dirname) {
		dirName = dirname;
	}

	public DataTable Fill(String filename) {
		DataTable dataTable = new DataTable(filename);
		Vector<DataAdapter> da = new Vector<DataAdapter>();
		DataAdapterCreater dac = new DataAdapterCreater();
		String str = "";

		FileReader rd = new FileReader(dirName + filename + ".head");
		while ((str = rd.readLine(false)) != null) {
			String[] ss = str.split(",");
			dataTable.addColumn(ss[0]);
			da.add(dac.createDataAdapter(ss[1]));
		}
		rd.close();

		DataCell dc;
		rd = new FileReader(dirName + filename + ".csv");
		while ((str = rd.readLine(false)) != null) {
			String[] ss = str.split(",");
			dataTable.addRow();
			for (int i = 0; i < dataTable.columns.size(); i++) {
				dc = new DataCell(da.get(i).adaptate(ss[i]));
				dc.bind(dataTable.rows.getLastRow(),
						dataTable.columns.getColumn(i));
			}
		}
		rd.close();

		return dataTable;
	}
}
