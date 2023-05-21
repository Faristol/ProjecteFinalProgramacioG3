package com.grup.projecteprofinal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class InterficieSeleccioJocs extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel texto;
	private JButton btnNewButton, btnNewButton_1, btnNewButton_2;
    private Buscaminas buscaminasFrame;
    private JuegoVida juegoVidaFrame;
    private PixelArt pixelArtFrame;

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
	 */
	public InterficieSeleccioJocs() {
		setTitle("Seleccio Jocs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 400);
		
        //setLayout(new FlowLayout());
        getContentPane().setBackground(Color.RED);
        
       
        
//        button1.addActionListener(this);
//        button2.addActionListener(this);
//        button3.addActionListener(this);
        
        
        
        
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 217, 61));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton_3 = new JButton("Dona'mde baixa");
		panel.add(btnNewButton_3);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_4 = new JLabel("La img de l'usuari");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(40, 5, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Opci�n 1");
		comboBox.addItem("Opci�n 2");
		comboBox.addItem("Opci�n 3");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 5;
		panel_1.add(comboBox, gbc_comboBox);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("La Llar Dels Jocs");
		panel_2.add(lblNewLabel_3);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 117, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel = new JLabel("Buscamines:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		//lblNewLabel.setHorizontalAlignment(SwingConstraints.CENTER);
		gbc_lblNewLabel.gridwidth = 6;
		gbc_lblNewLabel.insets = new Insets(50, 200, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 3;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);
		
		btnNewButton = new JButton("Accedir");
		btnNewButton.addActionListener(this);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(50, 40, 5, 0);
		gbc_btnNewButton.gridx = 10;
		gbc_btnNewButton.gridy = 3;
		panel_3.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Pixel Art:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 6;
		gbc_lblNewLabel_1.insets = new Insets(10, 200, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 5;
		panel_3.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		btnNewButton_1 = new JButton("Accedir");
		btnNewButton_1.addActionListener(this);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(10, 40, 5, 0);
		gbc_btnNewButton_1.gridx = 10;
		gbc_btnNewButton_1.gridy = 5;
		panel_3.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Joc de la vida:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.gridwidth = 6;
		gbc_lblNewLabel_2.insets = new Insets(10, 200, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 7;
		panel_3.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		btnNewButton_2 = new JButton("Accedir");
		btnNewButton_2.addActionListener(this);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridwidth = 2;
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(10, 40, 5, 0);
		gbc_btnNewButton_2.gridx = 10;
		gbc_btnNewButton_2.gridy = 7;
		panel_3.add(btnNewButton_2, gbc_btnNewButton_2);
		
		
		
		
		
		//pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
        if (buscaminasFrame == null || buscaminasFrame.isClosed()) {
            if (e.getSource() == btnNewButton) {
                buscaminasFrame = new Buscaminas();
                buscaminasFrame.setVisible(true);
            }
        }
        if(pixelArtFrame==null|| pixelArtFrame.isClosed()) {
        	if (e.getSource() == btnNewButton_1) {
        		pixelArtFrame = new PixelArt();
        		pixelArtFrame.setVisible(true);
            }
        }
//        if(juegoVidaFrame==null|| juegoVidaFrame.isClosed()) {
//        	if (e.getSource() == btnNewButton_2) {
//                juegoVidaFrame = new JuegoVida();
//                juegoVidaFrame.setVisible(true);
//            }
//        }
    }

	public void mostrarVentana() {
	    setVisible(true); // Muestra la ventana cuando se llame a este método
	}

}
