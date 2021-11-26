package org.zooplus.sales.checkout;

import org.apache.commons.lang3.StringUtils;
import org.zooplus.sales.product.types.BeautyProduct;
import org.zooplus.sales.product.types.BookProduct;
import org.zooplus.sales.product.types.FoodProduct;
import org.zooplus.sales.product.types.Item;
import org.zooplus.sales.product.types.MedicalProduct;
import org.zooplus.sales.product.types.MusicProduct;
import org.zooplus.sales.product.types.Product;
import org.zooplus.sales.tax.BaseSalesTax;
import org.zooplus.sales.tax.BasicSalesTax;
import org.zooplus.sales.tax.ImportDutySalesTax;
import org.zooplus.sales.tax.Tax;
import org.zooplus.sales.util.ProductCategory;

import java.util.HashMap;
import java.util.Map;

public class ProductFactory {

    private final static String IMPORT_STR = "import";

    // Normally should come from cache or Database
    private final static Map<ProductCategory, Boolean> exemptedProductCategoryMap = new HashMap<>();

    static {
        exemptedProductCategoryMap.put(ProductCategory.BOOK, true);
        exemptedProductCategoryMap.put(ProductCategory.BEAUTY, false);
        exemptedProductCategoryMap.put(ProductCategory.FOOD, true);
        exemptedProductCategoryMap.put(ProductCategory.MEDICAL, true);
        exemptedProductCategoryMap.put(ProductCategory.MUSIC, false);
        exemptedProductCategoryMap.put(ProductCategory.OTHERS, false);
    }

    public Product purchase(Item item) {
        Item itemWithProductCategory = assignProductTypeToProducts(item);

        if (itemWithProductCategory.getProductCategory() == ProductCategory.BOOK) {
            return new BookProduct(item, applyTaxPercentageOnProduct(item.isImported(), item.getProductCategory()));
        }

        if (itemWithProductCategory.getProductCategory() == ProductCategory.BEAUTY) {

            return new BeautyProduct(item, applyTaxPercentageOnProduct(item.isImported(), item.getProductCategory()));
        }

        if (itemWithProductCategory.getProductCategory() == ProductCategory.FOOD) {
            return new FoodProduct(item, applyTaxPercentageOnProduct(item.isImported(), item.getProductCategory()));
        }

        if (itemWithProductCategory.getProductCategory() == ProductCategory.MEDICAL) {
            return new MedicalProduct(item, applyTaxPercentageOnProduct(item.isImported(), item.getProductCategory()));
        }

        if (itemWithProductCategory.getProductCategory() == ProductCategory.MUSIC) {
            return new MusicProduct(item, applyTaxPercentageOnProduct(item.isImported(), item.getProductCategory()));
        }

        if (itemWithProductCategory.getProductCategory() == ProductCategory.OTHERS) {
            // return new BookProduct(item, applyTaxPercentageOnProduct(item.isImported(), item.getProductCategory()));
        }

        return null;
    }

    private Item assignProductTypeToProducts(Item item) {
        if (item != null) {
            if (StringUtils.containsIgnoreCase(item.getName(), IMPORT_STR)) {
                item.setImported(true);
            }
            item.setProductCategory(ProductCategory.ifContainsIgnoreCase(item.getName()));
        }
        return item;
    }

    private Tax applyTaxPercentageOnProduct(boolean isImported, ProductCategory productCategory) {
        boolean isExempted = exemptedProductCategoryMap.get(productCategory);

        // Exempted Basic Tax + import tax (0 + 5)
        if (isExempted && isImported) {
            return new ImportDutySalesTax(new BaseSalesTax());
        }

        // Non-exempted Basic Tax + Import (10 + 5)
        if (!isExempted && isImported) {
            return new BasicSalesTax(new ImportDutySalesTax(new BaseSalesTax()));
        }

        // Non-exempted Basic Tax (10)
        if (!isExempted) {
            return new BasicSalesTax(new BaseSalesTax());
        }

        return new BaseSalesTax();
    }

}
