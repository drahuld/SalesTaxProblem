package org.zooplus.sales.tax;

import java.math.BigDecimal;

public class BaseSalesTax implements SalesTax {

    // TODO: should come from cache object or db
    public static final BigDecimal EXEMPTED_SALES_TAX_PERCENTAGE = new BigDecimal(0);

    @Override
    public BigDecimal calculateTax() {
        return EXEMPTED_SALES_TAX_PERCENTAGE;
    }

    @Override
    public String getTaxType() {
        return "BaseTax";
    }
}
