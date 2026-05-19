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
        setResizable(false);
        setBounds(100, 100, 480, 559);
        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // LOGO

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

        // TITULO DE LA VENTANA
        JLabel label_titulo_ventana = new JLabel("Nueva Incidencia");
        label_titulo_ventana.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
        label_titulo_ventana.setForeground(Colores.AMARILLO_OSCURO);
        label_titulo_ventana.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo_ventana.setBounds(0, 12, 470, 30);
        contentPane.add(label_titulo_ventana);

        // LINEA SEPARADORA debajo del logo
        JSeparator separador = new JSeparator();
        separador.setForeground(Colores.AMARILLO_OSCURO);
        separador.setBounds(0, 62, 480, 2);
        contentPane.add(separador);

        // CAMPOS DEL FORMULARIO
        // reportador, estado y fecha se asignan automaticamente al enviar (no se muestran)

        JLabel label_Titulo = new JLabel("Titulo:");
        label_Titulo.setFont(new Font("Tahoma", Font.BOLD, 13));
        label_Titulo.setBounds(40, 80, 80, 25);
        contentPane.add(label_Titulo);

        input_Titulo = new JTextField();
        input_Titulo.setBounds(130, 80, 295, 25);
        input_Titulo.setColumns(10);
        contentPane.add(input_Titulo);

        JLabel label_Descripcion = new JLabel("Descripcion:");
        label_Descripcion.setFont(new Font("Tahoma", Font.BOLD, 13));
        label_Descripcion.setBounds(40, 120, 100, 25);
        contentPane.add(label_Descripcion);

        input_Descripcion = new JTextArea();
        input_Descripcion.setLineWrap(true);
        input_Descripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(input_Descripcion);
        scrollDescripcion.setBounds(130, 120, 295, 90);
        contentPane.add(scrollDescripcion);

        JLabel label_Zona = new JLabel("Zona:");
        label_Zona.setFont(new Font("Tahoma", Font.BOLD, 13));
        label_Zona.setBounds(40, 228, 80, 25);
        contentPane.add(label_Zona);

        // LISTA DE ZONAS cargada desde la BD
        ArrayList<Zona> zonas = ZonaDAO.obtenerZonas();
        String[] opciones = new String[zonas.size()];
        for (int i = 0; i < zonas.size(); i++) {
            opciones[i] = zonas.get(i).getNombre();
        }

        lista_Zonas = new JList<>(opciones);
        JScrollPane scrollZonas = new JScrollPane(lista_Zonas);
        scrollZonas.setBounds(130, 228, 150, 70);
        contentPane.add(scrollZonas);
        
        // LISTA DE CATEGORIAS cargas desde la BD
        JLabel label_Categoria = new JLabel("Categoria:");
        label_Categoria.setFont(new Font("Tahoma", Font.BOLD, 13));
        label_Categoria.setBounds(40, 323, 90, 25);
        contentPane.add(label_Categoria);

        ArrayList<Categoria> categorias = CategoriaDAO.obtenerCategorias();
        String[] opcionesCategorias = new String[categorias.size()];
        for (int i = 0; i < categorias.size(); i++) {
            opcionesCategorias[i] = categorias.get(i).getNombre();
        }

        lista_Categorias = new JList<>(opcionesCategorias);
        JScrollPane scrollCategorias = new JScrollPane(lista_Categorias);
        scrollCategorias.setBounds(130, 316, 150, 70);
        contentPane.add(scrollCategorias);

        // BOTONES

        JButton boton_cancelar = new JButton("Cancelar");
        boton_cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                v.setVisible(true);
                dispose();
            }
        });
        boton_cancelar.setForeground(Colores.AMARILLO_OSCURO);
        boton_cancelar.setBackground(Colores.AMARILLO_PASTEL);
        boton_cancelar.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
        boton_cancelar.setBounds(130, 477, 140, 35);
        contentPane.add(boton_cancelar);

        JButton boton_agregar = new JButton("Agregar Incidencia");
        boton_agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        boton_agregar.setForeground(Colores.VERDE_OSCURO);
        boton_agregar.setBackground(Colores.VERDE_BRILLANTE);
        boton_agregar.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
        boton_agregar.setBounds(285, 477, 140, 35);
        contentPane.add(boton_agregar);
    }
}