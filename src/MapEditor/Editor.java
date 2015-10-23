package MapEditor;

import java.awt.Color;
import java.util.ArrayList;

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

public class Editor {
	private SimpleWindow w;
	private SimpleWindow w1;
	private ArrayList<Entity> stuff;
	private ArrayList<Entity> ents;

	public Editor(SimpleWindow w, SimpleWindow w1) {
		this.w = w;
		this.w1 = w1;
		stuff = new ArrayList<Entity>();
		ents = new ArrayList<Entity>();
	}

	public void init() {
		for (int i = 1; i < 29; i++) {
			stuff.add(new Square(10, i * 20 + 10));
		}
		for (int i = 0; i < 30; i++) {
			stuff.add(new Square(i * 20 + 10, w.getHeight() - 10));
		}
		for (int i = 28; i > 8; i--) {
			stuff.add(new Square(590, i * 20 + 10));
		}
		for (int i = 7; i > 0; i--) {
			stuff.add(new Square(590, i * 20 + 10));
		}
		for (int i = 29; i >= 0; i--) {
			stuff.add(new Square(i * 20 + 10, 10));
		}
		for (Entity i : stuff) {
			i.draw(w);
		}

		w.setLineColor(Color.LIGHT_GRAY);
		for (int i = 20; i <= 580; i += 20) {
			for (int j = 20; j <= 580; j += 20) {
				w.moveTo(i, j);
				w.lineTo(580, j);
				w.moveTo(i, j);
				w.lineTo(i, 580);
			}
		}

		ents.add(new Square(20, 20));
		ents.add(new InvisibleSquare(60, 20));
		ents.add(new Teleport(100, 20, "ENTRY"));
		ents.add(new Teleport(140, 20, "EXIT"));
		ents.add(new Bounce(180, 20, "RU"));
		ents.add(new Bounce(220, 20, "LU"));
		ents.add(new Bounce(20, 60, "RD"));
		ents.add(new Bounce(60, 60, "LD"));
		ents.add(new Hole(100, 60));
		ents.add(new OneHit(140, 60));
		ents.add(new FakeSquare(180, 60));
		ents.add(new Mover(220, 60));
		ents.add(new Glider(20, 100));
		ents.add(new Counter(60, 100, 5));
		ents.add(new Pipe(100, 100, true));
		for (Entity i : ents) {
			i.setRadius(18);
			i.draw(w1);
		}
	}

//	public Entity select(int x, int y, ArrayList<Entity> ents) {
//		Entity ent = null;
////		w1.open();
//		while (true) {
////			w1.waitForEvent();
//			if (w.getEventType() == SimpleWindow.MOUSE_EVENT) {
//				for (Entity i : ents) {
//					if (w.getMouseX() >= i.getX() - i.getRadius()
//							&& w.getMouseX() <= i.getX() + i.getRadius()
//							&& w.getMouseY() >= i.getY() - i.getRadius()
//							&& w.getMouseY() <= i.getY() + i.getRadius()) {
//						ent = i.clone();
//						if (ent instanceof Counter) {
//							int n = 10;
//							while (n > 9) {
//								n = Integer.parseInt(JOptionPane.showInputDialog("Hur många hits?"));
//							}
//							((Counter) ent).setCount(n);
//						}
////						ent.moveTo(x, y);
////						ent.setRadius(9);
////						w1.close();
////						return ent;
//					}
//				}
//			} else if (w.getEventType() == SimpleWindow.KEY_EVENT){
////				Entity ent = null;
//				switch (w1.getKey()){
//				case '1':
//					ent = ents.get(0).clone();
//					break;
//				case '2':
//					ent = ents.get(9).clone();
//					break;
//				}
//			}
//			ent.moveTo(x, y);
//			ent.setRadius(9);
////			w1.close();
//			return ent;
//		}
//	}
}
