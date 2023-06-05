package com.grup.projecteprofinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class PixelArt extends JFrame {
	private static String url;
	private static String user = "1daw03_pro";
	private static String password = "dEQ1e3Q2ZD";

	private JPanel contentPane = new JPanel();

	private int WIDTH = 1;
	private int HEIGHT = 1;
	private int PIXEL_SIZE = 1;
	private int GRID_SIZE = WIDTH / PIXEL_SIZE;

	private JButton[][] pixelButtons;
	private JPanel panel1 = new JPanel();
	private JLabel lblNewLabel;

	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	private JPanel panel2 = new JPanel();
	private JButton ColorActual;
	private JButton btnNewButton_3;

	private JPanel panellGeneral = new JPanel();
	private Color currentColor = Color.BLACK;

	private JButton btnGuardarPNG;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PixelArt frame = new PixelArt();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static boolean ComprobacionDibujo() throws Exception {
		Connection connection = crearConexion();
		String correu = InterficieSeleccioJocs.getCorreuElectronic();

		// Realizar operaciones en la base de datos
		int id = 0;
		String consulta = "SELECT id FROM tabla1 WHERE correuElectronic= '" + correu + "'";
		String consulta2 = "SELECT dibuixGuardat FROM tabla3 WHERE id = ?";

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(consulta);

			while (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			st.close();

			PreparedStatement ps = connection.prepareStatement(consulta2);
			ps.setInt(1, id);
			ResultSet filas = ps.executeQuery();

			byte[] cargarDibujo = new byte[0];
			while (filas.next()) {
				cargarDibujo = filas.getBytes(1);

			}
			filas.close();
			ps.close();

			if (cargarDibujo != null) {
				connection.close();
				return true;
			} else {
				connection.close();
				return false;
			}
		} catch (Exception e) {
			connection.close();
			e.printStackTrace();
			return false;
		}

	}

	public PixelArt() {

		this.WIDTH = 400;
		this.HEIGHT = 400;
		this.PIXEL_SIZE = 400;
		setBounds(100, 100, 450, 300);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		pixelButtons = new JButton[GRID_SIZE][GRID_SIZE];

		lblNewLabel = new JLabel("Bienvenidos a PixelArt, escoge un formato para el tablero!!!");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		lblNewLabel.setForeground(Color.BLUE);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		contentPane.add(panel1, BorderLayout.CENTER);

		btnNewButton = new JButton("Grande");
		btnNewButton_1 = new JButton("Mediano");
		btnNewButton_2 = new JButton("Pequeño");

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WIDTH = 500;
				HEIGHT = 500;
				PIXEL_SIZE = 10;
				GRID_SIZE = WIDTH / PIXEL_SIZE;
				creacioPanell();
			}
		});

		panel1.add(btnNewButton);

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WIDTH = 500;
				HEIGHT = 500;
				PIXEL_SIZE = 30;
				GRID_SIZE = WIDTH / PIXEL_SIZE;
				creacioPanell();
			}
		});
		panel1.add(btnNewButton_1);

		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WIDTH = 500;
				HEIGHT = 500;
				PIXEL_SIZE = 50;
				GRID_SIZE = WIDTH / PIXEL_SIZE;
				creacioPanell();
			}
		});
		panel1.add(btnNewButton_2);

		boolean existe = false;
		try {
			existe = ComprobacionDibujo();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		if (existe) {
			JLabel cargar = new JLabel(
					"Tienes un dibujo guardado, presiona Cargar para cargarlo o presiona el tamaño que quieras para tu nuevo dibujo.");
			JButton btnCargar = new JButton("Cargar");
			panel1.add(cargar);
			panel1.add(btnCargar);
			btnCargar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						creacioPanell();
						Cargar();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		} else {

		}

	}

	public void creacioPanell() {
		lblNewLabel.setVisible(false);
		btnNewButton.setVisible(false);
		btnNewButton_1.setVisible(false);
		btnNewButton_2.setVisible(false);
		if (panel1 != null) {
			panel1.setVisible(false);
		}
		panellGeneral.setLayout(new BorderLayout(0, 0));
		JButton btnDescription = new JButton("Descripción");
		JButton btnGuar = new JButton("Guardar");
		btnGuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Guardar(panel2);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnNewButton_3 = new JButton("Selecciona Color");
		btnGuardarPNG = new JButton("Guardar como PNG");
		btnGuardarPNG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					guardarComoPNG(panel2);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		bottomPanel.add(btnDescription);
		bottomPanel.add(btnGuar);
		bottomPanel.add(btnNewButton_3);
		bottomPanel.add(btnGuardarPNG);
		panellGeneral.add(bottomPanel, BorderLayout.SOUTH);

		getContentPane().add(panellGeneral);
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentColor = JColorChooser.showDialog(null, "Selecciona un color", currentColor);
			}
		});
		panel2 = new JPanel();

		ColorActual = new JButton("Color actual");
		ColorActual.setHorizontalAlignment((int) CENTER_ALIGNMENT);

		panel2.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
		panellGeneral.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel2.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// Calculate GRID_SIZE after updating WIDTH and PIXEL_SIZE
		GRID_SIZE = WIDTH / PIXEL_SIZE;

		pixelButtons = new JButton[GRID_SIZE][GRID_SIZE]; // Update pixelButtons size

		for (int x = 0; x < GRID_SIZE; x++) {
			for (int y = 0; y < GRID_SIZE; y++) {
				final JButton button = new JButton();
				button.setPreferredSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
				button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				button.setBackground(Color.WHITE);

				button.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						if (SwingUtilities.isLeftMouseButton(e)) {
							button.setBackground(currentColor);
						} else if (SwingUtilities.isRightMouseButton(e)) {
							button.setBackground(Color.WHITE);
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						if (SwingUtilities.isLeftMouseButton(e)) {
							button.setBackground(currentColor);
						} else if (SwingUtilities.isRightMouseButton(e)) {
							button.setBackground(Color.WHITE);
						}
					}
				});
				pixelButtons[x][y] = button;
				panel2.add(button);
			}
		}

		btnDescription.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Bienvenido a Pixel Art. Aquí puedes crear tu propio arte pixel por pixel.\n"
								+ "1. Elige un tamaño de cuadrícula para comenzar.\n"
								+ "2. Selecciona un color usando el botón 'Selecciona Color'.\n"
								+ "3. Haz clic izquierdo en un cuadro para pintarlo con el color seleccionado.\n"
								+ "4. Haz clic derecho en un cuadro para borrarlo y volverlo blanco.\n"
								+ "¡Diviértete creando!");
			}
		});

		panellGeneral.add(panel2, BorderLayout.CENTER);
		panellGeneral.setVisible(true);

	};

//		serializar el panel
	private byte[] serializado(JPanel panel2) throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(panel2);
			oos.flush();
			return bos.toByteArray();
		}
	}

	private void Guardar(JPanel panel2) throws Exception {

		int id = SacarId();
		int filasact = 0;
		try {
			Connection connection = crearConexion();
			String consultaAct = "UPDATE tabla3 set dibuixGuardat = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(consultaAct);
			byte[] serializedObject = serializado(panel2);
			statement.setObject(1, serializedObject);
			statement.setInt(2, id);
			filasact = statement.executeUpdate();
			if (filasact != 0) {
				
				JOptionPane.showMessageDialog(null,
						"Datos guardados en la base de datos correctamente", "Guardat correcte",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(null,
						"Datos guardados en la base de datos incorrectament", "Guardat incorrecte",
						JOptionPane.ERROR_MESSAGE);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// deserializar el panel
	public JPanel deserializado(byte[] serializedObject) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedObject);
				ObjectInputStream ois = new ObjectInputStream(bis)) {
			return (JPanel) ois.readObject();
		}
	}

	public void Cargar() throws Exception {
		int id = SacarId();
		try {
			Connection connection = crearConexion();
			String consulta = "SELECT dibuixGuardat FROM tabla3 WHERE id = ?";

			PreparedStatement statement = connection.prepareStatement(consulta);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				byte[] serializedObject = rs.getBytes("dibuixGuardat");
				JPanel loadedPanel = deserializado(serializedObject);
				if (panellGeneral != null) {
					panellGeneral.remove(panel2);
				}
				panel2 = loadedPanel;
				panellGeneral.add(panel2, BorderLayout.CENTER);
				panellGeneral.revalidate();
				panellGeneral.repaint();

				JOptionPane.showMessageDialog(null,
						"Dibuix carregat correctament", "Carregat correcte",
						JOptionPane.ERROR_MESSAGE);
			}

			rs.close();
			statement.close();

			// Cerrar la conexión
			connection.close();
			
		} catch (Exception e) {
			
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
//
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

	public static Connection crearConexion() throws Exception {
		obtindreLesConnexion();
		return DriverManager.getConnection(url, "1daw03_pro", "dEQ1e3Q2ZD");
	}

	public static int SacarId() {
		try {
			Connection connection;

			connection = crearConexion();
			// Cargar el controlador JDBC
			Class.forName("com.mysql.cj.jdbc.Driver");

			String correu = InterficieSeleccioJocs.getCorreuElectronic();

			// Realizar operaciones en la base de datos
			int filasact = 0;
			int id = 0;
			String consulta = "SELECT id FROM tabla1 WHERE correuElectronic= '" + correu + "'";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(consulta);

			while (rs.next()) {
				filasact++;
				id = rs.getInt(1);
			}
			rs.close();
			st.close();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	private void guardarComoPNG(JPanel panel) throws IOException {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                // Agregar la extensión .png si no está presente
                if (!filePath.endsWith(".png")) {
                    filePath += ".png";
                }

                BufferedImage image = new BufferedImage(panel2.getWidth(), panel2.getHeight(), BufferedImage.TYPE_INT_ARGB);
                panel2.paint(image.getGraphics());

                ImageIO.write(image, "PNG", new File(filePath));
                JOptionPane.showMessageDialog(null,
						"Archivo guardado correctamente "+filePath, "Guardat correcte",
						JOptionPane.ERROR_MESSAGE);

                
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null,
						"Error al guardar l'arxiu", "Error al guardar",
						JOptionPane.ERROR_MESSAGE);
                
            }
		}
	}
}
