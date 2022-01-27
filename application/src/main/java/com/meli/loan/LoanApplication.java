/**
 * 
 */
package com.meli.loan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main class.
 * @author diana.ciro
 * 
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.meli.loan")
public class LoanApplication {

	/**
	 * @param args Entry point
	 */
	public static void main(String[] args) {
		new SpringApplicationBuilder(LoanApplication.class)
				.run(args);
	}

}
