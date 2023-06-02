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
	static int alturaTauler;
	private static int amplariaTauler;
	static JButton condicionsInicials;
	private JButton start;
	private JButton pausar;
	private JButton alentir;
	private JButton accelerar;
	private JButton info;
	private JButton acaba;
	private JPanel panellBotons;
	private Tauler graella = new Tauler(1, 1);
	static JocDeLaVida frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JocDeLaVida();
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
		panellBotons = new JPanel(new GridLayout(1, 8));

		condicionsInicials = new JButton("Aleatori");
		start = new JButton("Start");
		pausar = new JButton("Pausar");
		alentir = new JButton("<<");
		accelerar = new JButton(">>");
		info = new JButton("Info");
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

	public static void setAlturaTauler(int alturaTauler) {
		JocDeLaVida.alturaTauler = alturaTauler;
	}

	public void iniciarInfoStart() {
		condicionsInicials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Tauler.condicionsAleatories();

				// si polsa tant en condicions manuals com en aleatories, es creara el listener
				// per a el start
				// quan es polse en start es desabilitarï¿½ el botï¿½ Dibuixa
				crearListenerStart();
			}
		});
		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String informacio = "\n"
						+ "El Joc de la Vida, creat per John Conway el 1970, és un joc de simulació de cèl·lules en un tauler bidimensional.\n"
						+ "Aquest joc és conegut com a \"autòmat cel·lular\" i ha captivat l'interès de matemàtics i entusiastes durant dècades.\n"
						+ "Les regles són les següents:\n\n"
						+ "Tota cèl·lula viva amb menys de dos veïnes vives mor (de solitud).\n"
						+ "Tota cèl·lula viva amb més de tres veïnes vives mor (d'excés de concentració).\n"
						+ "Tota cèl·lula viva amb dos o tres veïnes vives segueix viva per a la següent generació.\n"
						+ "Tota cèl·lula morta amb exactament tres veïnes vives torna a la vida.\n"
						+ "El joc acaba quan totes les cèl·lules han mort.\n\n"
						+ "Per a començar, polsa en Aleatori, així s'establiran les condicions inicials, i polsa en Start.\n"
						+ "Podràs pausar, reprendre, aturar, accelerar o alentir les generacions en qualsevol moment.\n"
						+ "En acabar, es mostrarà un panell amb estadístiques. Així mateix, per a generar altres condicions inicials, hauràs\n"
						+ "d'apretar novament en Dibuixa.";
				JOptionPane.showMessageDialog(null, informacio, "Informaciï¿½", JOptionPane.INFORMATION_MESSAGE);

			}
		});

	}

	public void crearListenerStart() {
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// quan polsa start s'habiliten els botons
				setEnabled(true);
				habilitarBotons();
				condicionsInicials.setEnabled(false);
				start.setEnabled(false);
				Tauler.inicia(graella, frame);

			}
		});
	}

	public void habilitarBotons() {

		pausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Tauler.jocPausat == false) {

					Tauler.timer.stop();
					Tauler.jocPausat = true;
					pausar.setText("Continuar");
				} else {

					Tauler.timer.start();
					Tauler.jocPausat = false;
					pausar.setText("Pausar");
				}
			}
		});

		acaba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tauler.timer.start();
				Tauler.jocPausat = false;
				Tauler.acaba = true;
				destruirPanell();

			}
		});
		alentir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int velocitatActual = Tauler.timer.getDelay();
				int novaVelocitat = velocitatActual + 100;
				Tauler.timer.setDelay(novaVelocitat);

			}
		});
		accelerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int velocitatActual = Tauler.timer.getDelay();
				if (velocitatActual > 0) {
					int novaVelocitat = velocitatActual - 100;
					Tauler.timer.setDelay(novaVelocitat);
				}
			}
		});
	}

	public int getAmplariaTauler() {
		return amplariaTauler;
	}

	public void setAmplariaTauler(int amplariaTauler) {
		this.amplariaTauler = amplariaTauler;
	}

	public void destruirPanell() {
		panellBotons.removeAll();
		condicionsInicials = new JButton("Aleatori");
		start = new JButton("Start");
		pausar = new JButton("Pausar");
		alentir = new JButton("<<");
		accelerar = new JButton(">>");
		info = new JButton("Info");
		acaba = new JButton("Acaba");

		panellBotons.add(condicionsInicials);
		panellBotons.add(start);
		panellBotons.add(pausar);
		panellBotons.add(acaba);
		panellBotons.add(alentir);
		panellBotons.add(accelerar);
		panellBotons.add(info);
		condicionsInicials.setVisible(true);
		start.setVisible(true);
		pausar.setVisible(true);
		alentir.setVisible(true);
		accelerar.setVisible(true);
		info.setVisible(true);
		acaba.setVisible(true);
		repaint();
		revalidate();
		iniciarInfoStart();
	}
	
}
