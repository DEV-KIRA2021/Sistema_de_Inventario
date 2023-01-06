package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.componente.JComboBoxBD;
import com.biblioteca.controlador.MySqlActaDAO;
import com.biblioteca.entidad.ActaInventario;

import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class frmActaInventario extends JDialog {
	MySqlActaDAO ActaDAO=new MySqlActaDAO();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	public static JTextField txtCodigoPro;
	public static JTextField txtDescripcion;
	public static JTextField txtStock;
	private JScrollPane scrollPane;
	private JTable tblActa;
	private JButton btnBuscar;
	private JComboBox cboDiferencia;
	private JTextField txtCodigoActa;

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
					frmActaInventario frame = new frmActaInventario();
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
	public frmActaInventario() {
		setModal(true);
		setTitle("Acta de Inventario");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 659, 717);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("ACTA DE INVENTARIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(237, 11, 207, 42);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("C\u00F3d. Producto");
		lblNewLabel_1.setBounds(25, 114, 106, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Descripci\u00F3n");
		lblNewLabel_2.setBounds(25, 160, 106, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Stock");
		lblNewLabel_3.setBounds(25, 205, 106, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Tipo de Diferencia");
		lblNewLabel_4.setBounds(25, 251, 106, 14);
		contentPane.add(lblNewLabel_4);
		
		txtCodigoPro = new JTextField();
		txtCodigoPro.setBounds(141, 111, 86, 20);
		contentPane.add(txtCodigoPro);
		txtCodigoPro.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(141, 157, 165, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(141, 202, 86, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		cboDiferencia = new JComboBoxBD("Select*from tipoDiferencia");
		cboDiferencia.setBounds(141, 247, 185, 22);
		contentPane.add(cboDiferencia);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 302, 641, 374);
		contentPane.add(scrollPane);
		
		tblActa = new JTable();
		tblActa.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3d. Acta Inventario", "C\u00F3d. Producto", "Descripcion", "Stock", "Tipo de Diferencia"
			}
		));
		tblActa.getColumnModel().getColumn(0).setPreferredWidth(86);
		tblActa.getColumnModel().getColumn(1).setPreferredWidth(64);
		tblActa.getColumnModel().getColumn(2).setPreferredWidth(115);
		tblActa.getColumnModel().getColumn(3).setPreferredWidth(60);
		tblActa.getColumnModel().getColumn(4).setPreferredWidth(150);
		scrollPane.setViewportView(tblActa);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(frmActaInventario.class.getResource("/iconos/busca.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConsultaProductoXCodigoActa frm=new frmConsultaProductoXCodigoActa();
				frm.setLocationRelativeTo(null);
				frm.setVisible(true);
			}
		});
		btnBuscar.setBounds(237, 98, 89, 42);
		contentPane.add(btnBuscar);
		
		JLabel lblActaInventario = new JLabel("Cód. Acta Inventario");
		lblActaInventario.setBounds(25, 68, 121, 14);
		contentPane.add(lblActaInventario);
		
		txtCodigoActa = new JTextField();
		txtCodigoActa.setBounds(141, 65, 86, 20);
		contentPane.add(txtCodigoActa);
		txtCodigoActa.setColumns(10);
		
		listado();
		
		JButton btnGuardar = new JButton("");
		btnGuardar.setIcon(new ImageIcon(frmActaInventario.class.getResource("/iconos/guardar.png")));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String codigo,codProd,descripcion,stock;
				int codigoDif;

				
				
				codigo=txtCodigoActa.getText().trim();
				codProd=txtCodigoPro.getText().trim();
				descripcion=txtDescripcion.getText().trim();
				stock=txtStock.getText().trim();
				codigoDif=cboDiferencia.getSelectedIndex()+1;
			
				if(codigo.equals("")) {
					mensaje("Campo código es obligatorio");
					txtCodigoActa.requestFocus();
				}
				else if(codigo.matches("A"+"\\d+{1,7}")==false) {
					mensaje("Referencia de campo código: A###");
					txtCodigoActa.requestFocus();
				}
				else {
				ActaInventario a=new ActaInventario();
				if(cboDiferencia.getSelectedIndex()==0 && stock.matches("^-[0-9]+$")) {
					mensaje("Elija tipo de diferencia faltante...");	
					}
				else if(cboDiferencia.getSelectedIndex()==1  && stock.matches("^[0-9]+$") ||cboDiferencia.getSelectedIndex()==2  && stock.matches("^[0-9]+$")
						||cboDiferencia.getSelectedIndex()==3 && stock.matches("^[0-9]+$")){
					mensaje("Elija tipo de diferencia sobrante");	
				}
				else {


				a.setCodActInv(codigo);
				a.setCodprod(codProd);
				a.setDescripcion(descripcion);
				a.setStoc(Integer.parseInt(stock));
				a.setCodDifere(codigoDif);
				

				int resu=ActaDAO.save(a);
				if(resu>0) {
				    mensaje("Acta registrado");
				    listado();
				}
				else
					mensaje("Error en el registro");
			}
			}
			}
		});
		btnGuardar.setBounds(402, 228, 89, 42);
		contentPane.add(btnGuardar);
		
		JButton btnNewButton = new JButton("Nuevo");
		btnNewButton.setIcon(new ImageIcon(frmActaInventario.class.getResource("/iconos/nuevo.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCajas();

			}
		});
		btnNewButton.setBounds(517, 228, 89, 42);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(frmActaInventario.class.getResource("/iconos/actainventario.png")));
		lblNewLabel_5.setBounds(440, 71, 128, 128);
		contentPane.add(lblNewLabel_5);
	}
	
	void mensaje(String m){
		JOptionPane.showMessageDialog(this, m);
	}
	
    void listado() {
		
		DefaultTableModel model=(DefaultTableModel) tblActa.getModel();
		model.setRowCount(0);
	
		ArrayList<ActaInventario> lista= ActaDAO.findAll();
		for(ActaInventario a:lista) {
			Object row[]= {a.getCodActInv(),a.getCodprod(),a.getDescripcion(),a.getStoc(),a.getNombreDife()};
			model.addRow(row);
		}
		
	}
    
    void limpiarCajas() {
    	txtCodigoActa.setText("");
    	txtCodigoPro.setText("");
    	txtDescripcion.setText("");
    	txtStock.setText("");
    	
    }
}
