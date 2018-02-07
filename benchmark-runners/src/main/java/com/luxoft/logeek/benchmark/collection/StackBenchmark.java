package com.luxoft.logeek.benchmark.collection;

import com.intellij.util.containers.IdeaStack;
import org.openjdk.jmh.annotations.*;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class StackBenchmark {

    @Benchmark
    public Integer measureJdkStackPush(Data data) {
        return data.jdkStack.push(data.integer);
    }

    @Benchmark
    public Integer measureIdeaStackPush(Data data) {
        return data.ideaStack.push(data.integer);
    }

    @Benchmark
    public Integer measureJdkStackPeek(Data data) {
        return data._jdkStack.peek();
    }

    @Benchmark
    public Integer measureIdeaStackPeek(Data data) {
        return data._ideaStack.peek();
    }

    @State(Scope.Thread)
    public static class Data {
        //stacks to measure peek()
        Stack<Integer> _jdkStack;
        IdeaStack<Integer> _ideaStack;

        Stack<Integer> jdkStack;
        IdeaStack<Integer> ideaStack;
        Integer integer = 1;

        @Setup
        public void setup() {
            _jdkStack = new Stack<>();
            _jdkStack.push(integer);

            _ideaStack = new IdeaStack<>();
            _ideaStack.push(integer);
        }

        @Setup(Level.Iteration)
        public void setupIteration() {
            jdkStack = new Stack<>();
            ideaStack = new IdeaStack<>();
        }
    }
}
