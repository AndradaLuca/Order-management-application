package model;

public class Client {

	public int idclient;
	public String nume;
	public String prenume;
	public String adresa;
	public String email;

	public Client(int idclient, String nume, String prenume, String adresa, String email) {
		this.idclient = idclient;
		this.nume = nume;
		this.prenume = prenume;
		this.adresa = adresa;
		this.email = email;
	}

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public void setIdClient(int idclient) {
		this.idclient = idclient;
	}

	public int getIdClient() {
		return idclient;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getNume() {
		return nume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String toString() {
		String afisare;
		afisare = "Id Client: " + getIdClient() + " Nume: " + getNume() + " Prenume: " + getPrenume() + " Adresa: "
				+ getAdresa() + " Email " + getEmail() + "\n";
		return afisare;
	}
}
