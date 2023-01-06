package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class DlgInformacion extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			DlgInformacion dialog = new DlgInformacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgInformacion() {
		setBounds(100, 100, 461, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(DlgInformacion.class.getResource("/iconos/whatsapp.png")));
		lblNewLabel_3.setBounds(93, 279, 46, 32);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(DlgInformacion.class.getResource("/iconos/instagram.png")));
		lblNewLabel_2.setBounds(51, 276, 32, 35);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(DlgInformacion.class.getResource("/iconos/facebook.png")));
		lblNewLabel_1.setBounds(10, 275, 42, 36);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(DlgInformacion.class.getResource("/iconos/programar.png")));
		lblNewLabel_6.setBounds(371, 237, 64, 63);
		contentPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DlgInformacion.class.getResource("/iconos/img.jpg")));
		lblNewLabel.setBounds(0, 0, 445, 311);
		contentPanel.add(lblNewLabel);
	}
}
