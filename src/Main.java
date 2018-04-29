import javax.swing.JFrame;


public class Main {
	static int SCREEN_W = 1024;
	static int SCREEN_H = 768;
	
	public static void main(String args[]){
		JFrame frame = new JFrame("ZombieRPG");
		Render panel = new Render();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(SCREEN_W, SCREEN_H);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(panel);
		frame.setVisible(true);
	}
}
