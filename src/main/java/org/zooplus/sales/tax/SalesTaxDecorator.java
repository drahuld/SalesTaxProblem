package org.zooplus.sales.tax;

import java.math.BigDecimal;

public class SalesTaxDecorator implements SalesTax {

    protected SalesTax salesTax;

    public SalesTaxDecorator(SalesTax salesTax){
        this.salesTax = salesTax;
    }

    @Override
    public BigDecimal calculateTax() {
        return this.salesTax.calculateTax();
    }

    @Override
    public String getTaxType() {
        return "SaleTaxDecorator";
    }
}
