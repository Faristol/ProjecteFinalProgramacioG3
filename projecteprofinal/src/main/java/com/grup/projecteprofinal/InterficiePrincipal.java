package com.grup.projecteprofinal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InterficiePrincipal extends JFrame {

	private JPanel contentPane;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel();
		ImageIcon icona = new ImageIcon("img"+ File.separator + "inici.JPG");
		Image imatge = icona.getImage();
		Image novaImage = imatge.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon nouIcon = new ImageIcon(novaImage);
		lblNewLabel.setIcon(nouIcon);
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel lblNewLabel_1 = new JLabel("Usuari:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(5, 5, 5, 5);
		panel_2.add(lblNewLabel_1, gbc);
		GridBagConstraints gbc2 = new GridBagConstraints();
		JTextField textField = new JTextField();
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		gbc2.weightx = 1.0;
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbc2.insets = new Insets(5, 5, 5, 5);
		panel_2.add(textField, gbc2);
		JLabel lblNewLabel_2 = new JLabel("Contrassenya:");
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.gridx = 0;
		gbc3.gridy = 1;
		gbc3.weightx = 0;
		gbc3.anchor = GridBagConstraints.EAST;
		gbc3.insets = new Insets(5, 5, 5, 5);
		panel_2.add(lblNewLabel_2, gbc3);
		JPasswordField passwordField = new JPasswordField();
		GridBagConstraints gbc4 = new GridBagConstraints();
		gbc4.gridx = 1;
		gbc4.gridy = 1;
		gbc4.weightx = 1.0;
		gbc4.fill = GridBagConstraints.HORIZONTAL;

		gbc4.insets = new Insets(5, 5, 5, 5);
		panel_2.add(passwordField, gbc4);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Registra't");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterficieRegistre registre = new InterficieRegistre();
			}
		});
		JButton btnNewButton2 = new JButton("Inicia Sessió");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		panel_3.add(btnNewButton);
		panel_3.add(btnNewButton2);

	}

}
