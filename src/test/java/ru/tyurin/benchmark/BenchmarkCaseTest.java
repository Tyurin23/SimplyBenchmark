package ru.tyurin.benchmark;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BenchmarkCaseTest {

	final String name = "TEST";

	final Runnable EMPTY = new Runnable() {
		@Override
		public void run() {
		}
	};

	@BeforeMethod
	public void setUp() throws Exception {


	}

	@AfterMethod
	public void tearDown() throws Exception {


	}

	@Test
	public void testConstructor() throws Exception {
		new BenchmarkCase();
		new BenchmarkCase("TEST");
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
		BenchmarkCase benchmarkCase = new BenchmarkCase();

		benchmarkCase.setName(name);

		assertEquals(name, benchmarkCase.getName());
	}

	@Test
	public void testAddBenchmark() throws Exception {
		BenchmarkCase benchmarkCase = new BenchmarkCase();
		Benchmark benchmark = new Benchmark(EMPTY);

		benchmarkCase.addBenchmark(benchmark);

		assertEquals(1, benchmarkCase.getBenchmarks().size());
		assertTrue(benchmarkCase.getBenchmarks().contains(benchmark));

	}
}
