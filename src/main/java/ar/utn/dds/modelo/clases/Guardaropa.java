package ar.utn.dds.modelo.clases;

import ar.utn.dds.modelo.clima.Pronostico;
import ar.utn.dds.modelo.interfaces.AceptarSuegerenciaObservador;
import ar.utn.dds.modelo.interfaces.RechazarSugerenciaObservador;
import ar.utn.dds.modelo.ropa.Categoria;
import ar.utn.dds.modelo.ropa.Estilo;
import ar.utn.dds.modelo.ropa.Prenda;
import ar.utn.dds.modelo.ropa.TipoPrenda;
import ar.utn.dds.modelo.clima.NivelDeCalor;

import java.util.*;
import javax.persistence.*;
import java.util.stream.Collectors;


@Entity
@Table(name="guardaropa")
public class Guardaropa implements AceptarSuegerenciaObservador, RechazarSugerenciaObservador {

    @Id
    @GeneratedValue
    private long id_guardaropa;

    //agregar al constructor
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "Descripcion")
    private String descripcion;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="id_guardaropa")
    private List<Prenda> prendas;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="id_guardaropa")
    private List<Atuendo> atuendosMostrados;

    @ManyToMany(mappedBy = "roperos", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="id_guardaropa")
    private List<ConjuntosPredefinidos> conjuntosPredefinidos;


    public Atuendo sugerirAtuendoSinEvento(Estilo estilo, Atuendo atuendoSugerido) {
        this.prendas.stream().filter(prenda -> prenda.tengoEstilo(estilo)).forEach(prenda -> atuendoSugerido.agregarPrenda(prenda));
        return atuendoSugerido;
    }
    public Atuendo sugerirAtuendoSinEvento(Estilo estilo) {
        Atuendo atuendoSugerido = new Atuendo();
        this.prendas.stream().filter(prenda -> prenda.tengoEstilo(estilo)).forEach(prenda -> atuendoSugerido.agregarPrenda(prenda));
        return atuendoSugerido;
    }
    public Atuendo sugerirAtuendoSinEvento() {
        Atuendo atuendoSugerido = new Atuendo();
        this.prendas.forEach(prenda -> atuendoSugerido.agregarPrenda(prenda));
        return atuendoSugerido;
    }

    //Metodos-que-hay-que-borrar----------------------------------
    //Metodos-que-no-se-usan--------------------------------------
    public boolean estaEnLaLista(Atuendo atuendo) {
        return this.atuendosMostrados.stream().anyMatch(atuendo1 -> atuendo1.somosIguales(atuendo));
    }
    public void reservarPrendas(Atuendo atuendo){
        this.prendas.stream()
                .filter(prenda -> atuendo.tengoPrenda(prenda))
                .forEach(prenda -> prenda.bloquearse());
    }
    public void desbloquearPrendas(Atuendo atuendo){
        this.prendas.stream()
                .filter(prenda -> atuendo.tengoPrenda(prenda))
                .forEach(prenda -> prenda.desbloquearse());
    }
    //    public Atuendo sugerirAtuendoSinEvento(){
//        new Atuendo
//    }
    //Metodos-privados--------------------------------------------
    private ArrayList<Atuendo> atuendosUtilez(ArrayList<Atuendo> atuendos, Usuario usuario, Evento evento, Pronostico pronostico, ArrayList<NivelDeCalor> nivelesDeCalor){
        return (ArrayList<Atuendo>) atuendos.stream().filter(atuendo ->
                atuendo.tieneEstiloEnParticular(evento.getEstilo())
                        // && atuendo.compatibleConTiempo(pronostico,usuario)
                        && atuendo.satisfaceNivelesDeCalor(nivelesDeCalor))
                .collect(Collectors.toList());
    }

    private ArrayList<Atuendo> atuendosUtilez(ArrayList<Atuendo> atuendos, Usuario usuario, Estilo estilo, Pronostico pronostico, ArrayList<NivelDeCalor> nivelesDeCalor){

        System.out.println("Atuendos:"+atuendos.stream().filter(atuendo -> 1==1 ).collect(Collectors.toList()));

        return (ArrayList<Atuendo>) atuendos.stream().filter(atuendo ->
                atuendo.tieneEstiloEnParticular(estilo)
                        // && atuendo.compatibleConTiempo(pronostico,usuario) REVISAR
                        && atuendo.satisfaceNivelesDeCalor(nivelesDeCalor))
                .collect(Collectors.toList());

    }
    //Metodos-publicos--------------------------------------------
    //SugerenciaDeAtuendo-----------------------------------------
    public Atuendo sugerirAtuendo(Pronostico pronostico, Evento evento, Usuario usuario) {

        ArrayList<Atuendo> atuendos= new ArrayList<Atuendo>(this.atuendosMostrados);
        ArrayList<Prenda> prendas= new ArrayList<Prenda>(this.prendas);

        ArrayList<NivelDeCalor> nivelesDeCalor = usuario.getSensibilidad().ajustarNivelesDeCalor(pronostico.nivelesDeCalorRequeridos());
        ArrayList<Atuendo> atuendosMostradosUtilez =this.atuendosUtilez( atuendos,usuario,evento,pronostico,nivelesDeCalor);
        if(!atuendosMostradosUtilez.isEmpty() && 1==0) { //Que hacemos con esto?? Se estanca con el get(0)
            atuendosMostradosUtilez.get(0);
            //max(Comparator.comparing(atuendo -> atuendo.promedioCalificaciones(usuario,evento,pronostico)));
        }else{

            if(!this.atuendosUtilez((ArrayList<Atuendo>) this.conjuntosPredefinidos.stream().map(conjuntoPredefinido -> conjuntoPredefinido.cargarAtuendo(prendas,evento.getEstilo())).collect(Collectors.toList()),usuario,evento.getEstilo(),pronostico,nivelesDeCalor).isEmpty())
            {return this.atuendosUtilez((ArrayList<Atuendo>) this.conjuntosPredefinidos.stream()
                            .map(conjuntoPredefinido -> conjuntoPredefinido.cargarAtuendo(prendas,evento.getEstilo())).collect(Collectors.toList()),
                    usuario,evento,pronostico,nivelesDeCalor).get(0);}
            else
                System.out.println("ATENCION: NO SE ENCONTRO NINGUNA SUGERENCIA ACORDE A SUS NECESIDADES");
        }
        return null;
    }


    public Atuendo sugerirAtuendo(Pronostico pronostico, Estilo estilo, Usuario usuario) {

        ArrayList<Atuendo> atuendos= new ArrayList<Atuendo>(this.atuendosMostrados);
        ArrayList<Prenda> prendas= new ArrayList<Prenda>(this.prendas);
        ArrayList<NivelDeCalor> nivelesDeCalor = usuario.getSensibilidad().ajustarNivelesDeCalor(pronostico.nivelesDeCalorRequeridos());
        ArrayList<Atuendo> atuendosMostradosUtilez =this.atuendosUtilez( atuendos,usuario,estilo,pronostico,nivelesDeCalor);
        if(!atuendosMostradosUtilez.isEmpty() && 1==0) { //Que hacemos con esto??
            atuendosMostradosUtilez.get(0);
            //max(Comparator.comparing(atuendo -> atuendo.promedioCalificaciones(usuario,evento,pronostico)));
        }else{

            if(!this.atuendosUtilez((ArrayList<Atuendo>) this.conjuntosPredefinidos.stream().map(conjuntoPredefinido -> conjuntoPredefinido.cargarAtuendo(prendas,estilo)).collect(Collectors.toList()),usuario,estilo,pronostico,nivelesDeCalor).isEmpty())
            {return this.atuendosUtilez((ArrayList<Atuendo>) this.conjuntosPredefinidos.stream()
                            .map(conjuntoPredefinido -> conjuntoPredefinido.cargarAtuendo(prendas,estilo)).collect(Collectors.toList()),
                    usuario,estilo,pronostico,nivelesDeCalor).get(0);}
            else
                System.out.println("ATENCION: NO SE ENCONTRO NINGUNA SUGERENCIA ACORDE A SUS NECESIDADES");
        }
        return null;
    }

    public Atuendo sugerirAtuendoPorGuardaropa(Pronostico pronostico, Estilo estilo) {

        Atuendo atuendoPosible=new Atuendo(this.getPrendas().stream().filter(p->p.tengoEstilo(estilo)).collect(Collectors.toList()));
        
        List<Prenda> prendasNoSuperponibles=this.ObtenerPrendasNoSuperponibles(atuendoPosible.getPrendas());
    	Collections.shuffle(prendasNoSuperponibles);
    	List<Prenda> prendasNoSuperponiblesDistintas=this.obtenerPrendasNoSuperponiblesDeDistintasCategorias(prendasNoSuperponibles);
    	
    	List<Prenda> prendasASacar;
    	Collections.shuffle(prendasNoSuperponiblesDistintas);
    	Collections.shuffle(prendasNoSuperponibles);
 
    	if(prendasNoSuperponibles.size()!=prendasNoSuperponiblesDistintas.size()) {
    		prendasASacar=prendasNoSuperponibles.stream().filter(p-> !(prendasNoSuperponiblesDistintas.contains(p))).collect(Collectors.toList());		
    	} else {
    		prendasASacar=new ArrayList<Prenda>();
    	}
    	List<Prenda> prendasFinales= new ArrayList<Prenda>();
    	Atuendo atuendoSugerido=new Atuendo();
//genero atuendo sugerido        	
        	prendasFinales =
        	atuendoPosible.getPrendas().stream().filter(prenda-> !(prendasASacar.contains(prenda)) && 
        			(prendasNoSuperponiblesDistintas.contains(prenda)||
        			 prendasNoSuperponiblesDistintas.stream().anyMatch(prendaBase-> prendaBase.tieneSuperponibleA(prenda)))).collect(Collectors.toList());

//que tenga minimamente estas tres categorias
        	if(!prendasFinales.isEmpty() && prendasFinales.stream().anyMatch(p->p.TieneMismaCategoria(Categoria.TORSO)) && 
            	prendasFinales.stream().anyMatch(p->p.TieneMismaCategoria(Categoria.PARTEINFERIOR))  && 
                prendasFinales.stream().anyMatch(p->p.TieneMismaCategoria(Categoria.CALZADO))) {
        		prendasFinales.forEach(prenda -> atuendoSugerido.agregarPrenda(prenda));
        		return atuendoSugerido;
        	}else {
                System.out.println("ATENCION: NO SE ENCONTRO NINGUNA SUGERENCIA ACORDE A SUS NECESIDADES");
            }

        return null;
    }
    
	public List<Prenda> ObtenerPrendasNoSuperponibles(List<Prenda> prendas){
		List<Prenda> prendasNoSuperponibles=new ArrayList<Prenda>();
		for(int i=0; i<prendas.size();i++) {
			Prenda prendaBase=prendas.get(i);
			if(!prendas.stream().anyMatch(p->p.tieneSuperponibleA(prendaBase))) {			
				prendasNoSuperponibles.add(prendaBase);
			}
		}
		return prendasNoSuperponibles;
	}
	public List<Prenda> obtenerPrendasNoSuperponiblesDeDistintasCategorias(List<Prenda> prendas){
		List<Prenda> prendasNoSuperponiblesDistintas=new ArrayList<Prenda>();
		for (Prenda prenda : prendas) {
			if(!(prendasNoSuperponiblesDistintas.stream().anyMatch(p->!(p==prenda) && p.getCategoria().compareTo(prenda.getCategoria())==0)) ) {
				prendasNoSuperponiblesDistintas.add(prenda);
			}	
		}
		return prendasNoSuperponiblesDistintas;
	}
	
	
    public Atuendo sugerirAtuendoPorGuardaropa2(Pronostico pronostico, Estilo estilo) {
    	List<Prenda> prendas = this.getPrendas();
    	List<Prenda> prendasAtuendo=new ArrayList<Prenda>();
    	Prenda prendaaux;
    	if(!prendas.isEmpty()) {
    		Collections.shuffle(prendas);
        	Optional<Prenda> prenda=prendas.stream().filter(p->p.tengoEstilo(estilo)).findFirst();
        	if(prenda.isPresent()) {
        		prendaaux=prenda.get();
        		
	        	List<TipoPrenda> tipoDePrendas=prenda.get().getTipoDePrenda().getSuperponibles().stream().collect(Collectors.toList());
	        	List<String> categoriasObtenidas=new ArrayList<String>();
	        	
	        	for (TipoPrenda tipoPrenda : tipoDePrendas) {
	        		if(!categoriasObtenidas.contains(tipoPrenda.categoria())) {
	        			categoriasObtenidas.add(tipoPrenda.categoria());
	        		}
				}
	        	for(int i=0;i<categoriasObtenidas.size();i++) {
	        		Prenda prendaObtenida=obtengoPrenda(categoriasObtenidas.get(i),prenda.get(),tipoDePrendas);
	        		if (prendaObtenida!=null) {
	        			prendasAtuendo.add(prendaObtenida);
	        		}
	        	}
	        	prendasAtuendo.add(prenda.get());
	        	if(!prendasAtuendo.stream().anyMatch(p->p.getCategoria().compareTo(Categoria.CALZADO.getCategoria())==0)) {
	        		Prenda prendaObtenida=obtengoPrenda(Categoria.CALZADO.getCategoria(),prenda.get(),estilo);
	        		if (prendaObtenida!=null) {
	        			prendasAtuendo.add(prendaObtenida);
	        		}
	        	}
        	}
    	}
//que tenga minimamente estas tres categorias   
    	if(!prendasAtuendo.isEmpty() && prendasAtuendo.stream().anyMatch(p->p.getCategoria().compareTo(Categoria.TORSO.getCategoria())==0) && 
        	prendasAtuendo.stream().anyMatch(p->p.getCategoria().compareTo(Categoria.PARTEINFERIOR.getCategoria())==0) &&  
        	prendasAtuendo.stream().anyMatch(p->p.TieneMismaCategoria(Categoria.CALZADO))) {
    		Atuendo atuendoSugerido=new Atuendo(prendasAtuendo);
    		
    		return atuendoSugerido;
    	}else {
            System.out.println("ATENCION: NO SE ENCONTRO NINGUNA SUGERENCIA ACORDE A SUS NECESIDADES    ");
        }

        return null;
    }
    public Prenda obtengoPrenda(String categoria, Prenda prenda,List<TipoPrenda> tPrendas) {
    	List<Prenda> prendasDelGuardaropa=this.getPrendas().stream().filter(p->p.getCategoria().equals(categoria) && tPrendas.stream().anyMatch(tprenda->tprenda==(p.getTipoDePrenda()))).collect(Collectors.toList());
    	if(!prendasDelGuardaropa.isEmpty()) {
	    	Collections.shuffle(prendasDelGuardaropa);
	    	return prendasDelGuardaropa.get(0);
    	}
		return null;
	}
    public Prenda obtengoPrenda(String categoria, Prenda prenda,Estilo estilo) {
    	List<Prenda> prendasDelGuardaropa=this.getPrendas().stream().filter(p->p.getCategoria().equals(categoria) && p.tengoEstilo(estilo)).collect(Collectors.toList());
    	if(!prendasDelGuardaropa.isEmpty()) {
	    	Collections.shuffle(prendasDelGuardaropa);
	    	return prendasDelGuardaropa.get(0);
    	}
		return null;
	}
	public List<String> tiposCategorias(List<Prenda> prendas){ return prendas.stream().map(prenda -> prenda.getCategoria()).distinct().collect(Collectors.toList()); }
    public int cantidadCategorioas(List<Prenda> prendas){ return this.tiposCategorias(prendas).size(); }
    public int cantidadAtuendosGenerados() { return this.atuendosMostrados.size(); }
    public void bloquearPrenda(int i) {	this.prendas.get(i).bloquearse(); }
    public int cantidadDePrendas(){ return this.prendas.size(); }
    public void bloquearExcedente(int excedente) { if(this.cantidadDePrendas()>excedente) { for(int j= this.cantidadDePrendas()-1; j>=excedente; j--) { this.bloquearPrenda(j);} } }
    public void desbloquearTodo() { this.prendas.forEach(prenda->prenda.desbloquearse()); }
    public boolean puedoCrear(Atuendo atuendo){ return atuendo.getPrendas().stream().allMatch(prenda -> this.prendas.stream().anyMatch(prenda1 -> prenda.somosIguales(prenda1))); }
    public boolean atuendoMostrado(Atuendo atuendo){ return this.atuendosMostrados.stream().anyMatch(atuendo1 -> atuendo.somosIguales(atuendo1)); }
    public int cantidadDePrendasEnCategoria(String categoria) { return this.prendas.stream().filter(prenda -> prenda.getCategoria() == categoria).collect(Collectors.toList()).size(); }

    public boolean esDeIdUsuario(Usuario usuario) {
        return this.getUsuarios().stream().anyMatch(usu -> usu.getId_usuario()==usuario.getId_usuario());
    }
    //Getters-y-Setters------------------------------------------------
    public List<Atuendo> getAtuendosMostrados() {
        return atuendosMostrados;
    }
    public void setAtuendosMostrados(List<Atuendo> atuendosMostrados) {
        this.atuendosMostrados = atuendosMostrados;
    }
    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Prenda> getPrendas() { return this.prendas; }
    public void agregarPrenda(Prenda prenda) { this.prendas.add(prenda); }
    public void agregarAtuendo(Atuendo atuendo) { this.atuendosMostrados.add(atuendo); }
    public void agregarPrendas(ArrayList<Prenda> prendas){ prendas.forEach(prenda -> this.agregarPrenda(prenda)); }
    public void agregarUsuario(Usuario usuario1) { this.usuarios.add(usuario1); }
    public void quitarUsuario(Usuario usuario1) { this.usuarios = this.usuarios.stream().filter(usuario -> usuario != usuario1).collect(Collectors.toList()); }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getId_guardaropa() {
        return id_guardaropa;
    }
    //Constructores--------------------------------------------
    public Guardaropa(ArrayList<Prenda> prendas) {
        this.prendas = prendas;
        this.atuendosMostrados = new ArrayList<Atuendo>();
        this.usuarios = new ArrayList<Usuario>();
    }
    public Guardaropa(){
        this.prendas = new ArrayList<Prenda>();
        this.atuendosMostrados = new ArrayList<Atuendo>();
        this.usuarios = new ArrayList<Usuario>();
    }
    public Guardaropa(String nombre, String descripcion){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.prendas = new ArrayList<Prenda>();
        this.atuendosMostrados = new ArrayList<Atuendo>();
        this.usuarios = new ArrayList<Usuario>();
    }
    //observers-methods----------------------------------------
    @Override
    public void updateAceptarSugerencia(Atuendo atuendo) {
        if(this.puedoCrear(atuendo)){
            if(this.atuendoMostrado(atuendo)){
                this.atuendosMostrados.stream().filter(atuendo1 -> this.atuendoMostrado(atuendo)).collect(Collectors.toList()).get(0).agregarUsabilidad();
            }else{
                this.atuendosMostrados.add(atuendo);
                this.atuendosMostrados.stream().filter(atuendo1 -> this.atuendoMostrado(atuendo)).collect(Collectors.toList()).get(0).agregarUsabilidad();
            }
        }
    }
    @Override
    public void downdateAceptarSugerencia(Atuendo atuendo, Atuendo atuendoViejo) {

    }
    @Override
    public void updateRechazarSugerencia(Atuendo atuendo) {

    }
    @Override
    public void downdateRechazarSugerencia(Atuendo atuendo, Atuendo atuendoViejo) {

    }

}