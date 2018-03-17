package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connection.ConnectionFactory;
import model.Client;

public class ClientStatement {

	protected static final Logger LOGGER = Logger.getLogger(ClientStatement.class.getName());

	public static void insertClient(Client client) throws Exception {
		try {

			Connection con = ConnectionFactory.getConnection();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO  client (idclient,nume,prenume,adresa,email) values ('"
							+ client.getIdClient() + "','" + client.getNume() + "','" + client.getPrenume() + "','"
							+ client.getAdresa() + "' ,'" + client.getEmail() + "')");
			posted.executeUpdate();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void deleteClient(Client client) {
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement posted = con
					.prepareStatement("DELETE FROM client WHERE idclient = '" + client.getIdClient() + "'");
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateClient(Client client) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement posted = connection.prepareStatement(
					"UPDATE client SET  nume = ?, prenume = ?, adresa = ?, email = ? " + " WHERE idclient = ? ");
			posted.setString(1, client.getNume());
			posted.setString(2, client.getPrenume());
			posted.setString(3, client.getAdresa());
			posted.setString(4, client.getEmail());
			posted.setInt(5, client.getIdClient());
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Client cauta(int idClient) {
		Client client = null;

		String findStatementString = "SELECT * FROM client where idclient = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, idClient);
			rs = findStatement.executeQuery();
			rs.next();

			String nume = rs.getString("nume");
			String prenume = rs.getString("prenume");
			String adresa = rs.getString("adresa");
			String email = rs.getString("email");

			client = new Client(idClient, nume, prenume, adresa, email);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Client:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);

		}

		return client;
	}

	public JTable getAll() {
		ArrayList<Client> list = new ArrayList<Client>();

		String all = "SELECT * FROM client ";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID Client");
		model.addColumn("Nume");
		model.addColumn("Prenume");
		model.addColumn("Adresa");
		model.addColumn("Email");

		JTable tc = new JTable(model);
		try {
			st = connection.prepareStatement(all);
			rs = st.executeQuery(all);
			// rs.next();

			while (rs.next()) {
				Client client = new Client(rs.getInt("idclient"), rs.getString("nume"), rs.getString("prenume"),
						rs.getString("adresa"), rs.getString("email"));
				list.add(client);
				model.addRow(new Object[] { rs.getInt("idclient"), rs.getString("nume"), rs.getString("prenume"),
						rs.getString("adresa"), rs.getString("email") });
				System.out.println(client.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tc;

	}

}
