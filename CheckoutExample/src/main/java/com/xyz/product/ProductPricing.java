package com.xyz.product;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ProductPricing {
    
    private Map<Character,String> pricing = new HashMap<Character, String>();
    
    private static Map<Character,String> defaultPricing = new HashMap<Character, String>();
    
    private static Set<Character> discountedProducts = new HashSet<Character>();
    private static int[][] offerPricing;
    
    
    static{
	defaultPricing.put('A', "20");
	defaultPricing.put('B', "50");
	defaultPricing.put('C', "30");
	discountedProducts.add('B');
	setOfferPricing(new int[][] {{5, 3 } });
    }
    
    public Map<Character, String> getDefaultPricing() {
        return defaultPricing;
    }

    public Map<Character, String> getPricing() {
        return pricing;
    }

    
    public void setPricing(Map<Character, String> pricing) {
        this.pricing = pricing;
    }
    
    public Set<Character> getDiscountedProducts() {
        return discountedProducts;
    }

    
    public void setDiscountedProducts(Set<Character> discountedProducts) {
	ProductPricing.discountedProducts = discountedProducts;
    }

    
    public int[][] getOfferPricing() {
	return offerPricing;
    }

    public static void setOfferPricing(int[][] offerPricing) {
	ProductPricing.offerPricing = offerPricing;
    }
    
}
