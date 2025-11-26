package com.emergencias.model;
//Clase que representa un evento de emergencias
// Contiene información sobre el tipo, ubicación y usuario afectado
public class EmergencyEvent{
    private String tipoEmergencia; // Tipo de emergencia
    private Ubicacion ubicacion; // Ubicación donde ocurre la emergencia
    private UserData datosUsuario; // Datos del usuario

    public EmergencyEvent( String tipoEmergencia, Ubicacion ubicacion, UserData datosUsuario){  // Constructor que crea el elemento de emergencia completo
        this.tipoEmergencia = tipoEmergencia;
        this.ubicacion = ubicacion;
        this.datosUsuario = datosUsuario;

    }


    // Getter para obtener los datos del usuario, tipo de emergencia y ubicacion
    public UserData getDatosUsuario() {
        return datosUsuario;
    }

    public String getTipoEmergencia() {
        return tipoEmergencia;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    @Override
    public String toString() {  // Genera representación en texto del evento de emergencia
        return "Emergencia:" +tipoEmergencia+
                "| ubicacion:" +ubicacion+
                "| usuario:"+datosUsuario;

    }
}

