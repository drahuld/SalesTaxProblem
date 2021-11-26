package org.zooplus.sales.checkout;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.zooplus.sales.product.ProductCartImpl;
import org.zooplus.sales.product.types.Item;
import org.zooplus.sales.product.types.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPurchaseFacade {

    private static final String INPUT_FILE_OPTION = "i";

    public static void orderAndPrintReceipt(List<Product> productList) {
        var productCart = new ProductCartImpl();
        var finalSum = new BigDecimal(0);
        var salesTaxesSum = new BigDecimal(0);
        for (Product product : productList) {
            var productCost = product.addToCart(productCart);
            System.out.printf("%d %s : %s%n", productCost.getQuantity(), productCost.getName(), productCost.getTotalCostAfterTax());
            salesTaxesSum = salesTaxesSum.add(productCost.getTaxAmount());
            finalSum = finalSum.add(productCost.getTotalCostAfterTax());
        }
        System.out.printf("Sales Taxes: %s%n", salesTaxesSum); ;
        System.out.printf("Total: %s%n", finalSum); ;
    }

    public static List<Item> createItemListFromInputFile(String[] args) {
        try {
            String[] inputArray = readInputFileFromPath(args);
            if (inputArray != null && inputArray.length > 0) {
                return Arrays.stream(inputArray).skip(1).map(inputLine -> {
                    var quantity = inputLine.substring(0, inputLine.indexOf(" "));
                    var nameAndPrice = inputLine.substring(inputLine.indexOf(" ") + 1);
                    var name = nameAndPrice.substring(0, nameAndPrice.lastIndexOf(" "));
                    var price = nameAndPrice.substring(nameAndPrice.lastIndexOf(" ") + 1);
                    return new Item(name.strip(), Integer.parseInt(quantity.strip()), new BigDecimal(price.strip()));
                }).collect(Collectors.toList());
            }
        } catch (IOException | ParseException e) {
            System.out.printf("unable to parse arguments: %s%n", e.getMessage());
            System.exit(0);
        }
        return null;
    }

    private static CommandLine getCommandLine(String[] args) throws ParseException {
        var options = defineOptions();
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    private static String[] readInputFileFromPath(String[] args) throws ParseException, IOException {
        var inputStream = getOptionStream(getCommandLine(args), INPUT_FILE_OPTION);
        var test = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return test.split("\n");
    }

    private static Options defineOptions() {
        var inputFile = Option.builder(INPUT_FILE_OPTION)
                .required()
                .hasArg()
                .longOpt("inputFile")
                .desc("the input file path")
                .build();
        final Options options = new Options();
        options.addOption(inputFile);
        return options;
    }

    private static InputStream getOptionStream(CommandLine commandLine, String option) throws FileNotFoundException {
        var path = Paths.get(commandLine.getOptionValue(option));
        return new FileInputStream(path.toFile());
    }
}
