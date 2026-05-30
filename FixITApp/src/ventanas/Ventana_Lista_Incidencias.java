package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.IncidenciaDAO;
import modelo.Incidencia;
import modelo.Usuario;
import util.Colores;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Lista las incidencias abiertas que el usuario puede resolver.
 */
public class Ventana_Lista_Incidencias extends JFrame {

    private static final long serialVersionUID = 1L;
    // Usuario conectado y tabla de incidencias disponibles.
    private JPanel contentPane;
    private Usuario usuarioActual;

    private String[] columnas = {"ID", "Título", "Estado", "Zona", "Categoría", "Reportador"};
    private DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
        public boolean isCellEditable(int row, int column) { return false; }
    };
    private JTable tableIncidencias = new JTable(modeloTabla);

    // Recarga solo incidencias abiertas y que no pertenecen al usuario actual.
    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        for (Incidencia i : IncidenciaDAO.obtenerIncidencias()) {
            if (i.getEstado().equals("Abierta") && !i.getReportador().equals(usuarioActual.getNombreUsuario())) {
                modeloTabla.addRow(new Object[]{
                    i.getId(), i.getTitulo(), i.getEstado(),
                    i.getZona(), i.getCategorias(), i.getReportador()
                });
            }
        }
    }

    public Ventana_Lista_Incidencias(Ventana_Principal_Usuario v, Usuario u) {
        usuarioActual = u;

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Al cerrar se vuelve al menu del usuario.
                v.setVisible(true);
                dispose();
            }
        });

        setTitle("FIXIT!");
        setResizable(false);
        setBounds(100, 100, 820, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
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

        // TÍTULO
        JLabel label_titulo = new JLabel("Incidencias disponibles");
        label_titulo.setFont(new Font("Britannic Bold", Font.PLAIN, 24));
        label_titulo.setForeground(Colores.VERDE_OSCURO);
        label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo.setBounds(0, 15, 804, 35);
        contentPane.add(label_titulo);

        // BOTÓN RECARGAR
        JButton btnRecargar = new JButton("🔄");
        btnRecargar.setBackground(Colores.AMARILLO_PASTEL);
        btnRecargar.setForeground(Colores.AMARILLO_OSCURO);
        btnRecargar.setFocusPainted(false);
        btnRecargar.setBounds(736, 20, 60, 30);
        btnRecargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarTabla();
            }
        });
        contentPane.add(btnRecargar);

        // SEPARADOR
        JSeparator separador = new JSeparator();
        separador.setForeground(Colores.VERDE_OSCURO);
        separador.setBackground(Colores.VERDE_OSCURO);
        separador.setBounds(0, 72, 820, 3);
        contentPane.add(separador);

        // TABLA
        tableIncidencias.getTableHeader().setBackground(Colores.CABECERA_TABLA);
        tableIncidencias.getTableHeader().setForeground(Color.WHITE);
        tableIncidencias.getTableHeader().setReorderingAllowed(false);
        tableIncidencias.setRowHeight(28);
        tableIncidencias.setBackground(Colores.FILA_TABLA);
        tableIncidencias.setForeground(Colores.AMARILLO_OSCURO);
        tableIncidencias.setGridColor(Colores.BORDE_TABLA);
        tableIncidencias.setSelectionBackground(Colores.SELECCION_TABLA);
        tableIncidencias.setSelectionForeground(Color.WHITE);
        tableIncidencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollTabla = new JScrollPane(tableIncidencias);
        scrollTabla.setBounds(20, 88, 774, 400);
        contentPane.add(scrollTabla);

        // BOTÓN OFRECERSE A RESOLVER
        JButton btnResolver = new JButton("Ofrecerse a resolver");
        btnResolver.setBackground(Colores.VERDE_BRILLANTE);
        btnResolver.setForeground(Colores.VERDE_OSCURO);
        btnResolver.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        btnResolver.setFocusPainted(false);
        btnResolver.setBounds(280, 508, 260, 50);
        btnResolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Busca la incidencia real por ID antes de abrir la solucion.
                int sel = tableIncidencias.getSelectedRow();
                if (sel != -1) {
                    int idSeleccionado = (int) modeloTabla.getValueAt(sel, 0);
                    Incidencia inc = null;
                    for (Incidencia i : IncidenciaDAO.obtenerIncidencias()) {
                        if (i.getId() == idSeleccionado) {
                            inc = i;
                            break;
                        }
                    }
                    new Ventana_Resolver_Incidencia(Ventana_Lista_Incidencias.this, inc, usuarioActual).setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Selecciona una incidencia primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        contentPane.add(btnResolver);

        // BOTÓN VOLVER
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(Colores.AMARILLO_PASTEL);
        btnVolver.setForeground(Colores.AMARILLO_OSCURO);
        btnVolver.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
        btnVolver.setFocusPainted(false);
        btnVolver.setBounds(20, 508, 120, 50);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Vuelve al menu principal del usuario.
                v.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnVolver);

        cargarTabla();
    }
}
