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

public class Ventana_Principal_Usuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	private JTable table;
	protected DefaultTableModel modelo;
	
	private Usuario usuarioActual;
	
	public Ventana_Principal_Usuario(Ventana_Inicio v, Usuario u) {
		usuarioActual = u;
		
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
		
		String[] columnas = {"ID", "Estado", "Titulo", "Descripcion", "Fecha Creada", "Reportador", "Zona"};
		modelo = new DefaultTableModel(columnas, 0);
		
		table = new JTable(modelo);
		table.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		table.setBackground(Colores.AMARILLO_PASTEL);
		table.setOpaque(true);
		
		// Ocultar columna ID
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		
		cargarIncidencias();
		
		// Thiago Sesseler: Hace falta verificar como hacer lo del color sin tocar mucha cosa rara.
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(163, 59, 611, 419);
		scrollPane.getViewport().setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		scrollPane.getViewport().setBackground(Colores.AMARILLO_PASTEL); 
		scrollPane.setBackground(Colores.AMARILLO_FONDO);
		scrollPane.getViewport().setForeground(Colores.AMARILLO_OSCURO);
		contentPane.add(scrollPane);   
		
		// BOTONES
		
		JButton boton_borrarIncidencia = new JButton("Agregar Incidencia");
		boton_borrarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_AgregarIncidencia_Usuario v = new Ventana_AgregarIncidencia_Usuario(Ventana_Principal_Usuario.this, usuarioActual);
				v.setVisible(true);
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
				int fila = table.getSelectedRow();
				if (fila != -1) {
					int id = (int) modelo.getValueAt(fila, 0);
					Incidencia i = new Incidencia(id, "", "", "", "", "", "");
					IncidenciaDAO.eliminarIncidencia(i);
					JOptionPane.showMessageDialog(null, "Incidencia borrada con éxito.");
					cargarIncidencias();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona una incidencia para borrar.");
				}
			}
		});
		boton_borrarUsuario.setForeground(Colores.VERDE_OSCURO);
		boton_borrarUsuario.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
		boton_borrarUsuario.setBackground(Colores.VERDE_BRILLANTE);
		boton_borrarUsuario.setBounds(10, 166, 143, 93);
		contentPane.add(boton_borrarUsuario);
		
		JButton boton_verPerfil = new JButton("Ver Perfil");
		boton_verPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_VerPerfil_Usuario v = new Ventana_VerPerfil_Usuario(Ventana_Principal_Usuario.this, usuarioActual);
				v.setVisible(true);
				dispose();
			}
		});
		boton_verPerfil.setForeground(Colores.VERDE_OSCURO);
		boton_verPerfil.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
		boton_verPerfil.setBackground(Colores.VERDE_BRILLANTE);
		boton_verPerfil.setBounds(10, 270, 143, 93);
		contentPane.add(boton_verPerfil);
		
		JButton boton_enviarMensaje = new JButton("Enviar Mensaje");
		boton_enviarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Enviar_Mensaje v = new Ventana_Enviar_Mensaje(usuarioActual);
				v.setVisible(true);
			}
		});
		boton_enviarMensaje.setForeground(Colores.VERDE_OSCURO);
		boton_enviarMensaje.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
		boton_enviarMensaje.setBackground(Colores.VERDE_BRILLANTE);
		boton_enviarMensaje.setBounds(10, 374, 143, 93);
		contentPane.add(boton_enviarMensaje);
	}
	
	public void cargarIncidencias() {
		modelo.setRowCount(0); // Limpiar tabla
		ArrayList<Incidencia> incidencias = IncidenciaDAO.obtenerIncidencias();
		for (Incidencia i : incidencias) {
			Object[] fila = {
				i.getId(),
				i.getEstado(),
				i.getTitulo(),
				i.getDescripcion(),
				i.getFechaCreacion(),
				i.getReportador(),
				i.getZona()
			};
			modelo.addRow(fila);
		}
	}
}
