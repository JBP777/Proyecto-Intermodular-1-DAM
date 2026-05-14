package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import util.Colores;
import java.awt.*;
import java.awt.event.*;

public class Ventana_Principal_Admin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Usuario usuarioActual;

    // TABLA INCIDENCIAS
    private String[] columnasIncidencias = {"ID","Estado","Descripcion"};
    private DefaultTableModel modeloIncidencias = new DefaultTableModel(columnasIncidencias, 0);
    private JTable tableIncidencias;

    // TABLA USUARIOS
    private String[] columnasUsuarios = {"Usuario", "Email","Rol"};
    private DefaultTableModel modeloUsuarios = new DefaultTableModel(columnasUsuarios, 0);
    private JTable tableUsuarios;

    // TABLA MENSAJES (sin cambios)
    private String[] columnasTablaMensajes = {"Usuario", "Asunto"};
    private DefaultTableModel modeloTablaMensajes = new DefaultTableModel(columnasTablaMensajes, 0);
    private JTable tableMensajes;

    public Ventana_Principal_Admin(Ventana_Inicio v, Usuario u) {
        usuarioActual = u;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                v.setVisible(true);
                dispose();
            }
        });

        setTitle("FIXIT!");
        setBounds(100, 100, 810, 530);

        // MENU
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnOpciones = new JMenu("Opciones");
        menuBar.add(mnOpciones);

        JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
        mntmCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Ventana_Inicio vi = new Ventana_Inicio();
                vi.setVisible(true);
            }
        });
        mnOpciones.add(mntmCerrarSesion);

        // MENU — opciones de eliminar
        JMenu mnGestion = new JMenu("Gestion");
        menuBar.add(mnGestion);

        JMenuItem mntmEliminarInc = new JMenuItem("Eliminar incidencia");
        mnGestion.add(mntmEliminarInc); // sin logica por ahora

        JMenuItem mntmEliminarUsr = new JMenuItem("Eliminar usuario");
        mnGestion.add(mntmEliminarUsr); // sin logica por ahora

        // PANEL
        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
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

        JLabel label_admin = new JLabel("ADMIN");
        label_admin.setForeground(new Color(128, 0, 0));
        label_admin.setFont(new Font("Britannic Bold", Font.PLAIN, 19));
        label_admin.setBounds(109, 11, 58, 54);
        contentPane.add(label_admin);

        // LABEL + TABLA INCIDENCIAS
        JLabel lblIncidencias = new JLabel("INCIDENCIAS");
        lblIncidencias.setBounds(20, 62, 200, 25);
        contentPane.add(lblIncidencias);

        tableIncidencias = new JTable(modeloIncidencias);
        JScrollPane scrollIncidencias = new JScrollPane(tableIncidencias);
        scrollIncidencias.setBounds(10, 90, 240, 310);
        contentPane.add(scrollIncidencias);

        // LABEL + TABLA USUARIOS
        JLabel lblUsuarios = new JLabel("USUARIOS");
        lblUsuarios.setBounds(280, 62, 200, 25);
        contentPane.add(lblUsuarios);

        tableUsuarios = new JTable(modeloUsuarios);
        JScrollPane scrollUsuarios = new JScrollPane(tableUsuarios);
        scrollUsuarios.setBounds(270, 90, 240, 310);
        contentPane.add(scrollUsuarios);

        // LABEL + TABLA MENSAJES 
        JLabel lblMensajes = new JLabel("MENSAJES RECIBIDOS");
        lblMensajes.setBounds(540, 62, 200, 25);
        contentPane.add(lblMensajes);

        tableMensajes = new JTable(modeloTablaMensajes);
        JScrollPane scrollTablaMensajes = new JScrollPane(tableMensajes);
        scrollTablaMensajes.setBounds(530, 90, 250, 310);
        contentPane.add(scrollTablaMensajes);

        // BOTONES ELIMINAR
        JButton btnEliminarInc = new JButton("Eliminar incidencia");
        btnEliminarInc.setBounds(10, 410, 240, 28);
        contentPane.add(btnEliminarInc); // sin logica por ahora

        JButton btnEliminarUsr = new JButton("Eliminar usuario");
        btnEliminarUsr.setBounds(270, 410, 240, 28);
        contentPane.add(btnEliminarUsr); // sin logica por ahora
    }
}