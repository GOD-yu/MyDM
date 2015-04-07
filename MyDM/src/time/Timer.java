package time;

public class Timer {
	long ticks;

	public Timer() {
		ticks = 0;
	}

	public void start() {
		ticks = System.currentTimeMillis();
	}

	public long getRunTime() {
		return System.currentTimeMillis() - ticks;
	}
}
