package com.luxoft.logeek.benchmark.merge;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.entity.SimpleEntity;
import com.luxoft.logeek.repository.SimpleRepository;
import com.luxoft.logeek.service.SavingService;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;


@Fork(value = 5, jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
@State(Scope.Benchmark)
@Warmup(iterations = 15)
@Measurement(iterations = 100)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class MergeBenchmark extends ContextAwareBenchmark {
    private SavingService service;

    private Long id = 123L;
    private int i;

    @Setup
    public void setup() {
        super.init();
        SimpleRepository repository = getBean(SimpleRepository.class);
        service = getBean(SavingService.class);

        SimpleEntity entity = new SimpleEntity();
        entity.setId(id);
        entity.setName("azaza");

        repository.save(entity);

        i = random.nextInt();
    }

    @Benchmark
    public SimpleEntity measureModifyWithoutCallingSave() {
        int i = this.i++;
        String newName = i + "";

        return service.modifyWithoutCallingSave(id, newName);
    }

    @Benchmark
    public SimpleEntity measureModifyCallingSave() {
        int i = this.i++;
        String newName = i + "";

        return service.modifyCallingSave(id, newName);
    }

    @TearDown
    public void tearDown() {
        context.close();
    }
}
