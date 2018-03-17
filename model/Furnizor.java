package model;

public class Furnizor {

	private int idFurnizor;
	private String nume;
	private String adresa;
	private String tara;

	public Furnizor(int idFurnizor, String nume, String adresa, String tara) {
		this.idFurnizor = idFurnizor;
		this.nume = nume;
		this.adresa = adresa;
		this.tara = tara;
	}

	public Furnizor() {
		// TODO Auto-generated constructor stub
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

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setTara(String tara) {
		this.tara = tara;
	}

	public String getTara() {
		return tara;
	}

	public String toString() {
		String afisare;
		afisare = "Id Furnizor: " + getIdFurnizor() + " Nume: " + getNume() + " Adresa: " + getAdresa() + " Tara: "
				+ getTara() + "\n";
		return afisare;
	}

}
