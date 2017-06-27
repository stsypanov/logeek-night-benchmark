package com.luxoft.logeek.benchmark.equals;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class EqualsBenchmark extends BenchmarkBase {

	private boolean booleanField1;
	private boolean booleanField2;

	private char charField1;
	private char charField2;

	private int intField1;
	private int intField2;

	private long longField1;
	private long longField2;

	private int[] intArrayField1;
	private int[] intArrayField2;

	private String stringField1;
	private String stringField2;

	private Integer integerField1;
	private Integer integerField2;

	private Long longWrapperField1;
	private Long longWrapperField2;

	private Date date1;
	private Date date2;

	private LocalDate localDate1;
	private LocalDate localDate2;

	private LocalDateTime localDateTime1;
	private LocalDateTime localDateTime2;

	private BigDecimal bigDecimal1;
	private BigDecimal bigDecimal2;

	private Collection<Integer> intCollection1;
	private Collection<Integer> intCollection2;

	@Setup
	public void initTrial() {
		super.init();
	}

	@Setup(Level.Iteration)
	public void setup() {
		booleanField1 = random.nextBoolean();
		booleanField2 = random.nextBoolean();

		intField1 = random.nextInt();
		intField2 = random.nextInt();

		charField1 = (char)random.nextInt();
		charField2 = (char)random.nextInt();

		longField1 = random.nextLong();
		longField2 = random.nextLong();

		intArrayField1 = createRandomArrayOf10();
		intArrayField2 = createRandomArrayOf10();

		stringField1 = random.nextLong() + "";
		stringField2 = random.nextLong() + "";

		integerField1 = random.nextInt();
		integerField2 = random.nextInt();

		longWrapperField1 = random.nextLong();
		longWrapperField2 = random.nextLong();

		date1 = new Date();
		date2 = new Date();

		localDate1 = LocalDate.now();
		localDate2 = LocalDate.now();

		localDateTime1 = LocalDateTime.now();
		localDateTime2 = LocalDateTime.now();

		intCollection1 = createRandomCollectionOf10();
		intCollection2 = createRandomCollectionOf10();

		bigDecimal1 = BigDecimal.valueOf(random.nextDouble());
		bigDecimal2 = BigDecimal.valueOf(random.nextDouble());
	}

	private int[] createRandomArrayOf10() {
		int arraySize = random.nextInt(10);
		int[] ints = new int[arraySize];
		for (int i = 0; i < arraySize - 1; i++) {
			ints[i] = random.nextInt();
		}
		return ints;
	}

	private List<Integer> createRandomCollectionOf10() {
		return random.ints(10).boxed().collect(Collectors.toList());
	}

	@Benchmark
	public boolean compareBooleans() {
		return booleanField1 == booleanField2;
	}

	@Benchmark
	public boolean compareInts() {
		return intField1 == intField2;
	}

	@Benchmark
	public boolean compareChars() {
		return intField1 == intField2;
	}

	@Benchmark
	public boolean compareLongPrimitives() {
		return longField1 == longField2;
	}

	@Benchmark
	public boolean compareArrays() {
		return Arrays.equals(intArrayField1, intArrayField2);
	}

	@Benchmark
	public boolean compareStrings() {
		return stringField1.equals(stringField2);
	}

	@Benchmark
	public boolean compareIntegers() {
		return integerField1.equals(integerField2);
	}

	@Benchmark
	public boolean compareLongWrappers() {
		return longWrapperField1.equals(longWrapperField2);
	}

	@Benchmark
	public boolean compareDates() {
		return date1.equals(date2);
	}

	@Benchmark
	public boolean compareLocalDates() {
		return localDate1.equals(localDate2);
	}

	@Benchmark
	public boolean compareLocalDateTimes() {
		return localDateTime1.equals(localDateTime2);
	}

	@Benchmark
	public boolean compareBigDecimals() {
		return bigDecimal1.compareTo(bigDecimal2) == 0;
	}

	@Benchmark
	public boolean compareCollections() {
		return intCollection1.equals(intCollection2);
	}
}
