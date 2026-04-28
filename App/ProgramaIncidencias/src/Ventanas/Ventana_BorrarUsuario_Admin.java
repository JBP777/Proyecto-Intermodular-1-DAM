package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Ventana_BorrarUsuario_Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// COLORES GLOBALES
	
	Color amarilloFondo = new Color(244, 171, 0);
	Color amarilloOscuro = new Color(64, 45, 0);
	Color verdeBrillante = new Color(0, 204, 102);
	Color amarilloPastel = new Color(255, 255, 128);
	Color verdeOscuro = new Color(0, 121, 61);
	
	private JTable table;
	protected DefaultTableModel modelo;
	
	public Ventana_BorrarUsuario_Admin(Ventana_Principal_Admin v) {
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
		contentPane.setBackground(amarilloFondo);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// LABELS
		
		JLabel label_FIX = new JLabel("FIX");
		label_FIX.setForeground(amarilloOscuro);
		label_FIX.setFont(new Font("Britannic Bold", Font.PLAIN, 35));
		label_FIX.setBounds(10, 11, 58, 43);
		contentPane.add(label_FIX);
		
		JLabel label_IT = new JLabel("IT!");
		label_IT.setForeground(verdeBrillante);
		label_IT.setFont(new Font("Britannic Bold", Font.PLAIN, 35));
		label_IT.setBounds(64, 5, 49, 54);
		contentPane.add(label_IT);
		
		JLabel label_admin = new JLabel("ADMIN");
		label_admin.setForeground(new Color(128, 0, 0));
		label_admin.setFont(new Font("Britannic Bold", Font.PLAIN, 19));
		label_admin.setBounds(109, 11, 58, 54);
		contentPane.add(label_admin);
		
		// TABLA
		
		String[] columnas = {"Usuario","Email", "Fecha de Registro", "Contraseña"};
		modelo = new DefaultTableModel(columnas, 0);
		
		table = new JTable(modelo);
		table.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		table.setBackground(amarilloPastel);
		table.setOpaque(true);
		
		// Thiago Sesseler: Hace falta verificar como hacer lo del color sin tocar mucha cosa rara.
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(163, 59, 611, 419);
		scrollPane.getViewport().setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		scrollPane.getViewport().setBackground(amarilloPastel); 
		scrollPane.setBackground(amarilloFondo);
		scrollPane.getViewport().setForeground(amarilloOscuro);
		contentPane.add(scrollPane);   
		
		// BOTONES
		
		JButton boton_borrarIncidencia = new JButton("Borrar Incidencia");
		boton_borrarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.setVisible(true);
				dispose();
			}
		});
		boton_borrarIncidencia.setForeground(verdeOscuro);
		boton_borrarIncidencia.setBackground(verdeBrillante);
		boton_borrarIncidencia.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
		boton_borrarIncidencia.setBounds(10, 62, 143, 93);
		contentPane.add(boton_borrarIncidencia);
		
		JButton boton_borrarUsuario = new JButton("Borrar Usuario:");
		boton_borrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		boton_borrarUsuario.setForeground(new Color(0, 121, 61));
		boton_borrarUsuario.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
		boton_borrarUsuario.setBackground(new Color(128, 255, 128));
		boton_borrarUsuario.setBounds(10, 166, 143, 93);
		contentPane.add(boton_borrarUsuario);
		
		

	}
}
