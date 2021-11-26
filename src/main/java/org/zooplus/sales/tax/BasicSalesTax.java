package org.zooplus.sales.tax;

import java.math.BigDecimal;

public class BasicSalesTax extends SalesTaxDecorator {

    // TODO: should come from cache object or db
    public static final BigDecimal BASIC_SALES_TAX_PERCENTAGE = new BigDecimal(10);

    public BasicSalesTax(SalesTax salesTax) {
        super(salesTax);
    }

    @Override
    public BigDecimal calculateTax() {
        return BASIC_SALES_TAX_PERCENTAGE.add(super.calculateTax());
    }
}
