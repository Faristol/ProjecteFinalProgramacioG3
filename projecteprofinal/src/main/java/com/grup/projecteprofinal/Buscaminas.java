package com.grup.projecteprofinal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;

public class Buscaminas extends JFrame implements ActionListener, MouseListener {
    private JPanel tableroPanel;
    private JButton[][] casillas;
    private int numCasillas = 20;
    private boolean[][] bombas = ponerBombas();

    public Buscaminas() {
        setTitle("Tablero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el panel del tablero
        tableroPanel = new JPanel();
        tableroPanel.setLayout(new GridLayout(numCasillas, numCasillas));

        // Crear el arreglo de botones
        casillas = new JButton[numCasillas][numCasillas];

        // Agregar los botones al panel
        for (int fila = 0; fila < numCasillas; fila++) {
            for (int columna = 0; columna < numCasillas; columna++) {
                casillas[fila][columna] = new JButton();
                tableroPanel.add(casillas[fila][columna]);
            }
        }

        // Configurar los botones
        for (int fila = 0; fila < numCasillas; fila++) {
            for (int columna = 0; columna < numCasillas; columna++) {
                JButton boton = casillas[fila][columna];
                boton.setName(fila + "." + columna);
                boton.setPreferredSize(new Dimension(10, 10));
                boton.setBackground(Color.GRAY);
                boton.addMouseListener(this);
            }
        }

        getContentPane().add(tableroPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void clickDerechoBoton(MouseEvent e) {
        JButton botonPulsado = (JButton) e.getSource();
        String coordenada = botonPulsado.getName();
        System.out.println(coordenada);
    }

    public boolean isClosed() {
        return !isVisible();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton botonPulsado = (JButton) e.getSource();
        MouseEvent eventoRaton = e;

        if (eventoRaton.getButton() == MouseEvent.BUTTON1) {
            if (botonPulsado.getIcon() != null) {
                botonPulsado.setIcon(null);
            } else {
                String num = botonPulsado.getName();
                String[] nume = num.split("\\.");
                int num1 = Integer.parseInt(nume[0]), num2 = Integer.parseInt(nume[1]);
                desvelarAdj(num1, num2);
            }
        } else {
            if (botonPulsado.getIcon() == new ImageIcon("bandera.png")) {
                botonPulsado.setBackground(Color.GRAY);
                botonPulsado.setIcon(null);
            } else {
                ImageIcon imagen = new ImageIcon("bandera.png");
                botonPulsado.setBackground(Color.GREEN);
                botonPulsado.setIcon(imagen);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public boolean[][] ponerBombas() {
        boolean[][] bombas = new boolean[numCasillas][numCasillas];
        int cont = 0, finalBombas = (int) Math.ceil(numCasillas / 1.25);
        Random random = new Random();

        while (cont < finalBombas) {
            int fila = random.nextInt(numCasillas);
            int columna = random.nextInt(numCasillas);
            if (!bombas[fila][columna]) {
                bombas[fila][columna] = true;
                System.out.println("[" + fila + "][" + columna + "]");
                cont++;
            }
        }
        return bombas;
    }

    public void pisarBomba(int num1, int num2) {
        casillas[num1][num2].setBackground(Color.red);
        ImageIcon imagen = new ImageIcon("mina.png");
        for (int a = 0; a < casillas.length; a++) {
            for (int b = 0; b < casillas.length; b++) {
                if (bombas[a][b] == true) {
                    casillas[a][b].setBackground(Color.red);
                    casillas[a][b].setIcon(imagen);
                }
            }
        }
    }

    public int contarBombas(int num1, int num2) {
        int contador = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((num1 + i >= 0) && (num1 + i < numCasillas) && (num2 + j >= 0) && (num2 + j < numCasillas)) {
                    if (bombas[num1 + i][num2 + j]) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    public void desvelarAdj(int num1, int num2) {
        if (bombas[num1][num2]) {
            pisarBomba(num1, num2);
        } else {
            int bombasAdyacentes = contarBombas(num1, num2);
            casillas[num1][num2].setText(Integer.toString(bombasAdyacentes));
            casillas[num1][num2].setEnabled(false);
            switch(bombasAdyacentes) {
            case 1:
            	casillas[num1][num2].setBackground(Color.BLUE);
            	break;
            case 2:
            	casillas[num1][num2].setBackground(Color.GREEN);
            	break;
            case 3:
            	casillas[num1][num2].setBackground(Color.RED);
            	break;
            default:
            	casillas[num1][num2].setBackground(Color.WHITE);
            	break;
            }
            
            if (bombasAdyacentes == 0) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((num1 + i >= 0) && (num1 + i < numCasillas) && (num2 + j >= 0) && (num2 + j < numCasillas)) {
                            if (casillas[num1 + i][num2 + j].isEnabled()) {
                                desvelarAdj(num1 + i, num2 + j);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Buscaminas buscaminas = new Buscaminas();
    }
}
