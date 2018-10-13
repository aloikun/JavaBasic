package com.loikun.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String JSBN;
    private String name;
    private float price;
}
