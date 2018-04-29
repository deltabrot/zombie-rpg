import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Weapon {
	static String[] weaponTypeList = {"pistol"};
	String weaponType = "pistol";
	int range = 500;
	double reload = 100;
	long lastTimeFired = 0;
	boolean fired = false;
	Bullet bullet[] = new Bullet[10];
	public Weapon(String weaponType){
		this.weaponType = weaponType;
		boolean defaultWeapon = true;
		for(int i=0;i<weaponTypeList.length;i++){
			if(weaponType == weaponTypeList[i]){
				defaultWeapon = false;
			}
		}
		if(defaultWeapon){
			this.weaponType = "pistol";
		}
	}
	public void draw(Player player, Graphics g){
		for(int i=0; i<Bullet.getTotal();i++){
			if(bullet[i].isDestroy()){
				for(int k=i; k<Bullet.getTotal()-1;k++){
					bullet[k] = bullet[k+1];
				}
				Bullet.setTotal(Bullet.getTotal() - 1);
			}
		}
		for(int i=0; i<Bullet.getTotal();i++){
			bullet[i].draw(g);
		}
		Graphics2D gRotated = (Graphics2D) g;
		AffineTransform orig = gRotated.getTransform();
		gRotated.translate(Camera.cameraX(player.getX()), Camera.cameraY(player.getY()));
		gRotated.rotate(Math.toRadians(player.getAngle()));
		
		if(weaponType == "pistol"){
			drawPistol(g);
		}
		gRotated.translate(-Camera.cameraX(player.getX()), -Camera.cameraY(player.getY()));
		gRotated.setTransform(orig);
	}
	
	public Bullet[] getBullet() {
		return bullet;
	}
	public void setBullet(Bullet[] bullet) {
		this.bullet = bullet;
	}
	public void fire(double x, double y, double angle){
		bullet[Bullet.getTotal()] = new Bullet(x, y, angle, range);
	}
	public boolean isFired() {
		return fired;
	}
	public void setFired(boolean fired) {
		this.fired = fired;
	}
	public double getReload() {
		return reload;
	}
	public void setReload(double reload) {
		this.reload = reload;
	}
	public long getLastTimeFired() {
		return lastTimeFired;
	}
	public void setLastTimeFired(long lastTimeFired) {
		this.lastTimeFired = lastTimeFired;
	}
	public void drawPistol(Graphics g){
		g.setColor(new Color(80,80,80));
		g.fillRect(6, -25, 4, 16);
	}
	public static String[] getWeaponTypeList() {
		return weaponTypeList;
	}
	public static void setWeaponTypeList(String[] weaponTypeList) {
		Weapon.weaponTypeList = weaponTypeList;
	}
	public String getWeaponType() {
		return weaponType;
	}
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	
}
