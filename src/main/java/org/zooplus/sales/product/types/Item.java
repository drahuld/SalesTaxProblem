package org.zooplus.sales.product.types;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.zooplus.sales.util.ProductCategory;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class Item {

    @NonNull
    private String name;
    @NonNull
    private int quantity;
    @NonNull
    private BigDecimal price;

    private ProductCategory productCategory;

    private boolean isImported;
}
