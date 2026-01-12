package com.emergencias.model;

import java.util.ArrayList;
import java.util.Arrays;

public class PerfilMedico {
    private String tipoSangre;
    private ArrayList<String> alergias;
    private ArrayList<String> medicamentos;
    private ArrayList<String> condicionesMedicas;
    private String contactoEmergencia;
    private String telefonoEmergencia;
    private String seguroMedico;

    /**
     * Constructor que inicializa un perfil médico vacío
     */
    public PerfilMedico() {
        this.tipoSangre = "No especificado";
        this.alergias = new ArrayList<>();
        this.medicamentos = new ArrayList<>();
        this.condicionesMedicas = new ArrayList<>();
        this.contactoEmergencia = "No especificado";
        this.telefonoEmergencia = "No especificado";
        this.seguroMedico = "No especificado";
    }
    //Metodos para tipo de sangre//
    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

   //Metodos para alergias//

    public void agregarAlergia(String alergia) {
        if (!alergias.contains(alergia)) {
            alergias.add(alergia);
            System.out.println(" Alergia añadida: " + alergia);
        } else {
            System.out.println(" Esta alergia ya está registrada");
        }
    }

    public void eliminarAlergia(String alergia) {
        if (alergias.remove(alergia)) {
            System.out.println(" Alergia eliminada: " + alergia);
        } else {
            System.out.println(" Alergia no encontrada");
        }
    }

    public ArrayList<String> getAlergias() {
        return alergias;
    }

    //Metodo para medicamentos//

    public void agregarMedicamento(String medicamento) {
        if (!medicamentos.contains(medicamento)) {
            medicamentos.add(medicamento);
            System.out.println(" Medicamento añadido: " + medicamento);
        } else {
            System.out.println(" Este medicamento ya está registrado");
        }
    }

    public void eliminarMedicamento(String medicamento) {
        if (medicamentos.remove(medicamento)) {
            System.out.println(" Medicamento eliminado: " + medicamento);
        } else {
            System.out.println(" Medicamento no encontrado");
        }
    }

    public ArrayList<String> getMedicamentos() {
        return medicamentos;
    }

    //Metodo para condiciones medicas//

    public void agregarCondicion(String condicion) {
        if (!condicionesMedicas.contains(condicion)) {
            condicionesMedicas.add(condicion);
            System.out.println(" Condición médica añadida: " + condicion);
        } else {
            System.out.println(" Esta condición ya está registrada");
        }
    }

    public void eliminarCondicion(String condicion) {
        if (condicionesMedicas.remove(condicion)) {
            System.out.println(" Condición eliminada: " + condicion);
        } else {
            System.out.println(" Condición no encontrada");
        }
    }

    public ArrayList<String> getCondicionesMedicas() {
        return condicionesMedicas;
    }

    //Metodo para contacto de emergencias//
    public void setContactoEmergencia(String nombre, String telefono) {
        this.contactoEmergencia = nombre;
        this.telefonoEmergencia = telefono;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    //Metodos para seguro medico//

    public void setSeguroMedico(String seguroMedico) {
        this.seguroMedico = seguroMedico;
    }

    public String getSeguroMedico() {
        return seguroMedico;
    }

    // Verifica si el perfil medico esta completo//

    public boolean estaCompleto() {
        return !tipoSangre.equals("No especificado") ||
                !alergias.isEmpty() ||
                !contactoEmergencia.equals("No especificado");
    }

    //Genera un perfil medico para mostrar en pantalla//

    public void mostrarPerfil() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("         PERFIL MÉDICO DEL USUARIO");
        System.out.println("=".repeat(50));

        System.out.println("\n Tipo de Sangre: " + tipoSangre);
        System.out.println(" Seguro Médico: " + seguroMedico);

        System.out.println("\n Contacto de Emergencia:");
        System.out.println("   Nombre: " + contactoEmergencia);
        System.out.println("   Teléfono: " + telefonoEmergencia);

        System.out.println("\n  Alergias:");
        if (alergias.isEmpty()) {
            System.out.println("   Ninguna registrada");
        } else {
            for (String alergia : alergias) {
                System.out.println("   • " + alergia);
            }
        }

        System.out.println("\n Medicamentos:");
        if (medicamentos.isEmpty()) {
            System.out.println("   Ninguno registrado");
        } else {
            for (String med : medicamentos) {
                System.out.println("   • " + med);
            }
        }

        System.out.println("\n Condiciones Médicas:");
        if (condicionesMedicas.isEmpty()) {
            System.out.println("   Ninguna registrada");
        } else {
            for (String cond : condicionesMedicas) {
                System.out.println("   • " + cond);
            }
        }

        System.out.println("=".repeat(50));
    }

    public String getInformacionCritica() {
        StringBuilder info = new StringBuilder();
        info.append("\n️ INFORMACIÓN MÉDICA CRÍTICA:\n");
        info.append("Tipo de Sangre: ").append(tipoSangre).append("\n");

        if (!alergias.isEmpty()) {
            info.append(" ALERGIAS: ");
            info.append(String.join(", ", alergias)).append("\n");
        }

        if (!medicamentos.isEmpty()) {
            info.append(" Medicamentos: ");
            info.append(String.join(", ", medicamentos)).append("\n");
        }

        if (!condicionesMedicas.isEmpty()) {
            info.append(" Condiciones: ");
            info.append(String.join(", ", condicionesMedicas)).append("\n");
        }

        info.append(" Contacto: ").append(contactoEmergencia);
        info.append(" (").append(telefonoEmergencia).append(")");

        return info.toString();
    }

    @Override
    public String toString() {
        return "Tipo Sangre: " + tipoSangre +
                ", Alergias: " + (alergias.isEmpty() ? "Ninguna" : alergias.size()) +
                ", Contacto: " + contactoEmergencia;
    }

}
