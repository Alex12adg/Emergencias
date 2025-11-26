package com.emergencias.detector;

import com.emergencias.model.Ubicacion;

import java.util.Scanner;

// Clase encargada de detectar y validar eventos de emergencia. Usa un umbral de gravedad para determinar si una emergencia requiere respuesta
public class EmergencyDetector {
    private int umbral; // Nivel de gravedad
    private Ubicacion ubicacionInicial; // Ubicación base del detector
    private Scanner sc; // Scanner para la entrada del usuario

    public EmergencyDetector(int umbral, Ubicacion ubicacionInicial) {  // Constructor que inicializa el detector
        this.umbral = umbral;
        this.ubicacionInicial = ubicacionInicial;
        this.sc = new Scanner(System.in);
    }
    public boolean detectEvent(){ // Metodo que simula la detección de la emergencia, true si detecta emergencia, false en caso contrario

        System.out.println(" ¿Emergencia ?(1=Si, 0=No: ");
        int input = sc.nextInt();
        sc.nextLine();
        return input==1;

    }
    public  boolean validateSverity(int fuerzaGolpe){
        return fuerzaGolpe >= umbral;
    } // Valida si la gravedad del golpe supera el umbral establecido

    public Ubicacion getUbicacionInicial() {
        return ubicacionInicial;
    }  // Getter para obtener la ubicación inicial
    public int getUmbral(){
        return umbral;
    }//  Getter para obtener el umbral actuar
}
