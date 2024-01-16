package UserInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import GameObject.GamePanel;

public class UserInterface extends JFrame {

	public static String name;
	public Container c;
	public JLabel title;
	public JLabel Entername;
	public JLabel password;
	public JTextField tname;
	public JPasswordField tpassword;
	public JButton login;
	public JButton signup;
	
	
	File fileuser = new File("src/users.txt/");
	File filescore = new File("src/scores.txt/");
	HashMap<String, String> users = new HashMap<String, String>();
	HashMap<String, String> scores = new HashMap<String, String>();
	
	public UserInterface() {
		
		ImageIcon background = new ImageIcon("img/userInterface.bg.png/");
		JButton button = new JButton(background);
		button.setBackground(Color.BLACK);
		button.setBounds(90, 0, 600, 600);
		this.add(button);
		
		BufferedImage image;
		try {
			image = ImageIO.read(new File("img/icon.png"));
			this.setIconImage(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		JMenuBar menubar = new JMenuBar();
		
		JMenu file_menu = new JMenu("File");
		JMenuItem register_Item = new JMenuItem("Register");
		JMenuItem playGame_Item = new JMenuItem("Play Game");
		JMenuItem highScore_Item = new JMenuItem("High Score");
		JMenuItem quit_Item = new JMenuItem("Quit");
		file_menu.add(register_Item);
		file_menu.add(playGame_Item);
		file_menu.add(highScore_Item);
		file_menu.add(quit_Item);
		
		JMenu help_menu = new JMenu("Help");
		JMenuItem about_Item = new JMenuItem("About");
		help_menu.add(about_Item);
		
		this.setJMenuBar(menubar);
		
		menubar.add(file_menu);
		menubar.add(help_menu);
		
		
		
		register_Item.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.actionPerformed(e);
				register.login_signin();
			}

		});
		
		about_Item.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.actionPerformed(e);
				
			}
		});
		
		quit_Item.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			}
		});
		
		playGame_Item.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				playGame game = new playGame();
				game.actionPerformed(e);
				game.login_signin();
			}
		});
		
		highScore_Item.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				HighScore highScore = new HighScore();
				highScore.actionPerformed(e);
				
			}
		});
		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				playGame game = new playGame();
				game.actionPerformed(e);
				game.login_signin();
			}
		});
		this.setVisible(true);
		
		
		
	}

	public void login_signin() {
		login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(150, 200);
        login.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent a) {
        		
        		String password = new String(tpassword.getPassword());
        		name = tname.getText();
				
				try {
				      Scanner myReader = new Scanner(fileuser);
				      while (myReader.hasNext()) {
				        String dataName = myReader.nextLine();
				        String dataPassword = myReader.nextLine();
				        users.put(dataName, dataPassword);
				      }
				      myReader.close();
				    } catch (FileNotFoundException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
				if(users.containsKey(name)) {
					if(users.get(name).equals(password)) {
						JOptionPane.showMessageDialog(null, "Welcome enjoy the game");
						int levelChoice = Integer.parseInt(JOptionPane.showInputDialog("Please select one of the level from below:\n"
										+ "Level 1(Novice)\n" + "Level 2 (Intermediate)\n" + "Level 3 (Advance)"));
						while (levelChoice > 3 || levelChoice < 1 ) {
							JOptionPane.showMessageDialog(null, "İnvalid Level", "ERROR", JOptionPane.ERROR_MESSAGE);
							levelChoice = Integer.parseInt(JOptionPane.showInputDialog("Please select one of the level from below:\n"
									+ "Level 1(Novice)\n" + "Level 2 (Intermediate)\n" + "Level 3 (Advance)"));
						}
							JFrame frame = new JFrame("Game Screen");
							GamePanel gamePanel = new GamePanel(levelChoice);
							gamePanel.StartGameThread();
							frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							frame.getContentPane().add(gamePanel);
							frame.pack();
							frame.setLocationRelativeTo(null);
							frame.setResizable(false);
							frame.setVisible(true);
							if(gamePanel.gameover) {
								frame.dispose();
							}
						}
					else {
						JOptionPane.showMessageDialog(null, "Password wrong..");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Usersname or Password wrong...");
				}
				  
				  }
		});
        c.add(login);
        
        signup = new JButton("Sign Up");
        signup.setFont(new Font("Arial", Font.PLAIN, 15));
        signup.setSize(100, 20);
        signup.setLocation(275, 200);
        signup.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent a) {
				
				String password = new String(tpassword.getPassword());
				name = tname.getText();
			
				if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Entry Username");
				}
				else if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Entry Password");
				}
				try {
				      Scanner myReader = new Scanner(fileuser);
				      while (myReader.hasNext()) {
				        String dataName = myReader.nextLine();
				        String dataPassword = myReader.nextLine();
				        users.put(dataName, dataPassword);
				      }
				      myReader.close();
				    } catch (FileNotFoundException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
				if(!(users.containsKey(name))) {
					users.put(name, password);
					JOptionPane.showMessageDialog(null, "Registration Successful");
					try {
					      FileWriter myWriter = new FileWriter(fileuser,true);
					      myWriter.write(name + "\n" + password + "\n");
					      myWriter.close();
					    } catch (IOException e) {
					      e.printStackTrace();
					    }
				}
				else {
					JOptionPane.showMessageDialog(null, "Username is available try a different name or login");
				}
			}
		});
        c.add(signup);
	}
}