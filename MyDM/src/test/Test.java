package test;

import classify.decisiontree.DecisionTree;
import classify.decisiontree.DecisionTreeRoot;
import classify.decisiontree.ID3;
import time.Timer;
import data.DataAdapter;
import data.DataAdapter_String;
import data.DataTable;
import data.DataTableFill_CSV;
import data.DataTableSearch;

public class Test {

	public static void main(String[] args) {
		Timer t = new Timer();
		t.start();
		DataTableFill_CSV fill = new DataTableFill_CSV(
				"D:/Workspace/UCI_DataSet/Dresses_Attribute_Sales/");
		DataTable dt = fill.Fill("Attribute_DataSet");

		System.out.println(dt);
		DecisionTree id3 = new ID3();
		id3.load(dt);
		
		//DecisionTreeRoot.print1(id3.create());
		DecisionTreeRoot.print2(id3.create());

		System.out.println(t.getRunTime() + "ms");

	}

}
