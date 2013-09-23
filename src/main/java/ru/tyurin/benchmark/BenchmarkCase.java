package ru.tyurin.benchmark;


import java.util.*;

public class BenchmarkCase {

	private List<Benchmark> benchmarks;
	private Map<Benchmark, Collection<BenchmarkResult>> results;
	private Map<Benchmark, Integer> counts;
	private String name = "";

	public BenchmarkCase() {
		benchmarks = new ArrayList<Benchmark>();
		results = new HashMap<Benchmark, Collection<BenchmarkResult>>();
		counts = new HashMap<Benchmark, Integer>();
	}

	public BenchmarkCase(String name) {
		this();
		setName(name);
	}

	public String getName() {
		return name;
	}

	public Collection<BenchmarkResult> getResults() {
		Collection<BenchmarkResult> results = new ArrayList<BenchmarkResult>();
		for (Collection<BenchmarkResult> resultList : this.results.values()) {
			results.addAll(resultList);
		}
		return results;
	}

	public Collection<BenchmarkResult> getResults(Benchmark benchmark) {
		return this.results.get(benchmark);
	}

	public Collection<Benchmark> getBenchmarks() {
		return benchmarks;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	public void addBenchmark(Benchmark benchmark) {
		addBenchmark(benchmark, 1);
	}

	public void addBenchmark(Benchmark benchmark, int count) {
		if (benchmark == null) {
			throw new NullPointerException("Benchmark is null");
		}
		if (count < 0) {
			throw new IllegalArgumentException("Count value cannot be negative");
		}
		benchmarks.add(benchmark);
		counts.put(benchmark, count);
	}

	public void run() {
		results.clear();
		for (Benchmark benchmark : benchmarks) {
			int count = counts.get(benchmark);
			Collection<BenchmarkResult> res = new ArrayList<BenchmarkResult>(count);
			for (int c = 0; c < count; c++) {
				BenchmarkResult result = benchmark.run();
				res.add(result);
			}
			BenchmarkResult total = getTotal(res, benchmark);
			BenchmarkResult avg = getAVG(res, benchmark);
			res.add(total);
			res.add(avg);
			results.put(benchmark, res);
		}
	}

	public String getSummary() {
		StringBuilder builder = new StringBuilder(String.format("Benchmark Case %s%n", getName()));
		builder.append("\n");
		for (Benchmark benchmark : benchmarks) {
			builder.append("\t");
			builder.append(String.format("Benchmark %s%n", benchmark.getName()));
			for (BenchmarkResult result : results.get(benchmark)) {
				builder.append("\t\t");
				builder.append(String.format("[%s]%n \t\t\t Time: %d \tMemory: %d", result.getName(), result.getTime(), result.getMemory()));
				builder.append("\n");
			}
		}
		return builder.toString();
	}

	private BenchmarkResult getTotal(Collection<BenchmarkResult> results, Benchmark benchmark) {
		long time = 0;
		long mem = 0;
		BenchmarkResult res = new BenchmarkResult(benchmark, "total");
		for (BenchmarkResult result : results) {
			time += result.getTime();
			mem += result.getMemory();
		}
		res.setTime(time);
		res.setMemory(mem);
		return res;
	}

	private BenchmarkResult getAVG(Collection<BenchmarkResult> results, Benchmark benchmark) {
		long time = 0;
		long mem = 0;
		long count = results.size();
		BenchmarkResult res = new BenchmarkResult(benchmark, "avg");
		for (BenchmarkResult result : results) {
			time += result.getTime();
			mem += result.getMemory();
		}
		if (count > 0) {
			res.setTime(time / count);
			res.setMemory(mem / count);
		}
		return res;
	}

}
