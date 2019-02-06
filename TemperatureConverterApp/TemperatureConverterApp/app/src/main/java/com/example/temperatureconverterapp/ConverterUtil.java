package com.example.temperatureconverterapp;

public class ConverterUtil {

    /**
     * @param float
     * @return double
     * converts to Celsius
     */
    public static double convertFahrenheitToCelsius(float fahrenheit){

        return ((fahrenheit - 32) * 5.0/9.0);
    }

    /**
     * @param float
     * @return double
     * converts to Fahrenheit
     */
    public static double convertCelsiusToFahrenheit(float celcius){
        return ((celcius * 9.0/5.0) + 32);
    }
}