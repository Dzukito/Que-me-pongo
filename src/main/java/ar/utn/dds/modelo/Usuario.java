package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.HorarioYaOcupado;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private List<Guardaropa> roperos;
    private String userName;
    private Membrecia membrecia;
    private ArrayList<Evento> eventos;
    private Meteorologo meteorologo;


    public void agregarEvento(Evento evento){
        if (this.puedoAsistir(evento)){
            this.eventos.add(evento);
        }else{
            throw new HorarioYaOcupado();
        }
    }
    public boolean puedoAsistir(Evento evento){
        return !eventos.stream().anyMatch(evento1 -> evento1.estoyEnEseHorario(evento));
    }
    Usuario(String userName, ArrayList<Guardaropa> roperos){
        this.userName = userName;
        this.roperos = roperos;
        this.membrecia = new Gratuito();
    }

    public int cantidadPrendas(int guardaropa){
        return this.roperos.get(guardaropa).cantidadDePrendas();
    }
    public Guardaropa guardaropa(int i){
        return this.roperos.get(i);
    }
    public ArrayList<Atuendo>  atuendosGuardaropa(Guardaropa ropero){
        return ropero.atuendosGenerados();
    }
    public int cantidadDePrendasPorCategoria(Prenda prenda, int i){
        return this.guardaropa(i).cantidadDePrendasEnCategoria(prenda.categoria());
    }
    public int cantidadAtuendos(int i){
        return roperos.get(i).cantidadAtuendosGenerados();
    }
    public void agregarPreda(Prenda prenda, Guardaropa ropero){
        ropero.agregarPrenda(prenda);
    }
    public void agregarRopero(Guardaropa ropero){
        this.roperos.add(ropero);
    }

    public Atuendo pedirAtuendo(int i){
        return this.roperos.get(i).sugerirAtuendo();
    }
    public int cantidadDeAtuendosDisponibles(int i){
        return this.roperos.get(i).cantidadDeAtuendosPosibles();
    }

    public void cambiarMembrecia(Membrecia membrecia) {
        this.membrecia = membrecia;
    }

    public int cantidadDeRoperos() {
        return this.roperos.size();
    }
}
