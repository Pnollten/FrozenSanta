package Ents;
import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public abstract class Entity {
	protected int x;
	protected int y;
	protected int radius;
	protected int vx;
	protected int vy;
	protected boolean move;
	protected Entity prev;
	protected static final int delay = 1;
	protected int count;

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		radius = 9;
		vx = 0;
		vy = 0;
		move = false;
		prev = null;
		count = 0;
	}
	
	public abstract void draw(SimpleWindow w);
	
	public abstract void erase(SimpleWindow w);
	
	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w){
		s.stop();
		draw(w);
	}
	
	public void into(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		s.erase(w);
		s.stop();
		s.erase(w);
		s.moveTo(30, 570);
		draw(w);
	}
	
	public abstract void write(PrintWriter p);
	
	public void stop() {
		moveTo(x - 2 * vx, y - 2 * vy);
		vx = 0;
		vy = 0;
		move = false;
	}
	
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}
	
	public int getCount(){
		return count;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getVX() {
		return vx;
	}

	public int getVY() {
		return vy;
	}

	public void setVX(int vx) {
		this.vx = vx;
	}

	public void setVY(int vy) {
		this.vy = vy;
	}
	
	public String getDirection(){
		if(vx == 1){
			return "right";
		} else if(vx == -1){
			return "left";
		} else if(vy == 1){
			return "down";
		} else if(vy == -1){
			return "up";
		}
		return null;
	}
	
	public void setRadius(int radius){
		this.radius = radius;
	}
	
	public void count(){
		count++;
	}
	
	public void resetCount(){
		count = 0;
	}
	
	public void move(String direction, ArrayList<Entity> stuff, SimpleWindow w) {
		int oldX = x;
		int oldY = y;
		if (prev == null) {
			prev = stuff.get(1);
		}
		move = true;
		switch (direction) {
		case "up":
			vy = -1;
			break;
		case "down":
			vy = 1;
			break;
		case "right":
			vx = 1;
			break;
		case "left":
			vx = -1;
			break;
		}
		while (move) {
//			erase(w);
			int oldVX = vx;
			int oldVY = vy;
			for (int i = 0; i < stuff.size(); i++) {
				Entity e = stuff.get(i);
				if(x == e.getX() && y == e.getY() && e != this){
					e.into(stuff, prev, this, w);
					oldVX = vx;
					oldVY = vy;
					if (e instanceof Teleport) {
						if (((Teleport) e).getEntry()) {
							prev = stuff.get(stuff.indexOf(e) + 1);
						} else {
							prev = stuff.get(stuff.indexOf(e) - 1);
						}
					} else {
						prev = e;
					}
					i = 0;
				}
				if ((x + vx * radius == e.getX() - vx * radius && y == e.getY()
						|| y + vy * radius == e.getY() - vy * radius && x == e.getX()) && e != this) {
					e.hit(stuff, prev, this, w);
					prev = e;
				}
			}
			if(vx != 0 || vy != 0){
				erase(w);
				x += vx;
				y += vy;
			} else if(oldVX != 0 || oldVY != 0){
				moveTo(x + 2 * oldVX, y + 2 * oldVY);
				erase(w);
				moveTo(x - 2 * oldVX, y - 2 * oldVY);
				prev.draw(w);
				if(x != oldX || y != oldY){
					count();
				}
			}
			if (x > w.getWidth()) {
				move = false;
				vx = 0;
				vy = 0;
				prev = null;
			}
			draw(w);
			SimpleWindow.delay(delay);
		}
	}
	
	public void mark(SimpleWindow w){
		w.setLineColor(Color.RED);
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.moveTo(x - radius, y + radius);
		w.lineTo(x + radius, y - radius);
		w.moveTo(x, y - radius);
		w.lineTo(x, y + radius);
		w.moveTo(x - radius, y);
		w.lineTo(x + radius, y);
	}
	
	public void unmark(SimpleWindow w){
		w.setLineColor(Color.WHITE);
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.moveTo(x - radius, y + radius);
		w.lineTo(x + radius, y - radius);
		w.moveTo(x, y - radius);
		w.lineTo(x, y + radius);
		w.moveTo(x - radius, y);
		w.lineTo(x + radius, y);
		draw(w);
	}
	
	public abstract Entity clone();

}
