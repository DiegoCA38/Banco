/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcsc9.banco;

import java.time.LocalDateTime;

/**
 *
 * @author diego
 */
public class Tiquete implements Comparable<Tiquete> {
    private static int contadorID = 0;
    private String nombre;
    private String id;
    private int edad;
    private LocalDateTime horaCreacion;
    private LocalDateTime horaAtencion;
    private String tramite;
    private String tipo;

    
    public void setHoraAtencion(LocalDateTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }
    
    public int getTramites() {
        // Aquí Se cuenta la cantidad de trámites en base al String tramite. Por ejemplo, si el tramite es "A-B-C", entonces tiene 3 trámites, supondremos que los trámites están separados por guiones ("-").

        if (tramite == null || tramite.isEmpty()) {
            return 0;
        }

        String[] tramitesArray = tramite.split("-");
        return tramitesArray.length;
    }
    
    public Tiquete(String nombre, String id, int edad, String tramite, String tipo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.horaCreacion = LocalDateTime.now();
        this.horaAtencion = null;
        this.tramite = tramite;
        this.tipo = tipo;

        contadorID++;
    }

    public String getNombre() {
        return nombre;
    }

    public String getID() {
        return id;
    }

    public int getEdad() {
        return edad;
    }

    public LocalDateTime getHoraCreacion() {
        return horaCreacion;
    }

    public LocalDateTime getHoraAtencion() {
        return horaAtencion;
    }

    public String getTramite() {
        return tramite;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public int compareTo(Tiquete otroTiquete) {
        return this.horaCreacion.compareTo(otroTiquete.getHoraCreacion());
    }
}
