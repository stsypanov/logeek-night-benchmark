package com.luxoft.logeek.benchmark.hashcode;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import com.luxoft.logeek.hashcode.HashCodeCachingVO;
import com.luxoft.logeek.hashcode.SomeVO;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;

import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public class HashCodeCachingBenchmark extends BenchmarkBase {

	private Set<SomeVO> conventionalVOs;
	private Set<HashCodeCachingVO> enhancedVOs;

	private SomeVO presentConventionalVo;
	private HashCodeCachingVO presentEnhancedVo;

	@Param({"10", "100", "1000", "10000", "100000"})
	private int size;

	@Setup
	public void initTrial() {
		super.init();
	}

	@Setup(value = Level.Iteration)
	public void init() {
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
