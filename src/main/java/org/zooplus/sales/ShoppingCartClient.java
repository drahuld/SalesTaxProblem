package org.zooplus.sales;

import org.zooplus.sales.checkout.ProductFactory;
import org.zooplus.sales.checkout.ProductPurchaseFacade;

import java.util.stream.Collectors;

public class ShoppingCartClient {

    public static void main(String[] args) {

        System.out.println("************** OUTPUT START *********************");

        // Read the input from input file
        var itemList = ProductPurchaseFacade.createItemListFromInputFile(args);

        if (itemList != null) {
            // Purchase products
            var purchaseProductList = itemList.stream()
                    .map(item -> new ProductFactory().purchase(item)).collect(Collectors.toList());
            if (purchaseProductList != null) {
                // Order & Print Receipt
                ProductPurchaseFacade.orderAndPrintReceipt(purchaseProductList);
            }
        }
        System.out.println("************** OUTPUT START *********************");

    }
}
