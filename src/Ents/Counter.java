package Ents;

import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public class Counter extends Entity {
	private int count;
	
	public Counter(int x, int y, int count) {
		super(x, y);
		this.count = count;
	}

	public void draw(SimpleWindow w) {
		w.setLineColor(new Color(0, 150, 0));
		if(count == 2){
			w.setLineColor(new Color(255, 150, 0));
		} else if (count == 1){
			w.setLineColor(Color.RED);
		}
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
		w.moveTo(x - radius/3, y + radius/2);
		w.writeText("" + count);
	}

	public void erase(SimpleWindow w) {
		w.setLineColor(Color.WHITE);
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
		w.moveTo(x - radius/3, y + radius/2);
		w.writeText("" + count);
	}

	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w){
		s.stop();
		erase(w);
		count--;
		if(count == 0){
			moveTo(-10, -10);
		}
		draw(w);
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public void write(PrintWriter p) {
		p.println("C " + x + " " + y + " " + count);
	}

	public Entity clone() {
		return new Counter(x, y, count);
	}

}
