package com.itformacion.fixer_xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FixerIO {
    public static void main(String[] args) {
        // Conversion against EUR
        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/java/BeanGBP.xml");
        CurrencyConverter converter = context.getBean(CurrencyConverter.class);
        System.out.println(String.format(
            "100 EUR is %.2f GBP", converter.getConversion()
        ));
        
        context = new ClassPathXmlApplicationContext("file:src/main/java/Beans.xml");
        converter = context.getBean(CurrencyConverter.class);
        System.out.println(String.format(
            "100 EUR is %.2f USD", converter.getConversion()
        ));
    }
}