package com.grup.projecteprofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class ProcessamentRegistre {

	public ProcessamentRegistre() {
		// TODO Auto-generated constructor stub
	}

	public static void procesamentCampsRegistre(String nom, String cognoms, String poblacio, String correu,
			String password1, String password2, String imatge) {
		// Paràmetres a comprovar correu i correspondència entre contrassenyes.
		// també que les contrassenyes tinguen com a mínim 8 caràcters.
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		boolean correuCorrecte = Pattern.compile(regex).matcher(correu).matches();
		boolean longitudCorrecta = (password1.length() >= 8) && (password2.length() >= 8);
		boolean sonIguals = password1.equals(password2);
		if (!correuCorrecte) {
			JOptionPane.showMessageDialog(null, "El format de correu introduït no és vàlid", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!longitudCorrecta) {
			JOptionPane.showMessageDialog(null, "Les contrassenyes deuen tindre almenys 8 caràcters", "Error",
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
		String url = "jdbc:mysql://ticsimarro:3306/1daw03_pro";
		String user = "1daw03";
		String password = "dEQ1e3Q2ZD";

		try {

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
		String url = "jdbc:mysql://ticsimarro:3306/1daw03_pro";
		String user = "1daw03";
		String password = "dEQ1e3Q2ZD";

		PreparedStatement statement = null;
		try {

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
		String url = "jdbc:mysql://ticsimarro:3306/1daw03_pro";
		String user = "1daw03";
		String password = "dEQ1e3Q2ZD";

		PreparedStatement statement = null;
		try {

			Connection connection = DriverManager.getConnection(url, user, password);
			String input = "INSERT INTO tabla2 (contrassenyaXifrada, fortalesa, salt, longitudHash) VALUES (?, ?, ?, ?)";
			statement = connection.prepareStatement(input);
			statement.setString(1, contrassenyaXifrada);
			statement.setInt(2, fortalesa);
			statement.setBytes(3, salt);
			statement.setInt(4, longitudHash);
			int resultat = statement.executeUpdate();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
