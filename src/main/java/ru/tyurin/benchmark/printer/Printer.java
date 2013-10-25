package ru.tyurin.benchmark.printer;


import ru.tyurin.benchmark.Results;

/**
 * Interface for printing results of tests.
 */
public interface Printer {

	/**
	 * Print results of test.
	 *
	 * @param results Collection of benchmark results
	 * @throws NullPointerException     if results is null
	 * @throws IllegalArgumentException if collection results is empty
	 */
	public void print(Results results);
}
