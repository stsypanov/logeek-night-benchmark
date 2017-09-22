package com.luxoft.logeek.benchmark.queue;

import com.luxoft.logeek.benchmark.BenchmarkBase;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.LongStream;

public class ArrayDequeAsQueueBenchmark extends BenchmarkBase {

	@Param({"10", "100", "1000"})
	private int size;

	private Queue<Long> queue;
	private Queue<Long> preparedQueue;

	@Setup(Level.Invocation)
	public void setup() {
		queue = new ArrayDeque<>();
		preparedQueue = new ArrayDeque<>();
		LongStream.range(0, size).boxed().forEach(preparedQueue::add);
	}

	@Benchmark
	public Queue<Long> measureAdd() {
		LongStream.range(0, size).boxed().forEach(queue::add);
		return queue;
	}

	@Benchmark
	public Queue<Long> measureRemove() {
		LongStream.range(0, size).forEach(preparedQueue::remove);
		return preparedQueue;
	}

	@Benchmark
	public Queue<Long> measurePoll() {
		LongStream.range(0, size).forEach(aLong -> preparedQueue.poll());
		return preparedQueue;
	}

	@Benchmark
	public Queue<Long> measureElement() {
		LongStream.range(0, size).forEach(aLong -> preparedQueue.element());
		return preparedQueue;
	}
}
