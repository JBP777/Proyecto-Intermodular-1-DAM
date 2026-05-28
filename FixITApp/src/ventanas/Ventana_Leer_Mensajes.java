package ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.Contacto;
import util.Colores;
import java.awt.*;
import java.awt.event.*;

/**
 * Ventana de solo lectura para consultar un mensaje recibido.
 */
public class Ventana_Leer_Mensajes extends JFrame {

    private static final long serialVersionUID = 1L;
    // Panel principal donde se colocan los campos del mensaje.
    private JPanel contentPane;

    public Ventana_Leer_Mensajes(Contacto c) {

        setTitle("FIXIT! - Mensaje");
        setResizable(false);
        setBounds(200, 150, 400, 342);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(Colores.AMARILLO_FONDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // LOGO (igual que en Ventana_Principal_Admin)
        JLabel label_FIX = new JLabel("FIX");
        label_FIX.setForeground(Colores.AMARILLO_OSCURO);
        label_FIX.setFont(new Font("Britannic Bold", Font.PLAIN, 35));
        label_FIX.setBounds(10, 11, 58, 43);
        contentPane.add(label_FIX);

        JLabel label_IT = new JLabel("IT!");
        label_IT.setForeground(Colores.VERDE_BRILLANTE);
        label_IT.setFont(new Font("Britannic Bold", Font.PLAIN, 35));
        label_IT.setBounds(64, 5, 49, 54);
        contentPane.add(label_IT);

        JLabel label_admin = new JLabel("ADMIN");
        label_admin.setForeground(new Color(128, 0, 0));
        label_admin.setFont(new Font("Britannic Bold", Font.PLAIN, 19));
        label_admin.setBounds(109, 11, 58, 54);
        contentPane.add(label_admin);

        // CAMPOS
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 65, 70, 20);
        contentPane.add(lblNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setEditable(false);
        txtNombre.setBounds(85, 65, 144, 22);
        contentPane.add(txtNombre);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 95, 70, 20);
        contentPane.add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setEditable(false);
        txtEmail.setBounds(85, 95, 285, 22);
        contentPane.add(txtEmail);

        JLabel lblAsunto = new JLabel("Asunto:");
        lblAsunto.setBounds(10, 125, 70, 20);
        contentPane.add(lblAsunto);

        JTextField txtAsunto = new JTextField();
        txtAsunto.setEditable(false);
        txtAsunto.setBounds(85, 125, 285, 22);
        contentPane.add(txtAsunto);

        JLabel lblMensaje = new JLabel("Mensaje:");
        lblMensaje.setBounds(10, 155, 70, 20);
        contentPane.add(lblMensaje);

        JTextArea areaMensaje = new JTextArea();
        areaMensaje.setLineWrap(true);
        areaMensaje.setWrapStyleWord(true);
        areaMensaje.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaMensaje);
        scroll.setBounds(10, 175, 360, 90);
        contentPane.add(scroll);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(285, 275, 85, 26);
        contentPane.add(btnCerrar);
    
        // Carga los datos del mensaje seleccionado en campos no editables.
        txtNombre.setText(c.getNombre());
        txtEmail.setText(c.getEmail());
        txtAsunto.setText(c.getAsunto());
        areaMensaje.setText(c.getMensaje());
        
    
    }
}
