package Game;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Ents.Santa;
import se.lth.cs.window.SimpleWindow;

public class FrozenSanta {

	public static void main(String[] args) {

		SimpleWindow w = new SimpleWindow(600, 600, "Frozen Santa");
		int n = 1;
		int save = 0;
		boolean mapOn = true;
		boolean play = false;
		PrintWriter p = null;
		Scanner scan = null;
		GregorianCalendar cal;
		File f = new File("res\\save.txt");
		Santa santa = new Santa(30, 570);
		Menu menu;

		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.err.println("Sparfilen kan ej hittas.");
		}
		save = scan.nextInt();
		if (save == 0) {
			write(w);
			save++;
		}
		menu = new Menu();

		while (true) {
			menu.draw(w, save);
			n = menu.chooseMap(w, save);
			play = true;

			while (play) {
				try {
					p = new PrintWriter(f);
					p.print(save);
					p.close();
				} catch (FileNotFoundException e) {
					System.err.println("Sparfilén kan ej hittas.");
				}

				Map map = new Map(n, w, santa);
				map.init(w, n, santa);
				mapOn = true;
				cal = new GregorianCalendar();
				double sec = cal.getTimeInMillis();

				while (mapOn) {
					w.waitForEvent();
					if (w.getEventType() == SimpleWindow.KEY_EVENT) {
						char c = w.getKey();
						switch(c){
						case 'w': 
							santa.move("up", map.getStuff(), w);
							break;
						case 's': 
							santa.move("down", map.getStuff(), w);
							break;
						case 'd': 
							santa.move("right", map.getStuff(), w);
							break;
						case 'a': 
							santa.move("left", map.getStuff(), w);
							break;
						}
						if (c == 'r') {
							mapOn = false;
							santa.resetCount();
							w.clear();
						}
						if (c == 'c') {
							mapOn = false;
							play = false;
							santa.resetCount();
							n = 1;
							w.clear();
						}
					}
					if (santa.getX() > w.getWidth()) {
						n++;
						santa.count();
						if (n > save) {
							save = n;
						}
						mapOn = false;
						cal = new GregorianCalendar();
						sec = (double) (cal.getTimeInMillis() - sec) / 1000;
						System.out.println(sec + " Sekunder!, you're not the champ Heinrich!");
						System.out.println("Antal drag: " + santa.getCount());
						santa.resetCount();
						w.clear();

						if (n == 101) {
							w.setLineColor(Color.BLACK);
							w.moveTo(w.getWidth() / 3, w.getHeight() / 2);
							w.writeText("Grattis, du klarade spelet!");
							w.moveTo(w.getWidth() / 3, w.getHeight() / 2 + 20);
							w.writeText("Tryck på valfri knapp för att avsluta.");
							w.waitForEvent();
							System.exit(1);
							scan.close();
						}
					}
				}
			}
		}
	}

	/** Skriver ut storyn om save = 0 */
	public static void write(SimpleWindow w) {
		w.setLineColor(Color.BLACK);
		w.moveTo(40, 40);
		w.writeText("Tomten har blivit inlåst i sin källare.");
		w.moveTo(40, 60);
		w.writeText("Källaren är väldigt kall så tomten har frusit till is");
		w.moveTo(40, 80);
		w.writeText("När nissarna hittar tomten försöker de knuffa ut honom.");
		w.moveTo(40, 100);
		w.writeText("När nissarna ska knuffa ut tomten glider han väldigt lätt och ");
		w.moveTo(40, 120);
		w.writeText("de får inte stopp på honom förrän han krockar i något.");
		w.moveTo(40, 160);
		w.writeText("Styr tomten med 'WASD'. Återställ banan med 'R' och gå till menyn med 'C'.");
		w.moveTo(40, 200);
		w.writeText("Tryck på valfri knapp för att starta!");
		w.waitForEvent();
		w.clear();
	}
}
