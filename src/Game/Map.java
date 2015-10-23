package Game;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import se.lth.cs.window.SimpleWindow;
import Ents.Bounce;
import Ents.Counter;
import Ents.Entity;
import Ents.FakeSquare;
import Ents.Glider;
import Ents.Hole;
import Ents.InvisibleSquare;
import Ents.Mover;
import Ents.OneHit;
import Ents.Pipe;
import Ents.Santa;
import Ents.Square;
import Ents.Teleport;

public class Map {
	private ArrayList<Entity> stuff;
	private Scanner scan;

	/** Skapar en bana med standardtunnor och huvudtunnorna. */
	public Map(int nbr, SimpleWindow w, Santa santa) {
		stuff = new ArrayList<Entity>();
		stuff.add(santa);
		for (int i = 1; i < 29; i++) {
			stuff.add(new Square(10, i * 20 + 10));
		}
		for (int i = 0; i < 30; i++) {
			stuff.add(new Square(i * 20 + 10, w.getHeight() - 10));
		}
		for (int i = 28; i > 8; i--) {
			stuff.add(new Square(w.getWidth() - 10, i * 20 + 10));
		}
		for (int i = 7; i > 0; i--) {
			stuff.add(new Square(w.getWidth() - 10, i * 20 + 10));
		}
		for (int i = 29; i >= 0; i--) {
			stuff.add(new Square(i * 20 + 10, 10));
		}

		try {
			scan = new Scanner(new File("res\\map" + nbr + ".txt"));
			while (scan.hasNext()) {
				switch (scan.next()) {
				case "S":
					Square s = new Square(scan.nextInt(), scan.nextInt());
					stuff.add(s);
					break;
				case "T":
					Teleport t = new Teleport(scan.nextInt(), scan.nextInt(), scan.next());
					stuff.add(t);
					break;
				case "B":
					Bounce b = new Bounce(scan.nextInt(), scan.nextInt(), scan.next());
					stuff.add(b);
					break;
				case "F":
					FakeSquare f = new FakeSquare(scan.nextInt(), scan.nextInt());
					stuff.add(f);
					break;
				case "I":
					InvisibleSquare i = new InvisibleSquare(scan.nextInt(), scan.nextInt());
					stuff.add(i);
					break;
				case "O":
					OneHit o = new OneHit(scan.nextInt(), scan.nextInt());
					stuff.add(o);
					break;
				case "H":
					Hole h = new Hole(scan.nextInt(), scan.nextInt());
					stuff.add(h);
					break;
				case "M":
					Mover m = new Mover(scan.nextInt(), scan.nextInt());
					stuff.add(m);
					break;
				case "G":
					Glider g = new Glider(scan.nextInt(), scan.nextInt());
					stuff.add(g);
					break;
				case "C":
					Counter c = new Counter(scan.nextInt(), scan.nextInt(), scan.nextInt());
					stuff.add(c);
					break;
				case "P":
					Pipe p = new Pipe(scan.nextInt(), scan.nextInt(), scan.nextBoolean());
					stuff.add(p);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Banan kan ej hittas.");
		}
	}

	/** Returnerar stuff. */
	public ArrayList<Entity> getStuff() {
		return stuff;
	}

	/** Ritar upp banan. */
	public void init(SimpleWindow w, int n, Santa s) {
		s.moveTo(30, 570);
		int blue = 255;
		
		/**Animerar banans nummer.**/
		while (blue >= 0) {
			Color c = new Color(blue, blue, 255);
			blue--;
			w.setLineColor(c);
			w.moveTo(300, 300);
			w.writeText("Bana " + n);
			SimpleWindow.delay(5);
		}
		w.clear();
//		System.out.println(stuff.size());
		
		for(int i = 0; i < 115; i++){
			stuff.get(i).draw(w);
			SimpleWindow.delay(5);
		}
		for(int i = 115; i < stuff.size(); i++){
			stuff.get(i).draw(w);
		}
		if (n < 10) {
			w.moveTo(7, 14);
		} else if (n < 100) {
			w.moveTo(3, 14);
		} else {
			w.moveTo(0, 14);
		}
		w.setLineColor(Color.RED);
		w.writeText(Integer.toString(n));
		w.moveTo(586, 175);
		w.writeText(">");
	}

}
