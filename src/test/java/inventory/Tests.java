package inventory;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class Tests {

	private static final String REG_EX = "0\\.0[1-9]|0\\.[1-9](\\d)?|[1-9]\\d{0,3}(\\.\\d{1,2})?";
	
	@Test
	public void regexTest() {
		verifyTrue("9999.99");
		verifyTrue("9000");
		verifyTrue("0.01");
		verifyTrue("0.1");
		verifyTrue("3.8");
		verifyFalse("9999.991");
		verifyFalse("10000");
		verifyFalse("0.0155");
		verifyFalse("0");
	}	
	
	private void verifyTrue(String val) {
		BigDecimal price = new BigDecimal(val);
		System.out.println(price.toString());
		assertTrue(price.toString().matches(REG_EX));
	}
	
	private void verifyFalse(String val) {
		BigDecimal price = new BigDecimal(val);
		System.out.println(price.toString());
		assertFalse(price.toString().matches(REG_EX));
	}
		
}
