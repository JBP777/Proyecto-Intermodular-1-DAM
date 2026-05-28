package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelo.Usuario;
import util.Colores;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 * Menu principal para usuarios normales tras iniciar sesion.
 */
public class Ventana_Principal_Usuario extends JFrame {

    private static final long serialVersionUID = 1L;
    // Usuario conectado y panel principal.
    private JPanel contentPane;
    private Usuario usuarioActual;

    public Ventana_Principal_Usuario(Ventana_Inicio v, Usuario u) {
        usuarioActual = u;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Cierra solo esta ventana.
                dispose();
            }
        });

        setTitle("FIXIT!");
        setBounds(100, 100, 560, 640);
        setResizable(false);
        setLocationRelativeTo(null);

        // MENU — verde oscuro como la ventana admin
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(34, 85, 34));
        setJMenuBar(menuBar);

        JMenu mnOpciones = new JMenu("Opciones");
        mnOpciones.setForeground(Color.WHITE);
        menuBar.add(mnOpciones);

        JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
        mntmCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Vuelve a la ventana de inicio manteniendo el flujo original.
                dispose();
                v.setVisible(true);
            }
        });
        mnOpciones.add(mntmCerrarSesion);

        // PANEL PRINCIPAL
        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // LOGO
        JLabel label_FIX = new JLabel("FIX");
        label_FIX.setForeground(Colores.AMARILLO_OSCURO);
        label_FIX.setFont(new Font("Britannic Bold", Font.PLAIN, 55));
        label_FIX.setBounds(168, 12, 110, 65);
        contentPane.add(label_FIX);

        JLabel label_IT = new JLabel("IT!");
        label_IT.setForeground(Colores.VERDE_BRILLANTE);
        label_IT.setFont(new Font("Britannic Bold", Font.PLAIN, 55));
        label_IT.setBounds(270, 12, 100, 65);
        contentPane.add(label_IT);

        // SEPARADOR — verde en vez de amarillo oscuro/marrón
        JSeparator separador = new JSeparator();
        separador.setForeground(new Color(34, 85, 34));
        separador.setBackground(new Color(34, 85, 34));
        separador.setBounds(0, 85, 560, 3);
        contentPane.add(separador);

        // BIENVENIDA
        JLabel lblBienvenida = new JLabel("¡Bienvenido/a,");
        lblBienvenida.setFont(new Font("Britannic Bold", Font.PLAIN, 28));
        lblBienvenida.setForeground(new Color(34, 85, 34)); // verde oscuro
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenida.setBounds(0, 105, 544, 40);
        contentPane.add(lblBienvenida);

        JLabel lblNombreUsuario = new JLabel(usuarioActual.getNombreUsuario() + "!");
        lblNombreUsuario.setFont(new Font("Britannic Bold", Font.BOLD, 38));
        lblNombreUsuario.setForeground(Colores.VERDE_BRILLANTE);
        lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombreUsuario.setBounds(0, 148, 544, 55);
        contentPane.add(lblNombreUsuario);

        JLabel lblSubtitulo = new JLabel("¿Qué deseas hacer hoy?");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 17));
        lblSubtitulo.setForeground(new Color(34, 85, 34)); // verde oscuro
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setBounds(0, 210, 544, 30);
        contentPane.add(lblSubtitulo);

        // BOTONES
        JButton boton_verIncidencias = new JButton("Ver Incidencias");
        boton_verIncidencias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Ventana_Lista_Incidencias vli = new Ventana_Lista_Incidencias(Ventana_Principal_Usuario.this, usuarioActual);
            	vli.setVisible(true);
            	setVisible(false);
            }
        });
        boton_verIncidencias.setForeground(Colores.VERDE_OSCURO);
        boton_verIncidencias.setBackground(Colores.VERDE_BRILLANTE);
        boton_verIncidencias.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
        boton_verIncidencias.setFocusPainted(false);
        boton_verIncidencias.setBounds(142, 258, 260, 58);
        contentPane.add(boton_verIncidencias);

        JButton boton_agregarIncidencia = new JButton("Crear nueva Incidencia");
        boton_agregarIncidencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre el formulario de nueva incidencia.
                Ventana_AgregarIncidencia_Usuario va = new Ventana_AgregarIncidencia_Usuario(
                    Ventana_Principal_Usuario.this, usuarioActual);
                va.setLocationRelativeTo(null);
                va.setVisible(true);
                dispose();
            }
        });
        boton_agregarIncidencia.setForeground(Colores.VERDE_OSCURO);
        boton_agregarIncidencia.setBackground(Colores.VERDE_BRILLANTE);
        boton_agregarIncidencia.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
        boton_agregarIncidencia.setFocusPainted(false);
        boton_agregarIncidencia.setBounds(142, 334, 260, 58);
        contentPane.add(boton_agregarIncidencia);

        JButton boton_enviarMensaje = new JButton("Contactar Admin");
        boton_enviarMensaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre el formulario de contacto con administracion.
                Ventana_Enviar_Mensaje vm = new Ventana_Enviar_Mensaje(usuarioActual);
                vm.setLocationRelativeTo(null);
                vm.setVisible(true);
            }
        });
        boton_enviarMensaje.setForeground(Colores.VERDE_OSCURO);
        boton_enviarMensaje.setBackground(Colores.VERDE_BRILLANTE);
        boton_enviarMensaje.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
        boton_enviarMensaje.setFocusPainted(false);
        boton_enviarMensaje.setBounds(142, 410, 260, 58);
        contentPane.add(boton_enviarMensaje);

        JButton boton_verPerfil = new JButton("Ver Perfil");
        boton_verPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestra datos y estadisticas del usuario conectado.
                Ventana_VerPerfil_Usuario vp = new Ventana_VerPerfil_Usuario(
                    Ventana_Principal_Usuario.this, usuarioActual);
                vp.setVisible(true);
                dispose();
            }
        });
        boton_verPerfil.setForeground(Colores.VERDE_OSCURO);
        boton_verPerfil.setBackground(Colores.VERDE_BRILLANTE);
        boton_verPerfil.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
        boton_verPerfil.setFocusPainted(false);
        boton_verPerfil.setBounds(142, 486, 260, 58);
        contentPane.add(boton_verPerfil);
    }

}
