package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bll.Validators;
import connection.ConnectionFactory;
import dataAccess.ClientStatement;
import model.Client;

public class GuiClient extends JFrame {

	public JFrame frame = new JFrame("Client");
	private int i = 0;
	private JLabel label1 = new JLabel("ID:");
	private JLabel label2 = new JLabel("Nume:");
	private JLabel label3 = new JLabel("Prenume:");
	private JLabel label4 = new JLabel("Adresa:");
	private JLabel label5 = new JLabel("Email:");
	private JTextField id = new JTextField("");
	private JTextField nume = new JTextField("");
	private JTextField prenume = new JTextField("");
	private JTextField adresa = new JTextField("");
	private JTextField email = new JTextField("");
	private JButton adauga = new JButton("Add");
	private JButton sterge = new JButton("Delete");
	private JButton edit = new JButton("Edit");
	private JButton all = new JButton("View all");
	public JTable tblClienti;
	private JScrollPane scrClienti;
	JTable t = new JTable();

	public GuiClient() {

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
		prenume.setBounds(80, 100, 100, 30);
		frame.getContentPane().add(label3);
		frame.getContentPane().add(prenume);

		label4.setBounds(30, 140, 100, 30);
		adresa.setBounds(80, 140, 100, 30);
		frame.getContentPane().add(label4);
		frame.getContentPane().add(adresa);

		label5.setBounds(40, 180, 100, 30);
		email.setBounds(80, 180, 100, 30);
		frame.getContentPane().add(label5);
		frame.getContentPane().add(email);

		adauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Client client = new Client(Integer.parseInt(id.getText()), nume.getText(), prenume.getText(),
						adresa.getText(), email.getText());

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				ClientStatement cs = new ClientStatement();

				Validators email = new Validators();

				if (email.EmailValidator(client) == 0) {
					String afisare;
					afisare = "Email incorect!";
					JOptionPane.showMessageDialog(null, new JTextArea(afisare));
				}
				if (email.EmailValidator(client) == 1) {

					try {
						cs.insertClient(client);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				con.close();
			}
		});

		adauga.setBounds(200, 30, 80, 30);
		frame.getContentPane().add(adauga);

		sterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Client client = new Client(Integer.parseInt(id.getText()), nume.getText(), prenume.getText(),
						adresa.getText(), email.getText());

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				ClientStatement cs = new ClientStatement();
				try {
					cs.deleteClient(client);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				con.close();
			}
		});

		sterge.setBounds(200, 70, 80, 30);
		frame.getContentPane().add(sterge);

		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Client client = new Client(Integer.parseInt(id.getText()), nume.getText(), prenume.getText(),
						adresa.getText(), email.getText());

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				ClientStatement cs = new ClientStatement();

				Validators email = new Validators();

				if (email.EmailValidator(client) == 0) {
					String afisare;
					afisare = "Email incorect!";
					JOptionPane.showMessageDialog(null, new JTextArea(afisare));
				}
				if (email.EmailValidator(client) == 1) {
					try {
						cs.updateClient(client);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				con.close();
			}
		});

		edit.setBounds(200, 110, 80, 30);
		frame.getContentPane().add(edit);

		all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				ClientStatement cs = new ClientStatement();

				DefaultTableModel tabel = new DefaultTableModel();

				final JFrame framee = new JFrame("Clienti");
				// frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				framee.setSize(500, 300);
				JPanel p = new JPanel();
				p.setBackground(Color.CYAN);
				p.add(new JScrollPane(cs.getAll()), BorderLayout.CENTER);
				p.setLayout(new FlowLayout());
				framee.setBackground(Color.CYAN);
				framee.setContentPane(p);
				framee.setVisible(true);

				con.close();
			}
		});
		all.setBounds(200, 150, 80, 30);
		frame.getContentPane().add(all);

	}
}
