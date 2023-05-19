package com.grup.projecteprofinal;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class ProcesamentIniciSessio {
	private static String url;
	private static String user = "1daw03_pro";
	private static String password = "dEQ1e3Q2ZD";

	public ProcesamentIniciSessio() {
		// TODO Auto-generated constructor stub

	}

	public static void verificacioCamps(String correuElectronic, String contrassenya) {
		// fer primera consulta per veure si el correu existeix
		// si existeix fer altra consulta per veure si hi ha correspondencia entre
		// correu i contra
		obtindreLesConnexion();
		String nom = "";
		String contrassenyaXifrada = null;
		int fortalesa = 0;
		byte[] salt = null;
		int longitudHash = 0;
		boolean noexisteixCorreu = ProcessamentRegistre.comprovarCorreu(correuElectronic);
		if (noexisteixCorreu) {
			JOptionPane.showMessageDialog(null, "El correu introduït no existeix", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// si existeix el correu obtinguem tots els parametres de la contrassenya per a
		// desxifrar-los

		String consulta = "SELECT * FROM tabla2 WHERE id = (SELECT id FROM tabla1 WHERE correuElectronic = ?)";

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement(consulta)) {

			statement.setString(1, correuElectronic);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				contrassenyaXifrada = resultSet.getString("contrassenyaXifrada");
				fortalesa = resultSet.getInt("fortalesa");
				salt = resultSet.getBytes("salt");
				longitudHash = resultSet.getInt("longitudHash");
			}
			String consulta2 = "SELECT nom FROM tabla1 WHERE correuElectronic = ?";
			PreparedStatement statement2 = connection.prepareStatement(consulta2);
			statement2.setString(1, correuElectronic);
			ResultSet resultSet2 = statement2.executeQuery();
			if (resultSet2.next()) {
				nom = resultSet2.getString("nom");
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// desxifrar contrassenya
		String contrassenyaHash = desxifrarContrassenya(contrassenya, fortalesa, salt, longitudHash);
		if (contrassenyaHash == null || !contrassenyaHash.equals(contrassenyaXifrada)) {
			JOptionPane.showMessageDialog(null, "La contrassenya és incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null,
				"El registre ha resultat satisfactòri. Benvingut a la llar dels jocs " + nom + ".", "Inici sessió",
				JOptionPane.INFORMATION_MESSAGE);
		InterficiePrincipal.ferVisibleTancaSessio();
		InterficieSeleccioJocs seleccioJocs = new InterficieSeleccioJocs();
		Usuari.panellsActius.add(seleccioJocs);
	}

	public static String desxifrarContrassenya(String contrassenya, int fortalesa, byte[] salt, int longitudHash) {
		String contrassenyaHash = null;
		try {
			KeySpec spec = new PBEKeySpec(contrassenya.toCharArray(), salt, fortalesa, longitudHash);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] nouHash = factory.generateSecret(spec).getEncoded();
			contrassenyaHash = Base64.getEncoder().encodeToString(nouHash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return contrassenyaHash;
	}

	public static void obtindreLesConnexion() {
		Enumeration e;
		try {
			e = NetworkInterface.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				NetworkInterface n = (NetworkInterface) e.nextElement();
				Enumeration ee = n.getInetAddresses();
				while (ee.hasMoreElements()) {
					InetAddress i = (InetAddress) ee.nextElement();
					String adress = "" + (i.getHostAddress());

					if (adress.contains("1922.168.14")) {
						url = "jdbc:mysql://" + adress + "/1daw03_pro";
						user = "1daw03_pro";
						password = "dEQ1e3Q2ZD";
						return;
					}

				}
			}
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		url = "jdbc:mysql://ticsimarro.org:3306/1daw03_pro";

	}

}
