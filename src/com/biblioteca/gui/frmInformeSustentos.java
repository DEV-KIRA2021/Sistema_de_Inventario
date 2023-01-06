package com.biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.biblioteca.componente.JComboBoxBD;
import com.biblioteca.controlador.MySqlActaDAO;
import com.biblioteca.entidad.ActaInventario;
import com.biblioteca.utils.GeneradorReporte;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class frmInformeSustentos extends JFrame implements ItemListener, ActionListener {
	private MySqlActaDAO ActaDAO=new MySqlActaDAO();
	
	private JComboBox cboDiferencia;
	private JButton Procesar;
	private JPanel panelReporte;

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
					frmInformeSustentos frame = new frmInformeSustentos();
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
	public frmInformeSustentos() {
		setTitle("INFORME");
		setBounds(100, 100, 812, 658);
		getContentPane().setLayout(null);
		
		JLabel lblConsultaDeLibros = new JLabel("INFORME DE SUSTENTO DE BAJA DE BIENES");
		lblConsultaDeLibros.setOpaque(true);
		lblConsultaDeLibros.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeLibros.setForeground(Color.WHITE);
		lblConsultaDeLibros.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblConsultaDeLibros.setBackground(SystemColor.desktop);
		lblConsultaDeLibros.setBounds(30, 11, 732, 45);
		getContentPane().add(lblConsultaDeLibros);
		
		JLabel lblNewLabel = new JLabel("Seleccione Tipo de Diferencia:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(30, 124, 230, 14);
		getContentPane().add(lblNewLabel);
		
		cboDiferencia = new JComboBoxBD("select *from tipoDiferencia");
		cboDiferencia.addItemListener(this);
		cboDiferencia.setBounds(261, 106, 342, 32);
		getContentPane().add(cboDiferencia);
		
		Procesar = new JButton("Procesar");
		Procesar.setIcon(new ImageIcon(frmInformeSustentos.class.getResource("/iconos/procesar.png")));
		Procesar.addActionListener(this);
		Procesar.setBounds(623, 106, 139, 32);
		getContentPane().add(Procesar);
		
		panelReporte = new JPanel();
		panelReporte.setBounds(26, 151, 736, 447);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout(0, 0));

	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboDiferencia) {
			itemStateChangedCboEditorial(e);
		}
	}
	protected void itemStateChangedCboEditorial(ItemEvent e) {
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Procesar) {
			actionPerformedProcesar(e);
		}
	}
	protected void actionPerformedProcesar(ActionEvent e) {
		
		mostrar(cboDiferencia.getSelectedItem()+"");
		
		
		
	}
	void mostrar(String nomDif){
		try {
			ArrayList<ActaInventario> data=new MySqlActaDAO().findAllByTipoDiferencia(nomDif);
			if(data.size()>0){
				//origen de datos
				JRBeanCollectionDataSource datos=new JRBeanCollectionDataSource(data);
				//pdf
				JasperPrint pdf=GeneradorReporte.genera("InformeSustento.jasper", datos, null);
				//visor
				JRViewer visor=new JRViewer(pdf);
				//limpiar panel
				panelReporte.removeAll();
				//adicionar en el panel el visor
				panelReporte.add(visor);
				//volver a dibujar en panel
				panelReporte.repaint();
				//validar los cambios en el panel
				panelReporte.revalidate();
			}
			else{
				//limpiar panel
				panelReporte.removeAll();
				//volver a dibujar en panel
				panelReporte.repaint();
				//validar los cambios en el panel
				panelReporte.revalidate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}




