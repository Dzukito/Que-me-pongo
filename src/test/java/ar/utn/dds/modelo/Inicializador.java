package ar.utn.dds.modelo;

import ar.utn.dds.modelo.clases.*;
import ar.utn.dds.modelo.clima.MeteorologoWeatherAdapter;
import ar.utn.dds.modelo.interfaces.Meteorologo;
import ar.utn.dds.modelo.ropa.*;

import java.util.*;

public class Inicializador {
    Ubicacion buenosAires;
    Usuario usuario1,usuario2,usuario3,usuario4;
    List<Usuario> usuarios;
    Meteorologo meteorologo;
    Evento irATrabajar;

    public Ubicacion getBuenosAires() {
        return buenosAires;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public Usuario getUsuario3() {
        return usuario3;
    }

    public Usuario getUsuario4() {
        return usuario4;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Meteorologo getMeteorologo() {
        return meteorologo;
    }

    public Evento getIrATrabajar() {
        return irATrabajar;
    }

    public Evento getIrAlGym() {
        return irAlGym;
    }

    public Evento getIrALaFacu() {
        return irALaFacu;
    }

    public Guardaropa getRopero1() {
        return ropero1;
    }

    public Guardaropa getRopero2() {
        return ropero2;
    }

    public Guardaropa getRopero3() {
        return ropero3;
    }

    public Guardaropa getRopero4() {
        return ropero4;
    }

    public Guardaropa getRopero5() {
        return ropero5;
    }

    public Guardaropa getRopero6() {
        return ropero6;
    }

    public Calendar getFechaDeHoy() {
        return fechaDeHoy;
    }

    public Calendar getFechaDeHoyMas2Hora() {
        return fechaDeHoyMas2Hora;
    }

    public Atuendo getAtuendo() {
        return atuendo;
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

    public HashSet<Material> getMaterialRemera1() {
        return materialRemera1;
    }

    public HashSet<Material> getMaterialLentes() {
        return materialLentes;
    }

    public HashSet<Material> getMaterialParaguas() {
        return materialParaguas;
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

    public TipoPrenda getPantalonCorto() {
        return pantalonCorto;
    }

    public TipoPrenda getBufanda() {
        return bufanda;
    }

    public TipoPrenda getZapatos() {
        return zapatos;
    }

    public TipoPrenda getRemeraTop() {
        return remeraTop;
    }

    public TipoPrenda getLentes() {
        return lentes;
    }

    public TipoPrenda getParaguas() {
        return paraguas;
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

    public Prenda getAccesorio3() {
        return accesorio3;
    }

    public Prenda getAccesorio4() {
        return accesorio4;
    }

    public Prenda getPrenda() {
        return prenda;
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

    public ArrayList<Prenda> getPrendas4() {
        return prendas4;
    }

    public ArrayList<Prenda> getPrendas5() {
        return prendas5;
    }

    public ArrayList<Prenda> getPrendas6() {
        return prendas6;
    }

    public ArrayList<Prenda> getPrendaUnica() {
        return prendaUnica;
    }

    public ConjuntosPredefinidos getConjuntoRemeraTop() {
        return conjuntoRemeraTop;
    }

    public ConjuntosPredefinidos getConjuntoRemera() {
        return conjuntoRemera;
    }

    public ArrayList<ConjuntosPredefinidos> getConjuntosGuardaropa() {
        return conjuntosGuardaropa;
    }

    Evento irAlGym;
    Evento irALaFacu;
    Guardaropa ropero1,ropero2,ropero3,ropero4,ropero5,ropero6;
    Calendar fechaDeHoy, fechaDeHoyMas2Hora;
    Atuendo atuendo, atuendo1, atuendo2, atuendo3, atuendo4;
    HashSet<Material> materialRemera, materialPantalon, materialCalzado, materialAccesorio,materialRemera1, materialLentes, materialParaguas;
    TipoPrenda pantalon, remera, zapatillas, accesorio, top,pantalonCorto,bufanda, zapatos, remeraTop, lentes, paraguas;
    ArrayList<Color> blancoYNegro, azul, amarillo, rojoYVerde;
    Prenda remera1, remera2, pantalon1, pantalon2, zapatillas1, zapatillas2, accesorio1, accesorio2, accesorio3, accesorio4, prenda;
    ArrayList<Prenda> prendas1, prendas2, prendas3, prendas4, prendas5, prendas6, prendaUnica;
    ConjuntosPredefinidos conjuntoRemeraTop, conjuntoRemera;
    ArrayList<ConjuntosPredefinidos> conjuntosGuardaropa;

    public Inicializador(){
        this.setup();
    }

    public void setup() {
    //Meteorologos
        meteorologo = new MeteorologoWeatherAdapter();
    //Fechas
        fechaDeHoyMas2Hora = Calendar.getInstance();
        fechaDeHoyMas2Hora.add(Calendar.HOUR, 7);
        fechaDeHoy = Calendar.getInstance();
        fechaDeHoy.add(Calendar.HOUR, 2);
    //Ubicaciones
        buenosAires = new Ubicacion("3435910", "Buenos Aires", "BUENOS Aires", "ar");
    //Eventos
        irAlGym = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires, Estilo.DEPORTIVO);
        irALaFacu = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires, Estilo.ENTRECASA);
        irATrabajar = new Evento(fechaDeHoy,fechaDeHoyMas2Hora,buenosAires, Estilo.ELEGANTSPORT);
    //Materiales por prenda
        materialRemera = new HashSet<Material>();
        materialRemera.add(Material.LINO);
        materialRemera.add(Material.FRANELA);
        materialRemera.add(Material.ALGODON);
        materialRemera1 = new HashSet<Material>();
        materialRemera1.add(Material.LINO);
        materialRemera1.add(Material.FRANELA);
        materialRemera1.add(Material.ALGODON);
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
        materialLentes = new HashSet<Material>();
        materialLentes.add(Material.PLASTICO);
        materialParaguas = new HashSet<Material>();
        materialParaguas.add(Material.PLASTICO);
    //Colores
        blancoYNegro = new ArrayList<Color>(Arrays.asList(Color.Blanco,Color.Negro));
        azul = new ArrayList<Color>(Arrays.asList(Color.Azul));
        amarillo = new ArrayList<Color>(Arrays.asList(Color.Amarillo));
        rojoYVerde = new ArrayList<Color>(Arrays.asList(Color.Rojo,Color.Verde));
    //Tipo de Prendas
        //Torso
        remera = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        top = new TipoPrenda(Categoria.TORSO, "Top", materialRemera);
        remeraTop = new TipoPrenda(Categoria.TORSO, "Remera", materialRemera);
        //Parte Inferior
        pantalon = new TipoPrenda(Categoria.PARTEINFERIOR, "Pantalon", materialPantalon);
        pantalonCorto = new TipoPrenda(Categoria.PARTEINFERIOR, "PantalonCorto", materialPantalon);
        //Zapatillas
        zapatillas = new TipoPrenda(Categoria.CALZADO, "Zapatillas", materialCalzado);
        zapatos = new TipoPrenda(Categoria.CALZADO, "Zapatos", materialCalzado);
        //Cabeza
        //Accesorio
        accesorio = new TipoPrenda(Categoria.ACCESORIOS, "Accesorio", materialAccesorio);
        bufanda = new TipoPrenda(Categoria.ACCESORIOS, "Bufanda", materialAccesorio);
        paraguas = new TipoPrenda(Categoria.ACCESORIOS, "Paraguas", materialParaguas);
        lentes = new TipoPrenda(Categoria.ACCESORIOS, "Lentes", materialLentes);
        //Manos
        //Cuello
    //Prendas
        //Torso
        remera1 = new Prenda(remera, "RemeraDePandas", blancoYNegro, Material.LINO);
        remera2 = new Prenda(top, "Top", blancoYNegro, Material.ALGODON);
        prenda = new Prenda(remeraTop, "RemeraDePandas", blancoYNegro, Material.LINO, Estilo.NORMAL);
        //Parte Inferior
        pantalon1 = new Prenda(pantalon, "Pantalon1", azul, Material.JEAN);
        pantalon2 = new Prenda(pantalonCorto, "PantalonCorto", amarillo, Material.JEAN);
        //Zapatillas
        zapatillas1 = new Prenda(zapatillas, "Zapatillas1", rojoYVerde, Material.CUERO);
        zapatillas2 = new Prenda(zapatos, "Zapatos", blancoYNegro, Material.CUERO);
        //Cabeza
        //Accesorio
        accesorio1 = new Prenda(accesorio, "Gorra", azul, Material.PLASTICO);
        accesorio2 = new Prenda(bufanda, "Bufanda", amarillo, Material.ACEROINOXIDABLE);
        accesorio3 = new Prenda(paraguas, "Paraguas", Color.Amarillo, Material.PLASTICO);
        accesorio4 = new Prenda(lentes, "Lentes", Color.Amarillo, Material.PLASTICO);
    //Lista de prendas
        prendaUnica = new ArrayList<Prenda>(Arrays.asList(prenda));
        prendas1 = new ArrayList<Prenda>(Arrays.asList(remera1,accesorio1,zapatillas1,pantalon1));
        prendas2 = new ArrayList<Prenda>(Arrays.asList(remera2,accesorio2,zapatillas2,pantalon2));
        prendas3 = new ArrayList<Prenda>(Arrays.asList(zapatillas1,pantalon1,remera2));
        prendas4 = new ArrayList<Prenda>(Arrays.asList(remera1,accesorio1,zapatillas1,pantalon1,accesorio3,accesorio4));
        prendas5 = new ArrayList<Prenda>(Arrays.asList(remera1,accesorio1,zapatillas1,pantalon1,remera2,accesorio2,zapatillas2,pantalon2,accesorio4));
        prendas6 = new ArrayList<Prenda>(Arrays.asList(remera1,accesorio1,zapatillas1,pantalon1,remera2,accesorio4));
    //Atuendos
        atuendo = new Atuendo(prendaUnica);
        atuendo1 = new Atuendo(prendas1);
        atuendo2 = new Atuendo(prendas1);
        atuendo3 = new Atuendo(prendas2);
        atuendo4 = new Atuendo(prendas3);
    //conjuntosGuardaRopa
        conjuntoRemeraTop = new ConjuntosPredefinidos(new ArrayList<TipoPrenda>(Arrays.asList(remeraTop)));
        conjuntoRemera = new ConjuntosPredefinidos(new ArrayList<TipoPrenda>(Arrays.asList(remera)));
        conjuntosGuardaropa = new ArrayList<ConjuntosPredefinidos>(Arrays.asList(conjuntoRemeraTop,conjuntoRemera));
    //Roperos
        ropero1 = new Guardaropa(prendas4);
        ropero2 = new Guardaropa(prendas5);
        ropero3 = new Guardaropa(prendas6);
        ropero4 = new Guardaropa(prendas1);
        ropero5 = new Guardaropa(prendas2);
        ropero6 = new Guardaropa(prendas3);
    //Usuarios
        usuario1 = new Usuario("Martín", new ArrayList<Guardaropa>(Arrays.asList(ropero1,ropero2,ropero3)));
        usuario1.agregarEvento(irAlGym);
        usuario2 = new Usuario("Martín", new ArrayList<Guardaropa>(Arrays.asList(ropero4)));
        usuario3 = new Usuario("Martín", new ArrayList<Guardaropa>(Arrays.asList(ropero5)));
        usuario4 = new Usuario("Martín", new ArrayList<Guardaropa>(Arrays.asList(ropero6)));
    //ListaDeUsuarios
        usuarios = new ArrayList<Usuario>(Arrays.asList(usuario1));
    }


}
