package Ents;
import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public class Bounce extends Entity {
	private String hyp;

	public Bounce(int x, int y, String hyp) {
		super(x, y);
		this.hyp = hyp;
	}

	public void draw(SimpleWindow w) {
		w.setLineColor(Color.BLUE);
		switch (hyp) {
		case "LU":
			w.moveTo(x + radius, y + radius);
			w.lineTo(x + radius, y - radius);
			w.lineTo(x - radius, y + radius);
			w.lineTo(x + radius, y + radius);
			break;
		case "RU":
			w.moveTo(x - radius, y + radius);
			w.lineTo(x - radius, y - radius);
			w.lineTo(x + radius, y + radius);
			w.lineTo(x - radius, y + radius);
			break;
		case "LD":
			w.moveTo(x + radius, y - radius);
			w.lineTo(x + radius, y + radius);
			w.lineTo(x - radius, y - radius);
			w.lineTo(x + radius, y - radius);
			break;
		case "RD":
			w.moveTo(x - radius, y - radius);
			w.lineTo(x - radius, y + radius);
			w.lineTo(x + radius, y - radius);
			w.lineTo(x - radius, y - radius);
			break;
		}
	}
	
	public void erase(SimpleWindow w) {
		w.setLineColor(Color.WHITE);
		switch (hyp) {
		case "LU":
			w.moveTo(x + radius, y + radius);
			w.lineTo(x + radius, y - radius);
			w.lineTo(x - radius, y + radius);
			w.lineTo(x + radius, y + radius);
			break;
		case "RU":
			w.moveTo(x - radius, y + radius);
			w.lineTo(x - radius, y - radius);
			w.lineTo(x + radius, y + radius);
			w.lineTo(x - radius, y + radius);
			break;
		case "LD":
			w.moveTo(x + radius, y - radius);
			w.lineTo(x + radius, y + radius);
			w.lineTo(x - radius, y - radius);
			w.lineTo(x + radius, y - radius);
			break;
		case "RD":
			w.moveTo(x - radius, y - radius);
			w.lineTo(x - radius, y + radius);
			w.lineTo(x + radius, y - radius);
			w.lineTo(x - radius, y - radius);
			break;
		}
	}

	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		if ((s.getVX() == 1 && (hyp.equals("RU") || hyp.equals("RD")))
				|| (s.getVX() == -1 && (hyp.equals("LU") || hyp.equals("LD")))
				|| (s.getVY() == -1 && (hyp.equals("LU") || hyp.equals("RU")) 
				|| (s.getVY() == 1 && (hyp.equals("LD") || hyp.equals("RD"))))) {
			s.stop();
		}
	}

	public void into(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		if (hyp.equals("LU") && s.getVX() == 1) {
			s.setVX(0);
			s.setVY(-1);
			for (int j = 1; j <= 20; j++) {
				s.erase(w);
				s.moveTo(x + s.getVX() * j, y + s.getVY() * j);
				draw(w);
				s.draw(w);
				SimpleWindow.delay(delay);
			}
			s.erase(w);
		} else if (hyp.equals("LU") && s.getVY() == 1) {
			s.setVX(-1);
			s.setVY(0);
			for (int j = 1; j <= 20; j++) {
				s.erase(w);
				s.moveTo(x + s.getVX() * j, y + s.getVY() * j);
				draw(w);
				s.draw(w);
				SimpleWindow.delay(delay);
			}
			s.erase(w);
		} else if (hyp.equals("LD") && s.getVX() == 1) {
			s.setVX(0);
			s.setVY(1);
			for (int j = 1; j <= 20; j++) {
				s.erase(w);
				s.moveTo(x + s.getVX() * j, y + s.getVY() * j);
				draw(w);
				s.draw(w);
				SimpleWindow.delay(delay);
			}
			s.erase(w);
		} else if (hyp.equals("LD") && s.getVY() == -1) {
			s.setVX(-1);
			s.setVY(0);
			for (int j = 1; j <= 20; j++) {
				s.erase(w);
				s.moveTo(x + s.getVX() * j, y + s.getVY() * j);
				draw(w);
				s.draw(w);
				SimpleWindow.delay(delay);
			}
			s.erase(w);
		} else if (hyp.equals("RU") && s.getVX() == -1) {
			s.setVX(0);
			s.setVY(-1);
			for (int j = 1; j <= 20; j++) {
				s.erase(w);
				s.moveTo(x + s.getVX() * j, y + s.getVY() * j);
				draw(w);
				s.draw(w);
				SimpleWindow.delay(delay);
			}
			s.erase(w);
		} else if (hyp.equals("RU") && s.getVY() == 1) {
			s.setVX(1);
			s.setVY(0);
			for (int j = 1; j <= 20; j++) {
				s.erase(w);
				s.moveTo(x + s.getVX() * j, y + s.getVY() * j);
				draw(w);
				s.draw(w);
				SimpleWindow.delay(delay);
			}
			s.erase(w);
		} else if (hyp.equals("RD") && s.getVY() == -1) {
			s.setVX(1);
			s.setVY(0);
			for (int j = 1; j <= 20; j++) {
				s.erase(w);
				s.moveTo(x + s.getVX() * j, y + s.getVY() * j);
				draw(w);
				s.draw(w);
				SimpleWindow.delay(delay);
			}
			s.erase(w);
		} else if (hyp.equals("RD") && s.getVX() == -1) {
			s.setVX(0);
			s.setVY(1);
			for (int j = 1; j <= 20; j++) {
				s.erase(w);
				s.moveTo(x + s.getVX() * j, y + s.getVY() * j);
				draw(w);
				s.draw(w);
				SimpleWindow.delay(delay);
			}
			s.erase(w);
		}
	}
	
	public void write(PrintWriter p){
		p.println("B " + x + " " + y + " " + hyp);
	}

	@Override
	public Entity clone() {
		return new Bounce(x, y, hyp);
	}
}
