package data;

public class DataAdapter_String extends DataAdapter {

	@Override
	public Object Adaptate(Object data) {
		if (data instanceof String) {
			return (String) data;
		} else {
			return null;
		}
	}

}
