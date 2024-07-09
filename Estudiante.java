package org.parqueaderos.parqueadero;

import java.util.ArrayList;

public class Estudiante extends Usuario {
    private String matricula;
    private double saldo;
    private ArrayList<Vehiculo> vehiculos;

    public Estudiante(String correo, String contraseña, String matricula, double saldo) {
        super(correo, contraseña);
        this.matricula = matricula;
        this.saldo = saldo;
        this.vehiculos = new ArrayList<>();
    }

    public String getMatricula() {
        return matricula;
    }

    public double getSaldo() {
        return saldo;
    }

    public void recargarSaldo(double monto) {
        this.saldo += monto;
    }

    public void transferirSaldo(Estudiante otroEstudiante, double monto) {
        if (this.saldo >= monto) {
            this.saldo -= monto;
            otroEstudiante.recargarSaldo(monto);
        }
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
    }

    public void removerVehiculo(Vehiculo vehiculo) {
        this.vehiculos.remove(vehiculo);
    }
}
