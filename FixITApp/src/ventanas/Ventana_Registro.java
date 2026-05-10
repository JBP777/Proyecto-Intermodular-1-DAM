package ventanas;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.Colores;
import dao.UsuarioDAO;
import modelo.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JPasswordField;

public class Ventana_Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField input_usuario;
	private JTextField input_correo;
	private JPasswordField input_contrasena;
	
	// EVENTO CIERRE DE VENTANA
	
	public Ventana_Registro(Ventana_Inicio v) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				v.setVisible(true);
				dispose();
			}
		});
		
		
		setTitle("FIXIT!");
		setBounds(100, 100, 800, 528);
		contentPane = new JPanel();
		contentPane.setBackground(Colores.AMARILLO_FONDO);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// LABELS
		
		JLabel label_FIX = new JLabel("FIX");
		label_FIX.setForeground(Colores.AMARILLO_OSCURO);
		label_FIX.setFont(new Font("Britannic Bold", Font.PLAIN, 73));
		label_FIX.setBounds(282, 71, 108, 118);
		contentPane.add(label_FIX);
		
		JLabel label_IT = new JLabel("IT!");
		label_IT.setForeground(Colores.VERDE_BRILLANTE);
		label_IT.setFont(new Font("Britannic Bold", Font.PLAIN, 75));
		label_IT.setBounds(392, 70, 141, 118);
		contentPane.add(label_IT);
		
		JLabel label_Usuario = new JLabel("Nombre de Usuario:");
		label_Usuario.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
		label_Usuario.setBounds(263, 210, 127, 29);
		contentPane.add(label_Usuario);
		
		JLabel label_correo = new JLabel("Correo:");
		label_correo.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
		label_correo.setBounds(263, 240, 108, 29);
		contentPane.add(label_correo);
		
		JLabel label_contrasena = new JLabel("Contraseña:");
		label_contrasena.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
		label_contrasena.setBounds(263, 271, 108, 29);
		contentPane.add(label_contrasena);
		
		JLabel label_terminos = new JLabel("Acepto las condiciones y términos de uso");
		label_terminos.setFont(new Font("Britannic Bold", Font.PLAIN, 11));
		label_terminos.setBounds(268, 311, 218, 20);
		contentPane.add(label_terminos);
		
		JLabel label_errorTerminos = new JLabel("Debes aceptar las condiciones y terminos de uso");
		label_errorTerminos.setForeground(new Color(255, 0, 0));
		label_errorTerminos.setFont(new Font("Arial", Font.PLAIN, 9));
		label_errorTerminos.setBounds(281, 368, 218, 20);
		label_errorTerminos.setVisible(false);
		contentPane.add(label_errorTerminos);
		
		// INPUTS
		
		input_usuario = new JTextField();
		input_usuario.setBounds(400, 214, 86, 20);
		contentPane.add(input_usuario);
		input_usuario.setColumns(10);
		
		input_correo = new JTextField();
		input_correo.setColumns(10);
		input_correo.setBounds(400, 244, 86, 20);
		contentPane.add(input_correo);
		
		input_contrasena = new JPasswordField();
		input_contrasena.setBounds(400, 276, 86, 20);
		contentPane.add(input_contrasena);
		
		// CHECKBOXES
		JCheckBox checkbox_terminos = new JCheckBox("");
		checkbox_terminos.setBounds(473, 311, 26, 23);
		checkbox_terminos.setBackground(Colores.AMARILLO_FONDO);
		contentPane.add(checkbox_terminos);
		
		// BOTONES
		
		// Registrarse
		JButton boton_registrarse = new JButton("Registrarse");
		boton_registrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(UsuarioDAO.obtenerUsuario(input_usuario.getText()) != null) {
					JOptionPane.showMessageDialog(contentPane, "Usuario ya registrado, prueba a iniciar sesion.","Usuario no valido", JOptionPane.WARNING_MESSAGE);
				}else {
					String nombreUsuario,email,contrasenya,fechaRegistro;
					
					nombreUsuario = input_usuario.getText();
					email = input_correo.getText();
					// es necesario hacer esto porque JPassword no devuelve un string por seguridad, igualmente esto no es seguro.
					contrasenya = new String(input_contrasena.getPassword());
					
					// esto es para sacar la fecha de hoy 
					LocalDate fechaActual = LocalDate.now();
					// con esto creamos el formato necesario para nuestra base de datos
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("yy-MM-dd");
					// se guarda la fecha de registro con el formato aplicado
					fechaRegistro = fechaActual.format(formato);
					
					UsuarioDAO.insertarUsuario(new Usuario(nombreUsuario, email, contrasenya, fechaRegistro));
						
					JOptionPane.showMessageDialog(contentPane, "Usuario registrado correctamente","Registo Completado",JOptionPane.INFORMATION_MESSAGE);
					v.setVisible(true);
					dispose();
				}
				
			}
		});
		boton_registrarse.setForeground(new Color(64, 45, 0));
		boton_registrarse.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		boton_registrarse.setBackground(new Color(255, 255, 128));
		boton_registrarse.setBounds(293, 386, 174, 53);
		contentPane.add(boton_registrarse);
		

	}
}
