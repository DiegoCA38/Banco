/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcsc9.banco;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author diego
 */
public class Tiquete implements Comparable<Tiquete> {
    private static int contadorID = 0;
    private int numeroConsecutivo;
    private String nombre;
    private String id;
    private int edad;
    private LocalDateTime horaCreacion;
    private LocalDateTime horaAtencion;
    private String tramite;
    private String tipo;
    private int numeroCaja; 
    private int personasDelante;

   
    
    public void setHoraAtencion(LocalDateTime horaAtencion) {
        this.horaAtencion = horaAtencion;
    }
     public LocalDateTime getHoraAtencion() {
        return horaAtencion;
    }
    
    
    public int getTramites() {
        // Aquí Se cuenta la cantidad de trámites en base al String tramite. Por ejemplo, si el tramite es "A-B-C", entonces tiene 3 trámites, supondremos que los trámites están separados por guiones ("-").

        if (tramite == null || tramite.isEmpty()) {
            return 0;
        }

        String[] tramitesArray = tramite.split("-");
        return tramitesArray.length;
    }
    
    public Tiquete(int numeroConsecutivo,String nombre, String id, int edad, String tramite, String tipo) {
        this.numeroConsecutivo = numeroConsecutivo;
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.horaCreacion = LocalDateTime.now();
        this.horaAtencion = null;
        this.tramite = tramite;
        this.tipo = tipo;
     
        
        contadorID++;
                
    }

    public Tiquete(int numeroConsecutivo) {
        this.numeroConsecutivo = numeroConsecutivo;
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

    public String getTramite() {
        return tramite;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNumeroConsecutivo() {
        return numeroConsecutivo;
    }

    public void setNumeroConsecutivo(int numeroConsecutivo) {
        this.numeroConsecutivo = numeroConsecutivo;
    }

    public int getNumeroCaja() {
        return numeroCaja;
    }

    public void setNumeroCaja(int numeroCaja) {
        this.numeroCaja = numeroCaja;
    }

    public int getPersonasDelante() {
        return personasDelante;
    }

    public void setPersonasDelante(int personasDelante) {
        this.personasDelante = personasDelante;
    }
 
    @Override
    public int compareTo(Tiquete otroTiquete) {
        return this.horaCreacion.compareTo(otroTiquete.getHoraCreacion());
    }
    
    //Daniel - to string para importarlos a los TXT de cajas

    @Override
    public String toString() {
        
        LocalTime horaCreacion = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaFormateada = horaCreacion.format(formatter);
        
        return "Tiquete: " +numeroConsecutivo+"\n"+
               "Nombre: "+nombre+ "\n"+
               "ID: "+id+"\n"+
               "Edad: "+edad+ "\n"+
               "HoraCreacion: "+horaCreacion+"\n"+
               "HoraAtencion: "+horaAtencion+"\n"+
               "Tramite: " +tramite+"\n"+
               "Tipo: " + tipo +"\n"+
               "NumeroCaja: " + numeroCaja +"\n"+
               "PersonasDelante: " + personasDelante +"\n";
    }
    
    
    
}
