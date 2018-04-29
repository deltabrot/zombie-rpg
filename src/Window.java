import java.awt.Color;
import java.awt.Graphics;


public class Window {
	private static int openWindows = 0;
	private static boolean moving = false;
	int x, y, width, height;
	boolean open = false;
	String name;
	
	Statistics statistics = new Statistics();
	
	public Window(int x, int y, int width, int height, String name){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		if(this.height < 16){
			this.height = 16;
		}
		this.name = name;
	}
	public void draw(Graphics g){
		g.setColor(new Color(180,220,255));
		g.fillRect(x, y, width, height);
		g.setColor(new Color(0,0,0));
		g.drawRect(x, y, width, height);
		g.drawLine(x, y+16, x+width, y+16);
		g.drawRect(x+width-14, y+2, 12, 12);
		g.drawLine(x+width-12, y+4, x+width-4, y+12);
		g.drawLine(x+width-4, y+4, x+width-12, y+12);
		g.drawRect(x+width-28, y+2, 12, 12);
		g.drawRect(x+width-26, y+6, 8, 6);
		g.drawRect(x+width-42, y+2, 12, 12);
		g.drawLine(x+width-40, y+12, x+width-32, y+12);
		if(name != null){
			g.drawString(name, x+3, y+13);
		}
		statistics.draw(x,  y+16, g);
	}
	public void open(){
		this.open = true;
		setOpenWindows(getOpenWindows() + 1);
	}
	public void close(){
		this.open = false;
		setOpenWindows(getOpenWindows() - 1);
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public static boolean isMoving() {
		return moving;
	}
	public static void setMoving(boolean moving) {
		Window.moving = moving;
	}
	public boolean clickDragBar(int mouseX, int mouseY){
		return (mouseX>x && mouseX<x+width-40) && (mouseY>y && mouseY<y+16);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public static int getOpenWindows() {
		return openWindows;
	}
	public static void setOpenWindows(int openWindows) {
		Window.openWindows = openWindows;
	}
}
