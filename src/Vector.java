public class Vector {
	static double gravity = 6.67384*Math.pow(10, -11);
	static public double distance(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
	}
	static public double angle(double x1, double y1, double x2, double y2){
		if(x1 <= x2){
			return 90 + Math.toDegrees(Math.atan((y2-y1)/(x2-x1)));
		}
		else{
			return 270 - Math.toDegrees(Math.atan(-(y2-y1)/(x2-x1)));
		}
	}
	static public double xComponent(double angleDeg, double magnitude){
		return (Math.sin(Math.toRadians(angleDeg))*magnitude);
	}
	static public double yComponent(double angleDeg, double magnitude){
		return (Math.cos(Math.toRadians(angleDeg))*magnitude);
	}
	static public double modulus(double number){
		if(number >= 0){
			return number;
		}
		else{
			return -number;
		}
	}
}
