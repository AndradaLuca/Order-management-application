package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import connection.ConnectionFactory;
import dataAccess.ClientStatement;
import dataAccess.FurnizorStatement;
import model.Client;
import model.Furnizor;

public class GuiFurnizor extends JFrame {

	public JFrame frame = new JFrame("Furnizor");

	private JLabel label1 = new JLabel("ID:");
	private JLabel label2 = new JLabel("Nume:");
	private JLabel label3 = new JLabel("Adresa:");
	private JLabel label5 = new JLabel("Tara:");
	private JTextField id = new JTextField("");
	private JTextField nume = new JTextField("");
	private JTextField adresa = new JTextField("");
	private JTextField tara = new JTextField("");
	private JButton adauga = new JButton("Add");
	private JButton sterge = new JButton("Delete");
	private JButton edit = new JButton("Edit");
	private JButton all = new JButton("View all");

	public GuiFurnizor() {

		addComponents();
		jFrameSetup();

	}

	private void jFrameSetup() {
		int WIDTH = 900;
		int HEIGHT = 700;

		frame.getContentPane().setBackground(Color.CYAN);
		frame.setBounds(100, 100, 900, 700);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setLocationRelativeTo(null);

	}

	public void addComponents() {

		label1.setBounds(50, 20, 100, 30);
		id.setBounds(80, 20, 100, 30);
		frame.getContentPane().add(label1);
		frame.getContentPane().add(id);

		label2.setBounds(40, 60, 100, 30);
		nume.setBounds(80, 60, 100, 30);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(nume);

		label3.setBounds(22, 100, 100, 30);
		adresa.setBounds(80, 100, 100, 30);
		frame.getContentPane().add(label3);
		frame.getContentPane().add(adresa);

		label5.setBounds(30, 140, 100, 30);
		tara.setBounds(80, 140, 100, 30);
		frame.getContentPane().add(label5);
		frame.getContentPane().add(tara);

		adauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Furnizor furnizor = new Furnizor(Integer.parseInt(id.getText()), nume.getText(), adresa.getText(),
						tara.getText());

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				FurnizorStatement fs = new FurnizorStatement();
				try {
					fs.insertFurnizor(furnizor);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				con.close();
			}
		});

		adauga.setBounds(200, 20, 80, 30);
		frame.getContentPane().add(adauga);

		sterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Furnizor furnizor = new Furnizor(Integer.parseInt(id.getText()), nume.getText(), adresa.getText(),
						tara.getText());

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				FurnizorStatement fs = new FurnizorStatement();
				try {
					fs.deleteFurnizor(furnizor);
					;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				con.close();
			}
		});

		sterge.setBounds(200, 60, 80, 30);
		frame.getContentPane().add(sterge);

		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Furnizor furnizor = new Furnizor(Integer.parseInt(id.getText()), nume.getText(), adresa.getText(),
						tara.getText());

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				FurnizorStatement fs = new FurnizorStatement();
				try {
					fs.updateFurnizor(furnizor);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				con.close();
			}
		});

		edit.setBounds(200, 100, 80, 30);
		frame.getContentPane().add(edit);

		all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				FurnizorStatement fs = new FurnizorStatement();
				try {
					fs.getAll();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				final JFrame framee = new JFrame("Furnizori");
				// frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				framee.setSize(500, 300);
				JPanel p = new JPanel();
				p.setBackground(Color.CYAN);
				p.add(new JScrollPane(fs.getAll()), BorderLayout.CENTER);
				p.setLayout(new FlowLayout());
				framee.setBackground(Color.CYAN);
				framee.setContentPane(p);
				framee.setVisible(true);

				con.close();
			}
		});

		all.setBounds(200, 140, 80, 30);
		frame.getContentPane().add(all);

	}
}