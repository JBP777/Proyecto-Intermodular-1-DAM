package ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.ContactoDAO;
import modelo.Contacto;
import modelo.Usuario;
import util.Colores;
import java.awt.*;
import java.awt.event.*;

public class Ventana_Enviar_Mensaje extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Usuario usuarioActual;
    public Ventana_Enviar_Mensaje(Usuario u) {
    		usuarioActual = u;
        setTitle("FIXIT! - Enviar Mensaje");
        setResizable(false);
        setBounds(200, 150, 400, 370);
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

        JLabel lblAsunto = new JLabel("Asunto:");
        lblAsunto.setBounds(10, 64, 70, 20);
        contentPane.add(lblAsunto);

        JTextField txtAsunto = new JTextField();
        txtAsunto.setBounds(52, 64, 285, 22);
        contentPane.add(txtAsunto);

        JLabel lblMensaje = new JLabel("Mensaje:");
        lblMensaje.setBounds(10, 111, 70, 20);
        contentPane.add(lblMensaje);

        JTextArea areaMensaje = new JTextArea();
        areaMensaje.setLineWrap(true);
        areaMensaje.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(areaMensaje);
        scroll.setBounds(10, 141, 360, 144);
        contentPane.add(scroll);

        // BOTONES
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(190, 295, 85, 26);
        contentPane.add(btnCancelar);

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // comprobar que ningún campo esté vacío antes de enviar
                if (txtAsunto.getText().isEmpty() || areaMensaje.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Por favor, rellena todos los campos.");
                    
                }else {

	                ContactoDAO.enviarMensaje(new Contacto(usuarioActual.getNombreUsuario(),usuarioActual.getEmail(),txtAsunto.getText(),areaMensaje.getText()));
	
	                // limpiar campos tras enviar
	                txtAsunto.setText("");
	                areaMensaje.setText("");
	                JOptionPane.showMessageDialog(contentPane, "Mensaje enviado correctamente.");
                }
            }
        });
        btnEnviar.setBackground(Colores.VERDE_BRILLANTE);
        btnEnviar.setForeground(Colores.VERDE_OSCURO);
        btnEnviar.setBounds(285, 295, 85, 26);
        
        contentPane.add(btnEnviar);
    }
}