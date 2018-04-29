import java.awt.Color;
import java.awt.Graphics;


public class Statistics{
	int width = 0, height = 0;
	public Statistics(){
		
	}
	public void draw(int x, int y, Graphics g){
		g.setColor(new Color(255,255,255));
		g.fillRect(x+5,y+5,190,274);
		g.setColor(new Color(0,0,0));
		g.drawRect(x+5,y+5,190,274);
		for(int i=15;i<274;i+=15){
			g.drawLine(x+5, y+5 + i, x+195, y+5 + i);
		}
		g.drawLine(x+50, y+5, x+50, y+274);
	}
}
