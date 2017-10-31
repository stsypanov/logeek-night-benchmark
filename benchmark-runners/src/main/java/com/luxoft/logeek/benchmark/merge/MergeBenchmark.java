package com.luxoft.logeek.benchmark.merge;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.entity.ChildEntity;
import com.luxoft.logeek.entity.SimpleEntity;
import com.luxoft.logeek.repository.SimpleRepository;
import com.luxoft.logeek.service.SavingService;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class MergeBenchmark {

    @Benchmark
    public SimpleEntity measureModifyWithoutCallingSave(BmState state) {
        int i = state.i++;
        String newName = i + "";

        return state.service.modifyWithoutCallingSave(state.id, newName);
    }

    @Benchmark
    public SimpleEntity measureModifyCallingSave(BmState state) {
        int i = state.i++;
        String newName = i + "";

        return state.service.modifyCallingSave(state.id, newName);
    }

    @State(Scope.Thread)
    public static class BmState extends ContextAwareBenchmark {
        SavingService service;

        Long id = 123L;
        int i;

        @Setup
        public void setup() {
            super.init();
            service = getBean(SavingService.class);

            SimpleEntity parent = new SimpleEntity(id, "azaza");

            Arrays.asList(
                    new ChildEntity(11L),
                    new ChildEntity(12L),
                    new ChildEntity(13L),
                    new ChildEntity(14L),
                    new ChildEntity(15L),
                    new ChildEntity(16L),
                    new ChildEntity(17L),
                    new ChildEntity(18L),
                    new ChildEntity(19L),
                    new ChildEntity(20L)
            ).forEach(parent::addChild);

            getBean(SimpleRepository.class).save(parent);

            i = 100000;
        }

        @TearDown
        public void tearDown() {
            context.close();
        }
    }
}
