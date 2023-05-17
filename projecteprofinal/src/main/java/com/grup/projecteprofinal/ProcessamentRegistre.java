package com.grup.projecteprofinal;

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

}
