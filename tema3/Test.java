package tema3;

import java.util.ArrayList;

import connection.ConnectionFactory;
import dataAccess.ClientStatement;
import dataAccess.ComandaStatement;
import dataAccess.FurnizorStatement;
import dataAccess.ProdusStatement;
import model.Client;
import model.Comanda;
import model.Furnizor;
import model.Produs;

public class Test {
	public static void main(String[] args) throws Exception {

		ConnectionFactory con = new ConnectionFactory();
		con.createConnection();
		con.getConnection();

		Client client1 = new Client(1, "Teglas", "Florin", "Mehedinti 21 bl M2 ap 40", "teglas.florin@gmail.com");
		Furnizor furnizor1 = new Furnizor(1, "Caroso", "Str.Bucuresti nr.16", "Romania");
		Comanda comanda1 = new Comanda(1, 1, 1, 10, "Prelucrare");

		FurnizorStatement f = new FurnizorStatement();
		Produs produs1 = new Produs(1, 1, "Mere", 15, 2);

		ClientStatement cs = new ClientStatement();
		ProdusStatement ps = new ProdusStatement();
		ComandaStatement cos = new ComandaStatement();

		// cs.insertClient(client1);
		// ps.insertProdus(produs1);

		int p = comanda1.getCantitate();

		int c = produs1.getCantitate() - p;
		System.out.println(c);
		if (c >= 0) {

			produs1.setCantitate(c);
			// System.out.println(produs1.toString());
			ps.updateProdus(produs1);
			cos.updateComanda(comanda1);
			// cos.insertComanda(comanda1);
		}

		cs.getAll();
		ps.getAll();
		f.getAll();
		cos.getAll();

		con.close();

	}

}
