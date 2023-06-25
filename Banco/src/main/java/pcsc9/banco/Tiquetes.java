/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcsc9.banco;

import java.util.PriorityQueue;

/**
 *
 * @author diego
 */
public class Tiquetes {
 private PriorityQueue<Tiquete> colaPreferencial;
    private PriorityQueue<Tiquete> colaUnSoloTramite;
    private PriorityQueue<Tiquete> colaDosOMasTramites;

    public Tiquetes() {
        colaPreferencial = new PriorityQueue<>();
        colaUnSoloTramite = new PriorityQueue<>();
        colaDosOMasTramites = new PriorityQueue<>();
    }

    public void agregarTiquete(Tiquete tiquete) {
        if (tiquete.getTipo().equals("P")) {
            colaPreferencial.offer(tiquete);
        } else if (tiquete.getTipo().equals("A")) {
            colaUnSoloTramite.offer(tiquete);
        } else if (tiquete.getTipo().equals("B")) {
            colaDosOMasTramites.offer(tiquete);
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
}    