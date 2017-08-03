package com.itformacion.fixerpath;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

class CurrencyConverter {
    private static String result = null;
    static {
    	SocketAddress addr = new InetSocketAddress("vale.proxy.corp.sopra", 8080);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
	    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
	    requestFactory.setProxy(proxy);
        RestTemplate t = new RestTemplate(requestFactory);
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