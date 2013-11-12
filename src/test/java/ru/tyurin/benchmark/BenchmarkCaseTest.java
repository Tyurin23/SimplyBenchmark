package ru.tyurin.benchmark;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BenchmarkCaseTest {

	class TestRunnable implements Runnable {

		private int count = 0;

		int getCount() {
			return count;
		}

		@Override
		public void run() {
			count++;
		}
	}

	final String name = "TEST";

	TestRunnable test;

	BenchmarkCase benchmarkCase;
	Benchmark benchmark;

	@BeforeMethod
	public void setUp() throws Exception {
		test = new TestRunnable();
		benchmarkCase = new BenchmarkCase();
		benchmark = new Benchmark(test);
	}

	@AfterMethod
	public void tearDown() throws Exception {


	}

	@Test
	public void testConstructor() throws Exception {
		new BenchmarkCase();
	}

	@Test
	public void testConstructorName() throws Exception {
		BenchmarkCase benchmarkCase = new BenchmarkCase(name);

		assertEquals(name, benchmarkCase.getName());
	}

	@Test
	public void testConstructorNameNull() throws Exception {
		BenchmarkCase benchmarkCase = new BenchmarkCase(null);

		assertEquals("", benchmarkCase.getName());
	}

	@Test
	public void testSetName() throws Exception {
		benchmarkCase.setName(name);

		assertEquals(name, benchmarkCase.getName());
	}

	@Test
	public void testAddBenchmark() throws Exception {
		benchmarkCase.addBenchmark(benchmark);

		benchmarkCase.run();

		assertEquals(1, benchmarkCase.getBenchmarks().size());
		assertTrue(benchmarkCase.getBenchmarks().contains(benchmark));
		assertEquals(1, test.getCount());
	}

	@Test(
			expectedExceptions = NullPointerException.class
	)
	public void testAddBenchmarkNull() throws Exception {
		benchmarkCase.addBenchmark(null);
	}

	@Test
	public void testAddBenchmarkWithCount() throws Exception {
		final int COUNT = 100;
		benchmarkCase.addBenchmark(benchmark, COUNT);

		benchmarkCase.run();

		assertEquals(1, benchmarkCase.getBenchmarks().size());
		assertEquals(COUNT, benchmarkCase.getCount(benchmark));
		assertEquals(COUNT, test.getCount());
	}

	@Test
	public void testAddBenchmarkWithZeroCount() throws Exception {
		benchmarkCase.addBenchmark(benchmark, 0);

		benchmarkCase.run();

		assertEquals(1, benchmarkCase.getBenchmarks().size());
		assertEquals(0, benchmarkCase.getCount(benchmark));
		assertEquals(0, test.getCount());
	}

	@Test(
			expectedExceptions = IllegalArgumentException.class
	)
	public void testAddBenchmarkWithNegativeCount() throws Exception {
		benchmarkCase.addBenchmark(benchmark, -1);
	}

	@Test
	public void testRemoveBenchmark() throws Exception {
		benchmarkCase.addBenchmark(benchmark);

		boolean removed = benchmarkCase.removeBenchmark(benchmark);

		assertTrue(removed);
		assertEquals(0, benchmarkCase.getBenchmarks().size());
	}

	@Test
	public void testRemoveBenchmark2() throws Exception {
		Benchmark benchmark2 = new Benchmark(new Runnable() {
			@Override
			public void run() {
			}
		});
		benchmarkCase.addBenchmark(benchmark2);
		benchmarkCase.addBenchmark(benchmark);

		benchmarkCase.removeBenchmark(benchmark);

		assertEquals(1, benchmarkCase.getBenchmarks().size());
		assertEquals(benchmark2, benchmarkCase.getBenchmarks().toArray()[0]);
	}

	@Test(
			expectedExceptions = NullPointerException.class
	)
	public void testRemoveBenchmarkNull() throws Exception {
		benchmarkCase.addBenchmark(benchmark);

		benchmarkCase.removeBenchmark(null);
	}

	@Test
	public void testRemoveBenchmarkNotExist() throws Exception {
		boolean removed = benchmarkCase.removeBenchmark(benchmark);

		assertFalse(removed);
	}
}
