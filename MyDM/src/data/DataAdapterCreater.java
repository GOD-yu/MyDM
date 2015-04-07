package data;

public class DataAdapterCreater {
	public DataAdapter createDataAdapter(String type) {
		if (type.equals("String")) {
			return new DataAdapter_String();
		} else {
			return null;
		}
	}
}
