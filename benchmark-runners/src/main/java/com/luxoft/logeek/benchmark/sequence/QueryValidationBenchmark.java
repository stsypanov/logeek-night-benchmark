package com.luxoft.logeek.benchmark.sequence;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import org.openjdk.jmh.annotations.*;

import javax.persistence.EntityManager;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class QueryValidationBenchmark extends ContextAwareBenchmark {

    private EntityManager entityManager;

    @Setup
    public void init() {
        super.init();
        entityManager = context.getBean(EntityManager.class);
    }

    @Benchmark
    public Object measureQuery() {
        return validateQueryDefault();
    }

    private Object validateQueryDefault() {
        EntityManager validatingEm = null;

        try {
            validatingEm = entityManager.getEntityManagerFactory().createEntityManager();
            return validatingEm.createQuery("select r from CpyEntity r ");

        } catch (RuntimeException e) {
            throw new IllegalArgumentException("", e);
        } finally {
            if (validatingEm != null) {
                validatingEm.close();
            }
        }
    }

}
