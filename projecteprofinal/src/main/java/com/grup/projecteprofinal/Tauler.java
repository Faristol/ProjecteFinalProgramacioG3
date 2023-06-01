package com.grup.projecteprofinal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Tauler extends JPanel {
	private static int files;
	private static int columnes;
	private static Celda[][] celdes;
	static int intervaloTiempo = 1000;
	static Timer timer;
	static int comptadorGeneracions = 0;
	static int celCreades = 0;
	static boolean jocPausat = false;
	static boolean acaba = false;
	private static MouseAdapter mouseListener;
	

	public Tauler(int files, int columnes) {
		this.files = files;
		this.columnes = columnes;
		celdes = new Celda[files][columnes];
		setLayout(new GridLayout(files, columnes));
		setPreferredSize(new Dimension(files, columnes));
		inicialitzarCeldes();
	}

	private void inicialitzarCeldes() {
		for (int i = 0; i < files; i++) {
			for (int j = 0; j < columnes; j++) {
				Celda celda = new Celda(i, j);
				celdes[i][j] = celda;
				add(celda);
			}
		}
	}
	public static void desactivarMouseListener() {
	    for (int i = 0; i < files; i++) {
	        for (int j = 0; j < columnes; j++) {
	            final JPanel celda = celdes[i][j];
	            MouseListener[] mouseListeners = celda.getMouseListeners();
	            if (mouseListeners != null && mouseListeners.length > 0) {
	                celda.removeMouseListener(mouseListener);
	            }
	        }
	    }
	}


	public static void condicionsManuals() {
	    mouseListener = new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            JPanel celda = (JPanel) evt.getSource(); 
	            Celda celdaCelda = (Celda) celda; 
	            celdaCelda.setViva(true);
	        }
	    };

	    for (int i = 0; i < files; i++) {
	        for (int j = 0; j < columnes; j++) {
	            final JPanel celda = celdes[i][j];
	            celda.addMouseListener(mouseListener);
	        }
	    }
	}

	public static void condicionsAleatories() {
		Random aleatori = new Random();
		double densitatMinima = 0.20;
		double densitatMaxima = 0.30;
		double densitatVives = densitatMinima + (densitatMaxima - densitatMinima) * aleatori.nextDouble();
		int numVivesDesitjades = (int) (files * columnes * densitatVives);
		int celdesVivesGenerades = 0;

		while (celdesVivesGenerades < numVivesDesitjades) {
			int fila = aleatori.nextInt(files);
			int columna = aleatori.nextInt(columnes);

			if (!celdes[fila][columna].isViva()) {
				celdes[fila][columna].setViva(true);
				celdesVivesGenerades++;
			}
		}
	}

	public static void inicia(final Tauler tauler, final JocDeLaVida joc) {
		comptadorGeneracions = 0;
		celCreades = 0;
		timer = new Timer(intervaloTiempo, new ActionListener() {

			private ArrayList<Integer> celVivesCadaGeneracio = new ArrayList<Integer>();
			private Celda[][] generacionAnterior;
			private int generacionCount = 0;

			public void actionPerformed(ActionEvent e) {
				if (!acaba) {
					if (!jocPausat) {
						Celda[][] nuevaGeneracion = new Celda[files][columnes];
						boolean hayCambios = false;
						int celVives = 0;

						for (int i = 0; i < files; i++) {
							for (int j = 0; j < columnes; j++) {
								nuevaGeneracion[i][j] = new Celda(i, j);
								int vecinosVivos = contarVecinosVivos(i, j);

								if (celdes[i][j].isViva()) {
									celVives++;
									// Célula viva
									if (vecinosVivos < 2 || vecinosVivos > 3) {
										// Muere por soledad o sobrepoblación
										nuevaGeneracion[i][j].setViva(false);
										hayCambios = true;

									} else {
										// Sobrevive a la siguiente generación
										nuevaGeneracion[i][j].setViva(true);
									}
								} else {
									// Célula muerta
									if (vecinosVivos == 3) {
										// Nace una nueva célula
										celCreades++;
										nuevaGeneracion[i][j].setViva(true);
										hayCambios = true;
									}
								}
							}
						}
						celVivesCadaGeneracio.add(celVives);
						comptadorGeneracions++;

						if (generacionCount >= 2 && generacionAnterior != null
								&& Arrays.deepEquals(nuevaGeneracion, generacionAnterior)) {
							// El juego ha llegado a un estado estacionario, detener la ejecución
							
							for (int i = 0; i < files; i++) {
								for (int j = 0; j < columnes; j++) {
									celdes[i][j].setViva(nuevaGeneracion[i][j].isViva());
								}
							}
							tauler.repaint();
							tauler.revalidate();
							timer.stop();
							int max = Collections.max(celVivesCadaGeneracio);
							double densitatMaxima = (double) max / (files * columnes);
							JOptionPane.showMessageDialog(null,
									"El joc ha arribat a un estat estacionari.\nNº Generacions: " + comptadorGeneracions
											+ "\nNº Cèl·lules creades: " + celCreades + "\nNº Cèl·lules Màxim Arribat: "
											+ max + "\nDensitat Màxima Arribada: " + densitatMaxima * 100 + "%",
									"Fi del joc", JOptionPane.INFORMATION_MESSAGE);
							resetejarTauler();
							
							joc.destruirPanell();
							joc.repaint();
							joc.revalidate();
							
							
							return;

						} else if (!hayCambios) {
							
							// No hay cambios en la generación, detener la ejecución
							for (int i = 0; i < files; i++) {
								for (int j = 0; j < columnes; j++) {
									celdes[i][j].setViva(nuevaGeneracion[i][j].isViva());
								}
							}
							
							tauler.repaint();
							tauler.revalidate();
							timer.stop();

							
							int max = Collections.max(celVivesCadaGeneracio);
							double densitatMaxima = (double) max / (files * columnes);
							JOptionPane.showMessageDialog(null,
									"Totes les cèl·lules han mort.\nNº Generacions: " + comptadorGeneracions
											+ "\nNº Cèl·lules creades: " + celCreades + "\nNº Cèl·lules Màxim Arribat: "
											+ max + "\nDensitat Màxima Arribada: " + densitatMaxima * 100 + "%",
									"Fi del joc", JOptionPane.INFORMATION_MESSAGE);
							resetejarTauler();
							joc.destruirPanell();
							joc.repaint();
							joc.revalidate();
							
							return;

						} else {
							// Actualizar la generación anterior y el contador
							generacionAnterior = new Celda[files][columnes];
							for (int i = 0; i < files; i++) {
								for (int j = 0; j < columnes; j++) {
									generacionAnterior[i][j] = new Celda(i, j);
									generacionAnterior[i][j].setViva(celdes[i][j].isViva());
								}
							}
							for (int i = 0; i < files; i++) {
								for (int j = 0; j < columnes; j++) {
									celdes[i][j].setViva(nuevaGeneracion[i][j].isViva());
								}
							}
							generacionCount++;
							tauler.repaint();
							tauler.revalidate();
						}
					}

				}
				if (acaba) {
					Tauler.timer.stop();
					
					
					joc.destruirPanell();
					
					JOptionPane.showMessageDialog(null,
							"El joc ha sigut aturat.\nNº Generacions: " + comptadorGeneracions
									+ "\nNº Cèl·lules creades: " + celCreades,
							"Fi del joc", JOptionPane.INFORMATION_MESSAGE);
					Tauler.resetejarTauler();
					joc.repaint();
					joc.revalidate();
					acaba = false;

					return;
				}

			}

		});

		timer.start();
	}
	public static void deshabilitar() {
	    for (int i = 0; i < files; i++) {
	        for (int j = 0; j < columnes; j++) {
	            final JPanel celda = celdes[i][j];
	            celda.setEnabled(false);
	        }
	    }
	}

	static void resetejarTauler() {
		for (int i = 0; i < files; i++) {
			for (int j = 0; j < columnes; j++) {
				celdes[i][j].setViva(false);
			}
		}
		celCreades = 0;
		comptadorGeneracions = 0;
	}

	private static int contarVecinosVivos(int fila, int columna) {
		int contador = 0;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int vecinaFila = (fila + i + files) % files;
				int vecinaColumna = (columna + j + columnes) % columnes;

				if (celdes[vecinaFila][vecinaColumna].isViva()) {
					contador++;
				}
			}
		}

		// Restar la propia celda si está viva
		if (celdes[fila][columna].isViva()) {
			contador--;
		}

		return contador;
	}
}
