package ru.tyurin.benchmark;


import java.util.*;

public class BenchmarkCase {

	private Set<Benchmark> benchmarks;
	private Map<Benchmark, Integer> counts;
	private String name = "";

	/**
	 * Create Benchmark Case
	 */
	public BenchmarkCase() {
		benchmarks = new HashSet<Benchmark>();
		counts = new HashMap<Benchmark, Integer>();
	}

	/**
	 * Create named benchmark case
	 *
	 * @param name benchmark case name
	 * @see #setName
	 */
	public BenchmarkCase(String name) {
		this();
		setName(name);
	}

	/**
	 * Get benchmark case name
	 *
	 * @return case name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set benchmark case name
	 * <p/>
	 * If name is null, then name - empty string
	 *
	 * @param name case name
	 */
	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	/**
	 * Add benchmark in test case
	 *
	 * @param benchmark
	 * @throws NullPointerException If  benchmark is null
	 */
	public void addBenchmark(Benchmark benchmark) {
		addBenchmark(benchmark, 1);
	}

	/**
	 * Add benchmark in test case.
	 * <p/>
	 * If benchmark already exist, it wont be added, but count will be changed
	 *
	 * @param benchmark
	 * @param count     Count of starts test
	 * @throws NullPointerException     If  benchmark is null
	 * @throws IllegalArgumentException If count is negative
	 */
	public void addBenchmark(Benchmark benchmark, int count) {
		if (benchmark == null) {
			throw new NullPointerException("Benchmark is null");
		}
		if (count < 0) {
			throw new IllegalArgumentException("Count value cannot be negative");
		}
		addBenchmarkTest(benchmark);
		setCount(benchmark, count);
	}

	/**
	 * Set count of benchmark runs
	 *
	 * @param benchmark
	 * @param count     Count of starts test
	 * @throws NullPointerException     If  benchmark is null
	 * @throws IllegalArgumentException If count is negative or if benchmark not found
	 */
	public void setBenchmarkCount(Benchmark benchmark, int count) {
		if (benchmark == null) {
			throw new NullPointerException("Benchmark is null");
		}
		if (count < 0) {
			throw new IllegalArgumentException("Count < 0");
		}
		if (!hasBenchmark(benchmark)) {
			throw new IllegalArgumentException("Benchmark not found");
		}
		setCount(benchmark, count);
	}

	/**
	 * Remove benchmark from case
	 *
	 * @param benchmark
	 * @return True if test removed, or False if not.
	 */
	public boolean removeBenchmark(Benchmark benchmark) {
		counts.remove(benchmark);
		return benchmarks.remove(benchmark);
	}

	/**
	 * Run test case
	 *
	 * @return test results
	 * @see Results
	 */
	public Results run() {
		Results results = new Results(getName());
		for (Benchmark benchmark : benchmarks) {
			int count = getCount(benchmark);
			for (int c = 0; c < count; c++) {
				BenchmarkResult result = benchmark.run();
				result.setName(String.valueOf(c));
				results.add(result);
			}
		}
		return results;
	}

	/**
	 * Get benchmarks
	 *
	 * @return Collection of benchmarks
	 */
	public Collection<Benchmark> getBenchmarks() {
		return benchmarks;
	}

	protected int getCount(Benchmark benchmark) {
		return counts.get(benchmark);
	}

	protected void setCount(Benchmark benchmark, int count) {
		counts.put(benchmark, count);
	}

	protected void addBenchmarkTest(Benchmark benchmark) {
		if (!hasBenchmark(benchmark))
			benchmarks.add(benchmark);
	}

	protected boolean hasBenchmark(Benchmark benchmark) {
		return benchmarks.contains(benchmark);
	}


}
