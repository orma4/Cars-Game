/**
 * Assignment 2
 * Or Maman - 311392450 
 * Sami Saliba - 313552234
 */
/**
 * 
 * @Class {@link CityFrame}
 *
 */
package graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import graphics.CityPanel;

public class CityFrame extends JFrame {
	public CityFrame() {
		super("City");
		JFrame frame = new JFrame();
		JMenuItem menuItem;
		this.setBounds(0, 0, 800, 600);
		this.setSize(800, 600);
		CityPanel app = CityPanel.getInstance();
		this.add(app);
		JMenuBar mb = new JMenuBar();
		JMenu menu1 = new JMenu("File");
		mb.add(menu1);
		JMenu menu2 = new JMenu("Help");
		mb.add(menu2);
		this.setJMenuBar(mb);
		menuItem = new JMenuItem("Exit");
		menu1.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuItem = new JMenuItem("Help");
		menu2.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Home Work 2\nGUI");
			}
		});
		Component contents = app.createComponents();
		this.add(contents, BorderLayout.PAGE_END);
		this.setResizable(false);
	}

	public static void main(String[] args) {
		new CityFrame().setVisible(true);
	}
}