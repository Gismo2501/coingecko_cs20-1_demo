# coingecko_cs20-1_demo
This project is for data processing class.

It represents some functions you can do with the coingecko api.

You need at least Java 11 to run this code.


Required Repositories:
- jfreechart : 1.5.3 : https://mvnrepository.com/artifact/jfree/jfreechart
- org.json : 20210307 : https://mvnrepository.com/artifact/org.json/json


# Documentation

## GetCurrency Class example

```GetCurrency testCurrency = new GetCurrency();```<br />

```testCurrency.getCurrency(String Cryptocurrency, String Fiat);```
Return: double <br />
Example: <br />
```testCurrency.getCurrency("bitcoin", "usd");```<br />
Description: Get the current value, in the fiat, of the cryptocurrency<br />



```testCurrency.getCurrencyOnGivenDate(String Cryptocurrency, String Date (Format: dd-mm-yyyy), String Fiat);```<br />
Return: double<br />
Example: <br/>  ```testCurrency.getCurrency("bitcoin", "08-05-2021", "usd");```<br />
Description: Get the value, in the fiat, of the cryptocurrency on a given date<br />

## GetOtherInformation Class example

```GetOtherInformation testInformation = new GetOtherInformation();```<br />

```testInformation.getDescription(String Cryptocurrency);```<br />
Return: String<br />
Example:<br /> ```testInformation.getCurrency("bitcoin");```<br />
Description: Get the coin description<br />



```testInformation.getGenesisDate(String Cryptocurrency);```<br />
Return: String<br />
Example:<br /> ```testInformation.getGenesisDate("bitcoin");```<br />
Description: Get the coin genesis date<br />



```testInformation.getSupportedCoins();```<br />
Return: void<br />
Example:<br /> ```testInformation.getSupportedCoins();```<br />
Description: List all supported coins<br />

## CheckCurrencyInInterval Class example

```CheckCurrencyInInterval testInterval = new CheckCurrencyInInterval();```<br />

```testInterval.startCheck(String Cryptocurrency, String fiat, int time (in Seconds), int runs);```<br />
Return: void<br />
Example:<br /> ```testInterval.startCheck("bitcoin","usd","1","10");```<br />
Description: Write current value of given coin in a file.<br />


## Stonks Class example

```Stonks stonk = new Stonks();```<br />

```stonk.wouldIBeRich(String Cryptocurrency, String fiat, double money, String Date (Format: dd-mm-yyyy));```<br />

Return: void<br />
Example: <br />```stonk.wouldIBeRich("bitcoin", "usd", 100.50, "25-01-2020");```<br />
Description: Shows you why you should invest in crypto!<br />


## OHLC Class example

```OHLC ohlc = new OHLC();<br />```

```ohlc.getOHLCData(String Cryptocurrency, String fiat, String days (1, 7, 14, 30, 90, 180, 365, max);```<br />
Return: void<br />
Example: <br />```ohlc.getOHLCData("bitcoin", "usd", "max");```<br />
Description: Creates a chart and shows you the OHLC from the cryptocurrency<br />


## Trending Class example

```Trending trend = new Trending();<br />```

```trend.getTrendingCoin();```<br />
Return: void<br />
Example: <br />```trend.getTrendingCoin();```<br />
Description: Prints the trending coins from Coingecko in the last 24 hours<br />




