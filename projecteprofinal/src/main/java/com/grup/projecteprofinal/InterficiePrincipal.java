package com.grup.projecteprofinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InterficiePrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField textField;
	private JPasswordField passwordField;
	private static JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterficiePrincipal frame = new InterficiePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterficiePrincipal() {
		setTitle("Projecte Final Programaci�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(88, 214, 141));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(88, 214, 141));
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(88, 214, 141));
		panel.add(panel_1, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel();
		ImageIcon icona = new ImageIcon("img" + File.separator + "inici.JPG");
		Image imatge = icona.getImage();
		Image novaImage = imatge.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon nouIcon = new ImageIcon(novaImage);
		lblNewLabel.setIcon(nouIcon);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(88, 214, 141));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		lblNewLabel_1 = new JLabel("Correu:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(5, 5, 5, 5);
		panel_2.add(lblNewLabel_1, gbc);
		GridBagConstraints gbc2 = new GridBagConstraints();
		textField = new JTextField();
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		gbc2.weightx = 1.0;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbc2.insets = new Insets(5, 5, 5, 5);
		panel_2.add(textField, gbc2);
		lblNewLabel_2 = new JLabel("Contrassenya:");
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.gridx = 0;
		gbc3.gridy = 1;
		gbc3.weightx = 0;
		gbc3.anchor = GridBagConstraints.EAST;
		gbc3.insets = new Insets(5, 5, 5, 5);
		panel_2.add(lblNewLabel_2, gbc3);
		passwordField = new JPasswordField();
		GridBagConstraints gbc4 = new GridBagConstraints();
		gbc4.gridx = 1;
		gbc4.gridy = 1;
		gbc4.weightx = 1.0;
		gbc4.fill = GridBagConstraints.HORIZONTAL;

		gbc4.insets = new Insets(5, 5, 5, 5);
		panel_2.add(passwordField, gbc4);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(88, 214, 141));
		panel.add(panel_3, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Registra't");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InterficieRegistre registre = new InterficieRegistre();
				Usuari.panellsActius.add(registre);
			}
		});
		JButton btnNewButton2 = new JButton("Inicia Sessi�");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contra = new String(passwordField.getPassword());
				if ((textField.getText().trim().isBlank() || textField.getText() == null)
						&& (contra.trim().isBlank() || contra == null)) {
					JOptionPane.showMessageDialog(null, "Cal ambd�s camps", "Av�s", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (textField.getText().trim().isBlank() || textField.getText() == null) {
					JOptionPane.showMessageDialog(null, "Cal emplenar el camp correu", "Av�s",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (contra.trim().isBlank() || contra == null) {
					JOptionPane.showMessageDialog(null, "Cal emplenar el camp contrassenya", "Av�s",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String contrassenya = contra.trim();
				String correuElectronic = textField.getText().trim();
				ProcesamentIniciSessio.verificacioCamps(correuElectronic, contrassenya);
			}
		});

		panel_3.add(btnNewButton);
		panel_3.add(btnNewButton2);

		btnNewButton_1 = new JButton("Tanca sessi�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuari.tancarPanells();
				textField.setText("");
				passwordField.setText("");
				InterficiePrincipal.ferinVisibleTancaSessio();
				revalidate();
			}
		});
		btnNewButton_1.setVisible(false);
		panel_3.add(btnNewButton_1);

	}
	public static void ferinVisibleTancaSessio() {
		btnNewButton_1.setVisible(false);
	}

	public static void ferVisibleTancaSessio() {
		btnNewButton_1.setVisible(true);

	}

}
