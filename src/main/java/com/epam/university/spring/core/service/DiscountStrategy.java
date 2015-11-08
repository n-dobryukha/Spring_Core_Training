package com.epam.university.spring.core.service;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-10-26
 * Time: 10:49
 */
public class DiscountStrategy implements Comparable<DiscountStrategy> {

    private int discountValue;
    private DiscountStrategyType discountType;

    public DiscountStrategy(int discountValue, DiscountStrategyType discountType) {
        this.discountValue = discountValue;
        this.discountType = discountType;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public DiscountStrategyType getDiscountType() {
        return discountType;
    }

    @Override
    public int compareTo(DiscountStrategy o) {
        return o.getDiscountValue() - this.getDiscountValue();
    }
}
