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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Bill.BillGenerator;
import connection.ConnectionFactory;
import dataAccess.ComandaStatement;
import dataAccess.ProdusStatement;
import model.Comanda;
import model.Produs;

public class GuiComanda extends JFrame {

	public JFrame frame = new JFrame("Comanda");

	private JLabel label1 = new JLabel("ID Comanda:");
	private JLabel label2 = new JLabel("ID Client:");
	private JLabel label3 = new JLabel("ID Produs:");
	private JLabel label4 = new JLabel("Cantitate:");
	private JLabel label5 = new JLabel("Stare:");
	private JTextField id = new JTextField("");
	private JTextField idC = new JTextField("");
	private JTextField idP = new JTextField("");
	private JTextField cantitate = new JTextField("");
	private JTextField stare = new JTextField("");
	private JButton adauga = new JButton("Add");
	private JButton sterge = new JButton("Delete");
	private JButton edit = new JButton("Edit");
	private JButton all = new JButton("View all");
	private JButton bill = new JButton("BILL");

	public GuiComanda() {

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

		label1.setBounds(40, 20, 100, 30);
		id.setBounds(120, 20, 100, 30);
		frame.getContentPane().add(label1);
		frame.getContentPane().add(id);

		label2.setBounds(50, 60, 100, 30);
		idC.setBounds(120, 60, 100, 30);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(idC);

		label3.setBounds(50, 100, 100, 30);
		idP.setBounds(120, 100, 100, 30);
		frame.getContentPane().add(label3);
		frame.getContentPane().add(idP);

		label4.setBounds(50, 140, 100, 30);
		cantitate.setBounds(120, 140, 100, 30);
		frame.getContentPane().add(label4);
		frame.getContentPane().add(cantitate);

		label5.setBounds(60, 180, 100, 30);
		stare.setBounds(120, 180, 100, 30);
		frame.getContentPane().add(label5);
		frame.getContentPane().add(stare);

		adauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Comanda comanda = new Comanda(Integer.parseInt(id.getText()), Integer.parseInt(idC.getText()),
						Integer.parseInt(idP.getText()), Integer.parseInt(cantitate.getText()), stare.getText());

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				ComandaStatement cs = new ComandaStatement();
				ProdusStatement ps = new ProdusStatement();

				Produs produs = new Produs();
				int cantitateProdus;
				int cantitateComanda;
				int cantitate;

				produs = ps.cauta(comanda.getIdProdus());

				cantitateProdus = produs.getCantitate();
				cantitateComanda = comanda.getCantitate();

				cantitate = cantitateProdus - cantitateComanda;

				if (cantitate < 0) {
					String afisare = "Cantitate depasita!\nIn Stoc: " + cantitateProdus + "\nCantitate ceruta:"
							+ cantitateComanda;
					JOptionPane.showMessageDialog(null, new JTextArea(afisare));
				}

				if (cantitate >= 0) {
					produs.setCantitate(cantitate);

					ps.updateProdus(produs);
					try {
						cs.insertComanda(comanda);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				con.close();
			}
		});

		adauga.setBounds(230, 30, 80, 30);
		frame.getContentPane().add(adauga);

		sterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Comanda comanda = new Comanda(Integer.parseInt(id.getText()), Integer.parseInt(idC.getText()),
						Integer.parseInt(idP.getText()), Integer.parseInt(cantitate.getText()), stare.getText());

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();

				ComandaStatement cs = new ComandaStatement();

				try {
					cs.deleteComanda(comanda);
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

				Comanda comanda = new Comanda(Integer.parseInt(id.getText()), Integer.parseInt(idC.getText()),
						Integer.parseInt(idP.getText()), Integer.parseInt(cantitate.getText()), stare.getText());

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				ComandaStatement cs = new ComandaStatement();
				try {
					cs.updateComanda(comanda);
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
				ComandaStatement cs = new ComandaStatement();
				try {
					cs.getAll();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				final JFrame framee = new JFrame("Comenzi");
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

		all.setBounds(230, 150, 80, 30);
		frame.getContentPane().add(all);

		bill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConnectionFactory con = new ConnectionFactory();
				con.createConnection();
				con.getConnection();
				Comanda comanda = new Comanda(Integer.parseInt(id.getText()), Integer.parseInt(idC.getText()),
						Integer.parseInt(idP.getText()), Integer.parseInt(cantitate.getText()), stare.getText());

				BillGenerator bill = new BillGenerator();
				bill.genereazaFactura(comanda);

				con.close();
			}
		});

		bill.setBounds(350, 90, 80, 30);
		frame.getContentPane().add(bill);

	}
}