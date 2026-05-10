package ventanas;

import java.awt.BorderLayout;
import util.ConexionBD;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.Colores;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Ventana_Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConexionBD.getConexion();
					Ventana_Inicio frame = new Ventana_Inicio();
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
	
	// COLORES GLOBALES
	

	
	private JTextField input_usuario;
	private JTextField input_contrasena;
	
	public Ventana_Inicio() {
		
		setTitle("FIXIT!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel label_Contrasena = new JLabel("Contraseña:");
		label_Contrasena.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
		label_Contrasena.setBounds(263, 240, 108, 29);
		contentPane.add(label_Contrasena);
		
		JLabel label_noTienesCuenta = new JLabel("¿No tienes cuenta?");
		label_noTienesCuenta.setForeground(Colores.VERDE_BRILLANTE);
		label_noTienesCuenta.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
		label_noTienesCuenta.setBounds(324, 366, 127, 20);
		contentPane.add(label_noTienesCuenta);
		
		JLabel label_contraseñaError = new JLabel("Contraseña incorrecta");
		label_contraseñaError.setHorizontalAlignment(SwingConstants.CENTER);
		label_contraseñaError.setVisible(false);
		label_contraseñaError.setForeground(new Color(255, 0, 0));
		label_contraseñaError.setBounds(293, 277, 174, 14);
		contentPane.add(label_contraseñaError);
		
		// INPUTS
		
		input_usuario = new JTextField();
		input_usuario.setBounds(400, 214, 86, 20);
		contentPane.add(input_usuario);
		input_usuario.setColumns(10);
		
		input_contrasena = new JTextField();
		input_contrasena.setColumns(10);
		input_contrasena.setBounds(400, 244, 86, 20);
		contentPane.add(input_contrasena);
		
		// BOTONES
		
		JButton boton_Iniciar = new JButton("Iniciar Sesion");
		boton_Iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(input_usuario.getText().isEmpty() && input_contrasena.getText().isEmpty()) {
					label_contraseñaError.setVisible(true);
				}else if(input_usuario.getText().equalsIgnoreCase("admin") && input_contrasena.getText().equalsIgnoreCase("admin")) {
					
					Ventana_Principal_Admin v = new Ventana_Principal_Admin(Ventana_Inicio.this);
					v.setVisible(true);
					setVisible(false);
				}else if(input_usuario.getText().equalsIgnoreCase("user") && input_contrasena.getText().equalsIgnoreCase("user")) {
					
					Ventana_Principal_Usuario vU = new Ventana_Principal_Usuario(Ventana_Inicio.this);
					vU.setVisible(true);
					setVisible(false);
				}
				
			}
		});
		boton_Iniciar.setBackground(Colores.AMARILLO_PASTEL);
		boton_Iniciar.setForeground(Colores.AMARILLO_OSCURO);
		boton_Iniciar.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		boton_Iniciar.setBounds(293, 291, 174, 53);
		contentPane.add(boton_Iniciar);
		
		JButton boton_registrarse = new JButton("Registrarse");
		boton_registrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Registro v = new Ventana_Registro(Ventana_Inicio.this);
				v.setVisible(true);
				setVisible(false);
			}
		});
		boton_registrarse.setForeground(new Color(64, 45, 0));
		boton_registrarse.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		boton_registrarse.setBackground(new Color(255, 255, 128));
		boton_registrarse.setBounds(293, 386, 174, 53);
		contentPane.add(boton_registrarse);

	}
}
