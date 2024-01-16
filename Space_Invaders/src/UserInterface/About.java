package UserInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class About extends UserInterface implements ActionListener{
	
	JFrame jFrame = new JFrame();
	
	public void actionPerformed(ActionEvent e) {
	
		JOptionPane.showMessageDialog(jFrame, " Furkan Ersel\n 20200702055"
				+ "\n furkan.ersel@std.yeditepe.edu.tr\n  ","Aplication Developer", JOptionPane.INFORMATION_MESSAGE, null);
	
	}
}
