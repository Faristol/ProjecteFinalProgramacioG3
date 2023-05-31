package com.grup.projecteprofinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.util.Random;

public class Buscaminas extends JFrame implements ActionListener, MouseListener {
	private JPanel panelGeneral, panelSup, tableroPanel;
    private JButton[][] casillas;
    private JLabel labelBombas, labelCasillas, labelTimer;
    private int numCasillas, contBombas, contCasillas, tiempoTranscurrido;
    private boolean[][] bombas;
    private Timer timer;
    
    //--------------------------
    private JPanel contentPane;

	private int WIDTH = 1;
	private int HEIGHT = 1;
	private int PIXEL_SIZE = 1;
	private int GRID_SIZE = WIDTH / PIXEL_SIZE;

	private boolean[][] pixels;
	private JButton[][] pixelButtons;
	private JPanel panel1;
	private JLabel lblNewLabel;

	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	private JPanel panel2;
	private JButton ColorActual;
	private JButton btnNewButton_3;

	private JPanel panellGeneral;
	//--------------------------

    public Buscaminas() {
    	
    	//--------------
    	this.WIDTH = 400;
		this.HEIGHT = 400;
		this.PIXEL_SIZE = 400;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lblNewLabel = new JLabel("Bienvenidos al Buscaminas, escoge un formato para el tablero!!!");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		lblNewLabel.setForeground(Color.BLUE);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.CENTER);

		btnNewButton = new JButton("Grande");
		btnNewButton_1 = new JButton("Mediano");
		btnNewButton_2 = new JButton("Pequeño");

		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WIDTH = 500;
				HEIGHT = 500;
				PIXEL_SIZE = 10;
				GRID_SIZE = WIDTH / PIXEL_SIZE;
				numCasillas=40;
				creacioPanell();
			}
		});

		panel1.add(btnNewButton);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WIDTH = 500;
				HEIGHT = 500;
				PIXEL_SIZE = 30;
				GRID_SIZE = WIDTH / PIXEL_SIZE;
				numCasillas=20;
				creacioPanell();

			}
		});
		panel1.add(btnNewButton_1);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WIDTH = 500;
				HEIGHT = 500;
				PIXEL_SIZE = 50;
				GRID_SIZE = WIDTH / PIXEL_SIZE;
				numCasillas=10;
				creacioPanell();

			}
		});
		panel1.add(btnNewButton_2);
    	
    	//-------------------------------------
		
		

    }
    
    public void creacioPanell() {
		

		getContentPane().removeAll();
		bombas = ponerBombas();
		setTitle("Tablero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
   // Crear el panel estadistiques
      labelBombas= new JLabel();
      labelBombas.setText(""+contBombas);
      
      labelCasillas= new JLabel();
      labelCasillas.setText(""+contCasillas);
      
      labelTimer= new JLabel();
      timer = new Timer(1000, this);
      labelTimer.setText(""+tiempoTranscurrido);
      timer.start();
      //timer.stop();
      //timer.restart();
      
      panelSup= new JPanel();
      panelSup.setLayout(new GridLayout(1,3));
      panelSup.add(labelBombas);
      panelSup.add(labelTimer);
      panelSup.add(labelCasillas);
      
      

      // Crear el panel del tablero
      tableroPanel = new JPanel();
      tableroPanel.setLayout(new GridLayout(numCasillas, numCasillas));

      // Crear el arreglo de botones
      casillas = new JButton[numCasillas][numCasillas];

      // Agregar los botones al panel
      for (int fila=0;fila<numCasillas;fila++) {
          for (int columna=0;columna<numCasillas;columna++) {
              casillas[fila][columna]= new JButton();
              tableroPanel.add(casillas[fila][columna]);
          }
      }

      // Configurar los botones
      for (int fila=0;fila<numCasillas;fila++) {
          for (int columna=0;columna<numCasillas;columna++) {
              JButton boton = casillas[fila][columna];
              boton.setName(fila+"."+columna);
              boton.setPreferredSize(new Dimension(10, 10));
              boton.setBackground(Color.GRAY);
              boton.addMouseListener(this);
              contCasillas++;
          }
      }
      contCasillas-=contBombas;
      labelCasillas.setText(""+contCasillas);

      // Crear el panel General
      panelGeneral=new JPanel();
      panelGeneral.setLayout(new BorderLayout());
      panelGeneral.add(panelSup, BorderLayout.NORTH);
      panelGeneral.add(tableroPanel);
      panelGeneral.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      
      getContentPane().add(panelGeneral);	
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		repaint();
		revalidate();

	}

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == timer) {
            tiempoTranscurrido++;
            labelTimer.setText(""+tiempoTranscurrido);
        }
    }

    public void clickDerechoBoton(MouseEvent e) {
        JButton botonPulsado = (JButton) e.getSource();
        String coordenada = botonPulsado.getName();
    }

    public boolean isClosed() {
        return !isVisible();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton botonPulsado = (JButton) e.getSource();
        MouseEvent eventoRaton = e;

        if (eventoRaton.getButton() == MouseEvent.BUTTON1) {
            if (botonPulsado.getIcon() != null || botonPulsado.getIcon() == new ImageIcon("bandera.png")) {
            	botonPulsado.setBackground(Color.GRAY);
                botonPulsado.setIcon(null);
                contBombas++;
                labelBombas.setText(""+contBombas);
                
            } else {
            	if(botonPulsado.getBackground()==Color.GRAY) {
            		String num = botonPulsado.getName();
                    String[] nume = num.split("\\.");
                    int num1 = Integer.parseInt(nume[0]), num2 = Integer.parseInt(nume[1]);
                    desvelarAdj(num1, num2);
            	}
            }
        } else {
            if(botonPulsado.getBackground()==Color.GRAY) {
            	ImageIcon imagen = new ImageIcon("bandera.png");
                botonPulsado.setBackground(Color.YELLOW);
                botonPulsado.setIcon(imagen);
                contBombas--;
                labelBombas.setText(""+contBombas);
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
        int cont = 0, finalBombas = numCasillas;
        Random random = new Random();

        while (cont < finalBombas) {
            int fila = random.nextInt(numCasillas);
            int columna = random.nextInt(numCasillas);
            if (!bombas[fila][columna]) {
                bombas[fila][columna] = true;
                cont++;
                contBombas++;
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
        String resultadoFinal="Has durado: "+tiempoTranscurrido+" seg. \n"+"Casillas por desvelar: "+contCasillas;
        JOptionPane.showMessageDialog(null, resultadoFinal);
        /*
        String result = showInputDialog(resultadoFinal, "Fin de la partida");
        if (result != null) {
            JOptionPane.showMessageDialog(null, "El valor ingresado fue: " + result);
        } else {
            JOptionPane.showMessageDialog(null, "No se ingres� ning�n valor.");
        }
        */
        dispose();
    }
    public static String showInputDialog(String message, String title) {
        JTextField textField = new JTextField();
        Object[] components = { message, textField };
        
        int option = JOptionPane.showConfirmDialog(null, components, title, JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            return textField.getText();
        }
        
        return null;
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
        	timer.stop();
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
            contCasillas--;
        	labelCasillas.setText(""+contCasillas);
            if (bombasAdyacentes == 0) {
                for (int i=-1;i<=1;i++) {
                    for (int j=-1;j<=1;j++) {
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscaminas frame = new Buscaminas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
