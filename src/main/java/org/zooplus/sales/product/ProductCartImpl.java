package org.zooplus.sales.product;

import org.zooplus.sales.product.types.BeautyProduct;
import org.zooplus.sales.product.types.BookProduct;
import org.zooplus.sales.product.types.FoodProduct;
import org.zooplus.sales.product.types.MedicalProduct;
import org.zooplus.sales.product.types.MusicProduct;

public class ProductCartImpl implements ProductCart {

    @Override
    public ProductCost visit(BookProduct bookProduct) {
        var item = bookProduct.getItem();
        var productCost = ProductCart.calculateProductCostAfterTax(item.getQuantity(), item.getPrice(),
                bookProduct.getTax().calculateTax());
        productCost.setName(item.getName());
        return productCost;
    }

    @Override
    public ProductCost visit(BeautyProduct beautyProduct) {
        var item = beautyProduct.getItem();
        var productCost = ProductCart.calculateProductCostAfterTax(item.getQuantity(), item.getPrice(),
                beautyProduct.getTax().calculateTax());
        productCost.setName(item.getName());
        return productCost;
    }

    @Override
    public ProductCost visit(FoodProduct foodProduct) {
        var item = foodProduct.getItem();
        var productCost = ProductCart.calculateProductCostAfterTax(item.getQuantity(), item.getPrice(),
                foodProduct.getTax().calculateTax());
        productCost.setName(item.getName());
        return productCost;
    }

    @Override
    public ProductCost visit(MedicalProduct medicalProduct) {
        var item = medicalProduct.getItem();
        var productCost = ProductCart.calculateProductCostAfterTax(item.getQuantity(), item.getPrice(),
                medicalProduct.getTax().calculateTax());
        productCost.setName(item.getName());
        return productCost;
    }

    @Override
    public ProductCost visit(MusicProduct musicProduct) {
        var item = musicProduct.getItem();
        var productCost = ProductCart.calculateProductCostAfterTax(item.getQuantity(), item.getPrice(),
                musicProduct.getTax().calculateTax());
        productCost.setName(item.getName());
        return productCost;
    }
}
