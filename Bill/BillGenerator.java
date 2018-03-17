package Bill;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dataAccess.ClientStatement;
import dataAccess.FurnizorStatement;
import dataAccess.ProdusStatement;
import model.Client;
import model.Comanda;
import model.Furnizor;
import model.Produs;

public class BillGenerator {

	public void genereazaFactura(Comanda comanda) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		File file = new File(comanda.getIdComanda() + ".txt");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		FileWriter fw;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Customer client = clienti.getCustomer(order.getCustomerID());
		// Product produs = warehouse.getProduct(order.getProductID());

		Client client = new Client();
		Produs produs = new Produs();
		Furnizor furnizor = new Furnizor();
		ClientStatement cs = new ClientStatement();
		ProdusStatement ps = new ProdusStatement();
		FurnizorStatement fs = new FurnizorStatement();

		client = cs.cauta(comanda.getIdClient());
		produs = ps.cauta(comanda.getIdProdus());
		furnizor = fs.cauta(produs.getIdFurnizor());

		String textFactura = "---- FACTURA ----\r\n\r\nDATA: " + dateFormat.format(date)
				+ "\r\n\r\n----DATE PERSONALE----\r\n\r\nNume: " + client.getNume() + "\r\nPrenume: "
				+ client.getPrenume() + "\r\nAdresa: " + client.getAdresa() + "\r\nEmail: " + client.getEmail()
				+ "\r\n\r\n---- PRODUS CUMPARAT ----\r\n\r\nDenumire: " + produs.getNume()
				+ "\r\n\r\n---- FURNIZAT DE ----\r\n\r\nNume: " + furnizor.getNume() + "\r\nAdresa: "
				+ furnizor.getAdresa() + "\r\nTara: " + furnizor.getTara() + "\r\nCantitate: " + comanda.getCantitate()
				+ "\r\nPret total: " + (comanda.getCantitate() * produs.getPret() + "\r\nStare: " + comanda.getStare());
		try {
			bw.write(textFactura);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
