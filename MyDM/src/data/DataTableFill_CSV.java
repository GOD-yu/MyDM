package data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DataTableFill_CSV {
	private String dirName;

	public DataTableFill_CSV(String dirname) {
		dirName = dirname;
	}

	public DataTable Fill(String filename) {
		DataTable dataTable = new DataTable(filename);

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		DataAdapterCreater dac = new DataAdapterCreater();
		// 添加列
		try {
			String str = "";
			fis = new FileInputStream(dirName + filename + ".head");// FileInputStream
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				String[] ss = str.split(",");
				dataTable.addColumn(ss[0], dac.createDataAdapter(ss[1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 添加行
		try {
			String str = "";
			fis = new FileInputStream(dirName + filename + ".csv");// FileInputStream
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			DataCell dc;
			while ((str = br.readLine()) != null) {
				String[] ss = str.split(",");
				dataTable.addRow();
				for (int i = 0; i < dataTable.getColumns().size(); i++) {
					dc = new DataCell(ss[i], dataTable.getColumn(i)
							.getDataAdapter());
					dc.bind(dataTable.getLastRow(), dataTable.getColumn(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dataTable;
	}
}
