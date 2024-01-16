package Main;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import UserInterface.UserInterface;

public class Main {

	public static void main(String[] args) {
		
		UserInterface user = new UserInterface();
		user.getContentPane().setBackground(Color.BLACK);
		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		user.setSize(800,715);
		user.setTitle("CSE 212 Term Project - Space Invader Game");
		user.setLocationRelativeTo(null);
		user.setResizable(false);
		user.setLayout(new FlowLayout());

	}

} 
