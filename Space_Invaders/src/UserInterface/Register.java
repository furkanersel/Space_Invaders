package UserInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Register extends UserInterface implements ActionListener {
	
	
	public void actionPerformed(ActionEvent e) {
		
		setTitle("Registration Screen");
		setBounds(300, 90, 900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
 
        c = getContentPane();
        c.setLayout(null);
 
        title = new JLabel("Registration Screen");
        title.setFont(new Font("Impact", Font.BOLD, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);
 
        Entername = new JLabel("Name");
        Entername.setFont(new Font("Arial", Font.PLAIN, 20));
        Entername.setSize(100, 20);
        Entername.setLocation(100, 100);
        c.add(Entername);
 
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);
 
        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 150);
        c.add(password);
		
        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(150, 20);
        tpassword.setLocation(200, 150);
        c.add(tpassword);
        
	}
	
}

