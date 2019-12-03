package ar.utn.dds;

import ar.utn.dds.modelo.*;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Inicializador {
    Categoria torso, inferior, accesorios, calzado, cuello, manos, cabeza;
    Atuendo atuendo1, atuendo2, atuendo3, atuendo4;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio;
    TipoPrenda pantalon, remera, zapatillas, accesorio, top,pantalonCorto,bufanda, zapatos;
    ArrayList<Color> blancoYNegro, azul, amarillo, rojoYVerde;
    Prenda remera1, remera2, pantalon1, pantalon2, zapatillas1, zapatillas2, accesorio1, accesorio2;
    ArrayList<Prenda> prendas1, prendas2, prendas3;
    TipoPrenda remeraTop, remeraNoTop;
    ConjuntosPredefinidos remeraTop1, remeraNoTop1;
    ArrayList<ConjuntosPredefinidos> conjuntosGuardaRopa;
    Prenda prenda;
    Atuendo atuendo;

    public Inicializador(){
        this.setup();
    }

    public void setup() {
        torso = Categoria.TORSO;
        inferior = Categoria.PARTEINFERIOR;
        accesorios = Categoria.ACCESORIOS;
        calzado = Categoria.CALZADO;
        cuello = Categoria.CUELLO;
        manos = Categoria.MANOS;
        cabeza = Categoria.CABEZA;
//        torso = new Categoria("torso", 5, 1);
//        inferior = new Categoria("inferior", 3,1);
//        accesorios = new Categoria( "accesorios", 10,0);
//        calzado = new Categoria("calzado", 3,1);
//        cuello = new Categoria("cuello",1,0);
//        manos = new Categoria( "mano", 1,0);
//        cabeza = new Categoria("cabeza", 1,0);
        materialRemera = new HashSet<Material>();
        materialRemera.add(Material.LINO);
        materialRemera.add(Material.FRANELA);
        materialRemera.add(Material.ALGODON);
        materialPantalon = new HashSet<Material>();
        materialPantalon.add(Material.MALLA);
        materialPantalon.add(Material.JEAN);
        materialPantalon.add(Material.CUERO);
        materialCalzado = new HashSet<Material>();
        materialCalzado.add(Material.CUERO);
        materialCalzado.add(Material.PLASTICO);
        materialAccesorio = new HashSet<Material>();
        materialAccesorio.add(Material.PLASTICO);
        materialAccesorio.add(Material.ACEROINOXIDABLE);
        materialAccesorio.add(Material.CUERO);
        remera = new TipoPrenda(torso, "Remera", materialRemera);
        pantalon = new TipoPrenda(inferior, "Pantalon", materialPantalon);
        pantalonCorto = new TipoPrenda(inferior, "PantalonCorto", materialPantalon);
        top = new TipoPrenda(torso, "Top", materialRemera);
        zapatillas = new TipoPrenda(calzado, "Zapatillas", materialCalzado);
        zapatos = new TipoPrenda(calzado, "Zapatos", materialCalzado);
        accesorio = new TipoPrenda(accesorios, "Accesorio", materialAccesorio);
        bufanda = new TipoPrenda(accesorios, "Bufanda", materialAccesorio);
        blancoYNegro = new ArrayList<Color>();
        blancoYNegro.add(Color.Blanco);
        blancoYNegro.add(Color.Negro);
        azul = new ArrayList<Color>();
        azul.add(Color.Azul);
        amarillo = new ArrayList<Color>();
        amarillo.add(Color.Amarillo);
        rojoYVerde = new ArrayList<Color>();
        rojoYVerde.add(Color.Rojo);
        rojoYVerde.add(Color.Verde);
        remera1 = new Prenda(remera, "RemeraDePandas", blancoYNegro, Material.LINO);
        remera2 = new Prenda(top, "Top", blancoYNegro, Material.ALGODON);
        pantalon1 = new Prenda(pantalon, "Pantalon1", azul, Material.JEAN);
        pantalon2 = new Prenda(pantalonCorto, "PantalonCorto", amarillo, Material.JEAN);
        zapatillas1 = new Prenda(zapatillas, "Zapatillas1", rojoYVerde, Material.CUERO);
        zapatillas2 = new Prenda(zapatos, "Zapatos", blancoYNegro, Material.CUERO);
        accesorio1 = new Prenda(accesorio, "Gorra", azul, Material.PLASTICO);
        accesorio2 = new Prenda(bufanda, "Bufanda", amarillo, Material.ACEROINOXIDABLE);
        prendas1 = new ArrayList<Prenda>();
        prendas1.add(remera1);
        prendas1.add(accesorio1);
        prendas1.add(zapatillas1);
        prendas1.add(pantalon1);
        prendas2 = new ArrayList<Prenda>();
        prendas2.add(remera2);
        prendas2.add(accesorio2);
        prendas2.add(zapatillas2);
        prendas2.add(pantalon2);
        prendas3 = new ArrayList<Prenda>();
        prendas3.add(zapatillas1);
        prendas3.add(pantalon1);
        prendas3.add(remera2);
        atuendo1 = new Atuendo(prendas1);
        atuendo2 = new Atuendo(prendas1);
        atuendo3 = new Atuendo(prendas2);
        atuendo4 = new Atuendo(prendas3);

        materialRemera = new HashSet<Material>();
        materialRemera.add(Material.LINO);
        materialRemera.add(Material.FRANELA);
        materialRemera.add(Material.ALGODON);
        remeraTop = new TipoPrenda(torso, "Remera", materialRemera);
        remeraNoTop = new TipoPrenda(torso, "Remera", materialRemera);
        remeraTop1 = new ConjuntosPredefinidos(new ArrayList<TipoPrenda>(Arrays.asList(remeraTop)));
        remeraNoTop1 = new ConjuntosPredefinidos(new ArrayList<TipoPrenda>(Arrays.asList(remeraNoTop)));
        conjuntosGuardaRopa = new ArrayList<ConjuntosPredefinidos>(Arrays.asList(remeraTop1,remeraNoTop1));
        blancoYNegro = new ArrayList<Color>();
        blancoYNegro.add(Color.Blanco);
        blancoYNegro.add(Color.Negro);
        prenda = new Prenda(remeraTop, "RemeraDePandas", blancoYNegro, Material.LINO,Estilo.NORMAL);
        prendas1 = new ArrayList<Prenda>();
        prendas1.add(prenda);
        atuendo = new Atuendo(prendas1);


    }

    public Categoria getTorso() {
        return torso;
    }

    public Categoria getInferior() {
        return inferior;
    }

    public Categoria getAccesorios() {
        return accesorios;
    }

    public Categoria getCalzado() {
        return calzado;
    }

    public Categoria getCuello() {
        return cuello;
    }

    public Categoria getManos() {
        return manos;
    }

    public Categoria getCabeza() {
        return cabeza;
    }

    public Atuendo getAtuendo1() {
        return atuendo1;
    }

    public Atuendo getAtuendo2() {
        return atuendo2;
    }

    public Atuendo getAtuendo3() {
        return atuendo3;
    }

    public Atuendo getAtuendo4() {
        return atuendo4;
    }

    public HashSet<Material> getMaterialRemera() {
        return materialRemera;
    }

    public HashSet<Material> getMaterialPantalon() {
        return materialPantalon;
    }

    public HashSet<Material> getMaterialCalzado() {
        return materialCalzado;
    }

    public HashSet<Material> getMaterialAccesorio() {
        return materialAccesorio;
    }

    public TipoPrenda getPantalon() {
        return pantalon;
    }

    public TipoPrenda getRemera() {
        return remera;
    }

    public TipoPrenda getZapatillas() {
        return zapatillas;
    }

    public TipoPrenda getAccesorio() {
        return accesorio;
    }

    public TipoPrenda getTop() {
        return top;
    }

    public TipoPrenda getRemeraTop() {
        return remeraTop;
    }

    public TipoPrenda getRemeraNoTop() {
        return remeraNoTop;
    }

    public ConjuntosPredefinidos getRemeraTop1() {
        return remeraTop1;
    }

    public ConjuntosPredefinidos getRemeraNoTop1() {
        return remeraNoTop1;
    }

    public ArrayList<ConjuntosPredefinidos> getConjuntosGuardaRopa() {
        return conjuntosGuardaRopa;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public Atuendo getAtuendo() {
        return atuendo;
    }


    public TipoPrenda getPantalonCorto() {
        return pantalonCorto;
    }

    public TipoPrenda getBufanda() {
        return bufanda;
    }

    public TipoPrenda getZapatos() {
        return zapatos;
    }

    public ArrayList<Color> getBlancoYNegro() {
        return blancoYNegro;
    }

    public ArrayList<Color> getAzul() {
        return azul;
    }

    public ArrayList<Color> getAmarillo() {
        return amarillo;
    }

    public ArrayList<Color> getRojoYVerde() {
        return rojoYVerde;
    }

    public Prenda getRemera1() {
        return remera1;
    }

    public Prenda getRemera2() {
        return remera2;
    }

    public Prenda getPantalon1() {
        return pantalon1;
    }

    public Prenda getPantalon2() {
        return pantalon2;
    }

    public Prenda getZapatillas1() {
        return zapatillas1;
    }

    public Prenda getZapatillas2() {
        return zapatillas2;
    }

    public Prenda getAccesorio1() {
        return accesorio1;
    }

    public Prenda getAccesorio2() {
        return accesorio2;
    }

    public ArrayList<Prenda> getPrendas1() {
        return prendas1;
    }

    public ArrayList<Prenda> getPrendas2() {
        return prendas2;
    }

    public ArrayList<Prenda> getPrendas3() {
        return prendas3;
    }
}
