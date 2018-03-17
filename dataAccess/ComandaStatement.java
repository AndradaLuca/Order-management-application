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
import model.Comanda;

public class ComandaStatement {

	protected static final Logger LOGGER = Logger.getLogger(ComandaStatement.class.getName());

	public static void insertComanda(Comanda comanda) throws Exception {
		try {

			Connection con = ConnectionFactory.getConnection();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO  comanda (idcomanda,idClient,idProdus,cantitate,stare) values ('"
							+ comanda.getIdComanda() + "','" + comanda.getIdClient() + "','" + comanda.getIdProdus()
							+ "','" + comanda.getCantitate() + "' ,'" + comanda.getStare() + "')");
			posted.executeUpdate();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void deleteComanda(Comanda comanda) {
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement posted = con
					.prepareStatement("DELETE FROM comanda WHERE idcomanda = '" + comanda.getIdComanda() + "'");
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateComanda(Comanda comanda) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement posted = connection
					.prepareStatement("UPDATE comanda SET   idClient = ?, idProdus = ?, cantitate = ?, stare = ? "
							+ " WHERE idcomanda = ? ");
			posted.setInt(1, comanda.getIdClient());
			posted.setInt(2, comanda.getIdProdus());
			posted.setInt(3, comanda.getCantitate());
			posted.setString(4, comanda.getStare());
			posted.setInt(5, comanda.getIdComanda());
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Comanda cauta(int idComanda) {
		Comanda comanda = null;

		String findStatementString = "SELECT * FROM comanda where idcomanda = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, idComanda);
			rs = findStatement.executeQuery();
			rs.next();

			int idClient = rs.getInt("idClient");
			int idProdus = rs.getInt("idProdus");
			int cantitate = rs.getInt("cantitate");
			String stare = rs.getString("stare");

			comanda = new Comanda(idComanda, idClient, idProdus, cantitate, stare);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Comanda:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);

		}

		return comanda;
	}

	public JTable getAll() {
		List<Comanda> list = new ArrayList<Comanda>();

		String all = "SELECT * FROM comanda ";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID Comanda");
		model.addColumn("ID Client");
		model.addColumn("ID Produs");
		model.addColumn("Cantitate");
		model.addColumn("Stare");

		JTable tco = new JTable(model);

		try {
			st = connection.prepareStatement(all);
			rs = st.executeQuery(all);
			// rs.next();

			while (rs.next()) {
				Comanda comanda = new Comanda(rs.getInt("idcomanda"), rs.getInt("idClient"), rs.getInt("idProdus"),
						rs.getInt("cantitate"), rs.getString("stare"));
				list.add(comanda);
				model.addRow(new Object[] { rs.getInt("idcomanda"), rs.getInt("idClient"), rs.getInt("idProdus"),
						rs.getInt("cantitate"), rs.getString("stare") });
				System.out.println(comanda.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tco;
	}

}
