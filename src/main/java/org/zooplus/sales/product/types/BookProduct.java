package org.zooplus.sales.product.types;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.zooplus.sales.product.ProductCart;
import org.zooplus.sales.product.ProductCost;
import org.zooplus.sales.tax.Tax;

@Getter
@Setter
@RequiredArgsConstructor
public class BookProduct implements Product {

    @NonNull
    private Item item;
    @NonNull
    private Tax tax;

    @Override
    public ProductCost addToCart(ProductCart productCart) {
        return productCart.visit(this);
    }

    @Override
    public String getProductCategory() {
        return "Book";
    }
}
