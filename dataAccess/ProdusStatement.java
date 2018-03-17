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
import model.Produs;

public class ProdusStatement {

	protected static final Logger LOGGER = Logger.getLogger(ProdusStatement.class.getName());

	public static void insertProdus(Produs produs) throws Exception {
		try {

			Connection con = ConnectionFactory.getConnection();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO  produs (idProdus,idFurnizor,nume,cantitate,pret) values ('"
							+ produs.getIdProdus() + "','" + produs.getIdFurnizor() + "','" + produs.getNume() + "','"
							+ produs.getCantitate() + "' ,'" + produs.getPret() + "')");
			posted.executeUpdate();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void deleteProdus(Produs produs) {
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement posted = con
					.prepareStatement("DELETE FROM produs WHERE idprodus = '" + produs.getIdProdus() + "'");
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateProdus(Produs produs) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement posted = connection.prepareStatement(
					"UPDATE produs SET  idFurnizor = ?, nume= ?, cantitate = ?, pret = ? " + " WHERE idProdus = ? ");
			posted.setInt(1, produs.getIdFurnizor());
			posted.setString(2, produs.getNume());
			posted.setInt(3, produs.getCantitate());
			posted.setInt(4, produs.getPret());
			posted.setInt(5, produs.getIdProdus());
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Produs cauta(int idProdus) {
		Produs produs = null;

		String findStatementString = "SELECT * FROM produs where idProdus = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, idProdus);
			rs = findStatement.executeQuery();
			rs.next();

			int idFurnizor = rs.getInt("idFurnizor");
			String nume = rs.getString("nume");
			int cantitate = rs.getInt("cantitate");
			int pret = rs.getInt("pret");

			produs = new Produs(idProdus, idFurnizor, nume, cantitate, pret);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Produs:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);

		}

		return produs;
	}

	public JTable getAll() {
		List<Produs> list = new ArrayList<Produs>();

		String all = "SELECT * FROM produs ";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID Produs");
		model.addColumn("ID Furnizor");
		model.addColumn("Nume");
		model.addColumn("Cantitate");
		model.addColumn("Pret");

		JTable tp = new JTable(model);

		try {
			st = connection.prepareStatement(all);
			rs = st.executeQuery(all);
			// rs.next();

			while (rs.next()) {
				Produs produs = new Produs(rs.getInt("idProdus"), rs.getInt("idFurnizor"), rs.getString("nume"),
						rs.getInt("cantitate"), rs.getInt("pret"));
				list.add(produs);
				model.addRow(new Object[] { rs.getInt("idProdus"), rs.getInt("idFurnizor"), rs.getString("nume"),
						rs.getInt("cantitate"), rs.getInt("pret") });
				System.out.println(produs.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tp;
	}

}
