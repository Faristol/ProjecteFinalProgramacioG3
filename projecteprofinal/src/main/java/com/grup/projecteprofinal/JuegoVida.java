package com.grup.projecteprofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JuegoVida extends JFrame implements ActionListener{

    private static final int TAMANO_CELDA = 10;
    private Tablero tablero;
    private Timer timer;
    private JButton iniciar, pausar,detener;

    public static void main(String[] args) {  
    	JuegoVida juego = new JuegoVida();
    	juego.setVisible(true);
        
    }

    public JuegoVida() {
        setTitle("Juego de la Vida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        
        tablero = new Tablero(50, 50);
        add(tablero, BorderLayout.CENTER);

        JPanel controles = new JPanel();
        iniciar = new JButton("Iniciar");
        pausar = new JButton("Pausar");
        detener = new JButton("Detener");
        controles.add(iniciar);
        controles.add(pausar);
        controles.add(detener);
        add(controles, BorderLayout.SOUTH);

        iniciar.addActionListener(this);
        pausar.addActionListener(this);
        detener.addActionListener(this);
    }
    
    private void actRepaint() {
    	
    }

    private void iniciarJuego() {
        timer = new Timer(1000, e -> {
        	tablero.actualizar();
        	tablero.repaint();});
        timer.start();
    }

    private void pausarJuego() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void detenerJuego() {
        pausarJuego();
        tablero.reset();
        tablero.repaint();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == iniciar) {
			iniciarJuego();
        }else if (e.getSource() == pausar) {
        	pausarJuego();
        }else {
        	detenerJuego();
        }
	}
}

class Tablero extends JPanel {

    private int filas;
    private int columnas;
    private boolean[][] celulas;
    private boolean[][] celulasSiguiente;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        celulas = new boolean[filas][columnas];
        celulasSiguiente = new boolean[filas][columnas];
        
        inicializarCelulas();
    }

    private void inicializarCelulas() {
        Random rand = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celulas[i][j] = rand.nextBoolean();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                g.setColor(celulas[i][j] ? Color.BLACK : Color.WHITE);
                g.fillRect(j * JuegoVida.TAMANO_CELDA, i * JuegoVida.TAMANO_CELDA, JuegoVida.TAMANO_CELDA, JuegoVida.TAMANO_CELDA);
            }
        }
    }

    public void actualizar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int vecinosVivos = contarVecinosVivos(i, j);
                celulasSiguiente[i][j] = celulas[i][j];
                if (celulas[i][j]) {
                    if (vecinosVivos < 2 || vecinosVivos > 3) {
                        celulasSiguiente[i][j] = false;
                    }
                } else {
                    if (vecinosVivos == 3) {
                        celulasSiguiente[i][j] = true;
                    }
                }
            }
        }

        boolean[][] temp = celulas;
        celulas = celulasSiguiente;
        celulasSiguiente = temp;
    }

    private int contarVecinosVivos(int fila, int columna) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int filaVecino = fila + i;
                int columnaVecino = columna + j;
                if (filaVecino >= 0&& filaVecino < filas && columnaVecino >= 0 && columnaVecino < columnas) {
                    if (celulas[filaVecino][columnaVecino]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public void reset() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celulas[i][j] = false;
            }
        }
    }
    public boolean isClosed() {
        return !isVisible();
    }
}