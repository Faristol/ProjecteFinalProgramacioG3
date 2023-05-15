package com.grup.projecteprofinal;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InterficiePrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JPanel panel_1;
	private JLabel lblNewLabel_2;
	private JPanel panel_2;
	private JButton btnNewButton;
	private JButton btnNewButton1;
	private JButton btnNewButton_1;
	private JLabel label;
	private boolean trobatuser, trobatcontra;
	private static ResultSet resultSet = null;
	private String nom, contrasenya;
	private Connection c;
	private java.sql.Statement s;

	private JPanel panelRegistro;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblApellido;
	private JTextField textFieldApellido;
	private JLabel lblEdad;
	private JTextField textFieldEdad;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JLabel lblUsuarioRegistro;
	private JLabel lblContrasenaRegistro;
	private JPasswordField passwordFieldRegistro;
	private JButton btnRegistrarse;
	private boolean registrado = false;

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

	public InterficiePrincipal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(168, 67, 210, 68);
		contentPane.add(panel);

		lblNewLabel = new JLabel("Usuari:");
		panel.add(lblNewLabel);

		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);

		lblNewLabel_1 = new JLabel("Contrasenya:");
		panel.add(lblNewLabel_1);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		panel_1 = new JPanel();
		panel_1.setBounds(72, 51, 65, 128);
		contentPane.add(panel_1);

		ImageIcon img = new ImageIcon("log");
		Image img2 = img.getImage().getScaledInstance(150, 128, Image.SCALE_SMOOTH);
		ImageIcon scaledImgIcon = new ImageIcon(img2);

		lblNewLabel_2 = new JLabel(scaledImgIcon);
		panel_1.add(lblNewLabel_2);

		panel_2 = new JPanel();
		panel_2.setBounds(189, 148, 106, 60);
		contentPane.add(panel_2);

		btnNewButton = new JButton("Login");
		panel_2.add(btnNewButton);

		btnNewButton1 = new JButton("Registrar-se");
		panel_2.add(btnNewButton1);

		btnNewButton_1 = new JButton("Logout");
		panel_2.add(btnNewButton_1);

		btnNewButton_1.setVisible(false);
		label = new JLabel("New label");
		label.setBounds(76, 92, 46, 14);
		contentPane.add(label);

		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		btnNewButton1.addActionListener(this);
		setResizable(false);

		// Panel de Registro
		panelRegistro = new JPanel();
		panelRegistro.setBounds(10, 10, 414, 241);
		contentPane.add(panelRegistro);
		panelRegistro.setLayout(null);
		panelRegistro.setVisible(false);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 10, 70, 14);
		panelRegistro.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(90, 10, 200, 20);
		panelRegistro.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 40, 70, 14);
		panelRegistro.add(lblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(90, 40, 200, 20);
		panelRegistro.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		lblEdad = new JLabel("Poblacion:");
		lblEdad.setBounds(10, 70, 70, 14);
		panelRegistro.add(lblEdad);

		textFieldEdad = new JTextField();
		textFieldEdad.setBounds(90, 70, 200, 20);
		panelRegistro.add(textFieldEdad);
		textFieldEdad.setColumns(10);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 100, 70, 14);
		panelRegistro.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(90, 100, 200, 20);
		panelRegistro.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		lblContrasenaRegistro = new JLabel("Contraseña:");
		lblContrasenaRegistro.setBounds(10, 130, 70, 14);
		panelRegistro.add(lblContrasenaRegistro);

		passwordFieldRegistro = new JPasswordField();
		passwordFieldRegistro.setBounds(90, 130, 200, 20);
		panelRegistro.add(passwordFieldRegistro);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(90, 190, 200, 30);
		panelRegistro.add(btnRegistrarse);

		btnRegistrarse.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
// falta solucionar error al conectar amb base de dades, mentres utilitzarem dos variables per al nom i contra per poder anar fent
		
		
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String urlBaseDades = "jdbc:mariadb://localhost:3306/1daw03_pro";
//			String usuari = "1daw03";
//			String contrasenya = "dEQ1e3Q2ZD";
//			String usuari_bd = "1daw03_pro";
//			String contrasenya_bd = "dEQ1e3Q2ZD";
//
//			c = DriverManager.getConnection(urlBaseDades, usuari, contrasenya);
//
//			s = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//			s.execute("USE " + usuari_bd);
//
//			resultSet = s.executeQuery("SELECT * FROM `alumnat`");
//
//		} catch (ClassNotFoundException | SQLException e1) {
//			e1.printStackTrace();
//		}

		if (e.getSource() == btnNewButton) {

//			try {
//				while (resultSet.next()) {
//					trobatuser = false;
//					trobatcontra = false;
//					nom = resultSet.getString("nom");
//					contrasenya = resultSet.getString("contra");
			nom="raul";
			contrasenya="raul";
					if (nom.equals(textField_1.getText())) {
						trobatuser = true;

					} else {
						JOptionPane.showMessageDialog(this, "Usuari no existent", "Error", JOptionPane.ERROR_MESSAGE);
					}

					if (contrasenya.equals(textField.getText()) && trobatuser) {
						trobatcontra = true;
					} else {
						JOptionPane.showMessageDialog(this, "Contrasenya incorrecta", "Error",
								JOptionPane.ERROR_MESSAGE);

					}

//				}
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} finally {
//				try {
//					resultSet.close();
//					c.close();
//				} catch (SQLException e2) {
//					e2.printStackTrace();
//				}
//			}

			if (trobatcontra) {
				btnNewButton_1.setVisible(true);
				btnNewButton.setVisible(false);
				lblNewLabel_1.setVisible(false);
				textField.setVisible(false);
				textField_1.setVisible(false);
				lblNewLabel.setVisible(false);
				btnNewButton1.setVisible(false);
			}
		} else if (e.getSource() == btnNewButton_1) {
			btnNewButton_1.setVisible(false);
			btnNewButton.setVisible(true);
			btnNewButton1.setVisible(true);
			lblNewLabel.setVisible(true);
			lblNewLabel_1.setVisible(true);
			textField.setVisible(true);
			textField_1.setVisible(true);

		} else if (e.getSource() == btnNewButton1) {
			panelRegistro.setVisible(true);
			panel.setVisible(false);
			btnNewButton.setVisible(false);
			lblNewLabel_1.setVisible(false);
			textField.setVisible(false);
			textField_1.setVisible(false);
			lblNewLabel.setVisible(false);
			btnNewButton1.setVisible(false);

		} else if (e.getSource() == btnRegistrarse) {
			String nombre = textFieldNombre.getText();
			String apellido = textFieldApellido.getText();
			String poblacion = textFieldEdad.getText();
			String email = textFieldEmail.getText();
			String contrasenaRegistro = new String(passwordFieldRegistro.getPassword());

			if (nombre.isEmpty() || apellido.isEmpty() || poblacion.isEmpty() || email.isEmpty() || contrasenaRegistro.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos del formulario de registro.",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				
//arrehlar error base de dades, tot el demes correctament
				
//				try {
//					s = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
//					PreparedStatement pstatement = c.prepareStatement("INSERT INTO `` (nom, cognoms, imatge, població, email, contrasenya) VALUES (?,?,?,?,?,?)");
//					pstatement.setString(1, nombre);
//					pstatement.setString(2, apellido);
//					pstatement.setString(2, nom);
//					pstatement.setString(4, poblacion);
//					pstatement.setString(5, email);
//					pstatement.setString(6, contrasenaRegistro);
//
//					pstatement.executeUpdate();
//
//					registrado = true;
//					JOptionPane.showMessageDialog(this, "¡Registro exitoso!", "Registro",
//							JOptionPane.INFORMATION_MESSAGE);
//
//					panelRegistro.setVisible(false);
//					panel.setVisible(true);
//					btnNewButton.setVisible(true);
//					lblNewLabel_1.setVisible(true);
//					textField.setVisible(true);
//					textField_1.setVisible(true);
//					lblNewLabel.setVisible(true);
//					btnNewButton1.setVisible(true);
//				} catch (SQLException ex) {
//					JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + ex.getMessage(), "Error",
//							JOptionPane.ERROR_MESSAGE);
//				}
			}
		}
	}
}
