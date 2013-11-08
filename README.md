#SimplyBenchmark


SimplyBenchmark is a lightweight java benchmarking library for testing you code.

##Using

Create single test:
```java
Runnable test = new Runnable() {
		@Override
		public void run() {
		  // You code here
		}
	};

Benchmark simpleBenchmark = new Benchmark(test);

BenchmarkResult result = simpleBenchmark.run();

System.out.println(result.getSummary());
```
Out:
```
Benchmark [1]
	Time: 567  Memory: 0
```
