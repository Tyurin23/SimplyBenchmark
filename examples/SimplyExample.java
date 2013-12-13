import ru.tyurin.benchmark.Benchmark;
import ru.tyurin.benchmark.BenchmarkCase;
import ru.tyurin.benchmark.Results;
import ru.tyurin.benchmark.printer.DefaultPrinter;
import ru.tyurin.benchmark.printer.Printer;

public class SimplyExample {

	static Runnable test = new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};


	public static void main(String[] args) {
		// Level 1
		// Run single test
		System.out.println(Benchmark.runBenchmark(test).getSummary());

		//Level 2
		// Run single test 10 times
		System.out.println(BenchmarkCase.runBenchmark(new Benchmark(test), 10));

		//Level 3
		//Run 2 tests
		BenchmarkCase benchmarkCase = new BenchmarkCase();
		//Add test in case
		benchmarkCase.addBenchmark(new Benchmark(test));
		//Add test that will run 2 times
		benchmarkCase.addBenchmark(new Benchmark(new Runnable() {
			@Override
			public void run() {
				// Do anything...
			}
		}), 2); // < ----  2 times!!
		// Run tests
		Results results = benchmarkCase.run();
		//Print results
		Printer printer = new DefaultPrinter();
		printer.print(results);
	}

}
