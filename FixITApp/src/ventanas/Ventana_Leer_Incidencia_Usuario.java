package ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.SolucionDAO;
import modelo.Incidencia;
import util.Colores;
import java.awt.*;

/**
 * Ventana que muestra el detalle completo de una incidencia resuelta.
 * Solo lectura — el usuario no puede modificar el estado.
 * Se abre desde Ventana_MisSoluciones al pulsar "Ver detalle".
 */
public class Ventana_Leer_Incidencia_Usuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Campos declarados como atributos para mantener consistencia con la ventana admin.
    private JTextField txtEstado;
    private JTextField txtTitulo;
    private JTextField txtZona;
    private JTextField txtFecha;
    private JTextField txtResueltaPor;
    private JTextField txtCategorias;
    private JTextArea areaDescripcion;
    private JTextArea areaSolucion;

    public Ventana_Leer_Incidencia_Usuario(Incidencia incidencia) {

        setTitle("FIXIT!");
        setResizable(false);
        setBounds(200, 150, 400, 620);
        setLocationRelativeTo(null);
        // DISPOSE_ON_CLOSE cierra solo esta ventana sin cerrar toda la aplicacion.
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

        // CAMPOS — todos setEditable(false), ventana de solo lectura.

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(10, 65, 85, 20);
        contentPane.add(lblEstado);

        txtEstado = new JTextField();
        txtEstado.setEditable(false);
        txtEstado.setBounds(100, 65, 270, 22);
        contentPane.add(txtEstado);

        JLabel lblTitulo = new JLabel("Titulo:");
        lblTitulo.setBounds(10, 95, 85, 20);
        contentPane.add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setEditable(false);
        txtTitulo.setBounds(100, 95, 270, 22);
        contentPane.add(txtTitulo);

        JLabel lblZona = new JLabel("Zona:");
        lblZona.setBounds(10, 125, 85, 20);
        contentPane.add(lblZona);

        txtZona = new JTextField();
        txtZona.setEditable(false);
        txtZona.setBounds(100, 125, 270, 22);
        contentPane.add(txtZona);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(10, 155, 85, 20);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setEditable(false);
        txtFecha.setBounds(100, 155, 270, 22);
        contentPane.add(txtFecha);

        JLabel lblResueltaPor = new JLabel("Resuelta por:");
        lblResueltaPor.setBounds(10, 185, 85, 20);
        contentPane.add(lblResueltaPor);

        txtResueltaPor = new JTextField();
        txtResueltaPor.setEditable(false);
        txtResueltaPor.setBounds(100, 185, 270, 22);
        contentPane.add(txtResueltaPor);

        // Campo categorias — muestra todas separadas por coma (viene de STRING_AGG en la vista).
        JLabel lblCategorias = new JLabel("Categorias:");
        lblCategorias.setBounds(10, 215, 85, 20);
        contentPane.add(lblCategorias);

        txtCategorias = new JTextField();
        txtCategorias.setEditable(false);
        txtCategorias.setBounds(100, 215, 270, 22);
        contentPane.add(txtCategorias);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(10, 245, 85, 20);
        contentPane.add(lblDescripcion);

        // JTextArea con scroll, la descripcion puede ser larga.
        areaDescripcion = new JTextArea();
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setWrapStyleWord(true);
        areaDescripcion.setEditable(false);
        JScrollPane scrollDesc = new JScrollPane(areaDescripcion);
        scrollDesc.setBounds(10, 265, 360, 80);
        contentPane.add(scrollDesc);

        // SOLUCION — fondo verde suave para distinguirla visualmente del resto de campos.
        JLabel lblSolucion = new JLabel("Solución:");
        lblSolucion.setBounds(10, 355, 85, 20);
        contentPane.add(lblSolucion);

        areaSolucion = new JTextArea();
        areaSolucion.setLineWrap(true);
        areaSolucion.setWrapStyleWord(true);
        areaSolucion.setEditable(false);
        areaSolucion.setBackground(new Color(240, 255, 240));
        JScrollPane scrollSol = new JScrollPane(areaSolucion);
        scrollSol.setBounds(10, 375, 360, 80);
        contentPane.add(scrollSol);

        // Carga los datos recibidos en los campos de solo lectura.
        txtEstado.setText(incidencia.getEstado());
        txtTitulo.setText(incidencia.getTitulo());
        txtZona.setText(incidencia.getZona());
        txtFecha.setText(incidencia.getFechaCreacion());
        // Si no tiene categorias asignadas muestra "Sin categoria".
        txtCategorias.setText(incidencia.getCategorias() == null ? "Sin categoria" : incidencia.getCategorias());
        areaDescripcion.setText(incidencia.getDescripcion());

        // Carga la solucion desde la BD; si no existe muestra aviso en el campo.
        String solucion = SolucionDAO.obtenerDescripcionPorIncidencia(incidencia);
        areaSolucion.setText(solucion != null ? solucion : "Esta incidencia aún no tiene solución registrada.");

        // Carga el colaborador desde la BD; si no existe muestra aviso en el campo.
        String colaborador = SolucionDAO.obtenerColaboradorPorIncidencia(incidencia);
        txtResueltaPor.setText(colaborador != null ? colaborador : "Sin asignar");
    }
}