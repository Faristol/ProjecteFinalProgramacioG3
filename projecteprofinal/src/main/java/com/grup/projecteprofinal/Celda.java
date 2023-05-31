package com.grup.projecteprofinal;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Celda extends JPanel {
    private boolean viva;
    private int posX;
    private int posY;
    private int amplaria = 5;
    private int altura = 5;

    public Celda(int posX, int posY) {
        this.viva = false;
        this.posX = posX;
        this.posY = posY;
        setPreferredSize(new Dimension(amplaria, altura));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    public boolean estaViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
        actualitzarColor();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    private void actualitzarColor() {
        if (viva) {
            setBackground(Color.WHITE);
        } else {
            setBackground(Color.BLACK);
        }
    }
}




