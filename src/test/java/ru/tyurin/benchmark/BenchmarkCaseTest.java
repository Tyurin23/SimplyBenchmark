package ru.tyurin.benchmark;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * User: tyurin
 * Date: 9/20/13
 * Time: 1:13 PM
 */
public class BenchmarkCaseTest {

	String f1 = "f1";
	String f2 = "f2";
	String f3 = "f3";
	byte[] data;
	Runnable r1, r2, r3;

	@BeforeMethod
	public void setUp() throws Exception {
		Random random = new Random();
		data = new byte[8096];
		random.nextBytes(data);

		r1 = new Runnable() {
			@Override
			public void run() {
				File f = new File(f1);
				try {
					f.createNewFile();
					FileOutputStream outputStream = new FileOutputStream(f);
					outputStream.write(data);
					outputStream.flush();
					outputStream.close();
					f.delete();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		r2 = new Runnable() {
			@Override
			public void run() {
				Path p = Paths.get(f2);
				try {
					Files.createFile(p);
					OutputStream out = Files.newOutputStream(p);
					out.write(data);
					out.flush();
					out.close();
					Files.delete(p);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}

	@AfterMethod
	public void tearDown() throws Exception {
	}

	@Test
	public void testBenchmarkCase() throws Exception {
		Benchmark b1 = new Benchmark(r1, "b1");
		Benchmark b2 = new Benchmark(r2, "b2");
		BenchmarkCase benchCase = new BenchmarkCase();
		benchCase.addBenchmark(b1);
		benchCase.addBenchmark(b2, 2);

		benchCase.run();

		System.out.println(benchCase.getSummary());
		System.out.println(benchCase.getResults().size());
	}
}
