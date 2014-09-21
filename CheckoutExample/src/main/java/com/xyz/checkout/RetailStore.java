package com.xyz.checkout;

import java.util.LinkedHashMap;
import java.util.Map;

import com.xyz.product.ProductPricing;
import com.xyz.product.StoreUtil;

/**
 * This thread is responsible for check out times at store.
 * 
 *
 */
public class RetailStore implements Supermarket, Runnable {

    private String input;
    private ProductPricing productPricing;

    public RetailStore(String input, ProductPricing productPricing) {
	super();
	this.input = input;
	this.productPricing = productPricing;
    }

    /**
     * This method checkout the given products.
     */
    @Override
    public int checkout(String items) throws Exception {

	Map<Character, Integer> counts = new LinkedHashMap<Character, Integer>(getInput().length());
	// iterate over the char array and count similar items count
	for(char c: items.toCharArray()){
	    counts.put(c, counts.containsKey(c)?counts.get(c) + 1:1);
	}

	StoreUtil util = StoreUtil.getInstance();

	return util.calculateTotal(counts, getProductPricing());
    }

    @Override
    public void run() {
	// System.out.println(Thread.currentThread().getName() +
	// " input entered is " + getInput());
	int total = 0;
	try{
	    total = checkout(getInput());
	}catch(Exception e){
	    e.printStackTrace();
	}
	System.out.println("Total amount === " + total);
    }

    public String getInput() {
	return input;
    }

    public void setInput(String input) {
	this.input = input;
    }

    public ProductPricing getProductPricing() {
	return productPricing;
    }

    public void setProductPricing(ProductPricing productPricing) {
	this.productPricing = productPricing;
    }

}
