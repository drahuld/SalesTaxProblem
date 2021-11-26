package org.zooplus.sales.tax;

import java.math.BigDecimal;

public class ImportDutySalesTax extends SalesTaxDecorator {

    // TODO: should come from cache object or db
    public static final BigDecimal IMPORT_DUTY_SALES_TAX_PERCENTAGE = new BigDecimal(5);

    public ImportDutySalesTax(SalesTax salesTax) {
        super(salesTax);
    }

    @Override
    public BigDecimal calculateTax() {
        return IMPORT_DUTY_SALES_TAX_PERCENTAGE.add(super.calculateTax());
    }
}
