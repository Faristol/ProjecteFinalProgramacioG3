package com.grup.projecteprofinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InterficieRegistre extends JFrame {

	private JPanel contentPane;
	private JTextField textNom;
	private JTextField textCognoms;
	private JTextField textPoblacio;
	private JTextField textCorreu;
	private JPasswordField passwordText1;
	private JPasswordField passwordText2;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private String[] componentsCadena = new String[7];
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;
	private JLabel nom;
	private JLabel cognoms;
	private JLabel poblacio;
	private JLabel correu;
	private JLabel contra1;
	private JLabel contra2;
	private JLabel lblNewLabel_1;
	private String imagePath;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterficieRegistre frame = new InterficieRegistre();
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
	public InterficieRegistre() {
		setTitle("Registre");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		try {
			Font stocky = Font.createFont(Font.TRUETYPE_FONT, new File("stocky.ttf"));
			stocky = stocky.deriveFont(Font.PLAIN, 18);

			btnNewButton = new JButton("Registrar-se");
			btnNewButton.setFont(stocky);
			btnNewButton_1 = new JButton("Cancel-la");
			btnNewButton_1.setFont(stocky);
			lblNewLabel = new JLabel("Registre");
			lblNewLabel.setFont(stocky);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		contentPane.setBackground(new Color(255, 217, 61));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 217, 61));
		contentPane.add(panel, BorderLayout.SOUTH);

		panel.add(btnNewButton);

		panel.add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 217, 61));
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		try {
			Font stocky = Font.createFont(Font.TRUETYPE_FONT, new File("stocky.ttf"));
			stocky = stocky.deriveFont(Font.PLAIN, 12);
			nom = new JLabel("Nom:");
			nom.setFont(stocky);
			cognoms = new JLabel("Cognoms:");
			cognoms.setFont(stocky);
			poblacio = new JLabel("Poblacio:");
			poblacio.setFont(stocky);
			correu = new JLabel("Correu:");
			correu.setFont(stocky);
			contra1 = new JLabel("Contrassenya: ");
			contra1.setFont(stocky);
			contra2 = new JLabel("Repeteix la contrassenya:");
			contra2.setFont(stocky);
			lblNewLabel_1 = new JLabel();
			lblNewLabel_1.setFont(stocky);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(nom, gbc_lblNewLabel_1);

		textNom = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 1;
		panel_1.add(textNom, gbc_textField);
		textNom.setColumns(10);

		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(cognoms, gbc_lblNewLabel_2);

		textCognoms = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 2;
		panel_1.add(textCognoms, gbc_textField_1);
		textCognoms.setColumns(10);

		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 3;
		panel_1.add(poblacio, gbc_lblNewLabel_4);

		textPoblacio = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 5;
		gbc_textField_2.gridy = 3;
		panel_1.add(textPoblacio, gbc_textField_2);
		textPoblacio.setColumns(10);

		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 4;
		panel_1.add(correu, gbc_lblNewLabel_3);

		textCorreu = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 5;
		gbc_textField_3.gridy = 4;
		panel_1.add(textCorreu, gbc_textField_3);
		textCorreu.setColumns(10);

		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.gridwidth = 2;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 5;
		panel_1.add(contra1, gbc_lblNewLabel_5);

		passwordText1 = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 5;
		gbc_passwordField.gridy = 5;
		panel_1.add(passwordText1, gbc_passwordField);

		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.gridwidth = 2;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 6;
		panel_1.add(contra2, gbc_lblNewLabel_6);

		passwordText2 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 5;
		gbc_passwordField_1.gridy = 6;
		panel_1.add(passwordText2, gbc_passwordField_1);


		JButton selectImageButton2 = new JButton("Selecciona la imagen");
		
		
		GridBagConstraints selectImageButton = new GridBagConstraints();
		selectImageButton.insets = new Insets(0, 0, 5, 5);
		selectImageButton.fill = GridBagConstraints.HORIZONTAL;
		selectImageButton.gridx = 4;
		selectImageButton.gridy = 7;
		panel_1.add(selectImageButton2,selectImageButton);
		
		GridBagConstraints lblNewLabel_111 = new GridBagConstraints();
		lblNewLabel_111.insets = new Insets(0, 0, 5, 5);
		lblNewLabel_111.fill = GridBagConstraints.HORIZONTAL;
		lblNewLabel_111.gridx = 4;
		lblNewLabel_111.gridy = 7;
		panel_1.add(lblNewLabel_1,lblNewLabel_111);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				componentsCadena[0] = textNom.getText();
				componentsCadena[1] = textCognoms.getText();
				componentsCadena[2] = textPoblacio.getText();
				componentsCadena[3] = textCorreu.getText();
				String pwd1 = new String(passwordText1.getPassword());
				componentsCadena[4] = pwd1;
				String pwd2 = new String(passwordText2.getPassword());
				componentsCadena[5] = pwd2;
				componentsCadena[6] = imagePath;
				boolean correcte = true;
				for (String cadena : componentsCadena) {
					if (cadena == null || cadena.isBlank() || cadena.isEmpty()) {
						correcte = false;
						break;

					}
				}
				if (correcte == true) {
					ProcessamentRegistre.procesamentCampsRegistre(componentsCadena[0].trim(),
							componentsCadena[1].trim(), componentsCadena[2].trim(), componentsCadena[3].trim(),
							componentsCadena[4].trim(), componentsCadena[5].trim(), componentsCadena[6].trim());

				} else {
					JOptionPane.showMessageDialog(null, "Cal plenar tots els camps!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}});selectImageButton2.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "png", "jpg"));
			        int result = fileChooser.showOpenDialog(null);
			        if (result == JFileChooser.APPROVE_OPTION) {
			            File selectedFile = fileChooser.getSelectedFile();
			            if (selectedFile != null && selectedFile.isFile()) {
			                imagePath = selectedFile.getAbsolutePath();
			               // lblNewLabel_1.setText(imagePath);

			                JOptionPane.showMessageDialog(null, "Imagen seleccionada: " + imagePath);
			            } else {
			                JOptionPane.showMessageDialog(null, "El archivo seleccionado no es válido", "Error",
			                        JOptionPane.ERROR_MESSAGE);
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Debes seleccionar una imagen", "Error",
			                    JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});

		setVisible(true);

	}

}

