import java.awt.Color;
import java.awt.Graphics;


public class Bullet {
	static int total = 0;
	double x, y, angle;
	double range;
	boolean destroy = false;
	int counter = 0;
	public Bullet(double x, double y, double angle, double range){
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.range = range;
		total++;
	}
	public void draw(Graphics g){
		double distanceMoved = 35;
		g.setColor(new Color(80,80,80));
		g.fillOval((int)(Camera.cameraX(x)),(int)Camera.cameraY(y), 4, 4);
		//g.drawLine((int)(Camera.cameraX(x)),(int)Camera.cameraY(y), (int)(Camera.cameraX(x) + 18*Math.cos(Math.toRadians(angle-90))),(int)(Camera.cameraY(y) + 18*Math.sin(Math.toRadians(angle-90))));
		this.x += distanceMoved*Math.cos(Math.toRadians(angle-90));
		this.y += distanceMoved*Math.sin(Math.toRadians(angle-90));
		counter++;
		if(counter*distanceMoved >= range){
			destroy = true;
		}
	}
	
	public boolean isDestroy() {
		return destroy;
	}
	public void setDestroy(boolean destory) {
		this.destroy = destory;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public static int getTotal() {
		return total;
	}
	public static void setTotal(int total) {
		Bullet.total = total;
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
	public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
	}
	

}
