import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Player {
	double x, y, maxHp = 100, hp = maxHp, stamina, angle = 0;
	double speed = 1.2;
	boolean insideCar = false;
	
	Weapon weapon = new Weapon("pistol");
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void draw(int mouseX, int mouseY, Graphics g){
		if(weapon != null){
			Graphics2D gRotated = (Graphics2D) g;
			AffineTransform orig = gRotated.getTransform();
			
			gRotated.translate(Camera.cameraX(x), Camera.cameraY(y));
			gRotated.rotate(Math.toRadians(angle));
			g.setColor(new Color(255,255,255));
			g.fillRect(6, -14, 4, 14);
			g.setColor(new Color(0,0,0));
			g.drawRect(6, -14, 4, 14);
			
			
			gRotated.translate(-Camera.cameraX(x), -Camera.cameraY(y));
			gRotated.setTransform(orig);
			weapon.draw(this, g);
		}
		
		g.setColor(new Color(255,255,255));
		g.fillOval((int)Camera.cameraX(x-10), (int)Camera.cameraY(y-10), 20, 20);
		g.setColor(new Color(0,0,0));
		g.drawOval((int)Camera.cameraX(x-10), (int)Camera.cameraY(y-10), 20, 20);
		g.drawLine((int)Camera.cameraX(x), (int)Camera.cameraY(y), (int)Camera.cameraX(x + (int)Vector.xComponent(Vector.angle((int)Camera.cameraX(x),(int)Camera.cameraY(y),mouseX,mouseY), 10)), (int)Camera.cameraY(y - (int)Vector.yComponent(Vector.angle((int)Camera.cameraX(x),(int)Camera.cameraY(y),mouseX,mouseY), 10)));
		
		
	}
	public void fire(Zombie zombie[]){
		if(weapon.getWeaponType() == "pistol"){
			if(System.currentTimeMillis() - weapon.getLastTimeFired() > weapon.getReload()){
				int zombiesShot = 0;
				weapon.fire(x,y,angle);
				for(int i=0;i<Zombie.getTotal();i++){
					for(int j=0;j<weapon.getRange();j+=10){
						if(!zombie[i].isDead() && Math.sqrt(Math.pow((x + j*Math.sin(Math.toRadians(angle))) - (zombie[i].getX()), 2) + Math.pow((y - j*Math.cos(Math.toRadians(angle))) - (zombie[i].getY()), 2)) < 10){
							if(zombiesShot < 2){
								zombie[i].setDead(true);
								zombiesShot++;
							}
						}
					}
				}
				weapon.setLastTimeFired(System.currentTimeMillis());
			}
		}
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public double getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}
	public double getHp() {
		return hp;
	}
	public void setHp(double hp) {
		this.hp = hp;
	}
	public double getStamina() {
		return stamina;
	}
	public void setStamina(double stamina) {
		this.stamina = stamina;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
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

	public boolean isInsideCar() {
		return insideCar;
	}

	public void setInsideCar(boolean insideCar) {
		this.insideCar = insideCar;
	}
	
	
}
