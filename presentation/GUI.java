package presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class GUI extends JFrame {

	private JFrame frame = new JFrame("Tema 3");

	private JButton button = new JButton("Client");
	private JButton button1 = new JButton("Furnizor");
	private JButton button2 = new JButton("Produs");
	private JButton button3 = new JButton("Comanda");

	public GUI() {

		addComponents();
		jFrameSetup();

	}

	private void jFrameSetup() {
		int WIDTH = 900;
		int HEIGHT = 700;

		frame.getContentPane().setBackground(Color.CYAN);
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setLocationRelativeTo(null);

	}

	public void addComponents() {

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GuiClient interfata = new GuiClient();

				interfata.frame.setVisible(true);

			}

		});

		button.setBounds(330, 100, 200, 50);
		frame.getContentPane().add(button);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GuiFurnizor interfata = new GuiFurnizor();

				interfata.frame.setVisible(true);

			}
		});

		button1.setBounds(330, 200, 200, 50);
		frame.getContentPane().add(button1);

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GuiProdus interfata = new GuiProdus();
				interfata.frame.setVisible(true);

			}
		});

		button2.setBounds(330, 300, 200, 50);
		frame.getContentPane().add(button2);

		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GuiComanda interfata = new GuiComanda();
				interfata.frame.setVisible(true);

			}
		});

		button3.setBounds(330, 400, 200, 50);
		frame.getContentPane().add(button3);

	}

	public static void main(String arg[]) {

		GUI interfata = new GUI();

		interfata.frame.setVisible(true);

	}

}