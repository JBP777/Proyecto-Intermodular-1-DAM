package ventanas;

import util.ConexionBD;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.UsuarioDAO;
import modelo.Usuario;
import util.Administrator;
import util.Colores;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

/**
 * Ventana de inicio de sesion y punto de entrada de la aplicacion.
 */
public class Ventana_Inicio extends JFrame {

    private static final long serialVersionUID = 1L;
    // Estado de la ventana y usuario que ha iniciado sesion.
    private JPanel contentPane;
    private Usuario usuarioActual;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConexionBD.getConexion();
                    Ventana_Inicio frame = new Ventana_Inicio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JTextField input_usuario;
    private JPasswordField input_contrasena;

    public Ventana_Inicio() {

        setTitle("FIXIT!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 671, 620);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ── LOGO ──────────────────────────────────────────────────────
        // "FIX" (ancho ~155px) + "IT!" (ancho ~130px) = ~285px en total
        // Para centrarlo: x_inicio = (655 - 285) / 2 = 185
        JLabel label_FIX = new JLabel("FIX");
        label_FIX.setForeground(Colores.AMARILLO_OSCURO);
        label_FIX.setFont(new Font("Bahnschrift", Font.BOLD, 100));
        label_FIX.setBounds(185, 50, 165, 130);
        contentPane.add(label_FIX);

        JLabel label_IT = new JLabel("IT!");
        label_IT.setForeground(Colores.VERDE_BRILLANTE);
        label_IT.setFont(new Font("Bahnschrift", Font.BOLD, 100));
        label_IT.setBounds(340, 50, 155, 130);
        contentPane.add(label_IT);

        // ── FORMULARIO ────────────────────────────────────────────────
        // Label (195px) + gap(10px) + input (180px) = 385px total
        // x_inicio = (655 - 385) / 2 = 135  → label en 135, input en 340
        int labelX  = 135;
        int inputX  = 340;
        int inputW  = 180;
        int inputH  = 30;

        JLabel label_Usuario = new JLabel("Nombre de Usuario:");
        label_Usuario.setForeground(Colores.AMARILLO_OSCURO);
        label_Usuario.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
        label_Usuario.setBounds(labelX, 228, 195, 36);
        contentPane.add(label_Usuario);

        input_usuario = new JTextField();
        input_usuario.setBackground(Color.WHITE);
        input_usuario.setForeground(Colores.AMARILLO_OSCURO);
        input_usuario.setCaretColor(Colores.AMARILLO_OSCURO);
        input_usuario.setFont(new Font("SansSerif", Font.PLAIN, 15));
        input_usuario.setBounds(inputX, 233, inputW, inputH);
        input_usuario.setColumns(10);
        contentPane.add(input_usuario);

        JLabel label_Contrasena = new JLabel("Contraseña:");
        label_Contrasena.setForeground(Colores.AMARILLO_OSCURO);
        label_Contrasena.setFont(new Font("Britannic Bold", Font.PLAIN, 17));
        label_Contrasena.setBounds(labelX, 278, 155, 36);
        contentPane.add(label_Contrasena);

        input_contrasena = new JPasswordField();
        input_contrasena.setBackground(Color.WHITE);
        input_contrasena.setForeground(Colores.AMARILLO_OSCURO);
        input_contrasena.setCaretColor(Colores.AMARILLO_OSCURO);
        input_contrasena.setFont(new Font("SansSerif", Font.PLAIN, 15));
        input_contrasena.setBounds(inputX, 283, inputW, inputH);
        contentPane.add(input_contrasena);

        // ── BOTONES ───────────────────────────────────────────────────
        // boton 230px de ancho → x = (655 - 230) / 2 = 212
        int botonX = 212;
        int botonW = 230;
        int botonH = 60;

        JButton boton_Iniciar = new JButton("Iniciar Sesion");
        boton_Iniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Comprueba credenciales y abre la ventana segun el rol.
                usuarioActual = UsuarioDAO.obtenerUsuario(
                    input_usuario.getText(),
                    new String(input_contrasena.getPassword()));
                if (usuarioActual != null) {
                    if (Administrator.esAdmin(usuarioActual.getNombreUsuario())) {
                        setVisible(false);
                        new Ventana_Principal_Admin(Ventana_Inicio.this, usuarioActual).setVisible(true);
                    } else {
                        setVisible(false);
                        new Ventana_Principal_Usuario(Ventana_Inicio.this, usuarioActual).setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane,
                        "No existe el usuario, prueba a registrarte",
                        "Error de login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        boton_Iniciar.setBackground(Colores.VERDE_BRILLANTE);
        boton_Iniciar.setForeground(Colores.VERDE_OSCURO);
        boton_Iniciar.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        boton_Iniciar.setFocusPainted(false);
        boton_Iniciar.setBounds(botonX, 340, botonW, botonH);
        contentPane.add(boton_Iniciar);

        // Label "¿No tienes cuenta?" centrado entre los dos botones
        JLabel label_noTienesCuenta = new JLabel("¿No tienes cuenta?");
        label_noTienesCuenta.setHorizontalAlignment(SwingConstants.CENTER);
        label_noTienesCuenta.setForeground(Colores.VERDE_OSCURO);
        label_noTienesCuenta.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        label_noTienesCuenta.setBounds(botonX, 412, botonW, 24);
        contentPane.add(label_noTienesCuenta);

        JButton boton_registrarse = new JButton("Registrarse");
        boton_registrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Oculta el login mientras se muestra el formulario de registro.
                Ventana_Registro v = new Ventana_Registro(Ventana_Inicio.this);
                v.setVisible(true);
                setVisible(false);
            }
        });
        boton_registrarse.setBackground(Colores.VERDE_BRILLANTE);
        boton_registrarse.setForeground(Colores.VERDE_OSCURO);
        boton_registrarse.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        boton_registrarse.setFocusPainted(false);
        boton_registrarse.setBounds(botonX, 445, botonW, botonH);
        contentPane.add(boton_registrarse);
    }
}
