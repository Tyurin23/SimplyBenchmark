package ru.tyurin.benchmark.printer;


import ru.tyurin.benchmark.Benchmark;
import ru.tyurin.benchmark.BenchmarkResult;
import ru.tyurin.benchmark.Results;

public class DefaultPrinter implements Printer {

	@Override
	public void print(Results results) {
		System.out.println(getResultsString(results));
	}

	public String getResultsString(Results results) {
		if (results == null) {
			throw new NullPointerException("Collection of results is null");
		}
		StringBuilder builder = new StringBuilder(String.format("Benchmark Case %s%n", results.getName()));
		builder.append("\n");
		for (Benchmark benchmark : results.getBenchmarks()) {
			builder.append("\t");
			builder.append(String.format("Benchmark %s%n", benchmark.getName()));
			for (BenchmarkResult result : results.getByBenchmark(benchmark)) {
				builder.append("\t\t");
				builder.append(String.format("[%s]%n \t\t\t Time: %d \tMemory: %d", result.getName(), result.getTime(), result.getMemory()));
				builder.append("\n");
			}
		}
		builder.append("AVG: " + getAVG(results));
		builder.append("Total: " + getTotal(results));
		return builder.toString();
	}

	private String getTotal(Results results) {
		long time = 0;
		long mem = 0;
		for (BenchmarkResult result : results.getAll()) {
			time += result.getTime();
			mem += result.getMemory();
		}
		return getFormattedString(time, mem);
	}

	private String getAVG(Results results) {
		long time = 0;
		long mem = 0;
		long count = results.numberOfResults();
		for (BenchmarkResult result : results.getAll()) {
			time += result.getTime();
			mem += result.getMemory();
		}
		if (count > 0) {
			time /= count;
			mem /= count;
		}
		return getFormattedString(time, mem);
	}

	private String getFormattedString(long time, long memory) {
		return String.format("Time: %d Mem: %d", time, memory);
	}


}
