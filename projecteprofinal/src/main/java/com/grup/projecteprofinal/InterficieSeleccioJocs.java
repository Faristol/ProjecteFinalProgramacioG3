package com.grup.projecteprofinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class InterficieSeleccioJocs extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel texto;
	private JButton btnNewButton, btnNewButton_1, btnNewButton_2;
	private static String url = "";
	private String user = "1daw03_pro";
	private String password = "dEQ1e3Q2ZD";
	private static String correuElectronic;
	private ImageIcon imagenUsuario;
	private JLabel lblNombreApellidos;
	private JLabel lblPoblacion;
	private JLabel lblCorreoElectronico;
	private Font stocky;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterficieSeleccioJocs frame = new InterficieSeleccioJocs();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public InterficieSeleccioJocs() {

		setTitle("Seleccio Jocs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 400);
		setResizable(false);
		getContentPane().setBackground(Color.RED);
		try {
			stocky = Font.createFont(Font.TRUETYPE_FONT, new File("stocky.ttf"));
			stocky = stocky.deriveFont(Font.PLAIN, 18);

		} catch (FontFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 217, 61));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnNewButton_3 = new JButton("Dona'm de baixa");
		btnNewButton_3.setFont(stocky);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection c;
				InterficieSeleccioJocs.obtindreLesConnexion();
				String correu = InterficieSeleccioJocs.getCorreuElectronic();

				try {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					c = DriverManager.getConnection(url, user, password);
					Statement cerca = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
					String sentenciaIdUser = "SELECT id FROM tabla1 WHERE correuElectronic = '" + correu + "'";
					ResultSet idUser = cerca.executeQuery(sentenciaIdUser);

					if (idUser.next()) {
						int id = idUser.getInt("id");

						String sentenciaBorrarTaula1 = "DELETE FROM tabla1 WHERE id = " + id;
						int filasBorradas1 = cerca.executeUpdate(sentenciaBorrarTaula1);

						String sentenciaBorrarTaula2 = "DELETE FROM tabla2 WHERE id = " + id;
						int filasBorradas2 = cerca.executeUpdate(sentenciaBorrarTaula2);

						String sentenciaBorrarTaula3 = "DELETE FROM tabla3 WHERE id = " + id;
						int filasBorradas3 = cerca.executeUpdate(sentenciaBorrarTaula3);

						Usuari.panellsActius.get("panellJocs").dispose();
						if (Usuari.panellsActius.get("pescamines") != null) {
							JFrame framePescamines = Usuari.panellsActius.get("pescamines");
							framePescamines.setFont(stocky);

							if (framePescamines.isVisible()) {
								framePescamines.dispose();
							}

						}
						if (Usuari.panellsActius.get("vida") != null) {
							JFrame frameVida = Usuari.panellsActius.get("vida");
							if (frameVida.isVisible()) {
								frameVida.dispose();
							}

						}
						if (Usuari.panellsActius.get("pixel") != null) {
							JFrame framePixelArt = Usuari.panellsActius.get("pixel");
							if (framePixelArt.isVisible()) {
								framePixelArt.dispose();
							}

						}
						Usuari.panellsActius.clear();
						JOptionPane.showMessageDialog(null, "Espere tornar-te a veure en la base de dades!", "Ad�u",
								JOptionPane.INFORMATION_MESSAGE);
						InterficiePrincipal panellPrincipal = new InterficiePrincipal();

						Usuari.panellsActius.put("panellPrincipal", panellPrincipal);
						panellPrincipal.setVisible(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton_3);
		panel.setBackground(new Color(255, 217, 61));

		JButton btnNewButton_4 = new JButton("Tanca Sessio");
		btnNewButton_4.setFont(stocky);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuari.panellsActius.get("panellJocs").dispose();
				if (Usuari.panellsActius.get("pescamines") != null) {
					JFrame framePescamines = Usuari.panellsActius.get("pescamines");
					if (framePescamines.isVisible()) {
						framePescamines.dispose();
					}

				}
				if (Usuari.panellsActius.get("vida") != null) {
					JFrame frameVida = Usuari.panellsActius.get("vida");
					if (frameVida.isVisible()) {
						frameVida.dispose();
					}

				}
				if (Usuari.panellsActius.get("pixel") != null) {
					JFrame framePixelArt = Usuari.panellsActius.get("pixel");
					if (framePixelArt.isVisible()) {
						framePixelArt.dispose();
					}

				}
				Usuari.panellsActius.clear();
				JOptionPane.showMessageDialog(null, "Fins la pr�xima!", "Ad�u", JOptionPane.INFORMATION_MESSAGE);
				InterficiePrincipal panellPrincipal = new InterficiePrincipal();
				Usuari.panellsActius.put("panellPrincipal", panellPrincipal);
				panellPrincipal.setVisible(true);
			}
		});
		panel.add(btnNewButton_4);

		JPanel panel_1 = new JPanel();

		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel img = new JLabel();
		GridBagConstraints gbc_img = new GridBagConstraints();
		gbc_img.insets = new Insets(40, 5, 5, 0);
		gbc_img.gridx = 0;
		gbc_img.gridy = GridBagConstraints.RELATIVE;
		gbc_img.anchor = GridBagConstraints.CENTER;

		GridBagConstraints gbc_etiquetas = new GridBagConstraints();
		gbc_etiquetas.anchor = GridBagConstraints.WEST;
		gbc_etiquetas.insets = new Insets(20, 15, 5, 0);
		gbc_etiquetas.gridx = 0;
		gbc_etiquetas.gridy = GridBagConstraints.RELATIVE;

		try {
			obtindreLesConnexion();
			String correu = InterficieSeleccioJocs.getCorreuElectronic();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Connection connection = DriverManager.getConnection(url, user, password);
			Statement cerca = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT nom, cognoms, poblacio, correuElectronic, imatgeBytes FROM tabla1 WHERE correuElectronic = '"
					+ correu + "'";
			ResultSet r = cerca.executeQuery(consulta);

			if (r.next()) {

				String nombre = r.getString("nom");
				String apellidos = r.getString("cognoms");
				String poblacion = r.getString("poblacio");
				String correoElectronico = r.getString("correuElectronic");
				byte[] imagenBytes = r.getBytes("imatgeBytes");

				if (imagenBytes != null) {
					Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
					Image imagen = Toolkit.getDefaultToolkit().createImage(imagenBytes);
					int nuevoAncho = 80;
					int nuevoAlto = 80;
					Image imagenRedimensionada = imagen.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
					ImageIcon imagenUsuario = new ImageIcon(imagenRedimensionada);

					img.setIcon(imagenUsuario);
					img.setBorder(borde);
					panel_1.add(img, gbc_img);

				}

				lblNombreApellidos = new JLabel("Nom i cognoms: " + nombre + " " + apellidos);
				lblNombreApellidos.setFont(stocky);
				lblPoblacion = new JLabel("Poblacio: " + poblacion);
				lblPoblacion.setFont(stocky);
				lblCorreoElectronico = new JLabel("Correu: " + correoElectronico);
				lblCorreoElectronico.setFont(stocky);

				panel_1.add(lblNombreApellidos, gbc_etiquetas);
				panel_1.add(lblPoblacion, gbc_etiquetas);
				panel_1.add(lblCorreoElectronico, gbc_etiquetas);

			}
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel_1.setBackground(new Color(255, 217, 61));
		contentPane.add(panel_1, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);

		JLabel lblNewLabel_3 = new JLabel("La Llar Dels Jocs ");
		lblNewLabel_3.setFont(stocky);
		panel_2.add(lblNewLabel_3);
		panel_2.setBackground(new Color(255, 217, 61));

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 117, 0, 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		JLabel lblNewLabel = new JLabel("Pescamines:");
		lblNewLabel.setFont(stocky);

		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		// lblNewLabel.setHorizontalAlignment(SwingConstraints.CENTER);
		gbc_lblNewLabel.gridwidth = 6;
		gbc_lblNewLabel.insets = new Insets(50, 200, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 3;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);

		btnNewButton = new JButton("Accedir");
		btnNewButton.setFont(stocky);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Usuari.panellsActius.get("pescamines") != null) {
					JFrame framePixelArt = Usuari.panellsActius.get("pescamines");
					if (!framePixelArt.isVisible()) {
						Usuari.panellsActius.remove("pescamines");
					}
					
				}

				if (Usuari.panellsActius.size() < 5 && Usuari.panellsActius.get("pescamines") == null) {
					Buscaminas framePescamines = new Buscaminas();
					Usuari.panellsActius.put("pescamines", framePescamines);
					framePescamines.setVisible(true);
				}
				if (Usuari.panellsActius.get("pixel") != null) {
					JFrame framePixelArt = Usuari.panellsActius.get("pixel");
					if (!framePixelArt.isVisible()) {
						Usuari.panellsActius.remove("pixel");
					}
					
				}
			if (Usuari.panellsActius.get("vida") != null) {
				JFrame framePixelArt = Usuari.panellsActius.get("vida");
				if (!framePixelArt.isVisible()) {
					Usuari.panellsActius.remove("vida");
				}
				
			}

			}

		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(50, 40, 5, 0);
		gbc_btnNewButton.gridx = 10;
		gbc_btnNewButton.gridy = 3;
		panel_3.add(btnNewButton, gbc_btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Pixel Art:");
		lblNewLabel_1.setFont(stocky);

		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 6;
		gbc_lblNewLabel_1.insets = new Insets(10, 200, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 5;
		panel_3.add(lblNewLabel_1, gbc_lblNewLabel_1);

		btnNewButton_1 = new JButton("Accedir");
		btnNewButton_1.setFont(stocky);

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (Usuari.panellsActius.get("pixel") != null) {
					JFrame framePixelArt = Usuari.panellsActius.get("pixel");
					if (!framePixelArt.isVisible()) {
						Usuari.panellsActius.remove("pixel");
					}
					
				}
				if (Usuari.panellsActius.size() < 5 && Usuari.panellsActius.get("pixel") == null) {
					PixelArt framePixelArt = new PixelArt();
					Usuari.panellsActius.put("pixel", framePixelArt);
					framePixelArt.setVisible(true);
				}
				if (Usuari.panellsActius.get("pescamines") != null) {
					JFrame framePixelArt = Usuari.panellsActius.get("pescamines");
					if (!framePixelArt.isVisible()) {
						Usuari.panellsActius.remove("pescamines");
					}
					
				}
			if (Usuari.panellsActius.get("vida") != null) {
				JFrame framePixelArt = Usuari.panellsActius.get("vida");
				if (!framePixelArt.isVisible()) {
					Usuari.panellsActius.remove("vida");
				}
				
			}

			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(10, 40, 5, 0);
		gbc_btnNewButton_1.gridx = 10;
		gbc_btnNewButton_1.gridy = 5;
		panel_3.add(btnNewButton_1, gbc_btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("Joc de la vida:");
		lblNewLabel_2.setFont(stocky);

		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.gridwidth = 6;
		gbc_lblNewLabel_2.insets = new Insets(10, 200, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 7;
		panel_3.add(lblNewLabel_2, gbc_lblNewLabel_2);

		btnNewButton_2 = new JButton("Accedir");
		btnNewButton_2.setFont(stocky);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Usuari.panellsActius.get("vida") != null) {
					JFrame framePixelArt = Usuari.panellsActius.get("vida");
					if (!framePixelArt.isVisible()) {
						Usuari.panellsActius.remove("vida");
					}
					
				}
			
				if (Usuari.panellsActius.size() < 5 && Usuari.panellsActius.get("vida") == null) {
					JocDeLaVida frameVida = new JocDeLaVida();
					Usuari.panellsActius.put("vida", frameVida);
					frameVida.setVisible(true);
				} 
				if (Usuari.panellsActius.get("pixel") != null) {
						JFrame framePixelArt = Usuari.panellsActius.get("pixel");
						if (!framePixelArt.isVisible()) {
							Usuari.panellsActius.remove("pixel");
						}
						
					}
				if (Usuari.panellsActius.get("pescamines") != null) {
					JFrame framePixelArt = Usuari.panellsActius.get("pescamines");
					if (!framePixelArt.isVisible()) {
						Usuari.panellsActius.remove("pescamines");
					}
					
				}	

				}

			
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridwidth = 2;
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(10, 40, 5, 0);
		gbc_btnNewButton_2.gridx = 10;
		gbc_btnNewButton_2.gridy = 7;
		panel_3.add(btnNewButton_2, gbc_btnNewButton_2);
		panel_3.setBackground(new Color(255, 217, 61));
		// pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void mostrarVentana() {
		setVisible(true); // Muestra la ventana cuando se llame a este método
	}

	public static void setCorreuElectronic(String correu) {
		correuElectronic = correu;
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

					if (adress.contains("192.168.14")) {
						url = "jdbc:mysql://" + adress + "/1daw03_pro";

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

// Canviar cuant les clases dels jocs estiguen fetes

	public boolean isClosed() {
		return !isVisible();
	}

	public static void posarCorreu(String correuElectornic) {
		correuElectornic = correuElectronic;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static String getCorreuElectronic() {
		return correuElectronic;
	}

}
