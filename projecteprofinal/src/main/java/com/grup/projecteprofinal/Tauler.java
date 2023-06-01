package com.grup.projecteprofinal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tauler extends JPanel {
    private int files;
    private int columnes;
    private Celda[][] celdes;

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

   

    public int getFiles() {
		return files;
	}

	public int getColumnes() {
		return columnes;
	}

	public Celda[][] getCeldes() {
		return celdes;
	}

	public void setFiles(int files) {
		this.files = files;
	}

	public void setColumnes(int columnes) {
		this.columnes = columnes;
	}

	public void setCeldes(Celda[][] celdes) {
		this.celdes = celdes;
	}

	public Celda getCelda(int fila, int columna) {
        return celdes[fila][columna];
    }
}