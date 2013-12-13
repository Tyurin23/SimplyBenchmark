package ru.tyurin.benchmark;

/**
 * Class for benchmark testing.
 * Measures the execution time and the amount of memory consumed.
 * To perform the test method used the run() method of interface Runnable.
 *
 * @author Y. Tyurin
 * @since JDK1.6
 */
public class Benchmark {


	private Runnable runnable;
	private String name = "";
	private int count = 0;

	/**
	 * Run test immediately
	 *
	 * @param test Test object
	 * @return result of test
	 * @throws NullPointerException if test is null
	 */
	public static BenchmarkResult runBenchmark(Runnable test) {
		if (test == null) {
			throw new NullPointerException("Runnable is null");
		}
		return new Benchmark(test).run();
	}

	/**
	 * Create Benchmark Test for Runnable object
	 *
	 * @param runnable Test object
	 * @throws NullPointerException if runnable is null
	 */
	public Benchmark(Runnable runnable) {
		this.setRunnable(runnable);
	}

	/**
	 * Create named Benchmark Test for Runnable object
	 * <p/>
	 * If name is null, then test name - empty string.
	 *
	 * @param runnable Test object
	 * @param name     Test name
	 * @throws NullPointerException if runnable is null
	 */
	public Benchmark(Runnable runnable, String name) {
		this(runnable);
		setName(name);
	}

	/**
	 * Set Test name.
	 * <p/>
	 * If name is null, then test name - empty string.
	 *
	 * @param name Test name
	 */
	public void setName(String name) {
		if (name != null) {
			this.name = name;
		} else {
			this.name = "";
		}
	}

	/**
	 * Get Test name
	 *
	 * @return name Test name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Run Test
	 *
	 * @return test result
	 * @see BenchmarkResult
	 */
	public BenchmarkResult run() {
		long m1 = Runtime.getRuntime().freeMemory();
		long t1 = System.nanoTime();
		runnable.run();
		long time = System.nanoTime() - t1;
		long memory = m1 - Runtime.getRuntime().freeMemory();
		count++;
		BenchmarkResult result = new BenchmarkResult(this, String.valueOf(count));
		result.setMemory(memory);
		result.setTime(time);
		return result;
	}

	/**
	 * Get Test Object
	 *
	 * @return Test object
	 */
	public Runnable getRunnable() {
		return runnable;
	}

	private void setRunnable(Runnable runnable) {
		if (runnable == null) {
			throw new NullPointerException("Runnable object is null");
		}
		this.runnable = runnable;
	}

}
