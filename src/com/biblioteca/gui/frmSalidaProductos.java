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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlSalidaDAO;
import com.biblioteca.entidad.Producto;
import com.biblioteca.entidad.SalidaProductos;

import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class frmSalidaProductos extends JDialog {
	MySqlSalidaDAO salidaDAO=new MySqlSalidaDAO();
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtCodigo;
	private JTextField txtFecha;
	public static JTextField txtCodPro;
	public static JTextField txtDescripcion;
	private JTextField txtCantidad;
	private JButton btnBuscar;
	private JScrollPane scrollPane;
	private JTable tblSalida;
	private JButton btnNuevo;
	private JButton btnRegistrarE;
	
	

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
					frmSalidaProductos frame = new frmSalidaProductos();
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
	public frmSalidaProductos() {
		setTitle("Salida de Productos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("SALIDA DE PRODUCTOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(159, 11, 243, 34);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("C\u00F3digo");
		lblNewLabel_1.setBounds(25, 75, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Fecha");
		lblNewLabel_2.setBounds(25, 120, 80, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("C\u00F3d. Producto");
		lblNewLabel_3.setBounds(25, 160, 80, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Descripcion");
		lblNewLabel_4.setBounds(25, 205, 80, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Cantidad");
		lblNewLabel_5.setBounds(25, 250, 80, 14);
		contentPane.add(lblNewLabel_5);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(125, 72, 120, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(125, 117, 120, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		txtCodPro = new JTextField();
		txtCodPro.setBounds(125, 157, 120, 20);
		contentPane.add(txtCodPro);
		txtCodPro.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(125, 202, 219, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(125, 247, 120, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConsultaProductoXCodigoSalida frm=new frmConsultaProductoXCodigoSalida();
				frm.setLocationRelativeTo(null);
				frm.setVisible(true);
			}
		});
		btnBuscar.setIcon(new ImageIcon(frmSalidaProductos.class.getResource("/iconos/busca.png")));
		btnBuscar.setBounds(255, 146, 89, 34);
		contentPane.add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 281, 564, 309);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.setEnabled(false);
		tblSalida.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Fecha", "C\u00F3d. Producto", "Descripci\u00F3n", "Cantidad"
			}
		));
		scrollPane.setViewportView(tblSalida);
		listado() ;
		txtFecha.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon(frmSalidaProductos.class.getResource("/iconos/nuevo.png")));
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnNuevo.setBounds(94, 614, 153, 44);
		contentPane.add(btnNuevo);
		
		btnRegistrarE = new JButton("Registrar Salida");
		btnRegistrarE.setIcon(new ImageIcon(frmSalidaProductos.class.getResource("/iconos/guardar.png")));
		btnRegistrarE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String codigoSalida, cantidad;
				
				codigoSalida=txtCodigo.getText().trim();
				cantidad=txtCantidad.getText().trim();
				if(codigoSalida.equals("")) {
					mensaje("Campo cÃ³digo es obligatorio");
					txtCodigo.requestFocus();
				}
				else if(codigoSalida.matches("S"+"\\d+{1,7}")==false) {
					mensaje("Referencia de campo cÃ³digo: S###");
					txtCodigo.requestFocus();
				}
				else if(cantidad.equals("")) {
					mensaje("Campo cantidad es obligatorio");
					txtCantidad.requestFocus();
				}
				else if(cantidad.matches("[0-9]{4}||[1]{1}[0-9]{4}||[2]{1}[0]{4}")==false) {
					mensaje("Campo cantidad: MIN 1,000 Y MÁX 20,000");
				}
				else {
				
				SalidaProductos sp=new SalidaProductos();
				sp.setCodigoSalida(txtCodigo.getText());
				sp.setFechaSalida(java.sql.Date.valueOf(txtFecha.getText()));
				sp.setCodigoProd(txtCodPro.getText());
				sp.setDescripcion(txtDescripcion.getText());
				sp.setCantidad(Integer.parseInt(txtCantidad.getText()));
				//crear arreglo de objetos de la clase Detalle
				ArrayList<Producto> data=new ArrayList<Producto>();
				//bucle para realizar recorrido sobre la tabla tblDetalle
				for(int i=0;i<tblSalida.getRowCount();i++){
					//crear objeto de la clase Detalle
					Producto p=new Producto();
					//setear
					p.setCodigoProd(tblSalida.getValueAt(i, 0).toString());
					p.setDescripcion(tblSalida.getValueAt(i, 2).toString());
					
					//adicionar objeto "d" dentro del arreglo "data"
					data.add(p);
				}
				//
				int salida=new MySqlSalidaDAO().save(sp, data);
				if(salida>0){
					mensaje("Salida de producto registrada");
					adicionar();
					
				}
				else
					mensaje("Error en el registro");
				
			}
				}
		});
		btnRegistrarE.setBounds(341, 615, 153, 44);
		contentPane.add(btnRegistrarE);
	}
	
	void adicionar() {
		DefaultTableModel model=(DefaultTableModel) tblSalida.getModel();
		//
		String codigo=txtCodigo.getText();
		for (int i = 0; i < tblSalida.getRowCount(); i++) {
			if (codigo == tblSalida.getValueAt(i, 0).toString()) {
				mensaje("Código existe");
				return;
			}
		}
		
		//2 arreglo de la clase Object
		Object row[]={txtCodigo.getText(),(txtFecha.getText()),
						txtCodPro.getText(),txtDescripcion.getText(),
						(txtCantidad.getText())};
		//3 adicionar
		model.addRow(row);
	}
	void mensaje(String m){
		JOptionPane.showMessageDialog(null, m);
	}
	
	void limpiar(){
		txtCodigo.setText("");
		txtCodPro.setText("");
		txtDescripcion.setText("");
		txtCantidad.setText("");
		//DefaultTableModel model=(DefaultTableModel) tblEntrada.getModel();
		//model.setRowCount(0);
	}
	
	void listado() {
		//PASO 1: obtener modelo de la tabla tblLibros
		DefaultTableModel model=(DefaultTableModel) tblSalida.getModel();
				//PASO 2: limpiar filas del "model"
				model.setRowCount(0);
				//PASO 3: invocar al mï¿½todo findAll
				ArrayList<SalidaProductos> lista=salidaDAO.findAll();
				//PASO 4: bucle para realizar recorrido sobre lista
				for(SalidaProductos sp:lista) {
					//PASO 5: crear un arreglo lineal de la clase Object con los valores del objeto "lib"
					Object row[]= {sp.getCodigoSalida(),sp.getFechaSalida(),sp.getCodigoProd(),sp.getDescripcion(),sp.getCantidad()};
					//PASO 6: adicionar como fila el objeto "row" dentro de model
					model.addRow(row);
	}
	}
}
