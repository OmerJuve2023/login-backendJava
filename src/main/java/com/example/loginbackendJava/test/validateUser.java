package com.example.loginbackendJava.test;

public class validateUser {


    public static void main(String[] args) {
        double coeficiente = -1.60062668;
        double intercepto = 0.06120934;
        double x = Double.parseDouble("45");
        double result = 1 / (1 + (Math.exp(-(coeficiente + (intercepto * x)))));
        System.out.println(result);
    }
}
