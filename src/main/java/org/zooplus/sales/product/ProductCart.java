package org.zooplus.sales.product;

import org.zooplus.sales.product.types.BeautyProduct;
import org.zooplus.sales.product.types.BookProduct;
import org.zooplus.sales.product.types.FoodProduct;
import org.zooplus.sales.product.types.MedicalProduct;
import org.zooplus.sales.product.types.MusicProduct;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public interface ProductCart {

    ProductCost visit(BookProduct bookProduct);

    ProductCost visit(BeautyProduct beautyProduct);

    ProductCost visit(FoodProduct foodProduct);

    ProductCost visit(MedicalProduct medicalProduct);

    ProductCost visit(MusicProduct musicProduct);

    private static BigDecimal calculateProductCostBeforeTax(int quantity, BigDecimal price) {
        var totalCost = new BigDecimal(BigInteger.ZERO);
        var itemCost = price.multiply(BigDecimal.valueOf(quantity));
        totalCost = totalCost.add(itemCost);
        return totalCost;
    }

    private static BigDecimal calculateTaxAmount(BigDecimal amount, BigDecimal percentage) {
        var result = amount.multiply(percentage).divide(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
        return round(result, new BigDecimal("0.05"), RoundingMode.UP);
    }

    private static BigDecimal round(BigDecimal value, BigDecimal increment,
                                   RoundingMode roundingMode) {
        var divided = value.divide(increment, 0, roundingMode);
        var result = divided.multiply(increment);
        return result;
    }

    static ProductCost calculateProductCostAfterTax(int quantity, BigDecimal price, BigDecimal taxPercentage) {
        var totalPrice = calculateProductCostBeforeTax(quantity, price);
        var taxAmount = calculateTaxAmount(totalPrice, taxPercentage);
        return new ProductCost("", quantity, totalPrice.add(taxAmount), taxAmount);
    }

}
