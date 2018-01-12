package org.musante.calc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellCalcTest {

	@Test
	void test() {
		CellCalc cc = new CellCalc();
		assertNotNull(cc);
	}
	
	@Test
	void canConvertANumber() {
		CellCalc cc = new CellCalc();
		assertEquals(42, cc.calculate("42"));
		assertEquals(73, cc.calculate("73"));
	}
	
	@Test
	void canAddTwoNumbers( ) {
		CellCalc cc = new CellCalc();
		assertEquals(123, cc.calculate("100 + 23"));
	}
	
	@Test
	void canSubtractTwoNumbers() {
		CellCalc cc = new CellCalc();
		assertEquals(42, cc.calculate("50-8"));
	}

	@Test
	void canMultiplyTwoNumbers() {
		CellCalc cc = new CellCalc();
		assertEquals(75, cc.calculate("15 * 5"));
	}
	
	@Test
	void canDivideTwoNumbers() {
		CellCalc cc = new CellCalc();
		assertEquals(2, cc.calculate("10/5"));
	}
	
	@Test
	void canHandleSinglePositiveNumber() {
		CellCalc cc = new CellCalc();
		assertEquals(99, cc.calculate("99"));
	}
	
	@Test
	void canHandleSingleNegativeNumber() {
		CellCalc cc = new CellCalc();
		assertEquals(-37, cc.calculate("-37"));
	}
	
	@Test
	void canAddTwoNegativeNumbers() {
		CellCalc cc = new CellCalc();
		assertEquals(-20, cc.calculate("-13 + -7"));
	}
	
	@Test
	void canHandleDecimalNumbers() {
		CellCalc cc = new CellCalc();
		assertEquals(-53.5, cc.calculate("-56+2.5"));
	}
	
	@Test
	void canAddMoreThanTwoNumbers() {
		CellCalc cc = new CellCalc();
		assertEquals(100, cc.calculate("25 + 50 + 25"));
	}
	
	@Test
	void multiplicationTakesPrecedence() {
		CellCalc cc = new CellCalc();
		assertEquals(20, cc.calculate("4+4*4"));
		assertEquals(20, cc.calculate("4*4+4"));
		assertEquals(32, cc.calculate("4*4+4*4"));
	}
	
	@Test
	void cellValueReturnsLiteralWithoutEqualSign() {
		CellCalc cc = new CellCalc();
		assertEquals("78+23", cc.cellValue("78+23"));
	}
	
	@Test
	void cellValueReturnsCalculationWithEqualSign() {
		CellCalc cc = new CellCalc();
		assertEquals("18", cc.cellValue("=9*2"));
	}
}
