package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao.IncidenciaDAO;
import modelo.Incidencia;
import modelo.Usuario;
import util.Colores;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Muestra las incidencias cerradas del usuario con la solucion recibida.
 */
public class Ventana_MisSoluciones extends JFrame {

    private static final long serialVersionUID = 1L;
    // Panel principal, tabla, modelo de datos y lista de incidencias cargadas.
    private JPanel contentPane;
    private JTable tabla_soluciones;
    private DefaultTableModel modeloTabla;
    private ArrayList<Incidencia> listaIncidencias;

    // Recarga la tabla con las incidencias cerradas del usuario.
    private void cargarTabla(Usuario u) {
        modeloTabla.setRowCount(0);
        listaIncidencias = IncidenciaDAO.obtenerIncidenciasCerradasDeUsuario(u);
        for (Incidencia i : listaIncidencias) {
            modeloTabla.addRow(new Object[]{
                i.getId(),
                i.getTitulo(),
                i.getFechaCreacion()
            });
        }
    }

    public Ventana_MisSoluciones(Ventana_Principal_Usuario vpu, Usuario usuarioActual) {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Vuelve al menu principal al cerrar la ventana.
                dispose();
                vpu.setVisible(true);
            }
        });

        setTitle("FIXIT! — Mis Incidencias Resueltas");
        setBounds(100, 100, 780, 580);
        setResizable(false);
        setLocationRelativeTo(null);

        // PANEL PRINCIPAL
        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // LOGO
        JLabel label_FIX = new JLabel("FIX");
        label_FIX.setForeground(Colores.AMARILLO_OSCURO);
        label_FIX.setFont(new Font("Bahnschrift", Font.BOLD, 45));
        label_FIX.setBounds(290, 10, 95, 55);
        contentPane.add(label_FIX);

        JLabel label_IT = new JLabel("IT!");
        label_IT.setForeground(Colores.VERDE_BRILLANTE);
        label_IT.setFont(new Font("Bahnschrift", Font.BOLD, 45));
        label_IT.setBounds(378, 10, 80, 55);
        contentPane.add(label_IT);

        // SEPARADOR
        JSeparator separador = new JSeparator();
        separador.setForeground(new Color(34, 85, 34));
        separador.setBackground(new Color(34, 85, 34));
        separador.setBounds(0, 73, 780, 3);
        contentPane.add(separador);

        // TITULO DE LA SECCION
        JLabel lblTitulo = new JLabel("Mis Incidencias Resueltas");
        lblTitulo.setFont(new Font("Britannic Bold", Font.PLAIN, 22));
        lblTitulo.setForeground(new Color(34, 85, 34));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 85, 764, 35);
        contentPane.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Incidencias que creaste y ya han sido solucionadas por otro usuario");
        lblSubtitulo.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(80, 80, 80));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setBounds(0, 120, 764, 20);
        contentPane.add(lblSubtitulo);

        // TABLA — id oculto en col 0, titulo y fecha visibles
        String[] columnas = {"ID", "Título", "Fecha cierre"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // La tabla es solo de lectura.
                return false;
            }
        };

        tabla_soluciones = new JTable(modeloTabla);
        tabla_soluciones.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla_soluciones.setRowHeight(28);
        tabla_soluciones.getTableHeader().setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        tabla_soluciones.getTableHeader().setBackground(new Color(34, 85, 34));
        tabla_soluciones.getTableHeader().setForeground(Color.WHITE);
        tabla_soluciones.getTableHeader().setReorderingAllowed(false);
        tabla_soluciones.setSelectionBackground(Colores.VERDE_BRILLANTE);
        tabla_soluciones.setSelectionForeground(Colores.VERDE_OSCURO);
        tabla_soluciones.setGridColor(new Color(200, 200, 200));

        // Columna ID oculta — se usa internamente para recuperar la incidencia seleccionada
        tabla_soluciones.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_soluciones.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_soluciones.getColumnModel().getColumn(0).setWidth(0);
        tabla_soluciones.getColumnModel().getColumn(1).setPreferredWidth(580); // titulo
        tabla_soluciones.getColumnModel().getColumn(2).setPreferredWidth(140); // fecha cierre

        JScrollPane scrollPane = new JScrollPane(tabla_soluciones);
        scrollPane.setBounds(20, 155, 724, 290);
        contentPane.add(scrollPane);

        // BOTON VER DETALLE — abre la ventana de lectura con el detalle de la incidencia seleccionada
        JButton boton_verDetalle = new JButton("Ver detalle");
        boton_verDetalle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tabla_soluciones.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Recupera la incidencia real por su ID desde la lista cargada.
                    int idSeleccionado = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
                    Incidencia incSeleccionada = null;
                    for (Incidencia i : listaIncidencias) {
                        if (i.getId() == idSeleccionado) {
                            incSeleccionada = i;
                            break;
                        }
                    }
                    new Ventana_Leer_Incidencia_Usuario(incSeleccionada).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(contentPane,
                        "Selecciona una incidencia primero.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        boton_verDetalle.setForeground(Colores.VERDE_OSCURO);
        boton_verDetalle.setBackground(Colores.VERDE_BRILLANTE);
        boton_verDetalle.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        boton_verDetalle.setFocusPainted(false);
        boton_verDetalle.setBounds(480, 460, 160, 40);
        contentPane.add(boton_verDetalle);

        // BOTON VOLVER
        JButton boton_volver = new JButton("Volver");
        boton_volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cierra esta ventana y vuelve al menu principal.
                dispose();
                vpu.setVisible(true);
            }
        });
        boton_volver.setForeground(Colores.VERDE_OSCURO);
        boton_volver.setBackground(Colores.VERDE_BRILLANTE);
        boton_volver.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        boton_volver.setFocusPainted(false);
        boton_volver.setBounds(130, 460, 160, 40);
        contentPane.add(boton_volver);

        // Carga los datos al abrir la ventana.
        cargarTabla(usuarioActual);
    }
}