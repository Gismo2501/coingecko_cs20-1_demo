# coingecko_cs20-1_demo
This project is for data processing class.

It represents some functions you can do with the coingecko api.

You need at least Java 11 to run this code.


Required Repositories:
- jfreechart : 1.5.3 : https://mvnrepository.com/artifact/jfree/jfreechart
- org.json : 20210307 : https://mvnrepository.com/artifact/org.json/json


# Documentation

## GetCurrency Class example

GetCurrency testCurrency = new GetCurrency();

testCurrency.getCurrency(String Cryptocurrency, String Fiat);
Return: double
Example: testCurrency.getCurrency("bitcoin", "usd");

testCurrency.getCurrencyOnGivenDate(String Cryptocurrency, String Date (Format: dd-mm-yyyy), String Fiat);
Return: double
Example: testCurrency.getCurrency("bitcoin", "08-05-2021", "usd");


## GetOtherInformation Class example

GetOtherInformation testInformation = new GetOtherInformation();

testCurrency.getCurrency(String Cryptocurrency, String Fiat);
Return: double
Example: testCurrency.getCurrency("bitcoin", "usd");

testCurrency.getCurrencyOnGivenDate(String Cryptocurrency, String Date (Format: dd-mm-yyyy), String Fiat);
Return: double
Example: testCurrency.getCurrency("bitcoin", "08-05-2021", "usd");


