package com.grup.projecteprofinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JButton condicionsInicials;
	private JButton start;
	private JButton pausar;
	private JButton alentir;
	private JButton accelerar;
	private JButton info;
	private JButton acaba;
	private JPanel panellBotons;
	private Tauler graella;

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
		gbl_panelNordInici.columnWidths = new int[] { 0, 0 };
		gbl_panelNordInici.rowHeights = new int[] { 0, 0 };
		gbl_panelNordInici.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelNordInici.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelNordInici.setLayout(gbl_panelNordInici);

		labelInici = new JLabel("Selecciona una mida");
		GridBagConstraints gbc_labelInici = new GridBagConstraints();
		gbc_labelInici.gridx = 0;
		gbc_labelInici.gridy = 0;
		panelNordInici.add(labelInici, gbc_labelInici);

		panelCentre = new JPanel();
		contentPane.add(panelCentre, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentreInici = new GridBagLayout();
		gbl_panelCentreInici.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelCentreInici.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelCentreInici.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelCentreInici.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
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
				graella.setBackground(Color.darkGray);
				contentPane.setBackground(Color.DARK_GRAY);
				contentPane.add(graella, BorderLayout.CENTER);
				panellBotons();

				revalidate();
				repaint();

			}
		});
		Mitjana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAlturaTauler(70);
				amplariaTauler = 70;
				getContentPane().removeAll();
				revalidate();
				setBounds(0, 0, 700, 700);

				graella = new Tauler(amplariaTauler, amplariaTauler);
				graella.setBackground(Color.darkGray);
				contentPane.setBackground(Color.DARK_GRAY);
				getContentPane().add(graella, BorderLayout.CENTER);
				panellBotons();
				revalidate();
				repaint();

			}
		});
		Gran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAlturaTauler(80);
				amplariaTauler = 80;
				getContentPane().removeAll();
				revalidate();
				setBounds(0, 0, 700, 700);

				Tauler graella = new Tauler(amplariaTauler, amplariaTauler);
				graella.setBackground(Color.darkGray);
				contentPane.setBackground(Color.DARK_GRAY);
				getContentPane().add(graella, BorderLayout.CENTER);
				panellBotons();
				revalidate();
				repaint();

			}
		});
	}

	public void panellBotons() {
		panellBotons = new JPanel(new GridLayout(1, 7));

		condicionsInicials = new JButton("Dibuixa");
		start = new JButton("Start");
		pausar = new JButton("Pausar");
		alentir = new JButton("Alentir");
		accelerar = new JButton("Accelerar");
		info = new JButton("Informacio");
		acaba = new JButton("Acaba");

		panellBotons.add(condicionsInicials);
		panellBotons.add(start);
		panellBotons.add(pausar);
		panellBotons.add(acaba);
		panellBotons.add(alentir);
		panellBotons.add(accelerar);
		panellBotons.add(info);

		contentPane.add(panellBotons, BorderLayout.SOUTH);
		setResizable(false);
		iniciarInfoStart();

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

	public void iniciarInfoStart() {
		condicionsInicials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Manual", "Aleatori" };
				int choice = JOptionPane.showOptionDialog(null, "Seleccione una opció", "Condicions Inicials",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (choice == 0) {
					Tauler.condicionsManuals();
					//si polsa tant en condicions manuals com en aleatories, es creara el listener per a el start
					//quan es polse en start es desabilitarà el botó Dibuixa
					
				} else if (choice == 1) {
					Tauler.condicionsAleatories();
				}
			}
		});
		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String informacio = "\n"
						+ "El Joc de la Vida, creat per John Conway el 1970, és un joc de simulació de cèl·lules en un tauler bidimensional.\nAquest joc és conegut com a \"autòmat cel·lular\" i ha captivat l'interès de matemàtics i entusiastes durant dècades.\nLes regles son les següents:"
						+ "\n\nTota cèl·lula viva amb menys de dos veïnes vives mor (de solitud).\n"
						
						+ "Tota cèl·lula viva amb més de tres veïnes vives mor (d'excés de concentració).\n"
						+ "Tota cèl·lula viva amb dos o tres veïnes vives segueix viva per a la següent generació.\n"
						+ "Tota cèl·lula morta amb exactament tres veïnes vives torna a la vida.\n"
						+ "El joc acaba quan totes les cèl·lules han mort.\n"
						+ "\n"
						+ "Per a començar polsa en Dibuixa, elegeix entre establir les condicions inicials tú mateixa o aleatoriament i polsa en Start.\n"
				        + "Podràs pausar, reprendre, aturar, accelerar o alentir les generacions en qualsevol moment.\n"
						+ "En acabar es mostrarà un panell amb estadístiques, així mateix per a generar altres condicions inicials, hauràs\n"
				        + "d'apretar novament en dibuixa.";
				JOptionPane.showMessageDialog(null, informacio, "Informació",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});

	}
}
