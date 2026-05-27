package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.IncidenciaDAO;
import modelo.Incidencia;
import modelo.Usuario;
import util.Colores;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Ventana_Lista_Incidencias extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableIncidencias;
    private String[] columnas = {"ID", "Título", "Estado", "Zona", "Categoría", "Reportador"};
    private DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private Usuario usuarioActual;

    private static final Color VERDE_OSCURO_UI  = new Color(34, 85, 34);
    private static final Color COLOR_FILA_PAR   = Color.WHITE;
    private static final Color COLOR_FILA_IMPAR = new Color(235, 245, 235);
    private static final Color COLOR_SELECCION  = new Color(80, 160, 80);
    private static final Color COLOR_GRID       = new Color(180, 210, 180);

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        for (Incidencia i : IncidenciaDAO.obtenerIncidencias()) {
            if (i.getEstado().equals("Abierta")) {
                modeloTabla.addRow(new Object[]{
                    i.getId(),
                    i.getTitulo(),
                    i.getEstado(),
                    i.getZona(),
                    i.getCategorias(),
                    i.getReportador()
                });
            }
        }
    }

    public Ventana_Lista_Incidencias(Ventana_Principal_Usuario v, Usuario u) {
        usuarioActual = u;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                v.setVisible(true);
                dispose();
            }
        });

        setTitle("FIXIT!");
        setResizable(false);
        setBounds(100, 100, 820, 600);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // LOGO
        JLabel label_FIX = new JLabel("FIX");
        label_FIX.setForeground(Colores.AMARILLO_OSCURO);
        label_FIX.setFont(new Font("Britannic Bold", Font.PLAIN, 45));
        label_FIX.setBounds(10, 8, 80, 55);
        contentPane.add(label_FIX);

        JLabel label_IT = new JLabel("IT!");
        label_IT.setForeground(Colores.VERDE_BRILLANTE);
        label_IT.setFont(new Font("Britannic Bold", Font.PLAIN, 45));
        label_IT.setBounds(84, 8, 70, 55);
        contentPane.add(label_IT);

        // TÍTULO
        JLabel label_titulo = new JLabel("Incidencias disponibles");
        label_titulo.setFont(new Font("Britannic Bold", Font.PLAIN, 24));
        label_titulo.setForeground(VERDE_OSCURO_UI);
        label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo.setBounds(0, 15, 804, 35);
        contentPane.add(label_titulo);

        // BOTÓN RECARGAR — llaves correctas
        JButton btnRecargar = new JButton("🔄️");
        btnRecargar.setBackground(Colores.AMARILLO_PASTEL);
        btnRecargar.setForeground(Colores.AMARILLO_OSCURO);
        btnRecargar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnRecargar.setFocusPainted(false);
        btnRecargar.setBounds(736, 20, 60, 30);
        btnRecargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarTabla(); // solo esto dentro
            }
        });
        contentPane.add(btnRecargar);

        // SEPARADOR
        JSeparator separador = new JSeparator();
        separador.setForeground(VERDE_OSCURO_UI);
        separador.setBackground(VERDE_OSCURO_UI);
        separador.setBounds(0, 72, 820, 3);
        contentPane.add(separador);

        // TABLA
        tableIncidencias = new JTable(modeloTabla);

        // CABECERA
        JTableHeader header = tableIncidencias.getTableHeader();
        header.setBackground(VERDE_OSCURO_UI);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setReorderingAllowed(false);

        // ESTILO FILAS
        tableIncidencias.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tableIncidencias.setRowHeight(28);
        tableIncidencias.setGridColor(COLOR_GRID);
        tableIncidencias.setSelectionBackground(COLOR_SELECCION);
        tableIncidencias.setSelectionForeground(Color.WHITE);
        tableIncidencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableIncidencias.setShowVerticalLines(true);
        tableIncidencias.setShowHorizontalLines(true);

        // FILAS ALTERNAS
        tableIncidencias.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                if (isSelected) {
                    setBackground(COLOR_SELECCION);
                    setForeground(Color.WHITE);
                } else {
                    setBackground(row % 2 == 0 ? COLOR_FILA_PAR : COLOR_FILA_IMPAR);
                    setForeground(new Color(40, 40, 40));
                }
                return this;
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tableIncidencias);
        scrollTabla.setBounds(20, 88, 774, 400);
        scrollTabla.getViewport().setBackground(Color.WHITE);
        contentPane.add(scrollTabla);

        // BOTÓN OFRECERSE A RESOLVER
        JButton btnResolver = new JButton("Ofrecerse a resolver");
        btnResolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sel = tableIncidencias.getSelectedRow();
                if (sel != -1) {
                    Incidencia inc = IncidenciaDAO.obtenerIncidencias().get(sel);
                    new Ventana_Resolver_Incidencia(Ventana_Lista_Incidencias.this, inc, usuarioActual).setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(contentPane,
                        "Selecciona una incidencia primero.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnResolver.setBackground(Colores.VERDE_BRILLANTE);
        btnResolver.setForeground(Colores.VERDE_OSCURO);
        btnResolver.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        btnResolver.setFocusPainted(false);
        btnResolver.setBounds(280, 508, 260, 50);
        contentPane.add(btnResolver);

        // BOTÓN VOLVER
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                v.setVisible(true);
                dispose();
            }
        });
        btnVolver.setBackground(Colores.AMARILLO_PASTEL);
        btnVolver.setForeground(Colores.AMARILLO_OSCURO);
        btnVolver.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
        btnVolver.setFocusPainted(false);
        btnVolver.setBounds(20, 508, 120, 50);
        contentPane.add(btnVolver);

        cargarTabla();
    }
}