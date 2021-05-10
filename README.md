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
Description: Get the current value, in the fiat, of the cryptocurrency

testCurrency.getCurrencyOnGivenDate(String Cryptocurrency, String Date (Format: dd-mm-yyyy), String Fiat);
Return: double
Example: testCurrency.getCurrency("bitcoin", "08-05-2021", "usd");
Description: Get the value, in the fiat, of the cryptocurrency on a given date

## GetOtherInformation Class example

GetOtherInformation testInformation = new GetOtherInformation();

testInformation.getDescription(String Cryptocurrency);
Return: String
Example: testInformation.getCurrency("bitcoin");
Description: Get the coin description

testInformation.getGenesisDate(String Cryptocurrency);
Return: String
Example: testInformation.getGenesisDate("bitcoin");
Description: Get the coin genesis date

testInformation.getSupportedCoins();
Return: void
Example: testInformation.getSupportedCoins();
Description: List all supported coins

## CheckCurrencyInInterval Class example

CheckCurrencyInInterval testInterval = new CheckCurrencyInInterval();

testInterval.startCheck(String Cryptocurrency, String fiat, int time (in Seconds), int runs);
Return: void
Example: testInterval.startCheck("bitcoin","usd","1","10");
Description: Write current value of given coin in a file.

## Stonks Class example
```java
Stonks stonk = new Stonks();
```
stonk.wouldIBeRich(String Cryptocurrency, String fiat, double money, String Date (Format: dd-mm-yyyy));

Return: void
Example: stonk.wouldIBeRich("bitcoin", "usd", 100.50, "25-01-2020");
Description: Shows you why you should invest in crypto!

## OHLC Class example

OHLC ohlc = new OHLC();

ohlc.getOHLCData(String Cryptocurrency, String fiat, String days (1, 7, 14, 30, 90, 180, 365, max);
Return: void
Example: ohlc.getOHLCData("bitcoin", "usd", "max");
Description: Creates a chart and shows you the OHLC from the cryptocurrency

## Trending Class example

Trending trend = new Trending();

trend.getTrendingCoin();
Return: void
Example: trend.getTrendingCoin();
Description: Prints the trending coins from Coingecko in the last 24 hours




