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
import dataAccess.FurnizorStatement;
import dataAccess.ProdusStatement;
import model.Furnizor;
import model.Produs;

public class GuiProdus extends JFrame {

	public JFrame frame = new JFrame("Produs");

	private JLabel label1 = new JLabel("ID Produs:");
	private JLabel label2 = new JLabel("ID Furnizor:");
	private JLabel label3 = new JLabel("Nume:");
	private JLabel label4 = new JLabel("Cantitate:");
	private JLabel label5 = new JLabel("Pret:");
	private JTextField idP = new JTextField("");
	private JTextField idF = new JTextField("");
	private JTextField nume = new JTextField("");
	private JTextField cantitate = new JTextField("");
	private JTextField pret = new JTextField("");
	private JButton adauga = new JButton("Add");
	private JButton sterge = new JButton("Delete");
	private JButton edit = new JButton("Edit");
	private JButton all = new JButton("View all");

	public GuiProdus() {

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
		idP.setBounds(120, 20, 100, 30);
		frame.getContentPane().add(label1);
		frame.getContentPane().add(idP);

		label2.setBounds(50, 60, 100, 30);
		idF.setBounds(120, 60, 100, 30);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(idF);

		label3.setBounds(60, 100, 100, 30);
		nume.setBounds(120, 100, 100, 30);
		frame.getContentPane().add(label3);
		frame.getContentPane().add(nume);

		label4.setBounds(50, 140, 100, 30);
		cantitate.setBounds(120, 140, 100, 30);
		frame.getContentPane().add(label4);
		frame.getContentPane().add(cantitate);

		label5.setBounds(70, 180, 100, 30);
		pret.setBounds(120, 180, 100, 30);
		frame.getContentPane().add(label5);
		frame.getContentPane().add(pret);

		adauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Produs produs = new Produs(Integer.parseInt(idP.getText()), Integer.parseInt(idF.getText()),
						nume.getText(), Integer.parseInt(cantitate.getText()), Integer.parseInt(pret.getText()));

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				ProdusStatement ps = new ProdusStatement();
				try {
					ps.insertProdus(produs);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				con.close();
			}
		});

		adauga.setBounds(230, 30, 80, 30);
		frame.getContentPane().add(adauga);

		sterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Produs produs = new Produs(Integer.parseInt(idP.getText()), Integer.parseInt(idF.getText()),
						nume.getText(), Integer.parseInt(cantitate.getText()), Integer.parseInt(pret.getText()));

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				ProdusStatement ps = new ProdusStatement();
				try {
					ps.deleteProdus(produs);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				con.close();
			}
		});

		sterge.setBounds(230, 70, 80, 30);
		frame.getContentPane().add(sterge);

		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Produs produs = new Produs(Integer.parseInt(idP.getText()), Integer.parseInt(idF.getText()),
						nume.getText(), Integer.parseInt(cantitate.getText()), Integer.parseInt(pret.getText()));

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				ProdusStatement ps = new ProdusStatement();
				try {
					ps.updateProdus(produs);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				con.close();
			}
		});

		edit.setBounds(230, 110, 80, 30);
		frame.getContentPane().add(edit);

		all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				ProdusStatement ps = new ProdusStatement();
				final JFrame framee = new JFrame("Produs");
				// frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				framee.setSize(500, 300);
				JPanel p = new JPanel();
				p.setBackground(Color.CYAN);
				p.add(new JScrollPane(ps.getAll()), BorderLayout.CENTER);
				p.setLayout(new FlowLayout());
				framee.setBackground(Color.CYAN);
				framee.setContentPane(p);
				framee.setVisible(true);
				con.close();
			}
		});

		all.setBounds(230, 150, 80, 30);
		frame.getContentPane().add(all);

	}
}