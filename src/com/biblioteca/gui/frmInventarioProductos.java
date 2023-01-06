package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlInventarioDAO;
import com.biblioteca.entidad.Inventario;

import javax.swing.UIManager;

public class frmInventarioProductos extends JDialog {
	MySqlInventarioDAO invenDAO=new MySqlInventarioDAO();

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTable tblInventario;

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
					frmInventarioProductos frame = new frmInventarioProductos();
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
	public frmInventarioProductos() {
		setTitle("Inventario de Productos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("INVENTARIO DE PRODUCTOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(152, 11, 268, 43);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 98, 564, 447);
		contentPane.add(scrollPane);
		
		tblInventario = new JTable();
		tblInventario.setEnabled(false);
		tblInventario.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Descripci\u00F3n", "Entrada", "Salida", "Stock"
			}
		));
		scrollPane.setViewportView(tblInventario);
		
		listado();
	}
	void listado() {
		        //PASO 1: obtener modelo de la tabla 
				DefaultTableModel model=(DefaultTableModel) tblInventario.getModel();
				//PASO 2: limpiar filas del "model"
				model.setRowCount(0);
				//PASO 3: invocar al m�todo findAll
				ArrayList<Inventario> lista=invenDAO.findAll();
				//PASO 4: bucle para realizar recorrido sobre lista
				for(Inventario lis:lista) {
					//PASO 5: crear un arreglo lineal de la clase Object con los valores del objeto "lib"
					Object row[]= {lis.getCodigo(),lis.getDescripcion(),lis.getEntrada(),lis.getSalida(),lis.getStock()};
					//PASO 6: adicionar como fila el objeto "row" dentro de model
					model.addRow(row);
				}
	}
}