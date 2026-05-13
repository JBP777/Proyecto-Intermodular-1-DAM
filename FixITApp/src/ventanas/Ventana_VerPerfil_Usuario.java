package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Usuario;
import util.Colores;

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

public class Ventana_VerPerfil_Usuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected DefaultTableModel modelo;
	private JTextField mostrar_Usuario;
	private JTextField mostrar_Correo;
	private JTextField mostrar_IncidenciasCreadas;
	private JTextField mostrar_IncidenciasResueltas;
	private JTextField mostrar_ValoracionMedia;
	Ventana_Inicio vI = new Ventana_Inicio();
	private Usuario usuarioActual;
	Ventana_Principal_Usuario vPu = new Ventana_Principal_Usuario(vI, usuarioActual);
	
	
	public Ventana_VerPerfil_Usuario(Ventana_Principal_Usuario v, Usuario u) {
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
		label_FIX.setFont(new Font("Britannic Bold", Font.PLAIN, 35));
		label_FIX.setBounds(10, 11, 58, 43);
		contentPane.add(label_FIX);
		
		JLabel label_IT = new JLabel("IT!");
		label_IT.setForeground(Colores.VERDE_BRILLANTE);
		label_IT.setFont(new Font("Britannic Bold", Font.PLAIN, 35));
		label_IT.setBounds(64, 5, 49, 54);
		contentPane.add(label_IT);
		
		// TABLA
		
		String[] columnas = {"Estado", "Titulo", "Descripcion", "Fecha Creada", "Reportador", "Zona"};
		modelo = new DefaultTableModel(columnas, 0);
		
		// BOTONES
		
		JButton boton_borrarIncidencia = new JButton("Agregar Incidencia");
		boton_borrarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vPu.setVisible(true);
				dispose();

			}
		});
		boton_borrarIncidencia.setForeground(Colores.VERDE_OSCURO);
		boton_borrarIncidencia.setBackground(Colores.VERDE_BRILLANTE);
		boton_borrarIncidencia.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
		boton_borrarIncidencia.setBounds(10, 62, 143, 93);
		contentPane.add(boton_borrarIncidencia);
		
		JButton boton_borrarUsuario = new JButton("Borrar Incidencia");
		boton_borrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		boton_borrarUsuario.setForeground(Colores.VERDE_OSCURO);
		boton_borrarUsuario.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
		boton_borrarUsuario.setBackground(Colores.VERDE_BRILLANTE);
		boton_borrarUsuario.setBounds(10, 166, 143, 93);
		contentPane.add(boton_borrarUsuario);
		
		JButton boton_verPerfil = new JButton("Ver Perfil");
		boton_verPerfil.setForeground(Colores.VERDE_OSCURO);
		boton_verPerfil.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
		boton_verPerfil.setBackground(new Color(128, 255, 128));
		boton_verPerfil.setBounds(10, 270, 143, 93);
		contentPane.add(boton_verPerfil);
		
		JLabel label_Usuario = new JLabel("Usuario:");
		label_Usuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_Usuario.setBounds(195, 78, 67, 19);
		contentPane.add(label_Usuario);
		
		JLabel label_Correo = new JLabel("Correo:");
		label_Correo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_Correo.setBounds(195, 122, 67, 19);
		contentPane.add(label_Correo);
		
		JLabel label_IncidenciasCreadas = new JLabel("Incidencias Creadas:");
		label_IncidenciasCreadas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_IncidenciasCreadas.setBounds(195, 166, 143, 19);
		contentPane.add(label_IncidenciasCreadas);
		
		JLabel label_IncidenciasResueltas = new JLabel("Incidencias Resueltas:");
		label_IncidenciasResueltas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_IncidenciasResueltas.setBounds(195, 205, 143, 19);
		contentPane.add(label_IncidenciasResueltas);
		
		JLabel label_ValoracionMedia = new JLabel("Valoracion Media:");
		label_ValoracionMedia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_ValoracionMedia.setBounds(195, 252, 143, 19);
		contentPane.add(label_ValoracionMedia);
		
		mostrar_Usuario = new JTextField();
		mostrar_Usuario.setBackground(new Color(255, 255, 128));
		mostrar_Usuario.setBounds(351, 79, 119, 20);
		contentPane.add(mostrar_Usuario);
		mostrar_Usuario.setColumns(10);
		
		mostrar_Correo = new JTextField();
		mostrar_Correo.setBackground(new Color(255, 255, 128));
		mostrar_Correo.setColumns(10);
		mostrar_Correo.setBounds(351, 123, 119, 20);
		contentPane.add(mostrar_Correo);
		
		mostrar_IncidenciasCreadas = new JTextField();
		mostrar_IncidenciasCreadas.setBackground(new Color(255, 255, 128));
		mostrar_IncidenciasCreadas.setColumns(10);
		mostrar_IncidenciasCreadas.setBounds(351, 165, 119, 20);
		contentPane.add(mostrar_IncidenciasCreadas);
		
		mostrar_IncidenciasResueltas = new JTextField();
		mostrar_IncidenciasResueltas.setBackground(new Color(255, 255, 128));
		mostrar_IncidenciasResueltas.setColumns(10);
		mostrar_IncidenciasResueltas.setBounds(351, 202, 119, 20);
		contentPane.add(mostrar_IncidenciasResueltas);
		
		mostrar_ValoracionMedia = new JTextField();
		mostrar_ValoracionMedia.setBackground(new Color(255, 255, 128));
		mostrar_ValoracionMedia.setColumns(10);
		mostrar_ValoracionMedia.setBounds(351, 253, 119, 20);
		contentPane.add(mostrar_ValoracionMedia);
		
		

	}
}
