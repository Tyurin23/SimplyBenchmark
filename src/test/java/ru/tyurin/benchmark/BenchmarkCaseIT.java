package ru.tyurin.benchmark;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkCaseIT {

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			List<Object> objects = new ArrayList<Object>();
			for (int i = 0; i < 10000; i++) {
				objects.add(new Object());
			}
		}
	};

	Runnable sleep = new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	};

	@BeforeMethod
	public void setUp() throws Exception {
	}

	@AfterMethod
	public void tearDown() throws Exception {
	}

	@Test
	public void testBenchmarkCase() throws Exception {
		Benchmark benchmark = new Benchmark(runnable);
		BenchmarkCase testCase = new BenchmarkCase();
		testCase.addBenchmark(benchmark, 100);
		testCase.run();
	}

	@Test
	public void testSingleBenchmark() throws Exception {
		Benchmark benchmark = new Benchmark(sleep);

		BenchmarkResult result = benchmark.run();

		System.out.println(result.getSummary());
	}
}
