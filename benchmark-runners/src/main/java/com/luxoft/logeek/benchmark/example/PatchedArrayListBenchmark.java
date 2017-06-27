package com.luxoft.logeek.benchmark.example;

import com.luxoft.logeek.benchmark.BaseBenchmark;
import com.luxoft.logeek.example.PatchedArrayList;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class PatchedArrayListBenchmark extends BaseBenchmark {
	
	private List<Long> patchedArrayList1;
	private List<Long> patchedArrayList2;

	private List<Long> patchedArrayList3;
	private List<Long> patchedArrayList4;

	@Setup
	public void init() {
		super.init();
	}

	@Setup(value = Level.Iteration)
	public void prepareDate() {
		int size = 100;

		patchedArrayList1 = getPatchedArrayList(size);
		patchedArrayList2 = getPatchedArrayList(size);

		patchedArrayList3 = getPatchedArrayList(1);
		patchedArrayList4 = getPatchedArrayList(2);
	}

	private PatchedArrayList<Long> getPatchedArrayList(int size) {
		return random.longs(size, 0, 1)
				.boxed()
				.map(l -> 1L)
				.collect(Collectors.toCollection(PatchedArrayList::new));
	}

	@Benchmark
	public boolean measureEquals_patchedArrayLists_sameSize() {
		return patchedArrayList2.equals(patchedArrayList1);
	}

	@Benchmark
	public boolean measureEquals_patchedArrayLists_differentSize() {
		return patchedArrayList3.equals(patchedArrayList4);
	}

}
