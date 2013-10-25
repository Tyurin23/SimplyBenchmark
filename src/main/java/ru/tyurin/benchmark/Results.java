package ru.tyurin.benchmark;

import java.util.*;

/**
 * Container of test results
 */
public class Results {

	private String name;
	private Map<Benchmark, List<BenchmarkResult>> results;

	/**
	 * Create non-named results container
	 */
	public Results() {
		this("");
	}

	/**
	 * Create named result container.
	 *
	 * @param name name of container
	 */
	public Results(String name) {
		setName(name);
		results = new HashMap<Benchmark, List<BenchmarkResult>>();
	}

	/**
	 * Returns name of container
	 *
	 * @return name of container
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of container.
	 * If name is null then name of container is empty
	 *
	 * @param name name of container
	 */
	public void setName(String name) {
		if (name != null) {
			this.name = name;
		} else {
			this.name = "";
		}
	}

	/**
	 * Add result in container
	 *
	 * @param result result of benchmark
	 * @throws NullPointerException if result is null
	 */
	public void add(BenchmarkResult result) {
		if (result == null) {
			throw new NullPointerException("BenchmarkResult is null");
		}
		addResult(result);
	}


	private void addResult(BenchmarkResult result) {
		if (results.containsKey(result.getBenchmark())) {
			results.get(result.getBenchmark()).add(result);
		} else {
			List<BenchmarkResult> resultList = new ArrayList<BenchmarkResult>();
			resultList.add(result);
			results.put(result.getBenchmark(), resultList);
		}
	}

	/**
	 * Add collection of results in container
	 *
	 * @param results collection of results
	 * @throws NullPointerException if collection is null
	 */
	public void addAll(Collection<BenchmarkResult> results) {
		if (results == null) {
			throw new NullPointerException("Collection of results is null");
		}
		for (BenchmarkResult result : results) {
			if (result != null) {
				addResult(result);
			}
		}
	}

	/**
	 * Remove result from container
	 *
	 * @param result result, to be removed
	 * @return true if result removed
	 */
	public boolean remove(BenchmarkResult result) {
		if (result == null) {
			throw new NullPointerException("Result is null");
		}
		if (results.containsKey(result.getBenchmark())) {
			return results.get(result.getBenchmark()).remove(result);
		} else {
			return false;
		}
	}

	/**
	 * Returns count of results
	 *
	 * @return count of results
	 */
	public int numberOfResults() {
		int count = 0;
		for (List resultList : results.values()) {
			count += resultList.size();
		}
		return count;
	}

	/**
	 * Returns collection of results
	 *
	 * @return collection of results
	 */
	public Collection<BenchmarkResult> getAll() {
		ArrayList<BenchmarkResult> res = new ArrayList<BenchmarkResult>();
		for (List resultList : results.values()) {
			res.addAll(resultList);
		}
		return res;
	}

	public int numberOfBenchmarks() {
		return results.size();
	}


	public Collection<Benchmark> getBenchmarks() {
		return results.keySet();
	}

	public Collection<BenchmarkResult> getByBenchmark(Benchmark benchmark) {
		if (benchmark == null) {
			throw new NullPointerException("Benchmark is null");
		}
		return results.get(benchmark);
	}
}
