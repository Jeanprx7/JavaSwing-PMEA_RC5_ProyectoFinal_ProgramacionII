package org.parqueaderos.parqueadero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministradorFrame extends JFrame {
    private AdministradorParqueadero administrador;
    private UDLA udla;

    public AdministradorFrame(AdministradorParqueadero administrador, UDLA udla) {
        this.administrador = administrador;
        this.udla = udla;

        setTitle("Menú Administrador");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel menuLabel = new JLabel("Menú Administrador:");
        menuLabel.setBounds(10, 20, 200, 25);
        panel.add(menuLabel);

        JButton verEstudiantesButton = new JButton("Ver estudiantes");
        verEstudiantesButton.setBounds(10, 50, 200, 25);
        panel.add(verEstudiantesButton);

        verEstudiantesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder estudiantes = new StringBuilder("Estudiantes registrados:\n");
                for (Estudiante estudiante : udla.getEstudiantes()) {
                    estudiantes.append("Correo: ").append(estudiante.getCorreo()).append(", Saldo: $").append(estudiante.getSaldo()).append("\n");
                }
                JOptionPane.showMessageDialog(null, estudiantes.toString());
            }
        });

        JButton agregarEstudianteButton = new JButton("Agregar estudiante");
        agregarEstudianteButton.setBounds(10, 80, 200, 25);
        panel.add(agregarEstudianteButton);

        agregarEstudianteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correo = JOptionPane.showInputDialog("Ingrese el correo del nuevo estudiante:");
                String contraseña = JOptionPane.showInputDialog("Ingrese la contraseña del nuevo estudiante:");
                String matricula = JOptionPane.showInputDialog("Ingrese la matrícula del nuevo estudiante:");
                double saldo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el saldo inicial del nuevo estudiante:"));
                Estudiante nuevoEstudiante = new Estudiante(correo, contraseña, matricula, saldo);
                udla.agregarEstudiante(nuevoEstudiante);
                JOptionPane.showMessageDialog(null, "Estudiante agregado exitosamente.");
            }
        });

        JButton eliminarEstudianteButton = new JButton("Eliminar estudiante");
        eliminarEstudianteButton.setBounds(10, 110, 200, 25);
        panel.add(eliminarEstudianteButton);

        eliminarEstudianteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correo = JOptionPane.showInputDialog("Ingrese el correo del estudiante a eliminar:");
                Estudiante estudianteAEliminar = udla.buscarEstudiantePorCorreo(correo);
                if (estudianteAEliminar != null) {
                    udla.eliminarEstudiante(estudianteAEliminar);
                    JOptionPane.showMessageDialog(null, "Estudiante eliminado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
                }
            }
        });

        JButton verVehiculosButton = new JButton("Ver vehículos");
        verVehiculosButton.setBounds(10, 140, 200, 25);
        panel.add(verVehiculosButton);

        verVehiculosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder vehiculos = new StringBuilder("Vehículos registrados:\n");
                for (Estudiante estudiante : udla.getEstudiantes()) {
                    for (Vehiculo vehiculo : estudiante.getVehiculos()) {
                        vehiculos.append("Matrícula: ").append(vehiculo.obtenerMatricula()).append(", Tipo: ").append(vehiculo.obtenerTipo()).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(null, vehiculos.toString());
            }
        });

        JButton agregarVehiculoButton = new JButton("Agregar vehículo a estudiante");
        agregarVehiculoButton.setBounds(10, 170, 200, 25);
        panel.add(agregarVehiculoButton);

        agregarVehiculoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correo = JOptionPane.showInputDialog("Ingrese el correo del estudiante:");
                Estudiante estudiante = udla.buscarEstudiantePorCorreo(correo);
                if (estudiante != null) {
                    String matricula = JOptionPane.showInputDialog("Ingrese la matrícula del nuevo vehículo:");
                    String tipo = JOptionPane.showInputDialog("Ingrese el tipo del nuevo vehículo:");
                    Vehiculo nuevoVehiculo = new Vehiculo(matricula, tipo);
                    estudiante.agregarVehiculo(nuevoVehiculo);
                    JOptionPane.showMessageDialog(null, "Vehículo agregado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
                }
            }
        });
    }
}
