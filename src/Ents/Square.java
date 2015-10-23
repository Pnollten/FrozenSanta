package Ents;
import java.awt.Color;
import java.io.PrintWriter;

import se.lth.cs.window.SimpleWindow;

public class Square extends Entity {

	public Square(int x, int y) {
		super(x, y);
	}

	public void draw(SimpleWindow w) {
		w.setLineColor(Color.BLUE);
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
	}
	
	public void erase(SimpleWindow w) {
		w.setLineColor(Color.WHITE);
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
	}
	
	public void write(PrintWriter p){
		p.println("S " + x + " " + y);
	}

	@Override
	public Entity clone() {
		return new Square(x, y);
	}
}
