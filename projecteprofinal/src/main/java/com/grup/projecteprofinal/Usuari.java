package com.grup.projecteprofinal;

import java.io.File;

import javax.swing.ImageIcon;

public class Usuari {
	private String nom = null;
	private String cognoms = null;
	private String poblacio = null;
	private String correuElectronic = null;
	private String password = null;
	private ImageIcon imatge = null;
	private String contrassenyaXifrada = null;
	private final int fortalesa;
	private String salt = null;
	private int longitud;

	public Usuari(String nom, String cognoms, String poblacio, String correu, String password, String imatge) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.cognoms = cognoms;
		this.poblacio = poblacio;
		this.correuElectronic = correu;
		this.password = password;
		this.imatge = new ImageIcon("img" + File.separator + imatge + ".jpg");
	}

	public String getNom() {
		return nom;
	}

	public String getCognoms() {
		return cognoms;
	}

	public String getPoblacio() {
		return poblacio;
	}

	public String getCorreuElectronic() {
		return correuElectronic;
	}

	public String getPassword() {
		return password;
	}

	public ImageIcon getImatge() {
		return imatge;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}

	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}

	public void setCorreuElectronic(String correuElectronic) {
		this.correuElectronic = correuElectronic;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setImatge(ImageIcon imatge) {
		this.imatge = imatge;
	}

	public String getContrassenyaXifrada() {
		return contrassenyaXifrada;
	}

	public int getFortalesa() {
		return fortalesa;
	}

	public String getSalt() {
		return salt;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setContrassenyaXifrada(String contrassenyaXifrada) {
		this.contrassenyaXifrada = contrassenyaXifrada;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

}
