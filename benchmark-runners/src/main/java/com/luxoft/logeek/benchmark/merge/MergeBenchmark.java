package com.luxoft.logeek.benchmark.merge;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.entity.ChildEntity;
import com.luxoft.logeek.entity.SimpleEntity;
import com.luxoft.logeek.repository.ChildRepository;
import com.luxoft.logeek.repository.SimpleRepository;
import com.luxoft.logeek.service.SavingService;
import org.openjdk.jmh.annotations.*;

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

            ChildEntity child = new ChildEntity();
            child.setId(11L);
            getBean(ChildRepository.class).save(child);

            SimpleEntity entity = new SimpleEntity(id, "azaza", child);
            getBean(SimpleRepository.class).save(entity);

            i = 100000;
        }

        @TearDown
        public void tearDown() {
            context.close();
        }
    }
}
