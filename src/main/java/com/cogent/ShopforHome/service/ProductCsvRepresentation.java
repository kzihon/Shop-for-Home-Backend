package com.cogent.ShopforHome.service;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCsvRepresentation {
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "name")
    private double price;
    @CsvBindByName(column = "name")
    private String description;
    @CsvBindByName(column = "name")
    private String category;
    @CsvBindByName(column = "name")
    private int numberInStock;
    @CsvBindByName(column = "name")
    private String supplierName;
}
