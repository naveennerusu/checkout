package com.xyz.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.xyz.product.ProductPricing;

/**
 * This class is responsible for starting the program. It takes inputs from
 * console and checkout the items entered.
 * 
 *
 */
public class StartProgram {

    public static void main(String[] args) {

	// Creating Scanner instance to scan console for User input
	Scanner console = new Scanner(System.in);
	int count = 0;

	ProductPricing productPricing = null;
	System.out.println("Do you want to enter new pricing rules? YES/NO ");
	String input = console.nextLine();
	if (input.equalsIgnoreCase("YES")){
	    // get pricing of products
	    productPricing = savePricingDetails(console);
	}else if (input.equalsIgnoreCase("NO")){
	    System.out.println("Default pricing apply");
	    productPricing = new ProductPricing();
	}else{
	    System.out.println("Invalid input start again.");
	    return;
	}
	// Similarly we can add discounted products and offers like B 5 for 3
	// with some additional logic and can be stored in productPricing.

	while(true){
	    System.out.println((count > 0?" Start over ":"") + "Enter valid checkout items.");
	    count++;

	    String checkoutITems = console.nextLine();

	    if (checkoutITems.trim().length() != 0){
		// start the thread to checkout items
		RetailStore task = new RetailStore(checkoutITems.toUpperCase(), productPricing);
		Thread t = new Thread(task);
		t.start();

		// wait for Retailstore to complete.
		try{
		    t.join();
		    System.out.println("Finished checkout items.");
		}catch(InterruptedException e){
		    System.err.println("Exception " + e.getMessage());
		}

		// continue checkout for 5 times.
		if (count == 5){
		    System.exit(0);
		}

	    }else{
		System.out.println("Enter valid checkout items.Try again.");
	    }
	}

    }

    /**
     * THis method store prices for products to bean ProductPricing.
     * 
     * @param console
     * @return
     */
    private static ProductPricing savePricingDetails(Scanner console) {

	Map<Character, String> pricing = new HashMap<Character, String>();
	System.out.println("Enter Price of A:");
	String priceA = console.nextLine();
	pricing.put('A', priceA);
	System.out.println("Price of A:" + priceA);
	System.out.println("Enter Price of B:");
	String priceB = console.nextLine();
	pricing.put('B', priceB);
	System.out.println("Price of B:" + priceB);
	System.out.println("Enter Price of C:");
	String priceC = console.nextLine();
	System.out.println("Price of C:" + priceC);
	pricing.put('C', priceC);

	ProductPricing productPricing = new ProductPricing();
	productPricing.setPricing(pricing);
	// PricingService.set(productPricing);

	return productPricing;

    }
}
