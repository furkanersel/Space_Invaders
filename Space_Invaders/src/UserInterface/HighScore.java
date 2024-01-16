package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import javax.swing.JLabel;

public class HighScore extends UserInterface implements ActionListener {

	LinkedHashMap<String, String> sortedMap = new LinkedHashMap<>();
	ArrayList<String> list = new ArrayList<>();

	private int height = 60;

	@Override
	public void actionPerformed(ActionEvent e) {

		int no = 1;

		setTitle("High Score");
		setBounds(300, 90, 900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);

		title = new JLabel("HIGH SCORES");
		title.setFont(new Font("Impact", Font.BOLD, 30));
		title.setSize(300, 40);
		title.setForeground(Color.cyan);
		title.setLocation(390, 20);
		c.add(title);

		try {
			Scanner myReader = new Scanner(filescore);
			while (myReader.hasNext()) {
				String name = myReader.nextLine();
				String score = myReader.nextLine();
				for (Entry<String, String> entry : scores.entrySet()) {
				    if(name.equals(entry.getKey())) {
				    	
				    	int value1 = Integer.parseInt(entry.getValue());
						int value2 = Integer.parseInt(score);
						
						if(value1 > value2) {
							score = entry.getValue();
						}
					}
				  }
				
				scores.put(name, score);
				
			}
			myReader.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		List<Map.Entry<String, String>> sortedEntries = new ArrayList<>(scores.entrySet());

		Collections.sort(sortedEntries, new Comparator<Map.Entry<String, String>>() {
			@Override
			public int compare(Map.Entry<String, String> entry1, Map.Entry<String, String> entry2) {
				
				int value1 = Integer.parseInt(entry1.getValue());
				int value2 = Integer.parseInt(entry2.getValue());
				
				return Integer.compare(value1, value2);
			}
		});

		Collections.reverse(sortedEntries);

		for (Map.Entry<String, String> entry : sortedEntries) {
			
			JLabel noLabel = new JLabel("" + no);
			noLabel.setFont(new Font("Impact", Font.BOLD, 20));
			noLabel.setSize(300, 40);
			noLabel.setForeground(Color.white);
			noLabel.setLocation(250, height);
			c.add(noLabel);
			no++;

			JLabel keyLabel = new JLabel(entry.getKey());
			keyLabel.setFont(new Font("Impact", Font.BOLD, 20));
			keyLabel.setSize(300, 40);
			keyLabel.setForeground(Color.white);
			keyLabel.setLocation(300, height);
			c.add(keyLabel);

			JLabel valueLabel = new JLabel(entry.getValue());
			valueLabel.setFont(new Font("Impact", Font.BOLD, 20));
			valueLabel.setSize(300, 40);
			valueLabel.setForeground(Color.white);
			valueLabel.setLocation(600, height);
			c.add(valueLabel);
			height += 30;
		}

	}

}
