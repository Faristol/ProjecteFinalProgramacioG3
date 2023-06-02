package com.grup.projecteprofinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

	private JPanel contentPane;

	private int WIDTH = 1;
	private int HEIGHT = 1;
	private int PIXEL_SIZE = 1;
	private int GRID_SIZE = WIDTH / PIXEL_SIZE;

	private JButton[][] pixelButtons;
	private JPanel panel1;
	private JLabel lblNewLabel;

	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	private JPanel panel2;
	private JButton ColorActual;
	private JButton btnNewButton_3;

	private JPanel panellGeneral;
	private Color currentColor = Color.BLACK;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PixelArt frame = new PixelArt();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PixelArt() {

	    this.WIDTH = 400;
	    this.HEIGHT = 400;
	    this.PIXEL_SIZE = 400;
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 450, 300);
	    contentPane = new JPanel();
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

	    panel1 = new JPanel();
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
	}

	public void creacioPanell() {
	    lblNewLabel.setVisible(false);
	    btnNewButton.setVisible(false);
	    btnNewButton_1.setVisible(false);
	    btnNewButton_2.setVisible(false);
	    panel1.setVisible(false);
	    panellGeneral = new JPanel();
	    panellGeneral.setLayout(new BorderLayout(0, 0));
	    JButton btnDescription = new JButton("Descripción");
	    JButton btnGuar = new JButton("Guardar");
	    btnNewButton_3 = new JButton("Selecciona Color");

	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

	    bottomPanel.add(btnDescription);
	    bottomPanel.add(btnGuar);
	    bottomPanel.add(btnNewButton_3);

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
	            JOptionPane.showMessageDialog(null, "Bienvenido a Pixel Art. Aqu� puedes crear tu propio arte pixel por pixel.\n"
	                    + "1. Elije un tama�o de cuadr�cula para comenzar.\n"
	                    + "2. Selecciona un color usando el bot�n 'Selecciona Color'.\n"
	                    + "3. Haz clic izquierdo en un cuadro para pintarlo con el color seleccionado.\n"
	                    + "4. Haz clic derecho en un cuadro para borrarlo y volverlo blanco.\n"
	                    + "�Divi�rtete creando!");
	        }
	    });

	    panellGeneral.add(panel2, BorderLayout.CENTER);
	    panellGeneral.setVisible(true);

	    btnGuar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            JFileChooser filechooser = new JFileChooser();
	            int option = filechooser.showSaveDialog(PixelArt.this);
	            if (option == JFileChooser.APPROVE_OPTION) {
	                File archivo = filechooser.getSelectedFile();
	                int anc = WIDTH;
	                int alt = HEIGHT;

	                BufferedImage img = new BufferedImage(anc, alt, BufferedImage.TYPE_INT_RGB);
	                Graphics2D graphics = img.createGraphics();

	                for (int i = 0; i < GRID_SIZE; i++) {
	                    for (int j = 0; j < GRID_SIZE; j++) {
	                        Color color = pixelButtons[i][j].getBackground();
	                        graphics.setColor(color);
	                        graphics.fillRect(i * PIXEL_SIZE, j * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
	                    }
	                }

	                graphics.dispose();
	                try {
	                    ImageIO.write(img, "png", archivo);
	                    JOptionPane.showMessageDialog(PixelArt.this, "Guardado correctamente.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	            }
	        }
	    });

	    panellGeneral.add(panel2, BorderLayout.CENTER);
	    panellGeneral.setVisible(true);
	    
	    getContentPane().add(panellGeneral);
	    pack();
	    setLocationRelativeTo(null);
	    setVisible(true);
	    repaint();
	    revalidate();

	}
}