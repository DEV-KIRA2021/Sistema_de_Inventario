package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.biblioteca.controlador.MySqlAsisDAO;
import com.biblioteca.controlador.MySqlUsuarioDAO;
import com.biblioteca.entidad.Usuario;
import com.biblioteca.utils.Libreria;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class frmLogin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txtUsuario;
	private JPasswordField txtContra;
	private JButton btnIngresar;

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
					frmLogin frame = new frmLogin();
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
	public frmLogin() {
		setType(Type.UTILITY);
		setForeground(Color.GRAY);
		setBackground(SystemColor.menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(frmLogin.class.getResource("/iconos/login.png")));
		lblNewLabel.setBounds(29, 11, 256, 256);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(10, 300, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("CONTRASE\u00D1A");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(10, 384, 80, 14);
		contentPane.add(lblNewLabel_2);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(10, 325, 294, 35);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContra = new JPasswordField();
		txtContra.setBounds(10, 409, 294, 35);
		contentPane.add(txtContra);
		
		btnIngresar = new JButton("");
		btnIngresar.addActionListener(this);
		btnIngresar.setBackground(Color.WHITE);
		btnIngresar.setIcon(new ImageIcon(frmLogin.class.getResource("/iconos/Ingresar.png")));
		btnIngresar.setBounds(110, 455, 89, 45);
		contentPane.add(btnIngresar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		//leer cajas
				String vLogin,vClave;
				vLogin=txtUsuario.getText();
				vClave=txtContra.getText();
				//invocar al m�todo
				Usuario bean=new MySqlUsuarioDAO().iniciarSesion(vLogin, vClave);
				Usuario oasis=new MySqlAsisDAO().iniciarAsistente(vClave);
				//validar bean
				if (oasis!=null){
					//guardar el c�digoo del usuario
					Libreria.codigoUsuario=oasis.getCodigo();
					frmPrincipalAsistente frm1=new frmPrincipalAsistente();
					frm1.setVisible(true);
					frm1.setExtendedState(MAXIMIZED_BOTH);
					dispose();
				}
				else if(bean!=null){
					//guardar el c�digoo del usuario
					Libreria.codigoUsuario=bean.getCodigo();
					frmPrincipal frm=new frmPrincipal();
					frm.setVisible(true);
					frm.setExtendedState(MAXIMIZED_BOTH);
					dispose();
				}
				else{
					mensaje("Usuario y/o clave incorrectos");
				}
	}
	void mensaje(String m){
		JOptionPane.showMessageDialog(this, m);
	}
}
