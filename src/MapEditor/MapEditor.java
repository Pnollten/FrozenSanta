package MapEditor;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
import Ents.Square;
import Ents.Teleport;

public class MapEditor {

	public static void erase(int x, int y, SimpleWindow w, Entity ent, ArrayList<Entity> stuff) {
		ent.unmark(w);
		ent.erase(w);
		if (ent instanceof InvisibleSquare) {
			w.moveTo(ent.getX() - ent.getRadius() + 2, ent.getY() + 5);
			w.writeText("Inv");
		}
		x = (x / 20) * 20;
		y = (y / 20) * 20;
		w.setLineColor(Color.LIGHT_GRAY);
		w.moveTo(x, y);
		w.lineTo(x + 20, y);
		w.lineTo(x + 20, y + 20);
		w.lineTo(x, y + 20);
		w.lineTo(x, y);
	}

	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(1000, 600, "Editor");
		SimpleWindow w1 = new SimpleWindow(240, 120, "Välj block:");
		w1.close();
		ArrayList<Entity> stuff = new ArrayList<Entity>();
		Editor edit;
		PrintWriter print = null;
		Scanner scan = null;
		int n;
		int x = 0;
		int y = 0;
		boolean editing = false;
		boolean itemselect = false;
		ArrayList<Entity> ents = new ArrayList<Entity>();
		Entity ent;

		ents.add(new Square(640, 20));
		ents.add(new InvisibleSquare(680, 20));
		ents.add(new Teleport(720, 20, "ENTRY"));
		ents.add(new Teleport(760, 20, "EXIT"));
		ents.add(new Bounce(800, 20, "RU"));
		ents.add(new Bounce(840, 20, "LU"));
		ents.add(new Bounce(880, 20, "RD"));
		ents.add(new Bounce(920, 20, "LD"));
		ents.add(new Hole(960, 20));
		ents.add(new OneHit(640, 60));
		ents.add(new FakeSquare(680, 60));
		ents.add(new Mover(720, 60));
		ents.add(new Glider(760, 60));
		ents.add(new Counter(800, 60, 5));
		ents.add(new Pipe(840, 60, true));
		ents.add(new Pipe(880, 60, false));
		ent = ents.get(0);

		while (true) {
			for (Entity i : ents) {
				i.setRadius(18);
				i.draw(w);
			}
			ent.mark(w);
			edit = new Editor(w, w1);
			edit.init();
			w.waitForEvent();
			n = Integer.parseInt(JOptionPane.showInputDialog("Vilken bana?"));

			try {
				scan = new Scanner(new File("res\\map" + n + ".txt"));
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
			for (Entity i : stuff) {
				i.draw(w);
			}
			editing = true;

			while (editing) {
				itemselect = false;
				w.waitForEvent();
				if (w.getEventType() == SimpleWindow.MOUSE_EVENT) {
					for (Entity i : stuff) {
						if (w.getMouseX() >= i.getX() - i.getRadius()
								&& w.getMouseX() <= i.getX() + i.getRadius()
								&& w.getMouseY() >= i.getY() - i.getRadius()
								&& w.getMouseY() <= i.getY() + i.getRadius()) {
							itemselect = true;
							i.mark(w);
							w.waitForEvent();
							if (w.getEventType() == SimpleWindow.KEY_EVENT && w.getKey() == 'c') {
								erase(w.getMouseX(), w.getMouseY(), w, i, stuff);
								stuff.remove(i);
								break;
							} else {
								i.unmark(w);
//								itemselect = false;
							}
						}
					}
					if (!itemselect) {
						for (Entity i : ents) {
							if (w.getMouseX() >= i.getX() - i.getRadius()
									&& w.getMouseX() <= i.getX() + i.getRadius()
									&& w.getMouseY() >= i.getY() - i.getRadius()
									&& w.getMouseY() <= i.getY() + i.getRadius()) {
								ent.unmark(w);
								ent = i;
								i.mark(w);
								if (ent instanceof Counter) {
									int k = 10;
									while (k > 9) {
										k = Integer.parseInt(JOptionPane.showInputDialog("Hur många hits?"));
									}
									ent.erase(w);
									((Counter) ent).setCount(k);
									ent.draw(w);
								}
							}
						}
						if (w.getMouseX() < 580 && w.getMouseX() > 20 && w.getMouseY() < 580
								&& w.getMouseY() > 20) {
							x = (w.getMouseX() / 20) * 20 + 10;
							y = (w.getMouseY() / 20) * 20 + 10;
							Entity tempEnt = ent.clone();
							tempEnt.moveTo(x, y);
							tempEnt.setRadius(9);
							if (tempEnt instanceof InvisibleSquare) {
								w.moveTo(tempEnt.getX() - tempEnt.getRadius() + 2, tempEnt.getY() + 5);
								w.setLineColor(Color.BLUE);
								w.writeText("Inv");
							}
							stuff.add(tempEnt);
							tempEnt.draw(w);
						}
					}
				} else if (w.getEventType() == SimpleWindow.KEY_EVENT) {
					if (w.getKey() == 's') {
						try {
							print = new PrintWriter(new File("res\\map" + n + ".txt"));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						if (stuff.size() != 0) {
							for (Entity i : stuff) {
								i.write(print);
							}
						}
						editing = false;
						print.close();
						stuff.clear();
						w.clear();
					} else if (w.getKey() == 'r') {
						for (Entity e : stuff) {
							erase(e.getX(), e.getY(), w, e, stuff);
						}
						stuff.clear();
					}
				}
			}
		}
	}
}