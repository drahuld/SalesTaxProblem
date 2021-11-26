package org.zooplus.sales.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.function.Predicate;

public enum ProductCategory {

    BOOK("book"),
    BEAUTY("perfume"),
    FOOD("chocolate"),
    MEDICAL("headache;pills"),
    MUSIC("music"),
    OTHERS("");

    private final static String SEMI_COLON = ";";

    private final String category;

    ProductCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public static ProductCategory ifContainsIgnoreCase(final String itemName) {

        // check if item name matched with Enum value.
        Predicate<String> checkIfItemNameMatchWithEnumValuePredicate = enumStr -> StringUtils.containsIgnoreCase(itemName, enumStr);

        // split and check if anyone match with enum.
        Predicate<ProductCategory> checkIfItemNameMatchWithSplittedEnumValuePredicate =
                productCategoryEnum -> Arrays.stream(productCategoryEnum.getCategory().split(SEMI_COLON))
                        .anyMatch(checkIfItemNameMatchWithEnumValuePredicate);

        // if semicolon exist in enum then check if any match with item name
        Predicate<ProductCategory> itemNameMatchedPredicate = productCategoryEnum -> {
            if (productCategoryEnum.getCategory().indexOf(SEMI_COLON) != -1) {
                return checkIfItemNameMatchWithSplittedEnumValuePredicate.test(productCategoryEnum);
            } else {
                return checkIfItemNameMatchWithEnumValuePredicate.test(productCategoryEnum.getCategory());
            }
        };

        return Arrays.stream(ProductCategory.values())
                .filter(itemNameMatchedPredicate)
                .findFirst().orElse(ProductCategory.OTHERS);
    }

}
