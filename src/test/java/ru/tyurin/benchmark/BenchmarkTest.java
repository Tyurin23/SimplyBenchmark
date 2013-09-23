package ru.tyurin.benchmark;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BenchmarkTest {

	Runnable empty;
	TestRunned testRunned;
	Runnable big;

	class TestRunned implements Runnable {

		boolean run = false;

		@Override
		public void run() {
			run = true;
		}
	}

	@BeforeMethod
	public void setUp() throws Exception {
		empty = new Runnable() {
			@Override
			public void run() {
			}
		};

		big = new Runnable() {
			@Override
			public void run() {
				List<Object> list = new ArrayList<Object>();
				final int count = 10000;
				for (int i = 0; i < count; i++) {
					Object o = new Object();
					list.add(o);
					int x = i % 5;
				}
			}
		};

	}

	@AfterMethod
	public void tearDown() throws Exception {

	}

	@Test
	public void testConstructor() throws Exception {
		Benchmark test = new Benchmark(empty);


		assertEquals(empty, test.getRunnable());
	}

	@Test(
			expectedExceptions = NullPointerException.class
	)
	public void testConstructorNull() throws Exception {
		Benchmark test = new Benchmark(null);
	}

	@Test
	public void testRun() throws Exception {
		TestRunned testRunned = new TestRunned();
		Benchmark test = new Benchmark(testRunned);

		test.run();

		assertTrue(testRunned.run);
	}

	@Test
	public void testGetMemory() throws Exception {
		Benchmark test = new Benchmark(big);

		BenchmarkResult result = test.run();

		result.getMemory();
	}


	@Test
	public void testGetTime() throws Exception {
		Benchmark test = new Benchmark(big);

		BenchmarkResult result = test.run();

		long time = result.getTime();
		assertTrue(time > 0);
	}
}
