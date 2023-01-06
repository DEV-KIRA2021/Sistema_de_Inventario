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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlProductoDAO;
import com.biblioteca.entidad.Producto;
import com.biblioteca.interfaces.ProductoDAO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class frmMantenimientoProductos extends JDialog {
	
	MySqlProductoDAO ProductoDAO=new MySqlProductoDAO();

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JScrollPane scrollPane;
	private JTable tblProductos;
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnModificar;
	private JButton btnEliminar;

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
					frmMantenimientoProductos frame = new frmMantenimientoProductos();
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
	public frmMantenimientoProductos() {
		setTitle("Productos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("PRODUCTOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(220, 11, 118, 39);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("C\u00F3digo");
		lblNewLabel_1.setBounds(25, 70, 90, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Descripci\u00F3n");
		lblNewLabel_2.setBounds(25, 130, 90, 14);
		contentPane.add(lblNewLabel_2);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(125, 67, 140, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(125, 127, 140, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 189, 540, 355);
		contentPane.add(scrollPane);
		
		tblProductos = new JTable();
		tblProductos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				seleccionar();
			}
		});
		tblProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionar();
			}
		});
		tblProductos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Descripci\u00F3n"
			}
		));
		tblProductos.getColumnModel().getColumn(0).setMinWidth(10);
		tblProductos.getColumnModel().getColumn(0).setMaxWidth(20000);
		scrollPane.setViewportView(tblProductos);
		 listado();
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon(frmMantenimientoProductos.class.getResource("/iconos/nuevo.png")));
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpieza();
			}
		});
		btnNuevo.setBounds(34, 589, 105, 39);
		contentPane.add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(frmMantenimientoProductos.class.getResource("/iconos/guardar.png")));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PASO 1: leer controles
				String codigo, descripcion;

				codigo=txtCodigo.getText().trim();
				descripcion =txtDescripcion.getText().trim();
				//PASO 2: VALIDAR
				if(codigo.equals("")) {
					mensaje("Campo código es obligatorio");
					txtCodigo.requestFocus();
				}
				else if(codigo.matches("[a-zA-Z\\Ã±\\Ã‘\\s\\Ã¡\\Ã©\\Ã­\\Ã³\\Ãº\\Ã�\\Ã‰\\Ã�\\Ã“\\Ãš]{3}"+"-"+"\\d+{3,4}")==false) {
					mensaje("Referencia de campo cÃ³digo: PRO-###");
					txtCodigo.requestFocus();
					//System.out.println("123".matches("\\d"));
				}
				else if(descripcion.equals("")) {
					mensaje("Campo descripciÃ³n es obligatorio");
					txtDescripcion.requestFocus();
				}
				else if(descripcion.matches("[a-zA-Z\\Ã±\\Ã‘\\s\\Ã¡\\Ã©\\Ã­\\Ã³\\Ãº\\Ã�\\Ã‰\\Ã�\\Ã“\\Ãš]{2,50}")==false) {
					mensaje("Campo descripciÃ³n: MIN 2 Y MÃ�X 50 carÃ¡cteres");
					txtDescripcion.requestFocus();
				}
				
				else {
					//PASO 3: crear objeto de la clase Libro
					Producto p=new Producto();
					//PASO 4: asignar valor a los atributos del objeto "lib" usando las variables "setear"
					p.setCodigoProd(codigo);
					p.setDescripcion(descripcion);
					//PASO 5: invocar al mï¿½todo save y enviar el objeto "lib"
					int resu=ProductoDAO.save(p);
					if(resu>0) {
						mensaje("Producto registrado");
						listado();
					}
					else
						mensaje("Error en el registro");
				}
				
				
			}
		});
		btnGuardar.setBounds(173, 589, 105, 39);
		contentPane.add(btnGuardar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(frmMantenimientoProductos.class.getResource("/iconos/modificar.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PASO 1: leer controles
				String codigo, descripcion;
				codigo=txtCodigo.getText();
				descripcion=txtDescripcion.getText().trim();
				//PASO 2: VALIDAR
				if(codigo.equals("")) {
					mensaje("Campo cÃ³digo es obligatorio");
					txtCodigo.requestFocus();
				}
				else if(codigo.matches("[a-zA-Z\\Ã±\\Ã‘\\s\\Ã¡\\Ã©\\Ã­\\Ã³\\Ãº\\Ã�\\Ã‰\\Ã�\\Ã“\\Ãš]{3}"+"-"+"\\d+{3,4}")==false) {
					mensaje("Referencia de campo cÃ³digo: PRO-###");
					txtCodigo.requestFocus();
					//System.out.println("123".matches("\\d"));
				}
				else if(descripcion.equals("")) {
					mensaje("Campo descripciÃ³n es obligatorio");
					txtDescripcion.requestFocus();
				}
				else if(descripcion.matches("[a-zA-Z\\Ã±\\Ã‘\\s\\Ã¡\\Ã©\\Ã­\\Ã³\\Ãº\\Ã�\\Ã‰\\Ã�\\Ã“\\Ãš]{2,50}")==false) {
					mensaje("Campo descripciÃ³n: MIN 2 Y MÃ�X 50 carÃ¡cteres");
					txtDescripcion.requestFocus();
				}
				else {
					//PASO 3: crear objeto de la clase Libro
					Producto lib=new Producto();
					//PASO 4: asignar valor a los atributos del objeto "lib" usando las variables "setear"
					lib.setCodigoProd(codigo);
					lib.setDescripcion(descripcion);
					//PASO 5: invocar al mï¿½todo update y enviar el objeto "lib"
					int resu=ProductoDAO.update(lib);
					if(resu>0) {
						mensaje("Producto actualizado");
						listado();
					}
					else
						mensaje("Error en la actualizaciÃ³n");
				}

			}
		});
		btnModificar.setBounds(312, 589, 105, 39);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(frmMantenimientoProductos.class.getResource("/iconos/eliminar.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//mostrar ventana de confirmaciï¿½n
				int boton;
				boton=JOptionPane.showConfirmDialog(null,"Seguro de eliminar?","Sistema",JOptionPane.YES_NO_OPTION);
				if(boton==0) {//si
					//invocar al mï¿½todo deleteByID 
					int resu=ProductoDAO.deleteByID(txtCodigo.getText());
					//validar resu
					if(resu>0) {
						mensaje("Producto eliminado");
						listado();
					}
					else
						mensaje("Error en la eliminaciï¿½n");
				}
			}
		});
		btnEliminar.setBounds(451, 589, 105, 39);
		contentPane.add(btnEliminar);
	}
	
	void listado() {
		//PASO 1: obtener modelo de la tabla tblLibros
		DefaultTableModel model=(DefaultTableModel) tblProductos.getModel();
		//PASO 2: limpiar filas del "model"
		model.setRowCount(0);
		//PASO 3: invocar al mï¿½todo findAll
		ArrayList<Producto> lista=ProductoDAO.findAll();
		//PASO 4: bucle para realizar recorrido sobre lista
		for(Producto p:lista) {
			//PASO 5: crear un arreglo lineal de la clase Object con los valores del objeto "lib"
			Object row[]= {p.getCodigoProd(),p.getDescripcion()};
			//PASO 6: adicionar como fila el objeto "row" dentro de model
			model.addRow(row);
		}
	}
	
	//MÃ©todos tipo void (sin parÃ¡metros)
		void limpieza() {
			txtCodigo.setText("");
			txtDescripcion.setText("");
		}
		
		void mensaje(String m) {
			JOptionPane.showMessageDialog(null, m);
		}
		
		void seleccionar(){
			//variables
			int posFila;
			String cod,desc;
			//obtener posiciï¿½n de la fila seleccionada en la tabla
			posFila=tblProductos.getSelectedRow();
			//getValueAt(posFila,posColuma) retorna un valor(Object) segï¿½n la posiciï¿½n de una fila y columna
			//obtener valores de la fila seleccionada
			cod=tblProductos.getValueAt(posFila, 0).toString();//0 es la columa cï¿½digo
			desc=tblProductos.getValueAt(posFila, 1).toString();//1 es la columa tï¿½tulo

			txtCodigo.setText(cod);
			txtDescripcion.setText(desc);
		}
}
