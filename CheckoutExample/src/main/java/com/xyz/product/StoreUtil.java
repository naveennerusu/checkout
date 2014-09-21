package com.xyz.product;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class StoreUtil {

    private static StoreUtil storeUtil = new StoreUtil();

    private StoreUtil() {
	// do nothing
    }

    public static StoreUtil getInstance() {

	if (storeUtil == null){
	    return new StoreUtil();
	}

	return storeUtil;
    }

    /**
     * This method applies the discount and return products count to charge.
     * 
     * @param productCount
     * @return
     */
    private int applyOffers(int productCount, ProductPricing productPricing) {

	System.out.println("Applied discount " + productPricing.getOfferPricing()[0][0] + " itmes for price of "
		+ productPricing.getOfferPricing()[0][1]);

	// formula for applying discount is applied and returns the actual items
	// that need to be charged.
	int remainder = productCount % productPricing.getOfferPricing()[0][0];

	int quotient = productCount / productPricing.getOfferPricing()[0][0];

	int itemsCounttoCharge = (quotient * productPricing.getOfferPricing()[0][1]) + remainder;
	return itemsCounttoCharge;
    }

    /**
     * This method calculates total after discount.
     * 
     * @param counts
     * @return total
     */
    public int calculateTotal(Map<Character, Integer> counts, ProductPricing productPricing) {

	int total = 0;
	Map<Character, String> pricingMap = new HashMap<Character, String>();

	// for each product calculate price
	for(Entry<Character, Integer> entry: counts.entrySet()){

	    if (productPricing.getPricing().isEmpty()){
		pricingMap = productPricing.getDefaultPricing();
	    }else{
		pricingMap = productPricing.getPricing();
	    }

	    int productCount = entry.getValue();

	    Character procutkey = entry.getKey();

	    if (pricingMap.containsKey(procutkey)){

		// apply discount if offer exists
		if (productPricing.getDiscountedProducts().contains(procutkey)){
		    productCount = applyOffers(productCount, productPricing);
		}

		total = total + Integer.parseInt(pricingMap.get(procutkey)) * productCount;

	    }else{
		System.out.println("Invalid Product " + procutkey + " " + productCount + " items");
	    }

	}

	return total;
    }

}
