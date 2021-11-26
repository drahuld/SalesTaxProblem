package org.zooplus.sales.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductCost {

    private String name;

    private int quantity;

    private BigDecimal totalCostAfterTax;

    private BigDecimal taxAmount;
}
