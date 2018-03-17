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
import model.Furnizor;

public class FurnizorStatement {

	protected static final Logger LOGGER = Logger.getLogger(FurnizorStatement.class.getName());

	public static void insertFurnizor(Furnizor furnizor) throws Exception {
		try {

			Connection con = ConnectionFactory.getConnection();
			PreparedStatement posted = con.prepareStatement(
					"INSERT INTO  furnizor (idfurnizor,nume,adresa,tara) values ('" + furnizor.getIdFurnizor() + "','"
							+ furnizor.getNume() + "','" + furnizor.getAdresa() + "','" + furnizor.getTara() + "')");
			posted.executeUpdate();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void deleteFurnizor(Furnizor furnizor) {
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement posted = con
					.prepareStatement("DELETE FROM furnizor WHERE idfurnizor = '" + furnizor.getIdFurnizor() + "'");
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateFurnizor(Furnizor furnizor) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement posted = connection.prepareStatement(
					"UPDATE furnizor SET  nume = ?,  adresa = ?, tara = ? " + " WHERE idfurnizor = ? ");
			posted.setString(1, furnizor.getNume());
			posted.setString(2, furnizor.getAdresa());
			posted.setString(3, furnizor.getTara());
			posted.setInt(4, furnizor.getIdFurnizor());
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Furnizor cauta(int idFurnizor) {
		Furnizor furnizor = null;

		String findStatementString = "SELECT * FROM furnizor where idfurnizor = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, idFurnizor);
			rs = findStatement.executeQuery();
			rs.next();

			String nume = rs.getString("nume");
			String adresa = rs.getString("adresa");
			String tara = rs.getString("tara");

			furnizor = new Furnizor(idFurnizor, nume, adresa, tara);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Furnizor:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);

		}

		return furnizor;
	}

	public JTable getAll() {
		List<Furnizor> list = new ArrayList<Furnizor>();

		String all = "SELECT * FROM furnizor ";
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID Furnizor");
		model.addColumn("Nume");
		model.addColumn("Adresa");
		model.addColumn("Tara");

		JTable tf = new JTable(model);
		try {
			st = connection.prepareStatement(all);
			rs = st.executeQuery(all);
			// rs.next();

			while (rs.next()) {
				Furnizor furnizor = new Furnizor(rs.getInt("idfurnizor"), rs.getString("nume"), rs.getString("adresa"),
						rs.getString("tara"));
				list.add(furnizor);
				model.addRow(new Object[] { rs.getInt("idfurnizor"), rs.getString("nume"), rs.getString("adresa"),
						rs.getString("tara") });
				System.out.println(furnizor.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tf;
	}

}
