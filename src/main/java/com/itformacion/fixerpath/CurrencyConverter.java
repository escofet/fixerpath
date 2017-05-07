package com.itformacion.fixerpath;

import com.jayway.jsonpath.JsonPath;
import org.springframework.web.client.RestTemplate;

class CurrencyConverter {
    private static String result = null;
    static {
        RestTemplate t = new RestTemplate();
        result = t.getForObject(
            "http://api.fixer.io/latest?base={CURRENCY}", String.class, "EUR"
        );
    }
    
    private String curr;
    private double amount;

    public void setCurr(String curr) {
        this.curr = curr.toUpperCase();
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public double getConversion() {
        double rate = JsonPath.read(result, String.format(
            "$.rates.%s", curr
        ));
        return rate * amount;
    }
}