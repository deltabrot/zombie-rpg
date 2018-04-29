import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Car {
	double x, y, yv, angle;
	boolean driving = false;
	public Car(int x, int y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	public void draw(Graphics g){
		Graphics2D gRotated = (Graphics2D) g;
		AffineTransform orig = gRotated.getTransform();
		
		gRotated.translate(Camera.cameraX(x), Camera.cameraY(y));
		gRotated.rotate(angle);
		
		if(!driving){
			g.setColor(new Color(0,255,0,70));
			g.fillOval(25-25, -40-25, 50, 50);
			g.fillOval(-25-25, -40-25, 50, 50);
		}
		
		g.setColor(new Color(255,255,255));
		g.fillRect((int)-25, (int)-80, 50, 100);
		g.setColor(new Color(0,0,0));
		g.drawRect((int)-25, (int)-80, 50, 100);
		g.drawLine((int)(-25),(int)(-60),(int)(+25),(int)(-60));
		g.drawLine((int)(-25),(int)(0),(int)(+25),(int)(0));
		g.drawLine((int)(-22),(int)(-5),(int)(+22),(int)(-5));
		g.drawLine((int)(-22),(int)(-50),(int)(+22),(int)(-50));
		g.drawLine((int)(-22),(int)(-50),(int)(-22),(int)(-5));
		g.drawLine((int)(+22),(int)(-50),(int)(+22),(int)(-5));
		
		gRotated.translate(-Camera.cameraX(x), -Camera.cameraY(y));
		gRotated.setTransform(orig);
	}
	public void drawRoof(Graphics g){
		Graphics2D gRotated = (Graphics2D) g;
		AffineTransform orig = gRotated.getTransform();
		
		gRotated.translate(Camera.cameraX(x), Camera.cameraY(y));
		gRotated.rotate(angle);
		g.setColor(new Color(255,255,255));
		g.fillRect((int)-22, (int)-50, 44, 45);
		g.setColor(new Color(0,0,0));
		g.drawRect((int)-22, (int)-50, 44, 45);
		
		g.drawLine((int)(+22),(int)(-50),(int)(+25),(int)(-60));
		g.drawLine((int)(-22),(int)(-50),(int)(-25),(int)(-60));
		g.drawLine((int)(-22),(int)(-5),(int)(-25),(int)(0));
		g.drawLine((int)(+22),(int)(-5),(int)(+25),(int)(0));
		
		gRotated.translate(Camera.cameraX(x), Camera.cameraY(y));
		gRotated.setTransform(orig);
	}
	
	public boolean isDriving() {
		return driving;
	}
	public void setDriving(boolean driving) {
		this.driving = driving;
	}
	public double getYv() {
		return yv;
	}
	public void setYv(double yv) {
		this.yv = yv;
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
	public boolean nearDoor(int playerX, int playerY){
		
		if(Math.sqrt(Math.pow(playerX - (x+(40*Math.sin(angle) + (int)(25*Math.cos(angle)))), 2) + Math.pow(playerY - (y-(40*Math.cos(angle) - (int)(25*Math.sin(angle)))), 2)) <= 25+10){
			return true;
		}
		else if(Math.sqrt(Math.pow(playerX - (x+(40*Math.sin(angle) - (int)(25*Math.cos(angle)))), 2) + Math.pow(playerY - (y-(40*Math.cos(angle) + (int)(25*Math.sin(angle)))), 2)) <= 25+10){
			return true;
		}
		else{
			return false;
		}
	}
}
