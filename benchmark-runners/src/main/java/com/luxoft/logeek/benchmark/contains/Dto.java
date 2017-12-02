package com.luxoft.logeek.benchmark.contains;

class Dto {
    private final int integer;

    Dto(int integer) {
        this.integer = integer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dto dto = (Dto) o;

        return integer == dto.integer;
    }

    @Override
    public int hashCode() {
        return integer;
    }
}
