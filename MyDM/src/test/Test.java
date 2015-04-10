package test;

import classify.decisiontree.C4_5;
import classify.decisiontree.DecisionTree;
import classify.decisiontree.DecisionTreeAlgorithm;
import classify.decisiontree.ID3;
import time.Timer;
import data.DataTable;
import data.DataTableFill_CSV;
import file.FileWriter;

public class Test {

	public static void main(String[] args) {
		DataTableFill_CSV fill = new DataTableFill_CSV(
				"D:/Workspace/UCI_DataSet/votes/");
		DataTable dt1 = fill.Fill("votes-data");
		DataTable dt2 = fill.Fill("votes-test");
		FileWriter fw = new FileWriter("D:/Workspace/UCI_DataSet/votes/res");

		Timer t = new Timer();
		fw.writeLine("=====ID3-start=====");
		t.start();
		DecisionTreeAlgorithm decisiontreealgorithm = new ID3();
		decisiontreealgorithm.load(dt1);
		DecisionTree tree = decisiontreealgorithm.create();
		fw.writeLine(t.getRunTime() + "ms");
		fw.writeLine(tree.test(dt2));
		fw.writeLine(t.getRunTime() + "ms");
		fw.writeLine("=====ID3-end=====");
		fw.writeLine("=====C4.5-start=====");
		t.start();
		decisiontreealgorithm = new C4_5();
		decisiontreealgorithm.load(dt1);
		tree = decisiontreealgorithm.create();
		fw.writeLine(t.getRunTime() + "ms");
		fw.writeLine(tree.test(dt2));
		fw.writeLine(t.getRunTime() + "ms");
		fw.writeLine("=====C4.5-end=====");

		fw.close();
	}

}
