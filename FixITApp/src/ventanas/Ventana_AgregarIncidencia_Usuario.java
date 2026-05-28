package ventanas;

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
import modelo.Incidencia;
import modelo.Usuario;
import modelo.Zona;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import util.Colores;

public class Ventana_AgregarIncidencia_Usuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    protected DefaultTableModel modelo;
    private JTextField input_Titulo;
    private JTextArea input_Descripcion;
    private JList<String> lista_Zonas;
    private JList<String> lista_Categorias;
    private Usuario usuarioActual;

    private static final Color VERDE_OSCURO_UI = new Color(34, 85, 34);

    public Ventana_AgregarIncidencia_Usuario(Ventana_Principal_Usuario v, Usuario u) {
        this.usuarioActual = u;

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                v.setVisible(true);
                dispose();
            }
        });

        setTitle("FIXIT!");
        setResizable(false);
        setBounds(100, 100, 620, 700);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // LOGO
        JLabel label_FIX = new JLabel("FIX");
        label_FIX.setForeground(Colores.AMARILLO_OSCURO);
        label_FIX.setFont(new Font("Bahnschrift", Font.BOLD, 45));
        label_FIX.setBounds(10, 8, 80, 55);
        contentPane.add(label_FIX);

        JLabel label_IT = new JLabel("IT!");
        label_IT.setForeground(Colores.VERDE_BRILLANTE);
        label_IT.setFont(new Font("Bahnschrift", Font.BOLD, 45));
        label_IT.setBounds(84, 8, 70, 55);
        contentPane.add(label_IT);

        // TÍTULO VENTANA
        JLabel label_titulo_ventana = new JLabel("Nueva Incidencia");
        label_titulo_ventana.setFont(new Font("Britannic Bold", Font.PLAIN, 24));
        label_titulo_ventana.setForeground(VERDE_OSCURO_UI);
        label_titulo_ventana.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo_ventana.setBounds(0, 15, 610, 35);
        contentPane.add(label_titulo_ventana);

        // SEPARADOR
        JSeparator separador = new JSeparator();
        separador.setForeground(VERDE_OSCURO_UI);
        separador.setBackground(VERDE_OSCURO_UI);
        separador.setBounds(0, 72, 620, 3);
        contentPane.add(separador);

        // ── TÍTULO ────────────────────────────────────────────────────

        JLabel label_Titulo = new JLabel("Título");
        label_Titulo.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        label_Titulo.setForeground(VERDE_OSCURO_UI);
        label_Titulo.setBounds(30, 88, 560, 20);
        contentPane.add(label_Titulo);

        input_Titulo = new JTextField();
        input_Titulo.setBackground(Color.WHITE);
        input_Titulo.setForeground(new Color(40, 40, 40));
        input_Titulo.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
        input_Titulo.setBounds(30, 110, 555, 32);
        contentPane.add(input_Titulo);

        // ── DESCRIPCIÓN ───────────────────────────────────────────────

        JLabel label_Descripcion = new JLabel("Descripción");
        label_Descripcion.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        label_Descripcion.setForeground(VERDE_OSCURO_UI);
        label_Descripcion.setBounds(30, 158, 560, 20);
        contentPane.add(label_Descripcion);

        input_Descripcion = new JTextArea();
        input_Descripcion.setLineWrap(true);
        input_Descripcion.setWrapStyleWord(true);
        input_Descripcion.setBackground(Color.WHITE);
        input_Descripcion.setForeground(new Color(40, 40, 40));
        input_Descripcion.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
        JScrollPane scrollDescripcion = new JScrollPane(input_Descripcion);
        scrollDescripcion.setBounds(30, 180, 555, 100);
        contentPane.add(scrollDescripcion);

        // ── ZONA y CATEGORÍA (lado a lado) ────────────────────────────

        JLabel label_Zona = new JLabel("Zona");
        label_Zona.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        label_Zona.setForeground(VERDE_OSCURO_UI);
        label_Zona.setBounds(30, 298, 260, 20);
        contentPane.add(label_Zona);

        ArrayList<Zona> zonas = ZonaDAO.obtenerZonas();
        String[] opciones = new String[zonas.size()];
        for (int i = 0; i < zonas.size(); i++) opciones[i] = zonas.get(i).getNombre();

        lista_Zonas = new JList<>(opciones);
        lista_Zonas.setBackground(Color.WHITE);
        lista_Zonas.setForeground(new Color(40, 40, 40));
        lista_Zonas.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
        lista_Zonas.setSelectionBackground(new Color(80, 160, 80));
        lista_Zonas.setSelectionForeground(Color.WHITE);
        JScrollPane scrollZonas = new JScrollPane(lista_Zonas);
        scrollZonas.setBounds(30, 320, 265, 110);
        contentPane.add(scrollZonas);

        JLabel label_Categoria = new JLabel("Categoría");
        label_Categoria.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        label_Categoria.setForeground(VERDE_OSCURO_UI);
        label_Categoria.setBounds(320, 298, 260, 20);
        contentPane.add(label_Categoria);

        ArrayList<Categoria> categorias = CategoriaDAO.obtenerCategorias();
        String[] opcionesCategorias = new String[categorias.size()];
        for (int i = 0; i < categorias.size(); i++) opcionesCategorias[i] = categorias.get(i).getNombre();

        lista_Categorias = new JList<>(opcionesCategorias);
        lista_Categorias.setBackground(Color.WHITE);
        lista_Categorias.setForeground(new Color(40, 40, 40));
        lista_Categorias.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
        lista_Categorias.setSelectionBackground(new Color(80, 160, 80));
        lista_Categorias.setSelectionForeground(Color.WHITE);
        JScrollPane scrollCategorias = new JScrollPane(lista_Categorias);
        scrollCategorias.setBounds(320, 320, 265, 110);
        contentPane.add(scrollCategorias);

        // ── SEPARADOR INFERIOR ────────────────────────────────────────

        JSeparator sep2 = new JSeparator();
        sep2.setForeground(VERDE_OSCURO_UI);
        sep2.setBackground(VERDE_OSCURO_UI);
        sep2.setBounds(0, 452, 620, 2);
        contentPane.add(sep2);

        // ── BOTONES ───────────────────────────────────────────────────

        JButton boton_cancelar = new JButton("Cancelar");
        boton_cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                v.setVisible(true);
                dispose();
            }
        });
        boton_cancelar.setForeground(Colores.AMARILLO_OSCURO);
        boton_cancelar.setBackground(Colores.AMARILLO_PASTEL);
        boton_cancelar.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        boton_cancelar.setFocusPainted(false);
        boton_cancelar.setBounds(30, 470, 165, 45);
        contentPane.add(boton_cancelar);

        JButton boton_agregar = new JButton("Agregar Incidencia");
        boton_agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo      = input_Titulo.getText();
                String descripcion = input_Descripcion.getText();
                String reportador  = usuarioActual.getNombreUsuario();
                String zona        = lista_Zonas.getSelectedValue();
                String categoria   = lista_Categorias.getSelectedValue();

                if (titulo.trim().isEmpty() || descripcion.trim().isEmpty()
                        || zona == null || categoria == null) {
                    JOptionPane.showMessageDialog(contentPane,
                        "Por favor, rellena todos los campos y selecciona una zona y categoría.");
                    return;
                }

                Incidencia i = new Incidencia(0, null, titulo, descripcion, reportador, zona, null, categoria);

                if (IncidenciaDAO.agregarIncidencia(i)) {
                    JOptionPane.showMessageDialog(contentPane, "Incidencia creada correctamente.");
                    input_Titulo.setText("");
                    input_Descripcion.setText("");
                    lista_Zonas.clearSelection();
                    lista_Categorias.clearSelection();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Error - Incidencia no creada.");
                }
            }
        });
        boton_agregar.setForeground(Colores.VERDE_OSCURO);
        boton_agregar.setBackground(Colores.VERDE_BRILLANTE);
        boton_agregar.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        boton_agregar.setFocusPainted(false);
        boton_agregar.setBounds(420, 470, 165, 45);
        contentPane.add(boton_agregar);
    }
}
