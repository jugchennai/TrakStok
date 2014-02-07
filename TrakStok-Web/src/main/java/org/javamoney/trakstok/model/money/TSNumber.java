package org.javamoney.trakstok.model.money;

import java.math.BigDecimal;

import javax.money.NumberValue;

public class TSNumber extends NumberValue {

	BigDecimal value;
	
	
	public TSNumber(BigDecimal value) {
		this.value = value;
	}
	
	@Override
	public Class<?> getNumberType() {
		
		return BigDecimal.class;
	}

	@Override
	public int getPrecision() {
		
		return 0;
	}

	@Override
	public int getScale() {
		
		return 0;
	}

	@Override
	public int intValueExact() {
		
		return value.intValueExact();
	}

	@Override
	public long longValueExact() {
		
		return value.longValueExact();
	}

	@Override
	public double doubleValueExact() {
		
		return value.doubleValue();
	}

	@Override
	public <T extends Number> T numberValue(Class<T> numberType) {
		
		return null;
	}

	@Override
	public <T extends Number> T numberValueExact(Class<T> numberType) {
		
		return null;
	}

	@Override
	public double doubleValue() {
		
		return value.doubleValue();
	}

	@Override
	public float floatValue() {
		
		return value.floatValue();
	}

	@Override
	public int intValue() {
		
		return value.intValue();
	}

	@Override
	public long longValue() {
		
		return value.longValue();
	}


}
