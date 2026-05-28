package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.Colores;
import dao.UsuarioDAO;
import modelo.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

/**
 * Ventana para registrar nuevos usuarios.
 */
public class Ventana_Registro extends JFrame {

    private static final long serialVersionUID = 1L;
    // Campos del formulario de registro.
    private JPanel contentPane;
    private JTextField input_usuario;
    private JTextField input_correo;
    private JPasswordField input_contrasena;

    private static final Color VERDE_OSCURO_UI = new Color(34, 85, 34);

    public Ventana_Registro(Ventana_Inicio v) {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Al cerrar se vuelve al inicio de sesion.
                v.setVisible(true);
                dispose();
            }
        });

        setTitle("FIXIT!");
        setResizable(false);
        setBounds(100, 100, 700, 580);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // LOGO — centrado en 684px útiles
        // "FIX"(~140px) + "IT!"(~120px) = ~260px → x = (684-260)/2 = 212
        JLabel label_FIX = new JLabel("FIX");
        label_FIX.setForeground(Colores.AMARILLO_OSCURO);
        label_FIX.setFont(new Font("Bahnschrift", Font.BOLD, 100));
        label_FIX.setBounds(212, 45, 175, 130);
        contentPane.add(label_FIX);

        JLabel label_IT = new JLabel("IT!");
        label_IT.setForeground(Colores.VERDE_BRILLANTE);
        label_IT.setFont(new Font("Bahnschrift", Font.BOLD, 100));
        label_IT.setBounds(370, 45, 155, 130);
        contentPane.add(label_IT);

        // SEPARADOR
        JSeparator separador = new JSeparator();
        separador.setForeground(VERDE_OSCURO_UI);
        separador.setBackground(VERDE_OSCURO_UI);
        separador.setBounds(0, 182, 700, 3);
        contentPane.add(separador);

        // TÍTULO DEL FORMULARIO
        JLabel label_titulo = new JLabel("Crear cuenta");
        label_titulo.setFont(new Font("Britannic Bold", Font.PLAIN, 22));
        label_titulo.setForeground(VERDE_OSCURO_UI);
        label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        label_titulo.setBounds(0, 193, 684, 30);
        contentPane.add(label_titulo);

        // ── LABELS + INPUTS ───────────────────────────────────────────
        // label en x=160, input en x=390, inputW=200, inputH=30
        // fila 1 y=238, fila 2 y=282, fila 3 y=326

        JLabel label_Usuario = new JLabel("Nombre de Usuario:");
        label_Usuario.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
        label_Usuario.setForeground(VERDE_OSCURO_UI);
        label_Usuario.setBounds(160, 238, 220, 30);
        contentPane.add(label_Usuario);

        input_usuario = new JTextField();
        input_usuario.setBackground(Color.WHITE);
        input_usuario.setForeground(new Color(40, 40, 40));
        input_usuario.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        input_usuario.setBounds(390, 240, 200, 30);
        input_usuario.setColumns(10);
        contentPane.add(input_usuario);

        JLabel label_correo = new JLabel("Correo:");
        label_correo.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
        label_correo.setForeground(VERDE_OSCURO_UI);
        label_correo.setBounds(160, 282, 220, 30);
        contentPane.add(label_correo);

        input_correo = new JTextField();
        input_correo.setBackground(Color.WHITE);
        input_correo.setForeground(new Color(40, 40, 40));
        input_correo.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        input_correo.setBounds(390, 284, 200, 30);
        input_correo.setColumns(10);
        contentPane.add(input_correo);

        JLabel label_contrasena = new JLabel("Contraseña:");
        label_contrasena.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
        label_contrasena.setForeground(VERDE_OSCURO_UI);
        label_contrasena.setBounds(160, 326, 220, 30);
        contentPane.add(label_contrasena);

        input_contrasena = new JPasswordField();
        input_contrasena.setBackground(Color.WHITE);
        input_contrasena.setForeground(new Color(40, 40, 40));
        input_contrasena.setCaretColor(new Color(40, 40, 40));
        input_contrasena.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        input_contrasena.setBounds(390, 328, 200, 30);
        contentPane.add(input_contrasena);

        // ── CHECKBOX TÉRMINOS ─────────────────────────────────────────

        JLabel label_terminos = new JLabel("Acepto las condiciones y términos de uso");
        label_terminos.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
        label_terminos.setForeground(VERDE_OSCURO_UI);
        label_terminos.setBounds(200, 378, 260, 24);
        contentPane.add(label_terminos);

        JCheckBox checkbox_terminos = new JCheckBox("");
        checkbox_terminos.setBounds(465, 378, 26, 24);
        checkbox_terminos.setBackground(Colores.AMARILLO_FONDO);
        contentPane.add(checkbox_terminos);

        // ── BOTONES ───────────────────────────────────────────────────
        // centrados: ancho 190px → x = (684-190)/2 = 247

        JButton boton_registrarse = new JButton("Registrarse");
        boton_registrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nombreUsuario = input_usuario.getText().trim();
                String email         = input_correo.getText().trim();
                String contrasenya   = new String(input_contrasena.getPassword()).trim();

                // Validaciones previas antes de crear el usuario.
                // VALIDACIÓN — campos vacíos
                if (nombreUsuario.isEmpty() || email.isEmpty() || contrasenya.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane,
                        "Todos los campos son obligatorios.",
                        "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // VALIDACIÓN — términos y condiciones
                if (!checkbox_terminos.isSelected()) {
                    JOptionPane.showMessageDialog(contentPane,
                        "Debes aceptar las condiciones y términos de uso.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // VALIDACIÓN — usuario ya existente
                if (UsuarioDAO.obtenerUsuario(nombreUsuario) != null) {
                    JOptionPane.showMessageDialog(contentPane,
                        "Usuario ya registrado, prueba a iniciar sesión.",
                        "Usuario no válido", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // REGISTRO
                LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yy-MM-dd");
                String fechaRegistro = fechaActual.format(formato);// esto es para que se guarde justo la fecha en la que se registro el usuario y tenga el formato exacto de la base de datos.

                UsuarioDAO.insertarUsuario(new Usuario(nombreUsuario, email, contrasenya, fechaRegistro));

                JOptionPane.showMessageDialog(contentPane,
                    "Usuario registrado correctamente.",
                    "Registro Completado", JOptionPane.INFORMATION_MESSAGE);
                v.setVisible(true);
                dispose();
            }
        });
        boton_registrarse.setForeground(Colores.VERDE_OSCURO);
        boton_registrarse.setBackground(Colores.VERDE_BRILLANTE);
        boton_registrarse.setFont(new Font("Britannic Bold", Font.BOLD, 18));
        boton_registrarse.setFocusPainted(false);
        boton_registrarse.setBounds(247, 420, 190, 58);	
        contentPane.add(boton_registrarse);

        JButton boton_volver = new JButton("Volver");
        boton_volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cancela el registro y muestra de nuevo la ventana inicial.
                v.setVisible(true);
                dispose();
            }
        });
        boton_volver.setForeground(Colores.AMARILLO_OSCURO);
        boton_volver.setBackground(Colores.AMARILLO_PASTEL);
        boton_volver.setFont(new Font("Britannic Bold", Font.BOLD, 14));
        boton_volver.setFocusPainted(false);
        boton_volver.setBounds(273, 488, 138, 36);
        contentPane.add(boton_volver);
    }
}
