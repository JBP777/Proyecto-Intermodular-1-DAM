package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.ContactoDAO;
import dao.IncidenciaDAO;
import dao.UsuarioDAO;
import modelo.Contacto;
import modelo.Incidencia;
import modelo.Usuario;
import util.Administrator;
import util.Colores;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel principal del administrador con incidencias, usuarios y mensajes.
 */
public class Ventana_Principal_Admin extends JFrame {

    private static final long serialVersionUID = 1L;
    // Usuario conectado y panel principal.
    private JPanel contentPane;
    private Usuario usuarioActual;

    private DefaultTableModel modeloIncidencias = new DefaultTableModel(new String[]{"ID","Estado","Título"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private DefaultTableModel modeloUsuarios = new DefaultTableModel(new String[]{"Usuario","Email","Rol"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private DefaultTableModel modeloMensajes = new DefaultTableModel(new String[]{"Nombre","Asunto"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };

    private JTable tableIncidencias = new JTable(modeloIncidencias);
    private JTable tableUsuarios    = new JTable(modeloUsuarios);
    private JTable tableMensajes    = new JTable(modeloMensajes);

    // Aplica el mismo estilo visual a todas las tablas del panel.
    private void estilizarTabla(JTable tabla) {
        tabla.getTableHeader().setBackground(Colores.CABECERA_TABLA);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowHeight(24);
        tabla.setBackground(Colores.FILA_TABLA);
        tabla.setForeground(Colores.AMARILLO_OSCURO);
        tabla.setGridColor(Colores.BORDE_TABLA);
        tabla.setSelectionBackground(Colores.SELECCION_TABLA);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    protected void cargarTablas() {
        // Limpia y recarga datos desde los DAO.
        modeloMensajes.setRowCount(0);
        modeloUsuarios.setRowCount(0);
        modeloIncidencias.setRowCount(0);

        for (Contacto c : ContactoDAO.obtenerMensajesContacto())
            modeloMensajes.addRow(new Object[]{c.getNombre(), c.getAsunto()});

        for (Incidencia i : IncidenciaDAO.obtenerIncidencias())
            modeloIncidencias.addRow(new Object[]{i.getId(), i.getEstado(), i.getTitulo()});

        for (Usuario usr : UsuarioDAO.obtenerUsuarios())
            modeloUsuarios.addRow(new Object[]{
                usr.getNombreUsuario(),
                usr.getEmail(),
                Administrator.esAdmin(usr.getNombreUsuario()) ? "Admin" : "Usuario"
            });
    }

    public Ventana_Principal_Admin(Ventana_Inicio v, Usuario u) {
        usuarioActual = u;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("FIXIT! — Admin");
        setResizable(false);
        setBounds(100, 100, 1000, 660);
        setLocationRelativeTo(null);

        // MENÚ
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Colores.AMARILLO_FONDO);
        setJMenuBar(menuBar);

        JMenu mnOpciones = new JMenu("Opciones");
        mnOpciones.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
        mntmCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cierra la sesion actual y vuelve al login.
                dispose();
                new Ventana_Inicio().setVisible(true);
            }
        });
        mnOpciones.add(mntmCerrarSesion);
        menuBar.add(mnOpciones);

        // PANEL
        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // LOGO
        JLabel label_FIX = new JLabel("FIX");
        label_FIX.setForeground(Colores.AMARILLO_OSCURO);
        label_FIX.setFont(new Font("Britannic Bold", Font.PLAIN, 42));
        label_FIX.setBounds(10, 8, 75, 52);
        contentPane.add(label_FIX);

        JLabel label_IT = new JLabel("IT!");
        label_IT.setForeground(Colores.VERDE_BRILLANTE);
        label_IT.setFont(new Font("Britannic Bold", Font.PLAIN, 42));
        label_IT.setBounds(78, 8, 65, 52);
        contentPane.add(label_IT);

        JLabel label_admin = new JLabel("ADMIN");
        label_admin.setForeground(Colores.ROJO_ADMIN);
        label_admin.setFont(new Font("Britannic Bold", Font.PLAIN, 22));
        label_admin.setBounds(140, 18, 80, 36);
        contentPane.add(label_admin);

        JSeparator sep = new JSeparator();
        sep.setForeground(Colores.VERDE_OSCURO);
        sep.setBackground(Colores.VERDE_OSCURO);
        sep.setBounds(0, 70, 1000, 3);
        contentPane.add(sep);

        JButton btnRecargar = new JButton("🔄️");
        btnRecargar.setBackground(Colores.AMARILLO_PASTEL);
        btnRecargar.setFocusPainted(false);
        btnRecargar.setBounds(910, 18, 64, 30);
        btnRecargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarTablas();
            }
        });
        contentPane.add(btnRecargar);

        // ── COLUMNA 1: INCIDENCIAS ────────────────────────────────────

        JLabel lblIncidencias = new JLabel("📋 INCIDENCIAS");
        lblIncidencias.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
        lblIncidencias.setForeground(Colores.VERDE_OSCURO);
        lblIncidencias.setBounds(10, 82, 300, 24);
        contentPane.add(lblIncidencias);

        estilizarTabla(tableIncidencias);
        JScrollPane scrollIncidencias = new JScrollPane(tableIncidencias);
        scrollIncidencias.setBounds(10, 110, 300, 390);
        contentPane.add(scrollIncidencias);

        JButton btnVerInc = new JButton("Ver");
        btnVerInc.setBackground(Colores.VERDE_BRILLANTE);
        btnVerInc.setForeground(Colores.VERDE_OSCURO);
        btnVerInc.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        btnVerInc.setFocusPainted(false);
        btnVerInc.setBounds(10, 510, 145, 34);
        btnVerInc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre el detalle de la incidencia seleccionada.
                int sel = tableIncidencias.getSelectedRow();
                if (sel != -1) {
                    new Ventana_Leer_Incidencia(IncidenciaDAO.obtenerIncidencias().get(sel)).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Selecciona una incidencia.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        contentPane.add(btnVerInc);

        JButton btnElimInc = new JButton("Eliminar");
        btnElimInc.setBackground(Colores.ROJO_ELIMINAR);
        btnElimInc.setForeground(Colores.ROJO_ELIMINAR_TXT);
        btnElimInc.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        btnElimInc.setFocusPainted(false);
        btnElimInc.setBounds(165, 510, 145, 34);
        btnElimInc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Elimina la incidencia seleccionada y refresca la tabla.
                int sel = tableIncidencias.getSelectedRow();
                if (sel != -1) {
                    IncidenciaDAO.eliminarIncidencia(IncidenciaDAO.obtenerIncidencias().get(sel));
                    cargarTablas();
                    JOptionPane.showMessageDialog(contentPane, "Incidencia eliminada.");
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Selecciona una incidencia.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnElimInc);

        // ── COLUMNA 2: USUARIOS ───────────────────────────────────────

        JLabel lblUsuarios = new JLabel("👤 USUARIOS");
        lblUsuarios.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
        lblUsuarios.setForeground(Colores.VERDE_OSCURO);
        lblUsuarios.setBounds(330, 82, 310, 24);
        contentPane.add(lblUsuarios);

        estilizarTabla(tableUsuarios);
        JScrollPane scrollUsuarios = new JScrollPane(tableUsuarios);
        scrollUsuarios.setBounds(330, 110, 310, 390);
        contentPane.add(scrollUsuarios);

        JButton btnVerUsr = new JButton("Ver");
        btnVerUsr.setBackground(Colores.VERDE_BRILLANTE);
        btnVerUsr.setForeground(Colores.VERDE_OSCURO);
        btnVerUsr.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        btnVerUsr.setFocusPainted(false);
        btnVerUsr.setBounds(330, 510, 150, 34);
        btnVerUsr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre el perfil del usuario seleccionado.
                int sel = tableUsuarios.getSelectedRow();
                if (sel != -1) {
                    new Ventana_Ver_Usuario_Admin(Ventana_Principal_Admin.this, UsuarioDAO.obtenerUsuarios().get(sel)).setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Selecciona un usuario.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        contentPane.add(btnVerUsr);

        JButton btnEliminarUsr = new JButton("Eliminar");
        btnEliminarUsr.setBackground(Colores.ROJO_ELIMINAR);
        btnEliminarUsr.setForeground(Colores.ROJO_ELIMINAR_TXT);
        btnEliminarUsr.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        btnEliminarUsr.setFocusPainted(false);
        btnEliminarUsr.setBounds(490, 510, 150, 34);
        btnEliminarUsr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Pide confirmacion antes de borrar un usuario.
                int sel = tableUsuarios.getSelectedRow();
                if (sel != -1) {
                    Usuario uSel = UsuarioDAO.obtenerUsuarios().get(sel);
                    int confirmacion = JOptionPane.showConfirmDialog(contentPane,
                        "¿Seguro que quieres eliminar a \"" + uSel.getNombreUsuario() + "\"?\nSe borrarán todos sus datos.",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        UsuarioDAO.eliminarUsuario(uSel);
                        cargarTablas();
                        JOptionPane.showMessageDialog(contentPane, "Usuario eliminado correctamente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Selecciona un usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnEliminarUsr);

        // ── COLUMNA 3: MENSAJES ───────────────────────────────────────

        JLabel lblMensajes = new JLabel("✉ MENSAJES RECIBIDOS");
        lblMensajes.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
        lblMensajes.setForeground(Colores.VERDE_OSCURO);
        lblMensajes.setBounds(660, 82, 310, 24);
        contentPane.add(lblMensajes);

        estilizarTabla(tableMensajes);
        JScrollPane scrollMensajes = new JScrollPane(tableMensajes);
        scrollMensajes.setBounds(660, 110, 310, 390);
        contentPane.add(scrollMensajes);

        JButton btnLeerMensaje = new JButton("Leer Mensaje");
        btnLeerMensaje.setBackground(Colores.VERDE_BRILLANTE);
        btnLeerMensaje.setForeground(Colores.VERDE_OSCURO);
        btnLeerMensaje.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        btnLeerMensaje.setFocusPainted(false);
        btnLeerMensaje.setBounds(660, 510, 310, 34);
        btnLeerMensaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestra el contenido completo del mensaje seleccionado.
                int sel = tableMensajes.getSelectedRow();
                if (sel != -1) {
                    new Ventana_Leer_Mensajes(ContactoDAO.obtenerMensajesContacto().get(sel)).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Selecciona un mensaje.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnLeerMensaje);

        cargarTablas();
    }
}
