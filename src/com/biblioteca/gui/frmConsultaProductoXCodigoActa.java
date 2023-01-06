package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.controlador.MySqlInventarioDAO;
import com.biblioteca.controlador.MySqlProductoDAO;
import com.biblioteca.entidad.Inventario;
import com.biblioteca.entidad.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmConsultaProductoXCodigoActa extends JDialog implements KeyListener, ActionListener {
	
	
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JButton bnEnviar;
	private JTable tblConsultaProd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmConsultaProductoXCodigoActa dialog = new frmConsultaProductoXCodigoActa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmConsultaProductoXCodigoActa() {
		setModal(true);
		setBounds(100, 100, 726, 364);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Código:");
			lblNombre.setBounds(10, 37, 70, 14);
			contentPanel.add(lblNombre);
		}
		{
			txtNombre = new JTextField();
			txtNombre.addKeyListener(this);
			txtNombre.setBounds(76, 34, 511, 20);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			bnEnviar = new JButton("");
			bnEnviar.addActionListener(this);
			bnEnviar.setIcon(new ImageIcon(frmConsultaProductoXCodigoActa.class.getResource("/iconos/add.png")));
			bnEnviar.setBounds(611, 11, 89, 45);
			contentPanel.add(bnEnviar);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 686, 227);
		contentPanel.add(scrollPane);
		
		tblConsultaProd = new JTable();
		tblConsultaProd.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "DESCRIPCI\u00D3N", "STOCK"
			}
		));
		tblConsultaProd.getColumnModel().getColumn(1).setPreferredWidth(435);
		tblConsultaProd.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblConsultaProd);
	
		
	}

	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == txtNombre) {
			keyReleasedTxtNombre(arg0);
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	protected void keyReleasedTxtNombre(KeyEvent arg0) {
		listadoProductosXCodigo(txtNombre.getText());
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bnEnviar) {
			actionPerformedBnEnviar(e);
		}
	}
	protected void actionPerformedBnEnviar(ActionEvent e) {
		int posFila;
		String cod,des, stock;
		posFila=tblConsultaProd.getSelectedRow();
		cod=tblConsultaProd.getValueAt(posFila, 0).toString();
		des=tblConsultaProd.getValueAt(posFila, 1).toString();
		stock=tblConsultaProd.getValueAt(posFila, 2).toString();
		
		frmActaInventario.txtCodigoPro.setText(cod);
		frmActaInventario.txtDescripcion.setText(des);
		frmActaInventario.txtStock.setText(stock);
		dispose();
	}
	
	void mensaje(String m){
		JOptionPane.showMessageDialog(this,m);
	}
	
	void listadoProductosXCodigo(String cod){
		DefaultTableModel model=(DefaultTableModel) tblConsultaProd.getModel();
		model.setRowCount(0);
		ArrayList<Inventario> lista=new MySqlInventarioDAO().findAllByInventario(cod);
		for(Inventario p:lista){
			Object[] row={p.getCodigo(),p.getDescripcion(),p.getStock()};
			model.addRow(row);
		}
	}
}