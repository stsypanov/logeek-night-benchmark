package com.luxoft.logeek.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class SomeData {
    private long id;
    private String someName;
    private List someChildProperties;
    private String anotherString;
    private int count;
    private LocalDate localDate;
    private Date date;

}
