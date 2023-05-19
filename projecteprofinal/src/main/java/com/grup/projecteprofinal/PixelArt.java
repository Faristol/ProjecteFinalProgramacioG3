package com.grup.projecteprofinal;

import java.awt.Dimension;

import javax.swing.JFrame;

public class PixelArt extends JFrame{
	
        public PixelArt() {
            super("PixelArt");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(500, 500));
            setLocationRelativeTo(null);
            setVisible(true);
        }
        
        public boolean isClosed() {
            return !isVisible();
        }
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}