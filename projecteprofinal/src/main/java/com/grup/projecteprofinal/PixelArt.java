package com.grup.projecteprofinal;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.management.loading.PrivateClassLoader;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class PixelArt extends JFrame {

	private JPanel contentPane;

	private int WIDTH = 1;
	private int HEIGHT = 1;
	private int PIXEL_SIZE = 1;
	private int GRID_SIZE = WIDTH / PIXEL_SIZE;

	private boolean[][] pixels;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PixelArt frame = new PixelArt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PixelArt() throws FontFormatException, IOException {

		this.WIDTH = 400;
		this.HEIGHT = 400;
		this.PIXEL_SIZE = 400;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

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
		btnNewButton_3 = new JButton("Selecciona Color");
		panellGeneral.add(btnNewButton_3, BorderLayout.SOUTH);
		panel2 = new JPanel();

		ColorActual = new JButton("Color actual");
		ColorActual.setHorizontalAlignment((int) CENTER_ALIGNMENT);

		panel2.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
		panellGeneral.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel2.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		for (int x = 0; x < GRID_SIZE; x++) {
			for (int y = 0; y < GRID_SIZE; y++) {
				JButton button = new JButton();
				button.setPreferredSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
				button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				panel2.add(button);
			}

		}
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
