package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Fondo.Fondo;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;

public class frmPrincipalAsistente extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnSalir;
	private JMenu mnMantenimiento;
	private JMenu mnInformacion;
	private JMenuItem mntmSalir;
	private JMenuItem mntmMore;
	private JMenuItem mntmMemorandumDesig;
	private JMenuItem mntmMemorandumSoli;
	private JMenu mnReportes;
	private JMenuItem mntmInformeSustentos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipalAsistente frame = new frmPrincipalAsistente();
					frame.setVisible(true);
					frame.setExtendedState(MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmPrincipalAsistente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnSalir = new JMenu("Salir");
		mnSalir.setFont(new Font("Tahoma", Font.BOLD, 16));
		mnSalir.setIcon(new ImageIcon(frmPrincipalAsistente.class.getResource("/iconos/cerrar-sesion.png")));
		menuBar.add(mnSalir);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(frmPrincipalAsistente.class.getResource("/iconos/cerrar.png")));
		mntmSalir.addActionListener(this);
		mnSalir.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setFont(new Font("Tahoma", Font.BOLD, 16));
		mnMantenimiento.setIcon(new ImageIcon(frmPrincipalAsistente.class.getResource("/iconos/mantenimiento.png")));
		menuBar.add(mnMantenimiento);
		
		mntmMemorandumDesig = new JMenuItem("Memor\u00E1ndum de Designaci\u00F3n");
		mntmMemorandumDesig.setIcon(new ImageIcon(frmPrincipalAsistente.class.getResource("/iconos/memorandum.png")));
		mntmMemorandumDesig.addActionListener(this);
		mnMantenimiento.add(mntmMemorandumDesig);
		
		mntmMemorandumSoli = new JMenuItem("Memor\u00E1ndum de Solicitud");
		mntmMemorandumSoli.setIcon(new ImageIcon(frmPrincipalAsistente.class.getResource("/iconos/memorandum.png")));
		mntmMemorandumSoli.addActionListener(this);
		mnMantenimiento.add(mntmMemorandumSoli);
		
		mnReportes = new JMenu("Reportes");
		mnReportes.setFont(new Font("Tahoma", Font.BOLD, 16));
		mnReportes.setIcon(new ImageIcon(frmPrincipalAsistente.class.getResource("/iconos/reporte.png")));
		menuBar.add(mnReportes);
		
		mntmInformeSustentos = new JMenuItem("Informe Sustentos");
		mntmInformeSustentos.setIcon(new ImageIcon(frmPrincipalAsistente.class.getResource("/iconos/informe.png")));
		mntmInformeSustentos.addActionListener(this);
		mnReportes.add(mntmInformeSustentos);
		
		mnInformacion = new JMenu("Informaci\u00F3n");
		mnInformacion.setFont(new Font("Tahoma", Font.BOLD, 16));
		mnInformacion.setIcon(new ImageIcon(frmPrincipalAsistente.class.getResource("/iconos/informacion.png")));
		menuBar.add(mnInformacion);
		
		mntmMore = new JMenuItem("M\u00E1s....");
		mntmMore.setIcon(new ImageIcon(frmPrincipalAsistente.class.getResource("/iconos/mas.png")));
		mntmMore.addActionListener(this);
		mnInformacion.add(mntmMore);
		contentPane = new Fondo("/iconos/Fondo2.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmMore) {
			actionPerformedMntmMore(e);
		}
		if (e.getSource() == mntmMemorandumSoli) {
			actionPerformedMntmMemorandumSoli(e);
		}
		if (e.getSource() == mntmMemorandumDesig) {
			actionPerformedMntmMemorandumDesig(e);
		}
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
		if (e.getSource() == mntmInformeSustentos) {
			actionPerformedMntmInformeSustentos(e);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent e) {
		int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea cerrar el sistema", "Salir",
				JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	protected void actionPerformedMntmMemorandumDesig(ActionEvent e) {
		frmMemorandumDesignacion frm = new frmMemorandumDesignacion();
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
	}
	protected void actionPerformedMntmMemorandumSoli(ActionEvent e) {
		frmMemorandumSolicitud frm1 = new frmMemorandumSolicitud();
		frm1.setLocationRelativeTo(null);
		frm1.setVisible(true);
	}
	
	protected void actionPerformedMntmInformeSustentos(ActionEvent e) {
		frmInformeSustentos frm8 = new frmInformeSustentos();
		frm8.setLocationRelativeTo(null);
		frm8.setVisible(true);
	}
	protected void actionPerformedMntmMore(ActionEvent e) {
		DlgInformacion frm9 = new DlgInformacion();
		frm9.setLocationRelativeTo(null);
		frm9.setVisible(true);
	}
}
