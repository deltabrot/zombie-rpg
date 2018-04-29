import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.QuadCurve2D;
import java.util.Random;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Render extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener{
	private static final long serialVersionUID = 1L;
	
	Timer time = new Timer(10, this);
	Window window[] = new Window[10];
	Window equipment = new Window(200,500,200,300,"Equipment");
	Window inventory = new Window(350,100,200,200,"Inventory");
	Window statistics = new Window(100,100,200,300,"Statistics");
	Player player = new Player(0,0);
	
	Zombie zombie[] = new Zombie[35];
	Car car = new Car(100,100,1);
	
	int mouseX = 0, mouseY = 0;
	boolean mouseDown = false;
	boolean wDown = false, aDown = false, sDown = false, dDown = false, eDown = false;
	boolean eReleased = true;
	
	public Render(){
		this.setBackground(new Color(255,255,255));
		time.start();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
		for(int i=0;i<zombie.length;i++){
			zombie[i] = new Zombie((int)(new Random().nextFloat()*1500)-750, (int)(new Random().nextFloat()*1500)-750, 0);
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(new Color(160,160,160));
		for(int i=-50;i<=50;i++){
			g.drawLine((int)Camera.cameraX(15*i), (int)Camera.cameraY(-750), (int)Camera.cameraX(15*i), (int)Camera.cameraY(750));
		}
		for(int i=-50;i<=50;i++){
			g.drawLine((int)Camera.cameraX(-750), (int)Camera.cameraY(15*i), (int)Camera.cameraX(750), (int)Camera.cameraY(15*i));
		}
		
		for(int i=Zombie.getTotal()-1;i>=0;i--){
			if(zombie[i].isDead()){
				zombie[i].draw(g);
			}
		}
		for(int i=Zombie.getTotal()-1;i>=0;i--){
			if(!zombie[i].isDead()){
				zombie[i].draw(g);
			}
		}
		car.draw(g);
		player.draw(mouseX, mouseY, g);
		
		car.drawRoof(g);
		
		
		g.setColor(new Color(255,255,255));
		g.fillRect(5, 5, 195, 75);
		g.setColor(new Color(0,0,0));
		g.drawRect(5, 5, 195, 75);
		g.setColor(new Color(255,255,255));
		g.fillOval(10, 10, 50, 50);
		g.setColor(new Color(0,0,0));
		g.drawOval(10, 10, 50, 50);
		QuadCurve2D q = new QuadCurve2D.Float();
		q.setCurve(35, 10, 45,35, 40,58);
		g2.draw(q);
		q.setCurve(17, 39, 44,42, 58,35);
		g2.draw(q);
		g.drawString("Jamie", 20, 75);
		g.drawString("HP:", 75, 37);
		g.drawString("ST:", 75, 55);
		g.drawRect(96, 27, 100, 12);
		g.fillRect(98, 29, (int)(97*(player.getHp()/player.getMaxHp())), 9);
		g.drawRect(96, 44, 100, 12);
		g.fillRect(98, 46, 97, 9);
		
		g.setColor(new Color(0,0,0));
		for(int i=Window.getOpenWindows()-1;i>=0;i--){
				window[i].draw(g);
		}
		
	}
	public void actionPerformed(ActionEvent e){
		
		for(int i=0;i<Zombie.getTotal();i++){
			for(int j=0;j<i;j++){
				if(!zombie[i].isDead()){
					if(Vector.distance(zombie[j].getX(), zombie[j].getY(), player.getX(), player.getY()) > Vector.distance(zombie[j+1].getX(), zombie[j+1].getY(), player.getX(), player.getY())){
						Zombie tempZom = zombie[j];
						zombie[j] = zombie[j+1];
						zombie[j+1] = tempZom;
					}
				}
			}
		}
		for(int i=0;i<Zombie.getTotal();i++){
			if(!zombie[i].isDead()){
				double zomAngle = Math.toRadians(Vector.angle(zombie[i].getX(),zombie[i].getY(),player.getX(),player.getY()));
				zombie[i].setAngle(zomAngle);
				zombie[i].setX(zombie[i].getX() + Vector.xComponent(Math.toDegrees(zomAngle), zombie[i].getSpeed()));
				zombie[i].setY(zombie[i].getY() - Vector.yComponent(Math.toDegrees(zomAngle), zombie[i].getSpeed()));
			}
		}
		for(int i=0;i<Zombie.getTotal();i++){
			for(int j=0;j<Zombie.getTotal();j++){
				if(i != j){
					if(Vector.distance(zombie[i].getX(),  zombie[i].getY(), zombie[j].getX(),zombie[j].getY()) < 20){
						if(!zombie[i].isDead() && !zombie[j].isDead()){
							double zomDist = Vector.distance(zombie[i].getX(),  zombie[i].getY(), zombie[j].getX(),zombie[j].getY());
							zombie[i].setX(zombie[i].getX() - (Vector.xComponent(Vector.angle(zombie[i].getX(), zombie[i].getY(), zombie[j].getX(), zombie[j].getY()), (20-zomDist))));
							zombie[i].setY(zombie[i].getY() + (Vector.yComponent(Vector.angle(zombie[i].getX(), zombie[i].getY(), zombie[j].getX(), zombie[j].getY()), (20-zomDist))));
							zombie[j].setX(zombie[j].getX() - (Vector.xComponent(Vector.angle(zombie[j].getX(), zombie[j].getY(), zombie[i].getX(), zombie[i].getY()), (20-zomDist))));
							zombie[j].setY(zombie[j].getY() + (Vector.yComponent(Vector.angle(zombie[j].getX(), zombie[j].getY(), zombie[i].getX(), zombie[i].getY()), (20-zomDist))));
						}
					}
				}
			}
			if(Vector.distance(zombie[i].getX(),  zombie[i].getY(), player.getX(),player.getY()) <= 20){
				if(!zombie[i].isDead()){
					double zomDist = Vector.distance(zombie[i].getX(),  zombie[i].getY(), player.getX(),player.getY());
					zombie[i].setX(zombie[i].getX() - (Vector.xComponent(Vector.angle(zombie[i].getX(), zombie[i].getY(), player.getX(), player.getY()), (20-zomDist))));
					zombie[i].setY(zombie[i].getY() + (Vector.yComponent(Vector.angle(zombie[i].getX(), zombie[i].getY(), player.getX(), player.getY()), (20-zomDist))));
					player.setHp(player.getHp() - 0.1);
				}
			}
		}
		
		Camera.setX(Camera.getX() - ((Camera.getX()-player.getX())/10));
		Camera.setY(Camera.getY() - ((Camera.getY()-player.getY())/10));
		
		
		car.setX(car.getX() + car.getYv()*Math.sin(car.getAngle()));
		car.setY(car.getY() - car.getYv()*Math.cos(car.getAngle()));
		
		if(wDown){
			if(!player.isInsideCar()){
				if(!aDown && !dDown){
					player.setY(player.getY() - player.getSpeed());
				}
				if(aDown){
					player.setX(player.getX() - player.getSpeed()*Math.cos(Math.PI/4));
					player.setY(player.getY() - player.getSpeed()*Math.sin(Math.PI/4));
				}
				else if(dDown){
					player.setX(player.getX() + player.getSpeed()*Math.cos(Math.PI/4));
					player.setY(player.getY() - player.getSpeed()*Math.sin(Math.PI/4));
				}
			}
			else{
				if(car.getYv() < 4){
					car.setYv(car.getYv() + 0.05);
				}
			}
		}
		else{
			if(car.getYv() > 0){
				car.setYv(car.getYv()*0.99);
			}
		}
		if(sDown){
			if(!player.isInsideCar()){
				if(!aDown && !dDown){
					player.setY(player.getY() + player.getSpeed());
				}
				if(aDown){
					player.setX(player.getX() - player.getSpeed()*Math.cos(Math.PI/4));
					player.setY(player.getY() + player.getSpeed()*Math.sin(Math.PI/4));
				}
				else if(dDown){
					player.setX(player.getX() + player.getSpeed()*Math.cos(Math.PI/4));
					player.setY(player.getY() + player.getSpeed()*Math.sin(Math.PI/4));
				}
			}
			else{
				if(car.getYv() > -2){
					car.setYv(car.getYv() - 0.05);
				}
			}
		}
		else{
			if(car.getYv() < 0){
				car.setYv(car.getYv()*0.99);
			}
		}
		if((!wDown && !sDown) || player.isInsideCar()){
			if(dDown){
				if(!player.isInsideCar()){
					player.setX(player.getX() + player.getSpeed());
				}
				else{
					if(Math.abs(car.getYv()) < 2.2){
						car.setAngle(car.getAngle() + 0.01*car.getYv());
					}
					else{
						car.setAngle(car.getAngle() + 0.05/car.getYv());
					}
				}
			}
			if(aDown){
				if(!player.isInsideCar()){
					player.setX(player.getX() - player.getSpeed());
				}
				else{
					if(Math.abs(car.getYv()) < 2.2){
						car.setAngle(car.getAngle() - 0.01*car.getYv());
					}
					else{
						car.setAngle(car.getAngle() - 0.05/car.getYv());
					}
				}
			}
		}
		
		
		if(eDown && car.nearDoor((int)player.getX(), (int)player.getY()) && !player.isInsideCar() && eReleased){
			player.setInsideCar(true);
			car.setDriving(true);
			eReleased = false;
		}
		if(eDown && player.isInsideCar() && eReleased){
			player.setInsideCar(false);
			car.setDriving(false);
			player.setX((int)(car.getX() + (46*Math.sin(car.getAngle()) + (int)(38*Math.cos(car.getAngle())))));
			player.setY((int)(car.getY() + (-46*Math.cos(car.getAngle()) + (int)(38*Math.sin(car.getAngle())))));
			eReleased = false;
		}
		
		if(player.isInsideCar()){
			player.setX((int)(car.getX() + (46*Math.sin(car.getAngle()) + (int)(12*Math.cos(car.getAngle())))));
			player.setY((int)(car.getY() + (-46*Math.cos(car.getAngle()) + (int)(12*Math.sin(car.getAngle())))));
		}
		
		player.setAngle(Vector.angle(Camera.cameraX(player.getX()), Camera.cameraY(player.getY()), mouseX, mouseY));
		if(player.getWeapon() != null){
			if(mouseDown){
				player.fire(zombie);
			}
		}
        Toolkit.getDefaultToolkit().sync();
		repaint();
	}
	public void mouseDragged(MouseEvent e){
		for(int i=0;i<Window.getOpenWindows();i++){
			if(window[i].clickDragBar(mouseX, mouseY) && Window.isMoving()){
				window[i].setX(window[i].getX() + (e.getX()- mouseX));
				window[i].setY(window[i].getY() + (e.getY()- mouseY));
				break;
			}
		}
		mouseX = e.getX();
		mouseY = e.getY();
	}
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){
		mouseX = e.getX();
		mouseY = e.getY();
		mouseDown = true;
		
		boolean insideWindow = false;
		for(int i=0;i<Window.getOpenWindows();i++){
			if(!Window.isMoving() && (mouseX > window[i].getX() && mouseX < window[i].getX()+window[i].getWidth()) && (mouseY > window[i].getY()+16 && mouseY < window[i].getY()+window[i].getHeight())){
				insideWindow = true;
			}
		}
		for(int i=0;i<Window.getOpenWindows();i++){
			if(window[i].clickDragBar(mouseX, mouseY) && !insideWindow){
				Window.setMoving(true);
				Window tempWindow = window[i];
				for(int j=i;j>0;j--){
					window[j] = window[j-1];
				}
				window[0] = tempWindow;
				break;
			}
			else if(window[i].clickDragBar(mouseX, mouseY)){
				for(int j=0;j<Window.getOpenWindows();j++){
					if((mouseX > window[j].getX() && mouseX < window[j].getX()+window[j].getWidth()) && (mouseY > window[j].getY()+16 && mouseY < window[j].getY()+window[j].getHeight())){
						if(i<j){
							Window.setMoving(true);
							Window tempWindow = window[i];
							for(int k=i;k>0;k--){
								window[k] = window[k-1];
							}
							window[0] = tempWindow;
							break;
						}
					}
				}
			}
		}
		for(int i=0;i<Window.getOpenWindows();i++){
			if((mouseX > window[i].getX()+window[i].getWidth()-14 && mouseX < window[i].getX()+window[i].getWidth()-2) && (mouseY > window[i].getY()+2 && mouseY < window[i].getY()+14)){
				window[i].close();
				for(int j=i;j<Window.getOpenWindows();j++){
					window[j] = window[j+1];
				}
				window[Window.getOpenWindows()+1] = null;
				
			}
		}
		
	}
	public void mouseReleased(MouseEvent e){
		mouseDown = false;
		Window.setMoving(false);
	}
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_1){
			for(int i=0;i<Window.getOpenWindows();i++){
				
			}
			window[Window.getOpenWindows()] = statistics;
			window[Window.getOpenWindows()].open();
		}
		if(e.getKeyCode() == KeyEvent.VK_2){
			window[Window.getOpenWindows()] = equipment;
			window[Window.getOpenWindows()].open();
		}
		if(e.getKeyCode() == KeyEvent.VK_3){
			window[Window.getOpenWindows()] = inventory;
			window[Window.getOpenWindows()].open();
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			wDown = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			aDown = true;
					
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			sDown = true;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			dDown = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_E){
			if(eReleased){
				eDown = true;
			}
		}
	}
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W){
			wDown = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			aDown = false;
					
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			sDown = false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			dDown = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_E){
			eDown = false;
			eReleased = true;
		}
	}
	public void keyTyped(KeyEvent e){}
}
