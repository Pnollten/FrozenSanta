package Game;
import java.util.ArrayList;

import Ents.Button;
import se.lth.cs.window.SimpleWindow;


public class Menu {
	private ArrayList<Button> buttons;
	
	public Menu(){
		buttons = new ArrayList<Button>();
	}
	
	public void draw(SimpleWindow w, int save){
		buttons = new ArrayList<Button>();
		int n = 1;
		
		for (int i = 0; i <= save / 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (n <= save) {
					Button b = new Button(j * 60, i * 60, 58, n);
					buttons.add(b);
					b.draw(w);
					n++;
				} else {
					j = 9;
				}
			}
		}
	}
	
	public int chooseMap(SimpleWindow w, int save){
		while (true) {
			w.waitForMouseClick();
			for (int i = 0; i < save; i++) {
				Button b = buttons.get(i);
				if (w.getMouseX() <= b.getX() + b.getRadius() && w.getMouseX() >= b.getX()
						&& w.getMouseY() >= b.getY() && w.getMouseY() <= b.getY() + b.getRadius()) {
					w.clear();
					return buttons.get(i).clicked();
				}
			}
		}
	}
}
