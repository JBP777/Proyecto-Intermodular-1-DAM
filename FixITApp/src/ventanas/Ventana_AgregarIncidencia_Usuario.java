package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import dao.CategoriaDAO;
import dao.IncidenciaDAO;
import dao.ZonaDAO;
import modelo.Categoria;
import modelo.Usuario;
import modelo.Zona;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import util.Colores;

public class Ventana_AgregarIncidencia_Usuario extends JFrame {
	
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// COLORES GLOBALES
	
	protected DefaultTableModel modelo;
	private JTextField input_Titulo;
	private JTextField input_Fecha;
	private JTextArea input_Descripcion;
	private JList<String> lista_Zonas;
	private Usuario usuarioActual;
	
	public Ventana_AgregarIncidencia_Usuario(Ventana_Principal_Usuario v, Usuario u) {
		this.usuarioActual = u;
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
		
		JButton boton_verPerfil = new JButton("Ver Perfil");
		boton_verPerfil.setForeground(Colores.VERDE_OSCURO);
		boton_verPerfil.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
		boton_verPerfil.setBackground(Colores.VERDE_BRILLANTE);
		boton_verPerfil.setBounds(10, 270, 143, 93);
		contentPane.add(boton_verPerfil);
		
		JLabel label_Titulo = new JLabel("Titulo:");
		label_Titulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_Titulo.setBounds(196, 90, 65, 32);
		contentPane.add(label_Titulo);
		
		JLabel label_Descripcion = new JLabel("Descripcion:");
		label_Descripcion.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_Descripcion.setBounds(196, 133, 116, 32);
		contentPane.add(label_Descripcion);
		
		input_Descripcion = new JTextArea();
		input_Descripcion.setBounds(196, 166, 237, 54);
		contentPane.add(input_Descripcion);
		
		JLabel label_Fecha = new JLabel("Fecha de Creacion:");
		label_Fecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_Fecha.setBounds(196, 231, 152, 32);
		contentPane.add(label_Fecha);
		
		JLabel label_Zona = new JLabel("Zona:");
		label_Zona.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_Zona.setBounds(196, 284, 152, 32);
		contentPane.add(label_Zona);
		
		// TABLA
		
		String[] columnas = {"Estado", "Titulo", "Descripcion", "Fecha Creada", "Reportador", "Zona"};
		modelo = new DefaultTableModel(columnas, 0);
		
		// BOTONES
		
		JButton boton_borrarIncidencia = new JButton("Agregar Incidencia");
		boton_borrarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = input_Titulo.getText();
				String descripcion = input_Descripcion.getText();
				String fecha = input_Fecha.getText();
				int idZona = lista_Zonas.getSelectedIndex() + 1;
				
				if (titulo.isEmpty() || descripcion.isEmpty() || fecha.isEmpty() || idZona == 0) {
					JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos y seleccione una zona.");
					return;
				}
				
				boolean exito = IncidenciaDAO.agregarIncidencia(titulo, descripcion, fecha, usuarioActual.getNombreUsuario(), idZona);
				if (exito) {
					JOptionPane.showMessageDialog(null, "Incidencia agregada con éxito.");
					v.cargarIncidencias();
					v.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Error al agregar la incidencia. Revisa el formato de fecha (YYYY-MM-DD).");
				}
			}
		});
		boton_borrarIncidencia.setForeground(Colores.VERDE_OSCURO);
		boton_borrarIncidencia.setBackground(new Color(128, 255, 128));
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
				
		// INPUTS
		
		input_Titulo = new JTextField();
		input_Titulo.setBounds(295, 98, 138, 20);
		contentPane.add(input_Titulo);
		input_Titulo.setColumns(10);
		
		input_Fecha = new JTextField();
		input_Fecha.setColumns(10);
		input_Fecha.setBounds(347, 239, 86, 20);
		contentPane.add(input_Fecha);
		
		// LISTA
		
		String[] opciones = new String[ZonaDAO.obtenerZonas().size()]; // para guardar solo los nombre de las zonas
		ArrayList<Zona> zonas = ZonaDAO.obtenerZonas();

		for (int i = 0; i < zonas.size(); i++) {
		    opciones[i] = zonas.get(i).getNombre();
		}

		lista_Zonas = new JList<>(opciones);
		JScrollPane scrollPane = new JScrollPane(lista_Zonas);
		scrollPane.setBounds(260, 294, 173, 106);
		contentPane.add(scrollPane);
		
		

	}
}
