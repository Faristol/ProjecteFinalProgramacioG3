package com.grup.projecteprofinal;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class ProcesamentIniciSessio {

	public ProcesamentIniciSessio() {
		// TODO Auto-generated constructor stub

	}

	public static void verificacioCamps(String correuElectronic, String contrassenya) {
		// fer primera consulta per veure si el correu existeix
		// si existeix fer altra consulta per veure si hi ha correspondencia entre
		// correu i contra
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
		String url = "jdbc:mysql://ticsimarro:3306/1daw03_pro";
		String user = "1daw03";
		String password = "dEQ1e3Q2ZD";

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
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// desxifrar contrassenya
		String contrassenyaDesxifrada = desxifrarContrassenya(contrassenyaXifrada, fortalesa, salt, longitudHash);
		if (contrassenyaDesxifrada == null || !contrassenyaDesxifrada.equals(contrassenya)) {
			JOptionPane.showMessageDialog(null, "La contrassenya és incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		InterficieSeleccioJocs seleccioJocs = new InterficieSeleccioJocs();
	}

	public static String desxifrarContrassenya(String contrassenyaXifrada, int fortalesa, byte[] salt,
			int longitudHash) {
		String contrassenyaDesxifrada = null;
		try {
			KeySpec spec = new PBEKeySpec(contrassenyaXifrada.toCharArray(), salt, fortalesa, longitudHash);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] nouHash = factory.generateSecret(spec).getEncoded();
			contrassenyaDesxifrada = Base64.getEncoder().encodeToString(nouHash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return contrassenyaDesxifrada;
	}

}
