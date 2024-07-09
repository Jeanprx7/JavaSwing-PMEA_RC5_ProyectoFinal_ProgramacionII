package org.parqueaderos.parqueadero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private UDLA udla = new UDLA();

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de Login
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);

        // Crear algunos estudiantes de ejemplo
        Estudiante galoguevara = new Estudiante("galo.guevara.torres@udla.edu.ec", "password", "A00998511", 10.0);
        Estudiante rafaela = new Estudiante("rafaela.yanouch@udla.edu.ec", "password", "A001234412", 150.0);
        Estudiante jeanpaul = new Estudiante("jean.rodriguez@udla.edu.ec", "password", "A00987654", 50.0);
        Estudiante thais = new Estudiante("thais.rojas@udla.edu.ec", "password", "A00987654", 50.0);
        udla.agregarEstudiante(galoguevara);
        udla.agregarEstudiante(rafaela);
        udla.agregarEstudiante(jeanpaul);
        udla.agregarEstudiante(thais);

        // Crear un administrador
        AdministradorParqueadero administrador = new AdministradorParqueadero("santiago.cordova.paredes@udla.edu.ec", "admin");
        udla.agregarAdministrador(administrador);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Correo:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correo = userText.getText();
                String contraseña = new String(passwordText.getPassword());

                Usuario usuarioLogueado = udla.buscarUsuarioPorCorreo(correo);

                if (usuarioLogueado instanceof Estudiante) {
                    Estudiante estudianteLogueado = (Estudiante) usuarioLogueado;
                    if (estudianteLogueado.getContraseña().equals(contraseña)) {
                        new EstudianteFrame(estudianteLogueado, udla);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.");
                    }
                } else if (usuarioLogueado instanceof AdministradorParqueadero) {
                    AdministradorParqueadero adminLogueado = (AdministradorParqueadero) usuarioLogueado;
                    if (adminLogueado.getContraseña().equals(contraseña)) {
                        new AdministradorFrame(adminLogueado, udla);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                }
            }
        });
    }
}
