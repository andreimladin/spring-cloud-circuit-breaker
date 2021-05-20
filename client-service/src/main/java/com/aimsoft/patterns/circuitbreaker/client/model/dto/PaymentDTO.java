package com.aimsoft.patterns.circuitbreaker.client.model.dto;

public class PaymentDTO {

    private long amount;
    private int decimals;
    private String currency;

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public int getDecimals() {
        return decimals;
    }

    public String getCurrency() {
        return currency;
    }
}
