package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlMemSolDAO;
import com.biblioteca.entidad.MemorandumSolicitud;
import com.biblioteca.interfaces.MemorandumSolicitudDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmMemorandumSolicitud extends JDialog implements ActionListener {
	
	MySqlMemSolDAO memSolDAO = new MySqlMemSolDAO();

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnGenerar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnEnviar;
	private JTextField txtFecha;
	private JTextField txtDestinatario;
	private JTextField txtTema;
	private JTextField txtFirma;
	private JTextField txtCod;
	private JTable tblMemSol;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMemorandumSolicitud frame = new frmMemorandumSolicitud();
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
	public frmMemorandumSolicitud() {
		setTitle("MEMOR\u00C1NDUM DE SOLICITUD");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("MEMOR\u00C1NDUM DE SOLICITUD");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(SystemColor.activeCaptionText);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 30));
		lblNewLabel.setBounds(127, 11, 317, 44);
		contentPane.add(lblNewLabel);
		
		txtCod = new JTextField();
		txtCod.setBounds(187, 66, 96, 19);
		contentPane.add(txtCod);
		txtCod.setColumns(10);
		
		lblNewLabel_1 = new JLabel("FECHA:");
		lblNewLabel_1.setBounds(50, 107, 125, 17);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("DESTINATARIO:");
		lblNewLabel_2.setBounds(50, 156, 125, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("TEMA:");
		lblNewLabel_3.setBounds(50, 201, 125, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("FIRMA:");
		lblNewLabel_4.setBounds(50, 246, 125, 14);
		contentPane.add(lblNewLabel_4);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.setIcon(new ImageIcon(frmMemorandumSolicitud.class.getResource("/iconos/guardar.png")));
		btnGenerar.addActionListener(this);
		btnGenerar.setBounds(27, 412, 125, 50);
		contentPane.add(btnGenerar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(frmMemorandumSolicitud.class.getResource("/iconos/modificar.png")));
		btnModificar.addActionListener(this);
		btnModificar.setBounds(43, 595, 137, 44);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(frmMemorandumSolicitud.class.getResource("/iconos/eliminar.png")));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(223, 595, 137, 44);
		contentPane.add(btnEliminar);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setIcon(new ImageIcon(frmMemorandumSolicitud.class.getResource("/iconos/enviar.png")));
		btnEnviar.addActionListener(this);
		btnEnviar.setBounds(403, 595, 137, 44);
		contentPane.add(btnEnviar);
		
		txtFecha = new JTextField();
		txtFecha.setEnabled(false);
		txtFecha.setBounds(187, 104, 257, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		txtDestinatario = new JTextField();
		txtDestinatario.setBounds(187, 153, 257, 20);
		contentPane.add(txtDestinatario);
		txtDestinatario.setColumns(10);
		
		txtTema = new JTextField();
		txtTema.setBounds(187, 198, 257, 20);
		contentPane.add(txtTema);
		txtTema.setColumns(10);
		
		txtFirma = new JTextField();
		txtFirma.setBounds(187, 243, 257, 20);
		contentPane.add(txtFirma);
		txtFirma.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(185, 313, 377, 255);
		contentPane.add(scrollPane);
		
		tblMemSol = new JTable();
		tblMemSol.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				seleccionar();
			}
		});
		tblMemSol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionar();
			}
		});
		tblMemSol.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3d. S.", "Fecha", "Destinatario", "Tema", "Firma"
			}
		));
		tblMemSol.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblMemSol);
		
		lblNewLabel_5 = new JLabel("CÓDIGO");
		lblNewLabel_5.setBounds(50, 69, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		fecha();
		listadoTabla();
	}
	
	void fecha() {
		txtFecha.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEnviar) {
			actionPerformedBtnEnviar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnGenerar) {
			actionPerformedBtnGenerar(e);
		}
	}
	protected void actionPerformedBtnGenerar(ActionEvent e) {
		String codMem, fecha, destin, tema, firma;
		
		codMem = txtCod.getText();
		fecha = txtFecha.getText();
		destin = txtDestinatario.getText();
		tema = txtTema.getText();
		firma = txtFirma.getText();
		
		if(codMem.equals("")) {
			mensaje("Campo C�digo Memorandum es obligatorio");
			txtDestinatario.requestFocus();
		}
		else if(codMem.matches("MD"+"\\d+{1,6}")==false) {
			mensaje("Referencia de campo c�digo: MD###");
		}
		else if(destin.equals("")) {
			mensaje("Campo Destinatario es obligatorio");
			txtDestinatario.requestFocus();
		}
		else if(destin.matches("[a-zA-Z]{1,50}")==false) {
			mensaje("Campo Destinatario solo admite letras");
		}
		else if(tema.equals("")) {
			mensaje("Campo TEMA es obligatorio");
			txtTema.requestFocus();
		}
		else if(tema.matches("[a-zA-Z]{1,50}")==false) {
			mensaje("Campo TEMA solo admite letras");
		}
		else if(firma.equals("")) {
			mensaje("Campo FIRMA es obligatorio");
			txtFirma.requestFocus();
		}
		else if(firma.matches("[a-zA-Z]{1,50}")==false) {
			mensaje("Campo FIRMA solo admite letras");
		}
		
		else {
		MemorandumSolicitud memSol = new MemorandumSolicitud();
		
		memSol.setCodMemSol(codMem);
		memSol.setFechaMemSol(fecha);
		memSol.setDestinMemSol(destin);
		memSol.setTemaMemSol(tema);
		memSol.setFirmaMemSol(firma);
		
		int resu = memSolDAO.genReport(memSol);
		if(resu > 0) {
			listadoTabla();
		} else {
			mensaje("No se puede Generar el Reporte");
		}
		}
	}
	protected void actionPerformedBtnModificar(ActionEvent e) {
		String codMem, fecha, destin, tema, firma;
		
		codMem = txtCod.getText();
		fecha = txtFecha.getText();
		destin = txtDestinatario.getText();
		tema = txtTema.getText();
		firma = txtFirma.getText();
		
		MemorandumSolicitud memSol = new MemorandumSolicitud();
		
		memSol.setCodMemSol(codMem);
		memSol.setFechaMemSol(fecha);
		memSol.setDestinMemSol(destin);
		memSol.setTemaMemSol(tema);
		memSol.setFirmaMemSol(firma);
		
		int resu = memSolDAO.update(memSol);
		if(resu > 0) {
			listadoTabla();
		} else {
			mensaje("No se puede modificar el Reporte");
		}
		
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		int boton;
		
		boton = JOptionPane.showConfirmDialog(null, "Seguro de Eliminar?" , "Sistema" , JOptionPane.YES_NO_OPTION);
		
		if(boton == 0) {
			int resu = memSolDAO.delete(txtCod.getText());
			if(resu > 0) {
				txtCod.setText("");
				txtDestinatario.setText("");
				txtTema.setText("");
				txtFirma.setText("");
				listadoTabla();
			} else {
				mensaje("Error en la Eliminación");
			}
		}
	}
	protected void actionPerformedBtnEnviar(ActionEvent e) {
		mensaje("Se ha enviado Correctamente");
	}
	
	void listadoTabla() {
		DefaultTableModel model = (DefaultTableModel) tblMemSol.getModel();
		
		model.setRowCount(0);
		
		ArrayList<MemorandumSolicitud> lista = memSolDAO.findall();
		
		for(MemorandumSolicitud memSol:lista) {
			Object row[] = {memSol.getCodMemSol(),memSol.getFechaMemSol(), memSol.getDestinMemSol(), memSol.getTemaMemSol(), memSol.getFirmaMemSol()};
		
			model.addRow(row);
		}
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	
	void seleccionar(){
		//variables
		int posFila;
		String cod,fecha,des,tem,fir;
		//obtener posiciï¿½n de la fila seleccionada en la tabla
		posFila=tblMemSol.getSelectedRow();
		//getValueAt(posFila,posColuma) retorna un valor(Object) segï¿½n la posiciï¿½n de una fila y columna
		//obtener valores de la fila seleccionada
		cod=tblMemSol.getValueAt(posFila, 0).toString();//0 es la columa cï¿½digo
		fecha=tblMemSol.getValueAt(posFila, 1).toString();
		des=tblMemSol.getValueAt(posFila, 2).toString();
		tem=tblMemSol.getValueAt(posFila, 3).toString();
		fir=tblMemSol.getValueAt(posFila, 4).toString();//1 es la columa tï¿½tulo

		txtCod.setText(cod);
		txtFecha.setText(fecha);
		txtDestinatario.setText(des);
		txtTema.setText(tem);
		txtFirma.setText(fir);
	}
}
