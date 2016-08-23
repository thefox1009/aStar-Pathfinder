import javax.swing.JFrame;

public class Main {
	
public static void main(String[] args) {
	 JFrame jf = new JFrame();
	 Panel panel = new Panel();
	 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(panel);
		jf.setResizable(false);
		jf.setTitle("A* Pathfinding");
				jf.setFocusable(true);
				jf.requestFocus();
				
	
	jf.pack();
	jf.setLocationRelativeTo(null);
	jf.setVisible(true);
	
	panel.start();
	
	
}


	





}
