package model;

public class Produs {

	private int idprodus;
	private int idFurnizor;
	private String nume;
	private int cantitate;
	private int pret;

	public Produs(int idProdus, int idFurnizor, String nume, int cantitate, int pret) {
		this.idprodus = idProdus;
		this.idFurnizor = idFurnizor;
		this.nume = nume;
		this.cantitate = cantitate;
		this.pret = pret;
	}

	public Produs() {
		// TODO Auto-generated constructor stub
	}

	public void setIdProdus(int idProdus) {
		this.idprodus = idProdus;
	}

	public int getIdProdus() {
		return idprodus;
	}

	public void setIdFurnizor(int idFurnizor) {
		this.idFurnizor = idFurnizor;
	}

	public int getIdFurnizor() {
		return idFurnizor;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getNume() {
		return nume;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}

	public int getPret() {
		return pret;
	}

	public String toString() {
		String afisare;
		afisare = "Id Produs " + getIdProdus() + " Id Furnizor " + getIdFurnizor() + " Nume: " + getNume()
				+ " Cantitate: " + getCantitate() + " Pret: " + getPret();
		return afisare;
	}
}
