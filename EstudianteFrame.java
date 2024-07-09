package org.parqueaderos.parqueadero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstudianteFrame extends JFrame {
    private Estudiante estudiante;
    private UDLA udla;

    public EstudianteFrame(Estudiante estudiante, UDLA udla) {
        this.estudiante = estudiante;
        this.udla = udla;

        setTitle("Menú Estudiante");
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

        JLabel menuLabel = new JLabel("Menú Estudiante:");
        menuLabel.setBounds(10, 20, 200, 25);
        panel.add(menuLabel);

        JButton verVehiculosButton = new JButton("Ver vehículos registrados");
        verVehiculosButton.setBounds(10, 50, 200, 25);
        panel.add(verVehiculosButton);

        verVehiculosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder vehiculos = new StringBuilder("Vehículos registrados:\n");
                for (Vehiculo vehiculo : estudiante.getVehiculos()) {
                    vehiculos.append("Matrícula: ").append(vehiculo.obtenerMatricula()).append(", Tipo: ").append(vehiculo.obtenerTipo()).append("\n");
                }
                JOptionPane.showMessageDialog(null, vehiculos.toString());
            }
        });

        JButton registrarVehiculoButton = new JButton("Registrar nuevo vehículo");
        registrarVehiculoButton.setBounds(10, 80, 200, 25);
        panel.add(registrarVehiculoButton);

        registrarVehiculoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = JOptionPane.showInputDialog("Ingrese la matrícula del nuevo vehículo:");
                String tipo = JOptionPane.showInputDialog("Ingrese el tipo del nuevo vehículo:");
                Vehiculo nuevoVehiculo = new Vehiculo(matricula, tipo);
                estudiante.agregarVehiculo(nuevoVehiculo);
                JOptionPane.showMessageDialog(null, "Vehículo registrado exitosamente.");
            }
        });

        JButton eliminarVehiculoButton = new JButton("Eliminar vehículo");
        eliminarVehiculoButton.setBounds(10, 110, 200, 25);
        panel.add(eliminarVehiculoButton);

        eliminarVehiculoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = JOptionPane.showInputDialog("Ingrese la matrícula del vehículo a eliminar:");
                Vehiculo vehiculoAEliminar = null;
                for (Vehiculo vehiculo : estudiante.getVehiculos()) {
                    if (vehiculo.obtenerMatricula().equals(matricula)) {
                        vehiculoAEliminar = vehiculo;
                        break;
                    }
                }
                if (vehiculoAEliminar != null) {
                    estudiante.removerVehiculo(vehiculoAEliminar);
                    JOptionPane.showMessageDialog(null, "Vehículo eliminado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
                }
            }
        });

        JButton verSaldoButton = new JButton("Ver saldo");
        verSaldoButton.setBounds(10, 140, 200, 25);
        panel.add(verSaldoButton);

        verSaldoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saldo actual: $" + estudiante.getSaldo());
            }
        });

        JButton recargarSaldoButton = new JButton("Recargar saldo");
        recargarSaldoButton.setBounds(10, 170, 200, 25);
        panel.add(recargarSaldoButton);

        recargarSaldoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a recargar:"));
                estudiante.recargarSaldo(monto);
                JOptionPane.showMessageDialog(null, "Saldo recargado exitosamente. Nuevo saldo: $" + estudiante.getSaldo());
            }
        });

        JButton transferirHorasButton = new JButton("Transferir horas");
        transferirHorasButton.setBounds(10, 200, 200, 25);
        panel.add(transferirHorasButton);

        transferirHorasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correoDestino = JOptionPane.showInputDialog("Ingrese el correo del estudiante al que desea transferir horas:");
                Estudiante estudianteDestino = udla.buscarEstudiantePorCorreo(correoDestino);
                if (estudianteDestino != null) {
                    double montoTransferir = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a transferir:"));
                    if (montoTransferir <= estudiante.getSaldo()) {
                        estudiante.transferirSaldo(estudianteDestino, montoTransferir);
                        JOptionPane.showMessageDialog(null, "Horas transferidas exitosamente. Nuevo saldo: $" + estudiante.getSaldo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar la transferencia.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
                }
            }
        });
    }
}
