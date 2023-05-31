package com.grup.projecteprofinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")

public class JocDeLaVida extends JFrame {
	@SuppressWarnings("unused")
	private JPanel contentPane;
	private JLabel labelInici;
	private JPanel panelNordInici;
	private JPanel panelCentre;
	private JButton Menuda;
	private JButton Mitjana;
	private JButton Gran;
	private int alturaTauler;
	private int amplariaTauler;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JocDeLaVida frame = new JocDeLaVida();
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
	public JocDeLaVida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		setTitle("Joc de la Vida");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNordInici = new JPanel();
		contentPane.add(panelNordInici, BorderLayout.NORTH);
		GridBagLayout gbl_panelNordInici = new GridBagLayout();
		gbl_panelNordInici.columnWidths = new int[]{0, 0};
		gbl_panelNordInici.rowHeights = new int[]{0, 0};
		gbl_panelNordInici.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelNordInici.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelNordInici.setLayout(gbl_panelNordInici);
		
		labelInici = new JLabel("Selecciona una mida");
		GridBagConstraints gbc_labelInici = new GridBagConstraints();
		gbc_labelInici.gridx = 0;
		gbc_labelInici.gridy = 0;
		panelNordInici.add(labelInici, gbc_labelInici);
		
		panelCentre = new JPanel();
		contentPane.add(panelCentre, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentreInici = new GridBagLayout();
		gbl_panelCentreInici.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelCentreInici.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelCentreInici.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelCentreInici.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelCentre.setLayout(gbl_panelCentreInici);
		
		Menuda = new JButton("Menuda");
		
		GridBagConstraints gbc_Menuda = new GridBagConstraints();
		gbc_Menuda.fill = GridBagConstraints.HORIZONTAL;
		gbc_Menuda.insets = new Insets(0, 0, 0, 5);
		gbc_Menuda.gridx = 0;
		gbc_Menuda.gridy = 3;
		panelCentre.add(Menuda, gbc_Menuda);
		
		Mitjana = new JButton("Mitjana");
		
		GridBagConstraints gbc_Mitjana = new GridBagConstraints();
		gbc_Mitjana.fill = GridBagConstraints.HORIZONTAL;
		gbc_Mitjana.insets = new Insets(0, 0, 0, 5);
		gbc_Mitjana.gridx = 1;
		gbc_Mitjana.gridy = 3;
		panelCentre.add(Mitjana, gbc_Mitjana);
		
		Gran = new JButton("Gran");
		
		GridBagConstraints gbc_Gran = new GridBagConstraints();
		gbc_Gran.fill = GridBagConstraints.HORIZONTAL;
		gbc_Gran.gridx = 2;
		gbc_Gran.gridy = 3;
		panelCentre.add(Gran, gbc_Gran);
		Menuda.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        setAlturaTauler(50);
		        amplariaTauler = 50;
		        getContentPane().removeAll();
		        revalidate();
		        setBounds(0, 0, 700, 700);
		        

		        Tauler graella = new Tauler(amplariaTauler, amplariaTauler);
		        contentPane.add(graella, BorderLayout.CENTER);
		        revalidate();
		        repaint();
		        
		    }
		});
		Mitjana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAlturaTauler(80);
				amplariaTauler = 80;
				getContentPane().removeAll();
		        revalidate();
		        setBounds(0, 0, 700, 700);

		        Tauler graella = new Tauler(amplariaTauler, amplariaTauler);
		        getContentPane().add(graella, BorderLayout.CENTER);
		        revalidate();
		        repaint();
				
			}
		});
		Gran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAlturaTauler(110);
				amplariaTauler = 110;
				getContentPane().removeAll();
		        revalidate();
		        setBounds(0, 0, 700, 700);

		        Tauler graella = new Tauler(amplariaTauler, amplariaTauler);
		        getContentPane().add(graella, BorderLayout.CENTER);
		        revalidate();
		        repaint();
				
			}
		});
	}

	public JLabel labelInici() {
		return labelInici;
	}

	public int getAlturaTauler() {
		return alturaTauler;
	}

	public void setAlturaTauler(int alturaTauler) {
		this.alturaTauler = alturaTauler;
	}
}
