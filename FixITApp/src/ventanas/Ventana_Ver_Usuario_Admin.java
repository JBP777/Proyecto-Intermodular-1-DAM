package ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.UsuarioDAO;

import java.awt.*;
import java.awt.event.*;
import modelo.Usuario;
import util.Colores;

/**
 * Vista de administrador para consultar el perfil de un usuario.
 */
public class Ventana_Ver_Usuario_Admin extends JFrame {

    private static final long serialVersionUID = 1L;
    // Panel principal de la ventana.
    private JPanel contentPane;
    private static final Color VERDE_OSCURO_UI = new Color(34, 85, 34);

    public Ventana_Ver_Usuario_Admin(Ventana_Principal_Admin v, Usuario u) {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Al cerrar se devuelve el control al panel admin.
                v.setVisible(true);
                dispose();
            }
        });

        setTitle("FIXIT!");
        setResizable(false);
        setBounds(100, 100, 520, 580);
        setLocationRelativeTo(null);

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

        JLabel label_admin = new JLabel("ADMIN");
        label_admin.setForeground(new Color(160, 0, 0));
        label_admin.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
        label_admin.setBounds(152, 18, 70, 36);
        contentPane.add(label_admin);

        // SEPARADOR
        JSeparator sep = new JSeparator();
        sep.setForeground(VERDE_OSCURO_UI);
        sep.setBackground(VERDE_OSCURO_UI);
        sep.setBounds(0, 72, 520, 3);
        contentPane.add(sep);

        // ICONO
        JLabel iconoUsuario = new JLabel("👤");
        iconoUsuario.setFont(new Font("SansSerif", Font.PLAIN, 52));
        iconoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        iconoUsuario.setBounds(0, 85, 504, 65);
        contentPane.add(iconoUsuario);

        // NOMBRE
        JLabel lblNombre = new JLabel(u.getNombreUsuario());
        lblNombre.setFont(new Font("Britannic Bold", Font.PLAIN, 30));
        lblNombre.setForeground(Colores.VERDE_BRILLANTE);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setBounds(0, 150, 504, 40);
        contentPane.add(lblNombre);

        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(180, 210, 180));
        sep2.setBounds(40, 198, 430, 2);
        contentPane.add(sep2);

        // EMAIL
        JLabel lbl_emailTitulo = new JLabel("Email:");
        lbl_emailTitulo.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        lbl_emailTitulo.setForeground(VERDE_OSCURO_UI);
        lbl_emailTitulo.setBounds(50, 210, 220, 30);
        contentPane.add(lbl_emailTitulo);

        JLabel lbl_emailValor = new JLabel(u.getEmail());
        lbl_emailValor.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lbl_emailValor.setForeground(new Color(40, 40, 40));
        lbl_emailValor.setBounds(280, 210, 190, 30);
        contentPane.add(lbl_emailValor);

        JSeparator s1 = new JSeparator();
        s1.setForeground(new Color(220, 235, 220));
        s1.setBounds(40, 242, 430, 1);
        contentPane.add(s1);

        // FECHA REGISTRO
        JLabel lbl_fechaTitulo = new JLabel("Fecha de registro:");
        lbl_fechaTitulo.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        lbl_fechaTitulo.setForeground(VERDE_OSCURO_UI);
        lbl_fechaTitulo.setBounds(50, 250, 220, 30);
        contentPane.add(lbl_fechaTitulo);

        JLabel lbl_fechaValor = new JLabel(u.getFechaRegistro());
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

        // Estadisticas compartidas con la pantalla de perfil de usuario.
        String[] stats = UsuarioDAO.obtenerEstadisticas(u);
        
        JLabel lbl_creadasValor = new JLabel(stats[0]);
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

        JLabel lbl_resueltasValor = new JLabel(stats[1]);
        lbl_resueltasValor.setFont(new Font("SansSerif", Font.BOLD, 16));
        lbl_resueltasValor.setForeground(Colores.VERDE_BRILLANTE);
        lbl_resueltasValor.setBounds(280, 330, 190, 30);
        contentPane.add(lbl_resueltasValor);

        JSeparator s4 = new JSeparator();
        s4.setForeground(new Color(220, 235, 220));
        s4.setBounds(40, 362, 430, 1);
        contentPane.add(s4);

        // BOTÓN VOLVER
        JButton boton_volver = new JButton("Volver");
        boton_volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Refresca las tablas por si se han producido cambios.
                v.cargarTablas(); 
                v.setVisible(true);
                dispose();
            }
        });
        boton_volver.setBackground(Colores.VERDE_BRILLANTE);
        boton_volver.setForeground(VERDE_OSCURO_UI);
        boton_volver.setFont(new Font("Britannic Bold", Font.PLAIN, 16));
        boton_volver.setFocusPainted(false);
        boton_volver.setBounds(175, 430, 160, 48);
        contentPane.add(boton_volver);
    }
}
