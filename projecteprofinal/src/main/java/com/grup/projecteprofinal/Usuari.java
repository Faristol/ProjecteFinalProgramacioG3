package com.grup.projecteprofinal;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Usuari {
	private String nom = null;
	private String cognoms = null;
	private String poblacio = null;
	private String correuElectronic = null;
	private String password = null;
	private ImageIcon imatgeIcon = null;
	private String imatgeCadena = null;
	private Image imatge;
	private String contrassenyaXifrada = null;
	private int fortalesa = 65536;
	private int longitudSalt = 16;
	private byte[] salt = null;
	private int longitudHash = 64 * 8;
	private byte[] imatgeBytes;
	public static ArrayList<JFrame> panellsActius = new ArrayList<JFrame>();

	public Usuari(String nom, String cognoms, String poblacio, String correu, String password, String imatge) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.cognoms = cognoms;
		this.poblacio = poblacio;
		this.correuElectronic = correu;
		this.password = password;
		this.imatgeCadena = imatge;
		this.imatgeIcon = new ImageIcon(imatgeCadena);
		this.imatge = imatgeIcon.getImage();
		xifrarContrassenya();
		serialitzarImatge();
		comprovarCorreu();
		System.out.println("hola");

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
		return imatgeIcon;
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

	public void setImatge(Image imatge) {
		this.imatge = imatge;
	}

	public String getContrassenyaXifrada() {
		return contrassenyaXifrada;
	}

	public int getFortalesa() {
		return fortalesa;
	}

	public void setContrassenyaXifrada(String contrassenyaXifrada) {
		this.contrassenyaXifrada = contrassenyaXifrada;
	}

	public int getLongitudSalt() {
		return longitudSalt;
	}

	public byte[] getSalt() {
		return salt;
	}

	public int getLongitudHash() {
		return longitudHash;
	}

	public byte[] getImagenBytes() {
		return imatgeBytes;
	}

	public void setFortalesa(int fortalesa) {
		this.fortalesa = fortalesa;
	}

	public void setLongitudSalt(int longitudSalt) {
		this.longitudSalt = longitudSalt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public void setLongitudHash(int longitudHash) {
		this.longitudHash = longitudHash;
	}

	public void setImagenBytes(byte[] imagenBytes) {
		this.imatgeBytes = imagenBytes;
	}

	public ImageIcon getImatgeIcon() {
		return imatgeIcon;
	}

	public String getImatgeCadena() {
		return imatgeCadena;
	}

	public void setImatgeIcon(ImageIcon imatgeIcon) {
		this.imatgeIcon = imatgeIcon;
	}

	public void setImatgeCadena(String imatgeCadena) {
		this.imatgeCadena = imatgeCadena;
	}

	public void xifrarContrassenya() {
		try {

			SecureRandom random = new SecureRandom();
			salt = new byte[longitudSalt];
			random.nextBytes(salt);
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, fortalesa, longitudHash);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			this.contrassenyaXifrada = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		} catch (InvalidKeySpecException e) {
			System.out.println(e);
		}

	}

	public void serialitzarImatge() {
		try {
			BufferedImage imagen = ImageIO.read(new File(imatgeCadena));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(imagen, "jpg", baos);
			imatgeBytes = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void comprovarCorreu() {
		boolean noexisteix = ProcessamentRegistre.comprovarCorreu(correuElectronic);
		if (noexisteix) {
			ProcessamentRegistre.guardarInformacioRegistreTaula1(nom, cognoms, poblacio, correuElectronic, imatgeBytes);
			ProcessamentRegistre.guardarInformacioRegistreTaula2(contrassenyaXifrada, fortalesa, salt, longitudHash);
			JOptionPane.showMessageDialog(null,
					"El registre ha resultat satisfact�ri. Benvingut a la llar dels jocs " + nom + ".", "Registre",
					JOptionPane.INFORMATION_MESSAGE);
			// ara crear un objecte del frame que va despr�s de l'inici de sessi� on estan
			// els tres jocs

			InterficiePrincipal.ferVisibleTancaSessio();
			InterficieSeleccioJocs panellJocs = new InterficieSeleccioJocs();
			Usuari.panellsActius.add(panellJocs);
			return;
		}
		JOptionPane.showMessageDialog(null, "El correu ja existeix, proporciona altre.", "Error",
				JOptionPane.ERROR_MESSAGE);

	}

	public static void tancarPanells() {
		for (JFrame panells : panellsActius) {
			panells.dispose();
		}
		panellsActius.clear();
	}

}
