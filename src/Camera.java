
public class Camera {
	static double x, y;
	public Camera(double x, double y){
		Camera.x = 0;
		Camera.y = 0;
	}
	public static double getX() {
		return x;
	}
	public static void setX(double x) {
		Camera.x = x;
	}
	public static double getY() {
		return y;
	}
	public static void setY(double y) {
		Camera.y = y;
	}
	public static double cameraX(double num){
		return Main.SCREEN_W/2.0 + (num - x);
	}
	public static double cameraY(double num){
		return Main.SCREEN_H/2.0 + (num - y);
	}
}
