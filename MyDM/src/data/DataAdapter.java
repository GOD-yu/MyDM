package data;

public abstract class DataAdapter {
	public abstract Object adaptate(String data);

	public boolean equals(Object obj) {
		return this.getClass().getName().equals(obj.getClass().getName());
	}
}
