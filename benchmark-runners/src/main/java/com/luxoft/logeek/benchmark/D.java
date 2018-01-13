package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.entity.SomeEntity;
import com.luxoft.logeek.repository.SomeJpaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//todo remove at home
public class D {
    private static SomeJpaRepository repository;

    public static void main(String[] args) {
        new D().foo(1L,  true);
    }

    public void foo(Long id, boolean smth) {
        List<SomeEntity> movedIn = Collections.singletonList(repository.findOne(id));

        List<SomeEntity> movedOut = new ArrayList<>();

        if (smth) {
            bar(movedIn, movedOut);
        }

        bar(movedIn);
    }

    private void bar(List<SomeEntity> movedIn) {

    }

    private void bar(List<SomeEntity> movedIn, List<SomeEntity> movedOut) {

    }
}
