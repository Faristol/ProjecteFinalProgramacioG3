package com.grup.projecteprofinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
	private JButton btnNewButton;
	private JButton btnNewButton2;

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
		setTitle("Projecte Final Programació");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		setResizable(false);

		contentPane = new JPanel();

		contentPane.setBackground(new Color(255, 217, 61));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 217, 61));
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 217, 61));
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
		panel_2.setBackground(new Color(255, 217, 61));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridBagLayout());

		JLabel lblNewLabel_3 = new JLabel();
		try {
			Font stocky = Font.createFont(Font.TRUETYPE_FONT, new File("stocky.ttf"));
			stocky = stocky.deriveFont(Font.PLAIN, 18);

			lblNewLabel_3.setText("La Llar Dels Jocs");
			lblNewLabel_3.setFont(stocky);
			lblNewLabel_1 = new JLabel("Correu:");
			lblNewLabel_1.setFont(stocky);
			lblNewLabel_2 = new JLabel("Contrassenya:");
			lblNewLabel_2.setFont(stocky);
			btnNewButton = new JButton("Registra\"t");
			btnNewButton.setFont(stocky);
			btnNewButton2 = new JButton("Inicia");
			btnNewButton2.setFont(stocky);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(5, 5, 5, 5);
		panel_2.add(lblNewLabel_1, gbc);
		GridBagConstraints gbc2 = new GridBagConstraints();
		textField = new JTextField();
		gbc2.gridx = 1;
		gbc2.gridy = 2;
		gbc2.weightx = 1.0;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbc2.insets = new Insets(5, 5, 5, 0);
		panel_2.add(textField, gbc2);

		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.gridx = 0;
		gbc3.gridy = 3;
		gbc3.weightx = 0;
		gbc3.anchor = GridBagConstraints.EAST;
		gbc3.insets = new Insets(5, 5, 0, 5);
		panel_2.add(lblNewLabel_2, gbc3);
		passwordField = new JPasswordField();
		GridBagConstraints gbc4 = new GridBagConstraints();
		gbc4.gridx = 1;
		gbc4.gridy = 3;
		gbc4.weightx = 1.0;
		gbc4.fill = GridBagConstraints.HORIZONTAL;

		gbc4.insets = new Insets(5, 5, 0, 0);
		panel_2.add(passwordField, gbc4);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 217, 61));
		panel.add(panel_3, BorderLayout.SOUTH);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InterficieRegistre registre = new InterficieRegistre();
				Usuari.panellsActius.add(registre);
			}
		});

		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contra = new String(passwordField.getPassword());
				if ((textField.getText().trim().isBlank() || textField.getText() == null)
						&& (contra.trim().isBlank() || contra == null)) {
					JOptionPane.showMessageDialog(null, "Cal ambdós camps", "Avís", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (textField.getText().trim().isBlank() || textField.getText() == null) {
					JOptionPane.showMessageDialog(null, "Cal emplenar el camp correu", "Avís",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (contra.trim().isBlank() || contra == null) {
					JOptionPane.showMessageDialog(null, "Cal emplenar el camp contrassenya", "Avís",
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

		btnNewButton_1 = new JButton("Tanca sessió");
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
