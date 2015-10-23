package Ents;
import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;


public class OneHit extends Entity {

	public OneHit(int x, int y) {
		super(x, y);
	}

	public void draw(SimpleWindow w) {
		w.setLineColor(new Color(255, 150, 0));
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
		for(int i = 1; i < 3; i ++){
			w.moveTo(x - radius + (radius*2)/3 * i, y - radius);
			w.lineTo(x - radius + (radius*2)/3 * i, y + radius);
			w.moveTo(x - radius, y - radius + (radius*2)/3 * i);
			w.lineTo(x + radius, y - radius + (radius*2)/3 * i );
		}
	}

	public void erase(SimpleWindow w) {
		w.setLineColor(Color.WHITE);
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
		for(int i = 1; i < 3; i ++){
			w.moveTo(x - radius + (radius*2)/3 * i, y - radius);
			w.lineTo(x - radius + (radius*2)/3 * i, y + radius);
			w.moveTo(x - radius, y - radius + (radius*2)/3 * i);
			w.lineTo(x + radius, y - radius + (radius*2)/3 * i );
		}
	}

	public Entity clone() {
		return new OneHit(x, y);
	}
	
	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		s.stop();
		erase(w);
		moveTo(-10, -10);
	}
	public void into(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		s.stop();
		s.moveTo(x, y);
		erase(w);
		moveTo(-10, -10);
	}
	
	public void write(PrintWriter p){
		p.println("O " + x + " " + y);
	}

}
