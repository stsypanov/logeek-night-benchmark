package com.luxoft.logeek.benchmark.collection;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.dto.UserDto;
import com.luxoft.logeek.entity.User;
import com.luxoft.logeek.repository.UserRepository;
import com.luxoft.logeek.service.ltav.UserService;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toCollection;

/**
 * Measure retrieving collection of entities in loop one by one
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms4g", "-Xmx4g"})
public class LoopVsSingleCallBenchmark {

    @Benchmark
    public Set<User> findInLoop(Data data) {
        return data.service.findInLoop(data.dtos);
    }

    @Benchmark
    public List<User> findWithSingleCall(Data data) {
        return data.service.findWithSingleCall(data.dtos);
    }

    @State(Scope.Thread)
    public static class Data extends ContextAwareBenchmark {
        @Param({"10", "100", "1000"})
        private int entityCount;

        private UserRepository repository;
        private UserService service;

        private ArrayList<UserDto> dtos;

        @Setup
        public void init() {
            super.init();
            service = context.getBean(UserService.class);
            repository = context.getBean(UserRepository.class);
            populateTable();
        }

        @Setup(Level.Iteration)
        public void initIds() {
            List<Long> ids = repository.findAllIds();
            Collections.shuffle(ids);//different id sequence for every iteration

            dtos = ids.stream().map(UserDto::new).collect(toCollection(ArrayList::new));
        }

        @TearDown
        public void tearDown() {
            repository.deleteAllInBatch();
            context.close();
        }

        private void populateTable() {
            ArrayList<User> users = new ArrayList<>(entityCount);

            for (long i = 1; i < entityCount + 1; i++) {
                User user = new User(
                        i,
                        "sergei" + i,
                        i + "sergei",
                        "sergei" + i + "@yandex.ru",
                        "root"
                );
                users.add(user);
            }

            repository.save(users);
        }
    }
}
