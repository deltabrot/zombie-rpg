import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Zombie {
	static int total = 0;
	double x, y, speed = 0.4;
	double angle = 0;
	boolean dead = false;
	public Zombie(int x, int y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		setTotal(total+1);
	}
	public void draw(Graphics g){
		Graphics2D gRotated = (Graphics2D) g;
		AffineTransform orig = gRotated.getTransform();
		
		gRotated.translate(Camera.cameraX(x), Camera.cameraY(y));
		gRotated.rotate(angle);
		
		if(!dead){
			g.setColor(new Color(180,180,180));
			g.fillRect(-10, -13, 4, 13);
			g.setColor(new Color(0,0,0));
			g.drawRect(-10, -13, 4, 13);
			
			g.setColor(new Color(180,180,180));
			g.fillRect(+6, -13, 4, 13);
			g.setColor(new Color(0,0,0));
			g.drawRect(+6, -13, 4, 13);
			
			g.setColor(new Color(180,180,180));
			g.fillOval(-10, -10, 20, 20);
			g.setColor(new Color(0,0,0));
			g.drawOval(-10, -10, 20, 20);
		}
		else{
			
			g.setColor(new Color(180,180,180));
			g.fillRect(-4, -10, 4, 11);
			g.setColor(new Color(0,0,0));
			g.drawRect(-4, -10, 4, 11);
			
			g.setColor(new Color(180,180,180));
			g.fillRect(0, -10, 4, 11);
			g.setColor(new Color(0,0,0));
			g.drawRect(0, -10, 4, 11);
			
			g.setColor(new Color(180,180,180));
			g.fillRect(-4, 0, 8, 13);
			g.setColor(new Color(0,0,0));
			g.drawRect(-4, 0, 8, 13);
			
			g.setColor(new Color(180,180,180));
			g.fillRect(-8, 0, 3, 13);
			g.setColor(new Color(0,0,0));
			g.drawRect(-8, 0, 3, 13);
			
			g.setColor(new Color(180,180,180));
			g.fillRect(+5, 0, 3, 13);
			g.setColor(new Color(0,0,0));
			g.drawRect(+5, 0, 3, 13);
			
			g.setColor(new Color(180,180,180));
			g.fillOval(-8, 10, 16, 16);
			g.setColor(new Color(0,0,0));
			g.drawOval(-8, 10, 16, 16);
		}
		
		
		gRotated.translate(-Camera.cameraX(x), -Camera.cameraY(y));
		gRotated.setTransform(orig);
	}
	
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public static int getTotal() {
		return total;
	}
	public static void setTotal(int total) {
		Zombie.total = total;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
