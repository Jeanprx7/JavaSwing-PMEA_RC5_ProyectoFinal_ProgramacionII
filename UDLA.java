package org.parqueaderos.parqueadero;

import java.util.ArrayList;
import java.util.List;

public class UDLA {
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<AdministradorParqueadero> administradores = new ArrayList<>();

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        estudiantes.remove(estudiante);
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public Estudiante buscarEstudiantePorCorreo(String correo) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCorreo().equals(correo)) {
                return estudiante;
            }
        }
        return null;
    }

    public void agregarAdministrador(AdministradorParqueadero administrador) {
        administradores.add(administrador);
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCorreo().equals(correo)) {
                return estudiante;
            }
        }
        for (AdministradorParqueadero administrador : administradores) {
            if (administrador.getCorreo().equals(correo)) {
                return administrador;
            }
        }
        return null;
    }
}
