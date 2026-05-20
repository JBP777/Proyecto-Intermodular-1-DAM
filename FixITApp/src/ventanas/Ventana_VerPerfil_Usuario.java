package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modelo.Usuario;
import util.Colores;

public class Ventana_VerPerfil_Usuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private static final Color VERDE_OSCURO_UI = new Color(34, 85, 34);

    public Ventana_VerPerfil_Usuario(Ventana_Principal_Usuario v, Usuario u) {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                v.setVisible(true);
                dispose();
            }
        });

        setTitle("FIXIT!");
        setResizable(false);
        setBounds(100, 100, 520, 580);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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

        // TÍTULO VENTANA
        JLabel label_titulo = new JLabel("Mi Perfil");
        label_titulo.setFont(new Font("Britannic Bold", Font.PLAIN, 26));
        label_titulo.setForeground(VERDE_OSCURO_UI);
        label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo.setBounds(0, 15, 510, 35);
        contentPane.add(label_titulo);

        // SEPARADOR
        JSeparator separador = new JSeparator();
        separador.setForeground(VERDE_OSCURO_UI);
        separador.setBackground(VERDE_OSCURO_UI);
        separador.setBounds(0, 72, 520, 3);
        contentPane.add(separador);

        // ── ICONO DE USUARIO (círculo decorativo) ─────────────────────
        JLabel iconoUsuario = new JLabel("👤");
        iconoUsuario.setFont(new Font("SansSerif", Font.PLAIN, 52));
        iconoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        iconoUsuario.setBounds(0, 85, 510, 65);
        contentPane.add(iconoUsuario);

        // NOMBRE DE USUARIO grande y verde
        JLabel lblNombreUsuario = new JLabel(u.getNombreUsuario());
        lblNombreUsuario.setFont(new Font("Britannic Bold", Font.PLAIN, 30));
        lblNombreUsuario.setForeground(Colores.VERDE_BRILLANTE);
        lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombreUsuario.setBounds(0, 150, 510, 40);
        contentPane.add(lblNombreUsuario);

        // SEPARADOR fino debajo del nombre
        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(180, 210, 180));
        sep2.setBounds(40, 198, 430, 2);
        contentPane.add(sep2);

        // ── FILAS DE DATOS ────────────────────────────────────────────
        // Cada fila: label izquierda (etiqueta) + label derecha (valor)
        // labelX=50, valorX=280, filaH=42, y inicial=210

        // EMAIL
        JLabel lbl_emailTitulo = new JLabel("Email:");
        lbl_emailTitulo.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        lbl_emailTitulo.setForeground(VERDE_OSCURO_UI);
        lbl_emailTitulo.setBounds(50, 210, 220, 30);
        contentPane.add(lbl_emailTitulo);

        JLabel lbl_emailValor = new JLabel(u.getEmail()); // TODO: ajusta al getter real
        lbl_emailValor.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lbl_emailValor.setForeground(new Color(40, 40, 40));
        lbl_emailValor.setBounds(280, 210, 190, 30);
        contentPane.add(lbl_emailValor);

        JSeparator s1 = new JSeparator();
        s1.setForeground(new Color(220, 235, 220));
        s1.setBounds(40, 242, 430, 1);
        contentPane.add(s1);

        // FECHA DE REGISTRO
        JLabel lbl_fechaTitulo = new JLabel("Fecha de registro:");
        lbl_fechaTitulo.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        lbl_fechaTitulo.setForeground(VERDE_OSCURO_UI);
        lbl_fechaTitulo.setBounds(50, 250, 220, 30);
        contentPane.add(lbl_fechaTitulo);

        JLabel lbl_fechaValor = new JLabel(u.getFechaRegistro().toString()); // TODO: ajusta al getter real
        lbl_fechaValor.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lbl_fechaValor.setForeground(new Color(40, 40, 40));
        lbl_fechaValor.setBounds(280, 250, 190, 30);
        contentPane.add(lbl_fechaValor);

        JSeparator s2 = new JSeparator();
        s2.setForeground(new Color(220, 235, 220));
        s2.setBounds(40, 282, 430, 1);
        contentPane.add(s2);

        // INCIDENCIAS CREADAS
        JLabel lbl_creadasTitulo = new JLabel("Incidencias creadas:");
        lbl_creadasTitulo.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        lbl_creadasTitulo.setForeground(VERDE_OSCURO_UI);
        lbl_creadasTitulo.setBounds(50, 290, 220, 30);
        contentPane.add(lbl_creadasTitulo);

        JLabel lbl_creadasValor = new JLabel("—"); // TODO: sustituir con dato real
        lbl_creadasValor.setFont(new Font("SansSerif", Font.BOLD, 16));
        lbl_creadasValor.setForeground(Colores.VERDE_BRILLANTE);
        lbl_creadasValor.setBounds(280, 290, 190, 30);
        contentPane.add(lbl_creadasValor);

        JSeparator s3 = new JSeparator();
        s3.setForeground(new Color(220, 235, 220));
        s3.setBounds(40, 322, 430, 1);
        contentPane.add(s3);

        // INCIDENCIAS RESUELTAS
        JLabel lbl_resueltasTitulo = new JLabel("Incidencias resueltas:");
        lbl_resueltasTitulo.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        lbl_resueltasTitulo.setForeground(VERDE_OSCURO_UI);
        lbl_resueltasTitulo.setBounds(50, 330, 220, 30);
        contentPane.add(lbl_resueltasTitulo);

        JLabel lbl_resueltasValor = new JLabel("—"); // TODO: sustituir con dato real
        lbl_resueltasValor.setFont(new Font("SansSerif", Font.BOLD, 16));
        lbl_resueltasValor.setForeground(Colores.VERDE_BRILLANTE);
        lbl_resueltasValor.setBounds(280, 330, 190, 30);
        contentPane.add(lbl_resueltasValor);

        JSeparator s4 = new JSeparator();
        s4.setForeground(new Color(220, 235, 220));
        s4.setBounds(40, 362, 430, 1);
        contentPane.add(s4);

        // VALORACIÓN MEDIA
        JLabel lbl_valoracionTitulo = new JLabel("Valoración media:");
        lbl_valoracionTitulo.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        lbl_valoracionTitulo.setForeground(VERDE_OSCURO_UI);
        lbl_valoracionTitulo.setBounds(50, 370, 220, 30);
        contentPane.add(lbl_valoracionTitulo);

        JLabel lbl_valoracionValor = new JLabel("— ⭐"); // TODO: sustituir con dato real
        lbl_valoracionValor.setFont(new Font("SansSerif", Font.BOLD, 16));
        lbl_valoracionValor.setForeground(Colores.VERDE_BRILLANTE);
        lbl_valoracionValor.setBounds(280, 370, 190, 30);
        contentPane.add(lbl_valoracionValor);

        // SEPARADOR antes del botón
        JSeparator sep3 = new JSeparator();
        sep3.setForeground(new Color(180, 210, 180));
        sep3.setBounds(40, 412, 430, 2);
        contentPane.add(sep3);

        // BOTÓN VOLVER
        JButton boton_volver = new JButton("Volver");
        boton_volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                v.setVisible(true);
                dispose();
            }
        });
        boton_volver.setBackground(Colores.VERDE_BRILLANTE);
        boton_volver.setForeground(Colores.VERDE_OSCURO);
        boton_volver.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        boton_volver.setFocusPainted(false);
        boton_volver.setBounds(175, 430, 160, 48);
        contentPane.add(boton_volver);
    }
}