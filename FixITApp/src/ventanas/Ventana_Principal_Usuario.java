package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.Incidencia;
import dao.IncidenciaDAO;
import util.Colores;
import java.util.ArrayList;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Ventana_Principal_Usuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Usuario usuarioActual;

	public Ventana_Principal_Usuario(Ventana_Inicio v, Usuario u) {
		usuarioActual = u;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		setTitle("FIXIT!");
		setBounds(100, 100, 500, 480);
		setResizable(false);

		// MENU
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);

		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Redirige a la ventana de inicio y cierra esta
				// TODO: sustituir por la clase correcta si no es Ventana_Inicio
				dispose();
				v.setVisible(true);
			}
		});
		mnOpciones.add(mntmCerrarSesion);

		// PANEL PRINCIPAL
		contentPane = new JPanel();
		contentPane.setBackground(Colores.AMARILLO_FONDO);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// LABELS — LOGO

		JLabel label_FIX = new JLabel("FIX");
		label_FIX.setForeground(Colores.AMARILLO_OSCURO);
		label_FIX.setFont(new Font("Britannic Bold", Font.PLAIN, 35));
		label_FIX.setBounds(10, 11, 58, 43);
		contentPane.add(label_FIX);

		JLabel label_IT = new JLabel("IT!");
		label_IT.setForeground(Colores.VERDE_BRILLANTE);
		label_IT.setFont(new Font("Britannic Bold", Font.PLAIN, 35));
		label_IT.setBounds(64, 5, 49, 54);
		contentPane.add(label_IT);

		// LINEA SEPARADORA debajo del logo
		JSeparator separador = new JSeparator();
		separador.setForeground(Colores.AMARILLO_OSCURO);
		separador.setBounds(0, 62, 500, 2);
		contentPane.add(separador);

		// MENSAJE DE BIENVENIDA
		JLabel lblBienvenida = new JLabel("¡Bienvenido/a,");
		lblBienvenida.setFont(new Font("Britannic Bold", Font.PLAIN, 22));
		lblBienvenida.setForeground(Colores.AMARILLO_OSCURO);
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setBounds(0, 85, 490, 35);
		contentPane.add(lblBienvenida);

		// nombre del usuario en verde grande para destacarlo
		JLabel lblNombreUsuario = new JLabel(usuarioActual.getNombreUsuario() + "!");
		lblNombreUsuario.setFont(new Font("Britannic Bold", Font.PLAIN, 30));
		lblNombreUsuario.setForeground(Colores.VERDE_BRILLANTE);
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreUsuario.setBounds(0, 118, 490, 45);
		contentPane.add(lblNombreUsuario);

		// subtitulo orientativo
		JLabel lblSubtitulo = new JLabel("¿Qué deseas hacer hoy?");
		lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSubtitulo.setForeground(Colores.AMARILLO_OSCURO);
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setBounds(0, 168, 490, 25);
		contentPane.add(lblSubtitulo);

		// BOTONES — centrados, mismo ancho, separados uniformemente
		int bx = 145, bw = 200, bh = 50, by = 215, gap = 18;

		// BOTONES

		JButton boton_verIncidencias = new JButton("Ver Incidencias");
		boton_verIncidencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: abrir ventana de lista de incidencias cuando este creada
				// Ventana_Lista_Incidencias vli = new Ventana_Lista_Incidencias(Ventana_Principal_Usuario.this, usuarioActual);
				// vli.setVisible(true);
			}
		});
		boton_verIncidencias.setForeground(Colores.VERDE_OSCURO);
		boton_verIncidencias.setBackground(Colores.VERDE_BRILLANTE);
		boton_verIncidencias.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
		boton_verIncidencias.setBounds(bx, by, bw, bh);
		contentPane.add(boton_verIncidencias);

		JButton boton_agregarIncidencia = new JButton("Crear nueva Incidencia");
		boton_agregarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_AgregarIncidencia_Usuario va = new Ventana_AgregarIncidencia_Usuario(Ventana_Principal_Usuario.this, usuarioActual);
				va.setLocationRelativeTo(Ventana_Principal_Usuario.this); // se centra respecto a la ventana actual
				va.setVisible(true);
				dispose();
			}
		});
		boton_agregarIncidencia.setForeground(Colores.VERDE_OSCURO);
		boton_agregarIncidencia.setBackground(Colores.VERDE_BRILLANTE);
		boton_agregarIncidencia.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
		boton_agregarIncidencia.setBounds(bx, by + bh + gap, bw, bh);
		contentPane.add(boton_agregarIncidencia);

		JButton boton_enviarMensaje = new JButton("Enviar Mensaje");
		boton_enviarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Enviar_Mensaje vm = new Ventana_Enviar_Mensaje(usuarioActual);
				vm.setLocationRelativeTo(Ventana_Principal_Usuario.this);
				vm.setVisible(true);
			}
		});
		boton_enviarMensaje.setForeground(Colores.VERDE_OSCURO);
		boton_enviarMensaje.setBackground(Colores.VERDE_BRILLANTE);
		boton_enviarMensaje.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
		boton_enviarMensaje.setBounds(bx, by + (bh + gap) * 2, bw, bh);
		contentPane.add(boton_enviarMensaje);

		JButton boton_verPerfil = new JButton("Ver Perfil");
		boton_verPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_VerPerfil_Usuario vp = new Ventana_VerPerfil_Usuario(Ventana_Principal_Usuario.this, usuarioActual);
				vp.setVisible(true);
				dispose();
			}
		});
		boton_verPerfil.setForeground(Colores.VERDE_OSCURO);
		boton_verPerfil.setBackground(Colores.VERDE_BRILLANTE);
		boton_verPerfil.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
		boton_verPerfil.setBounds(bx, by + (bh + gap) * 3, bw, bh);
		contentPane.add(boton_verPerfil);
	}

	// cargarIncidencias se mantiene por si se necesita desde otras ventanas al volver aqui
	public void cargarIncidencias() {
		// TODO: llamar desde Ventana_Lista_Incidencias cuando este creada
		ArrayList<Incidencia> incidencias = IncidenciaDAO.obtenerIncidencias();
	}
}