package org.javamoney.trakstok.model.money;



import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;
import javax.money.MonetaryContext;
import javax.money.MonetaryOperator;
import javax.money.MonetaryQuery;
import javax.money.NumberValue;

import org.javamoney.trakstok.model.Currency;

public class TSAmount implements MonetaryAmount {

	TSNumber amount;
	Currency currency;
	
	public TSAmount(TSNumber amount) {
		this.amount = amount;
	}

	public TSAmount(TSNumber amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	@Override
	public CurrencyUnit getCurrency() {
		return currency;
	}

	@Override
	public MonetaryContext getMonetaryContext() {

		return null;
	}

	@Override
	public NumberValue getNumber() {

		return amount;
	}

	@Override
	public <R> R query(MonetaryQuery<R> query) {

		return null;
	}

	@Override
	public MonetaryAmount with(MonetaryOperator operator) {

		return null;
	}

	@Override
	public MonetaryAmountFactory<? extends MonetaryAmount> getFactory() {

		return null;
	}

	@Override
	public boolean isGreaterThan(MonetaryAmount amount) {

		return false;
	}

	@Override
	public boolean isGreaterThanOrEqualTo(MonetaryAmount amount) {

		return false;
	}

	@Override
	public boolean isLessThan(MonetaryAmount amount) {

		return false;
	}

	@Override
	public boolean isLessThanOrEqualTo(MonetaryAmount amt) {

		return false;
	}

	@Override
	public boolean isEqualTo(MonetaryAmount amount) {

		return false;
	}

	@Override
	public boolean isNegative() {

		return false;
	}

	@Override
	public boolean isNegativeOrZero() {

		return false;
	}

	@Override
	public boolean isPositive() {

		return false;
	}

	@Override
	public boolean isPositiveOrZero() {

		return false;
	}

	@Override
	public boolean isZero() {

		return false;
	}

	@Override
	public int signum() {

		return 0;
	}

	@Override
	public MonetaryAmount add(MonetaryAmount amount) {

		return null;
	}

	@Override
	public MonetaryAmount subtract(MonetaryAmount amount) {

		return null;
	}

	@Override
	public MonetaryAmount multiply(long multiplicand) {

		return null;
	}

	@Override
	public MonetaryAmount multiply(double multiplicand) {

		return null;
	}

	@Override
	public MonetaryAmount multiply(Number multiplicand) {

		return null;
	}

	@Override
	public MonetaryAmount divide(long divisor) {

		return null;
	}

	@Override
	public MonetaryAmount divide(double divisor) {

		return null;
	}

	@Override
	public MonetaryAmount divide(Number divisor) {

		return null;
	}

	@Override
	public MonetaryAmount remainder(long divisor) {

		return null;
	}

	@Override
	public MonetaryAmount remainder(double divisor) {

		return null;
	}

	@Override
	public MonetaryAmount remainder(Number divisor) {

		return null;
	}

	@Override
	public MonetaryAmount[] divideAndRemainder(long divisor) {

		return null;
	}

	@Override
	public MonetaryAmount[] divideAndRemainder(double divisor) {

		return null;
	}

	@Override
	public MonetaryAmount[] divideAndRemainder(Number divisor) {

		return null;
	}

	@Override
	public MonetaryAmount divideToIntegralValue(long divisor) {

		return null;
	}

	@Override
	public MonetaryAmount divideToIntegralValue(double divisor) {

		return null;
	}

	@Override
	public MonetaryAmount divideToIntegralValue(Number divisor) {

		return null;
	}

	@Override
	public MonetaryAmount scaleByPowerOfTen(int power) {

		return null;
	}

	@Override
	public MonetaryAmount abs() {

		return null;
	}

	@Override
	public MonetaryAmount negate() {

		return null;
	}

	@Override
	public MonetaryAmount plus() {

		return null;
	}

	@Override
	public MonetaryAmount stripTrailingZeros() {

		return null;
	}

}
