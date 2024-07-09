package org.parqueaderos.parqueadero;

public class Vehiculo {
    private String matricula;
    private String tipo;

    public Vehiculo(String matricula, String tipo) {
        this.matricula = matricula;
        this.tipo = tipo;
    }

    public String obtenerMatricula() {
        return matricula;
    }

    public String obtenerTipo() {
        return tipo;
    }
}
