package data;

public abstract class DataAdapter {
	public abstract Object Adaptate(Object data);

	public boolean equals(Object obj) {
		return this.getClass().getName().equals(obj.getClass().getName());
	}
}
