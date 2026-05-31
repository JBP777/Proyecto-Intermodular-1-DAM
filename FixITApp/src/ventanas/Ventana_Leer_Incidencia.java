package ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.IncidenciaDAO;
import dao.SolucionDAO;
import modelo.Incidencia;
import util.Colores;
import java.awt.*;
import java.awt.event.*;

/**
 * Ventana que muestra el detalle completo de una incidencia.
 * Permite cambiar su estado a Abierta o Cerrada llamando al DAO.
 * Se abre desde Ventana_Principal_Admin al seleccionar una incidencia de la tabla.
 */
public class Ventana_Leer_Incidencia extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Campos declarados como atributos para poder modificarlos
    private JTextField txtEstado;
    private JTextField txtTitulo;
    private JTextField txtReportador;
    private JTextField txtZona;
    private JTextField txtFecha;
    private JTextField txtCategorias;
    private JTextArea areaDescripcion;
    private JTextArea areaSolucion; // AÑADIDO — muestra la solucion recibida si existe

    public Ventana_Leer_Incidencia(Incidencia incidencia) {

        setTitle("FIXIT!");
        setResizable(false);
        setBounds(200, 150, 400, 610); // altura aumentada para el bloque solucion
        setLocationRelativeTo(null);
        // DISPOSE_ON_CLOSE cierra solo esta ventana, sin cerrar toda la aplicacion
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // LOGO
        JLabel label_FIX = new JLabel("FIX");
        label_FIX.setForeground(Colores.AMARILLO_OSCURO);
        label_FIX.setFont(new Font("Bahnschrift", Font.BOLD, 35));
        label_FIX.setBounds(10, 11, 58, 43);
        contentPane.add(label_FIX);

        JLabel label_IT = new JLabel("IT!");
        label_IT.setForeground(Colores.VERDE_BRILLANTE);
        label_IT.setFont(new Font("Bahnschrift", Font.BOLD, 35));
        label_IT.setBounds(64, 5, 49, 54);
        contentPane.add(label_IT);

        JLabel label_admin = new JLabel("ADMIN");
        label_admin.setForeground(new Color(128, 0, 0));
        label_admin.setFont(new Font("Britannic Bold", Font.PLAIN, 19));
        label_admin.setBounds(109, 11, 58, 54);
        contentPane.add(label_admin);

        // CAMPOS — todos setEditable(false) porque esta ventana es solo de lectura y gestion de estado,
        // no de edicion de datos

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(10, 65, 80, 20);
        contentPane.add(lblEstado);

        txtEstado = new JTextField();
        txtEstado.setEditable(false);
        txtEstado.setBounds(95, 65, 275, 22);
        contentPane.add(txtEstado);

        JLabel lblTitulo = new JLabel("Titulo:");
        lblTitulo.setBounds(10, 95, 80, 20);
        contentPane.add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setEditable(false);
        txtTitulo.setBounds(95, 95, 275, 22);
        contentPane.add(txtTitulo);

        JLabel lblReportador = new JLabel("Reportador:");
        lblReportador.setBounds(10, 125, 80, 20);
        contentPane.add(lblReportador);

        txtReportador = new JTextField();
        txtReportador.setEditable(false);
        txtReportador.setBounds(95, 125, 275, 22);
        contentPane.add(txtReportador);

        JLabel lblZona = new JLabel("Zona:");
        lblZona.setBounds(10, 155, 80, 20);
        contentPane.add(lblZona);

        txtZona = new JTextField();
        txtZona.setEditable(false);
        txtZona.setBounds(95, 155, 275, 22);
        contentPane.add(txtZona);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(10, 185, 80, 20);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setEditable(false);
        txtFecha.setBounds(95, 185, 275, 22);
        contentPane.add(txtFecha);

        // AÑADIDO — campo categorias, muestra todas separadas por coma (viene de STRING_AGG en la vista)
        JLabel lblCategorias = new JLabel("Categorias:");
        lblCategorias.setBounds(10, 215, 80, 20);
        contentPane.add(lblCategorias);

        txtCategorias = new JTextField();
        txtCategorias.setEditable(false);
        txtCategorias.setBounds(95, 215, 275, 22);
        contentPane.add(txtCategorias);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(10, 245, 80, 20);
        contentPane.add(lblDescripcion);

        // JTextArea con scroll, la descripcion puede ser larga
        areaDescripcion = new JTextArea();
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        areaDescripcion.setEditable(false);
        JScrollPane scrollDesc = new JScrollPane(areaDescripcion);
        scrollDesc.setBounds(10, 265, 360, 80);
        contentPane.add(scrollDesc);

        // SOLUCION — se consulta al DAO y se muestra si existe, si no aparece un mensaje informativo
        JLabel lblSolucion = new JLabel("Solución:");
        lblSolucion.setBounds(10, 355, 80, 20);
        contentPane.add(lblSolucion);

        areaSolucion = new JTextArea();
        areaSolucion.setLineWrap(true);
        areaSolucion.setWrapStyleWord(true);
        areaSolucion.setEditable(false);
        areaSolucion.setBackground(new Color(240, 255, 240)); // fondo verde muy suave para distinguirla
        JScrollPane scrollSol = new JScrollPane(areaSolucion);
        scrollSol.setBounds(10, 375, 360, 80);
        contentPane.add(scrollSol);

        // BOTONES DE CAMBIO DE ESTADO
        // El DAO devuelve true si el UPDATE se ha ejecutado, false si la incidencia ya tenia ese estado.
        // Segun el resultado se actualiza el campo txtEstado y se avisa al usuario con un dialogo.

        JButton btnAbrir = new JButton("Marcar como abierta");
        btnAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (IncidenciaDAO.abrirIncidencia(incidencia)) {
                    SolucionDAO.eliminarSolucionPorIncidencia(incidencia);
                    areaSolucion.setText("Esta incidencia aún no tiene solución registrada.");
                    txtEstado.setText("Abierta");
                    JOptionPane.showMessageDialog(contentPane,
                        "Incidencia abierta y solución eliminada.");
                } else {
                    JOptionPane.showMessageDialog(contentPane,
                        "La incidencia ya esta abierta.");
                }
            }
        });        btnAbrir.setBackground(Colores.VERDE_BRILLANTE);
        btnAbrir.setForeground(Colores.VERDE_OSCURO);
        btnAbrir.setBounds(10, 470, 175, 28);
        contentPane.add(btnAbrir);

        JButton btnCerrar = new JButton("Marcar como cerrada");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (IncidenciaDAO.cerrarIncidencia(incidencia)) {
                    // actualizar el campo visual para reflejar el nuevo estado sin recargar la ventana
                    txtEstado.setText("Cerrada");
                    JOptionPane.showMessageDialog(contentPane, "Incidencia Cerrada");
                } else {
                    JOptionPane.showMessageDialog(contentPane, "La incidencia ya esta cerrada");
                }
            }
        });
        btnCerrar.setBackground(Colores.AMARILLO_PASTEL);
        btnCerrar.setForeground(Colores.AMARILLO_OSCURO);
        btnCerrar.setBounds(195, 470, 175, 28);
        contentPane.add(btnCerrar);

        // Carga los datos recibidos en los campos de solo lectura.
        txtEstado.setText(incidencia.getEstado());
        txtTitulo.setText(incidencia.getTitulo());
        txtReportador.setText(incidencia.getReportador());
        txtZona.setText(incidencia.getZona());
        txtFecha.setText(incidencia.getFechaCreacion());
        // si no tiene categorias asignadas muestra "Sin categoria"
        txtCategorias.setText(incidencia.getCategorias() == null ? "Sin categoria" : incidencia.getCategorias());
        areaDescripcion.setText(incidencia.getDescripcion());

        // Carga la solucion desde la BD; si no existe muestra aviso en el campo.
        String solucion = SolucionDAO.obtenerDescripcionPorIncidencia(incidencia);
        areaSolucion.setText(solucion != null ? solucion : "Esta incidencia aún no tiene solución registrada.");
    }
}