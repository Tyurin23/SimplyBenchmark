package ru.tyurin.benchmark;


public class Benchmark {

	private Runnable runnable;
	private String name = "";
	private boolean run = false;
	private int count = 0;
	private long time = -1;
	private long memory = -1;

	public Benchmark(Runnable runnable) {
		this.setRunnable(runnable);
	}

	public Benchmark(Runnable runnable, String name) {
		this(runnable);
		setName(name);
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	public String getName() {
		return name;
	}

	public void run() {
		long m1 = Runtime.getRuntime().freeMemory();
		long t1 = System.nanoTime();
		runnable.run();
		time = System.nanoTime() - t1;
		memory = m1 - Runtime.getRuntime().freeMemory();
		run = true;
		count++;
	}

	public boolean isRun() {
		return run;
	}

	public int getCount() {
		return count;
	}

	public long getMemory() {
		if (!isRun()) {
			throw new IllegalStateException("Test is not running");
		}
		return memory;
	}

	public long getTime() {
		if (!isRun()) {
			throw new IllegalStateException("Test is not running");
		}
		return time;
	}

	public Runnable getRunnable() {
		return runnable;
	}

	private void setRunnable(Runnable runnable) {
		if (runnable == null) {
			throw new NullPointerException("Runnable object is null");
		}
		this.runnable = runnable;
	}

	public String getSummary() {
		return String.format("Benchmark %s[%d]\n    Time: %d  Memory: %d", getName(), getCount(), getTime(), getMemory());
	}
}
