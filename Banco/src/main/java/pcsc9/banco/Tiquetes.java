/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcsc9.banco;

import java.util.PriorityQueue;

/**
 *Daniel
 * @author diego
 */
public class Tiquetes {
    private PriorityQueue<Tiquete> colaPreferencial;
    private PriorityQueue<Tiquete> colaUnSoloTramite;
    private PriorityQueue<Tiquete> colaDosOMasTramites;
    
//    private PriorityQueue<Tiquete> caja1 = new PriorityQueue<>();   //creacion e cajas
//    private PriorityQueue<Tiquete> caja2 = new PriorityQueue<>();   //tipo B (dos o mas transmites)
//    private PriorityQueue<Tiquete> caja3 = new PriorityQueue<>();
    


    public Tiquetes() {
        colaPreferencial = new PriorityQueue<>();
        colaUnSoloTramite = new PriorityQueue<>();
        colaDosOMasTramites = new PriorityQueue<>();
    }

    public void agregarTiquete(Tiquete tiquete) {
        if (tiquete.getTipo().equals("P")) {        //preferencial
            colaPreferencial.offer(tiquete);
        } else if (tiquete.getTipo().equals("A")) { //rapida
            colaUnSoloTramite.offer(tiquete);
        } else if (tiquete.getTipo().equals("B")) { //dos o mas
            colaDosOMasTramites.offer(tiquete);
            
            llenarColas(tiquete);                         //llamar llenarcolas()
            
        }
    }

    public Tiquete atenderSiguienteTiquete() {
        if (!colaPreferencial.isEmpty()) {
            return colaPreferencial.poll();
        } else if (!colaUnSoloTramite.isEmpty()) {
            return colaUnSoloTramite.poll();
        } else if (!colaDosOMasTramites.isEmpty()) {
            return colaDosOMasTramites.poll();
        } else {
            return null;
        }
    }

//    public void llenarColas(Tiquete tiquete){   //pone los tiquets en caja al de menor espacio en cola
//    
//        int cantidadCaja1 = caja1.size();   //determina cantidad de objetos en caja
//        int cantidadCaja2 = caja2.size();
//        int cantidadCaja3 = caja3.size();
//
//        if(cantidadCaja1 <= cantidadCaja2 && cantidadCaja1 <= cantidadCaja3){       //caja 1 menor carga que las otras dos
//          caja1.offer(tiquete);
//
//        }else if(cantidadCaja2 <= cantidadCaja1 && cantidadCaja2 <= cantidadCaja3){ //caja 2 menor carga que las otras dos
//           caja2.offer(tiquete);
//
//        }else{                                                                      //caja 3 era el que menos carga tenia
//            caja3.offer(tiquete);
//        }  
//   }
    
}    