package com.grup.projecteprofinal;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class ProcessamentRegistre {
	private static String url;
	private static String user = "1daw03_pro";
	private static String password = "dEQ1e3Q2ZD";

	public ProcessamentRegistre() {
		// TODO Auto-generated constructor stub
	}

	public static void procesamentCampsRegistre(String nom, String cognoms, String poblacio, String correu,
			String password1, String password2, String imatge) {
		obtindreLesConnexion();
		// Par�metres a comprovar correu i correspond�ncia entre contrassenyes.
		// tamb� que les contrassenyes tinguen com a m�nim 8 car�cters.
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		boolean correuCorrecte = Pattern.compile(regex).matcher(correu).matches();
		boolean longitudCorrecta = (password1.length() >= 8) && (password2.length() >= 8);
		boolean sonIguals = password1.equals(password2);
		if (!correuCorrecte) {
			JOptionPane.showMessageDialog(null, "El format de correu introduit no es valid", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!longitudCorrecta) {
			JOptionPane.showMessageDialog(null, "Les contrassenyes deuen tindre almenys 8 caracters", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!sonIguals) {
			JOptionPane.showMessageDialog(null, "Les contrassenyes no coincideixen", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Usuari nouUsuari = new Usuari(nom, cognoms, poblacio, correu, password1, imatge);

	}

	public static boolean comprovarCorreu(String correuElectronic) {
		obtindreLesConnexion();

		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Connection connection = DriverManager.getConnection(url, user, password);
			Statement cerca = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT COUNT('correuElectronic') FROM tabla1 WHERE correuElectronic='" + correuElectronic
					+ "'";
			ResultSet r = cerca.executeQuery(consulta);
			r.next();
			int numCorreusCoincidents = r.getInt(1);
			if (numCorreusCoincidents == 0) {
				connection.close();

				return true;
			}
			connection.close();
			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public static void guardarInformacioRegistreTaula1(String nom, String cognoms, String poblacio,
			String correuElectronic, byte[] imatgeBytes) {
		obtindreLesConnexion();

		PreparedStatement statement = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Connection connection = DriverManager.getConnection(url, user, password);
			String input = "INSERT INTO tabla1 (nom, cognoms, poblacio, correuElectronic, imatgeBytes) VALUES (?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(input);
			statement.setString(1, nom);
			statement.setString(2, cognoms);
			statement.setString(3, poblacio);
			statement.setString(4, correuElectronic);
			statement.setBytes(5, imatgeBytes);
			int resultat = statement.executeUpdate();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void guardarInformacioRegistreTaula2(String contrassenyaXifrada, int fortalesa, byte[] salt,
			int longitudHash) {
		obtindreLesConnexion();

		PreparedStatement statement = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Connection connection = DriverManager.getConnection(url, user, password);
			String input = "INSERT INTO tabla2 (contrassenyaXifrada, fortalesa, salt, longitudHash) VALUES (?, ?, ?, ?)";
			String input2 = "INSERT INTO tabla3 (dibuixGuardat) VALUES (NULL)";
			statement = connection.prepareStatement(input);
			statement.setString(1, contrassenyaXifrada);
			statement.setInt(2, fortalesa);
			statement.setBytes(3, salt);
			statement.setInt(4, longitudHash);
			int resultat = statement.executeUpdate();
			statement = connection.prepareStatement(input2);
			int resultat2 = statement.executeUpdate();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void obtindreLesConnexion() {
//		Enumeration e;
//		try {
//			e = NetworkInterface.getNetworkInterfaces();
//			while (e.hasMoreElements()) {
//				NetworkInterface n = (NetworkInterface) e.nextElement();
//				Enumeration ee = n.getInetAddresses();
//				while (ee.hasMoreElements()) {
//					InetAddress i = (InetAddress) ee.nextElement();
//					String adress = "" + (i.getHostAddress());
//
//					if (adress.contains("192.168.14")) {
//						url = "jdbc:mysql://" + adress + "/1daw03_pro";
//						user = "1daw03_pro";
//						password = "dEQ1e3Q2ZD";
//						return;
//					}
//
//				}
//			}
//		} catch (SocketException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		url = "jdbc:mysql://ticsimarro.org:3306/1daw03_pro";

	}

}
