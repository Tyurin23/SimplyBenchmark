import ru.tyurin.benchmark.Benchmark;
import ru.tyurin.benchmark.BenchmarkCase;
import ru.tyurin.benchmark.BenchmarkResult;

public class SimplyExample {

	static Runnable runnable = new Runnable() {
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
		Benchmark test = new Benchmark(runnable, "Simply Test");
		BenchmarkResult result = test.run();
		System.out.println(result.getSummary());

		// or ....

		BenchmarkCase testCase = new BenchmarkCase("Simply case");
		testCase.addBenchmark(test, 10);// run test 10 times
		testCase.run();
		System.out.println(testCase.getSummary());
	}

}
