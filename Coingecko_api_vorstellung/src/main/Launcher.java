package main;

import java.io.IOException;
import java.util.Scanner;

public class Launcher {
	public static void main(String[] args) throws IOException, InterruptedException {
		boolean program = true;
		String crypto = "";
		String fiat = ""; 
		String date = "";
		int time = 1;
		int run = 1;
		double money = 0.0;
		String days = "1";
		
		Scanner scan = new Scanner(System.in);
		GetCurrency cur = new GetCurrency();
		GetOtherInformation info = new GetOtherInformation();
		CheckCurrencyInInterval interval = new CheckCurrencyInInterval();
		Stonks stonk = new Stonks();
		OHLC ohlc = new OHLC();
		Trending trend = new Trending();
		
		/*
		
		Examples how to use it!
		cur.getCurrency("bitcoin","eur");
		1. Argument: Cryptocurrency Name from Coingecko
		2. Argument: Fiat Short (like eur for Euro or USD for US Dollar)
		
		
		cur.getCurrencyOnGivenDate("bitcoin", "20-01-2021", "eur");
		1. Argument: Cryptocurrency Name from Coingecko
		2. Argument: Date (Past or now) in format DD-MM-YYYY
		3. Argument: Fiat Short (like eur for Euro or USD for US Dollar)
		
		*/
		
		
		
		
		
		while(program) {
			System.out.println("Powered by CoinGecko");
			System.out.println("Bitte wählen Sie aus! \n 1. für Aktuellen Preis \n 2. für Preis aus der Vergangenheit \n 3. um eine Englische Beschreibung zu bekommen \n 4. Um das Erstelldatum anzuzeigen \n 5. um alle Unterstützten Coins zu bekommen \n 6. um Preise in einem bestimmten Interval abzurufen \n 7. um zu wissen, wie viel du heute hättest, wenn du damals investiert hättest \n 8. um ein Chart Diagramm zu erstellen \n 9. um die Trendcoins der letzten 24 Std. anzuzeigen (nach Coingecko Suche) \n 10. um das Programm abzubrechen");
			String auswahl = scan.next();
			if (auswahl.equals("1")) {
				System.out.println("Geben Sie eine Kryptowährung an.");
				crypto = scan.next();
				System.out.println("Geben Sie jetzt ihren Wechselkurs an!");
				fiat = scan.next();
				System.out.println("Das ist der Aktuelle "+crypto+" Kurs in "+fiat+": "+cur.getCurrency(crypto, fiat));
				
			}
			
			else if (auswahl.equals("2")) {
				System.out.println("Geben Sie eine Kryptowährung an.");
				crypto = scan.next();
				System.out.println("Geben Sie jetzt ihren Wechselkurs an!");
				fiat = scan.next();
				System.out.println("Geben Sie das Datum an (Format: dd-mm-yyyy)!");
				date = scan.next();
				System.out.println("Das ist der Kurs von "+crypto+" in der Währung "+fiat+" am Datum "+date+": "+cur.getCurrencyOnGivenDate(crypto, date, fiat));
			}
			
			else if (auswahl.equals("3")) {
				System.out.println("Geben Sie eine Kryptowährung an.");
				crypto = scan.next();
				System.out.println("Das ist die Englische Beschreibung von "+crypto+":\n"+info.getDescription(crypto));
			}
			
			else if (auswahl.equals("4")) {
				System.out.println("Geben Sie eine Kryptowährung an.");
				crypto = scan.next();
				System.out.println("Erschaffungsdatum von "+crypto+":\n"+info.getGenesisDate(crypto));
			}
			
			else if (auswahl.equals("5")) {
				info.getSupportedCoins();
			}
			
			else if (auswahl.equals("6")) {
				System.out.println("Geben Sie eine Kryptowährung an.");
				crypto = scan.next();
				System.out.println("Geben Sie jetzt ihren Wechselkurs an!");
				fiat = scan.next();
				System.out.println("Geben Sie jetzt den Interval (Sekunden) an!");
				time = scan.nextInt();
				while (time < 1) {
					System.out.println("Bitte geben Sie einen Interval größer oder gleich 1 Sekunde an");
					time = scan.nextInt();
				}
				System.out.println("Bitte geben Sie die Durchläufe an.");
				run = scan.nextInt();
				while (run < 1) {
					System.out.println("Es muss mindestens ein Durchlauf passieren. Bitte wiederholen Sie die Eingabe.");
					run = scan.nextInt();
				}
				System.out.println("Das Programm läuft nun im Hintergrund! Sie können jetzt noch andere Funktionen nutzen.");
				System.out.println("Die Datei hat das Format: crypto-yyyy-mm-dd--hh-mm.txt");
				System.out.println("Dateipfad:");
				interval.startCheck(crypto, fiat, time, run);
			}
			
			else if (auswahl.equals("7")) {
				System.out.println("Geben Sie eine Kryptowährung an.");
				crypto = scan.next();
				System.out.println("Geben Sie jetzt ihren Wechselkurs an!");
				fiat = scan.next();
				System.out.println("Geben Sie jetzt das Datum ein (dd-mm-yyyy)");
				date = scan.next();
				System.out.println("Geben Sie jetzt die Menge an Geld ein, die Sie damals investiert haben.");
				money = scan.nextDouble();
				stonk.wouldIBeRich(crypto, fiat, money, date);
			}
			
			else if (auswahl.equals("8")) {
				System.out.println("Geben Sie eine Kryptowährung an.");
				crypto = scan.next();
				System.out.println("Geben Sie jetzt ihren Wechselkurs an!");
				fiat = scan.next();
				System.out.println("Geben Sie die Tage an (1, 7, 14, 30, 90, 180, 365, max)!");
				days = scan.next();
				
				ohlc.getOHLCData(crypto,fiat,days);
			}
			
			else if (auswahl.equals("9")) {
				trend.getTrendingCoin();
			}
			
			else if (auswahl.equals("10")) {
				System.out.println("Das Programm wird abgebrochen!");
				System.out.println("Vielen dank für die Nutzung");
				program = false;
				scan.close();
			}
			
			else {
				System.out.println("Falsche Eingabe! Bitte erneut probieren.");
				scan.reset();
			}
		}
		
		
	}
}
