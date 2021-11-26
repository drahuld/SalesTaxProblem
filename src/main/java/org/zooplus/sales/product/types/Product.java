package org.zooplus.sales.product.types;

import org.zooplus.sales.product.ProductCart;
import org.zooplus.sales.product.ProductCost;

public interface Product {

    ProductCost addToCart(ProductCart productCart);

    String getProductCategory();
}
