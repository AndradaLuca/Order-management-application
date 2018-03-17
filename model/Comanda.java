package model;

import java.util.*;

public class Comanda {

	private int idComanda;
	private int idClient;
	private int idProdus;
	private int cantitate;
	private String stare;

	public Comanda(int idComanda, int idClient, int idProdus, int cantitate, String stare) {
		this.idComanda = idComanda;
		this.idClient = idClient;
		this.idProdus = idProdus;
		this.cantitate = cantitate;
		this.stare = stare;

	}

	public Comanda() {
		// TODO Auto-generated constructor stub
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}

	public int getIdProdus() {
		return idProdus;
	}

	public void setStare(String stare) {
		this.stare = stare;
	}

	public String getStare() {
		return stare;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public int getCantitate() {
		return cantitate;
	}

	public String toString() {
		String afisare;
		afisare = "Id Comanda :" + getIdComanda() + " id Client: " + getIdClient() + " Id Produs:" + getIdProdus()
				+ " Stare: " + getStare() + " Cantiate:" + getCantitate();
		return afisare;
	}

}
