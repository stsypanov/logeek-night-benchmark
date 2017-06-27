package com.luxoft.logeek.benchmark.hashcode;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.hashcode.HashCodeCachingVO;
import com.luxoft.logeek.hashcode.SomeVO;
import org.openjdk.jmh.annotations.*;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

//@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HashCodeCachingBenchmark extends BenchmarkBase {

	private Set<SomeVO> conventionalVOs;
	private Set<HashCodeCachingVO> enhancedVOs;

	private SomeVO presentConventionalVo;
	private HashCodeCachingVO presentEnhancedVo;


	@Param({"10", "100", "1000", "10000", "100000"})
	private int size;

	@Setup(value = Level.Iteration)
	public void init() {
		super.init();
		conventionalVOs = random.longs(size).boxed()
				.map(aLong -> new SomeVO(
								aLong,
								random.nextGaussian() + "",
								asList(random.nextLong(), random.nextLong(), random.nextLong())
						)
				).collect(toSet());
		presentConventionalVo = conventionalVOs.stream().filter(someVO -> someVO.getId() > 0).findAny().orElse(null);

		enhancedVOs = random.longs(size).boxed()
				.map(aLong -> new HashCodeCachingVO(
								aLong,
								random.nextGaussian() + "",
								asList(random.nextLong(), random.nextLong(), random.nextLong())
						)
				).collect(toSet());

		presentEnhancedVo = enhancedVOs.stream().filter(someVO -> someVO.getId() > 0).findAny().orElse(null);
	}

	@Benchmark
	public boolean measureConventionalVO() {
		return conventionalVOs.contains(presentConventionalVo);
	}

	@Benchmark
	public boolean measureDateTimeFormatter() {
		return enhancedVOs.contains(presentEnhancedVo);
	}

	/*@Benchmark
	public Set<SomeVO> measureDateTimeFormatterWhenDateConverted() {
		return new HashSet<>(conventionalVOs);
	}*/
}
