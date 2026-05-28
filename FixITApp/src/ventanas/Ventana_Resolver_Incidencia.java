package ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.IncidenciaDAO;
import dao.SolucionDAO;

import java.awt.*;
import java.awt.event.*;
import modelo.Incidencia;
import modelo.Usuario;
import util.Colores;

/**
 * Ventana donde un colaborador escribe y envia una solucion para una incidencia.
 */
public class Ventana_Resolver_Incidencia extends JFrame {

    private static final long serialVersionUID = 1L;
    // Panel principal y color comun de la ventana.
    private JPanel contentPane;
    private static final Color VERDE_OSCURO_UI = new Color(34, 85, 34);

    public Ventana_Resolver_Incidencia(Ventana_Lista_Incidencias v, Incidencia i, Usuario u) {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Devuelve al listado de incidencias al cerrar.
                v.setVisible(true);
                dispose();
            }
        });

        setTitle("FIXIT!");
        setResizable(false);
        setBounds(100, 100, 560, 620);
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

        // TÍTULO
        JLabel label_titulo = new JLabel("Resolver Incidencia");
        label_titulo.setFont(new Font("Britannic Bold", Font.PLAIN, 22));
        label_titulo.setForeground(VERDE_OSCURO_UI);
        label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo.setBounds(0, 15, 544, 35);
        contentPane.add(label_titulo);

        // SEPARADOR
        JSeparator sep = new JSeparator();
        sep.setForeground(VERDE_OSCURO_UI);
        sep.setBackground(VERDE_OSCURO_UI);
        sep.setBounds(0, 72, 560, 3);
        contentPane.add(sep);

        // ── DATOS DE LA INCIDENCIA ────────────────────────────────────

        // TÍTULO INCIDENCIA
        JLabel lbl_tituloTxt = new JLabel("Título:");
        lbl_tituloTxt.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        lbl_tituloTxt.setForeground(VERDE_OSCURO_UI);
        lbl_tituloTxt.setBounds(30, 88, 100, 25);
        contentPane.add(lbl_tituloTxt);

        JLabel lbl_tituloValor = new JLabel(i.getTitulo());
        lbl_tituloValor.setFont(new Font("Britannic Bold", Font.BOLD, 15));
        lbl_tituloValor.setForeground(new Color(40, 40, 40));
        lbl_tituloValor.setBounds(140, 88, 380, 25);
        contentPane.add(lbl_tituloValor);

        JSeparator s1 = new JSeparator();
        s1.setForeground(new Color(220, 235, 220));
        s1.setBounds(20, 115, 520, 1);
        contentPane.add(s1);

        // ZONA
        JLabel lbl_zonaTxt = new JLabel("Zona:");
        lbl_zonaTxt.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        lbl_zonaTxt.setForeground(VERDE_OSCURO_UI);
        lbl_zonaTxt.setBounds(30, 122, 100, 25);
        contentPane.add(lbl_zonaTxt);

        JLabel lbl_zonaValor = new JLabel(i.getZona());
        lbl_zonaValor.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        lbl_zonaValor.setForeground(new Color(40, 40, 40));
        lbl_zonaValor.setBounds(140, 122, 380, 25);
        contentPane.add(lbl_zonaValor);

        JSeparator s2 = new JSeparator();
        s2.setForeground(new Color(220, 235, 220));
        s2.setBounds(20, 149, 520, 1);
        contentPane.add(s2);

        // CATEGORÍA
        JLabel lbl_catTxt = new JLabel("Categoría:");
        lbl_catTxt.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        lbl_catTxt.setForeground(VERDE_OSCURO_UI);
        lbl_catTxt.setBounds(30, 156, 100, 25);
        contentPane.add(lbl_catTxt);

        JLabel lbl_catValor = new JLabel(i.getCategorias());
        lbl_catValor.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        lbl_catValor.setForeground(new Color(40, 40, 40));
        lbl_catValor.setBounds(140, 156, 380, 25);
        contentPane.add(lbl_catValor);

        JSeparator s3 = new JSeparator();
        s3.setForeground(new Color(220, 235, 220));
        s3.setBounds(20, 183, 520, 1);
        contentPane.add(s3);

        // DESCRIPCIÓN
        JLabel lbl_descTxt = new JLabel("Descripción:");
        lbl_descTxt.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        lbl_descTxt.setForeground(VERDE_OSCURO_UI);
        lbl_descTxt.setBounds(30, 190, 120, 25);
        contentPane.add(lbl_descTxt);

        JTextArea txt_descripcion = new JTextArea(i.getDescripcion());
        txt_descripcion.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        txt_descripcion.setForeground(new Color(40, 40, 40));
        txt_descripcion.setBackground(new Color(255, 250, 210));
        txt_descripcion.setEditable(false);
        txt_descripcion.setLineWrap(true);
        txt_descripcion.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txt_descripcion);
        scrollDesc.setBounds(30, 218, 490, 80);
        contentPane.add(scrollDesc);

        JSeparator s4 = new JSeparator();
        s4.setForeground(new Color(180, 210, 180));
        s4.setBounds(20, 308, 520, 2);
        contentPane.add(s4);

        // ── CAMPO SOLUCIÓN ────────────────────────────────────────────

        JLabel lbl_solucionTxt = new JLabel("Tu solución:");
        lbl_solucionTxt.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        lbl_solucionTxt.setForeground(VERDE_OSCURO_UI);
        lbl_solucionTxt.setBounds(30, 316, 150, 25);
        contentPane.add(lbl_solucionTxt);

        JTextArea txt_solucion = new JTextArea();
        txt_solucion.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        txt_solucion.setForeground(new Color(40, 40, 40));
        txt_solucion.setBackground(Color.WHITE);
        txt_solucion.setLineWrap(true);
        txt_solucion.setWrapStyleWord(true);
        JScrollPane scrollSolucion = new JScrollPane(txt_solucion);
        scrollSolucion.setBounds(30, 344, 490, 110);
        contentPane.add(scrollSolucion);

        // ── BOTONES ───────────────────────────────────────────────────

        JButton btnEnviar = new JButton("Enviar solución");
        btnEnviar.setBackground(Colores.VERDE_BRILLANTE);
        btnEnviar.setForeground(Colores.VERDE_OSCURO);
        btnEnviar.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        btnEnviar.setFocusPainted(false);
        btnEnviar.setBounds(170, 468, 220, 46);
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // No se envia nada a BD si el texto de solucion esta vacio.
                String solucion = txt_solucion.getText().trim();

                if (solucion.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane,
                        "Escribe tu solución antes de enviar.",
                        "Campo vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean ok = SolucionDAO.insertarSolucion(solucion, u, i);

                if (ok) {
                    // Si se guarda la solucion, tambien se cierra la incidencia.
                	IncidenciaDAO.cerrarIncidencia(i);
                    JOptionPane.showMessageDialog(contentPane,
                        "Solución enviada correctamente.",
                        "Enviado", JOptionPane.INFORMATION_MESSAGE);
                    v.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(contentPane,
                        "Error al enviar la solución. Inténtalo de nuevo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnEnviar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                v.setVisible(true);
                dispose();
            }
        });
        btnVolver.setBackground(Colores.AMARILLO_PASTEL);
        btnVolver.setForeground(Colores.AMARILLO_OSCURO);
        btnVolver.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        btnVolver.setFocusPainted(false);
        btnVolver.setBounds(20, 468, 120, 46);
        contentPane.add(btnVolver);
    }
}
