package ru.tyurin.benchmark;

/**
 * Result of benchmark testing.
 *
 * @author Y. Tyurin
 * @see Benchmark
 * @since JDK1.6
 */
public class BenchmarkResult {

	private Benchmark benchmark;
	private long time;
	private long memory;
	private String name = "";

	protected BenchmarkResult(Benchmark benchmark) {
		setBenchmark(benchmark);
	}

	protected BenchmarkResult(Benchmark benchmark, String name) {
		this(benchmark);
		setName(name);
	}

	/**
	 * Get benchmark object
	 *
	 * @return benchmark object
	 */
	public Benchmark getBenchmark() {
		return benchmark;
	}

	/**
	 * Get memory used in the Test object
	 *
	 * @return approximate amount of memory used in the Test object in bytes
	 * @see java.lang.Runtime
	 */
	public long getMemory() {
		return memory;
	}

	/**
	 * Get execution time of Test object
	 *
	 * @return time in nanoseconds
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Get Test result name
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get summary string of test
	 *
	 * @return summary string
	 */
	public String getSummary() {
		return String.format("Benchmark %s[%s]\n\tTime: %d  Memory: %d", benchmark.getName(), getName(), getTime(), getMemory());
	}

	protected void setBenchmark(Benchmark benchmark) {
		if (benchmark == null) {
			throw new NullPointerException("Benchmark is null");
		}
		this.benchmark = benchmark;
	}

	protected void setMemory(long memory) {
		this.memory = memory;
	}

	protected void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	protected void setTime(long time) {
		if (time < 0) {
			throw new IllegalArgumentException("Time cannot be negative");
		}
		this.time = time;
	}

}
