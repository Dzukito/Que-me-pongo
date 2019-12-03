package ar.utn.dds.modelo;

import ar.utn.dds.controllers.LoginController;
import ar.utn.dds.excepciones.AlMenosUnColor;
import ar.utn.dds.excepciones.SoloTieneUnColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name="prenda")
public class Prenda{
	
	@Id
	@GeneratedValue
	private long id_prenda;
	
	/*
	@ElementCollection(targetClass = Color.class)
    @CollectionTable(name="colores", joinColumns=@JoinColumn(name="id_prenda"))
    @Column(name="color")
	@Enumerated(EnumType.STRING)
    private List<Color> colores;*/
	
	@Column(name="colorPrimario")
	@Enumerated(EnumType.STRING)
	private Color colorPrimario;
	
	@Column(name="colorSecundario")
	@Enumerated(EnumType.STRING)
	private Color colorSecundario;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="id_tipoPrenda")
    private TipoPrenda tipoPrenda;
    
    @Column(name = "nombrePrenda")
    private String nombrePrenda;
    
    @Enumerated(EnumType.STRING)
    @Column(name="id_material")
    private Material material;
    
    @ElementCollection(targetClass = Estilo.class)
    @CollectionTable(name="estilos", joinColumns=@JoinColumn(name="id_prenda"))
    @Column(name="estilo")
	@Enumerated(EnumType.STRING)
    private List<Estilo> estilos;
    
    @Column(name = "disponibilidad")
    private Boolean disponibilidad;
    
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fotografo")
    private Fotografo fotografo;

    @Column(name = "sexo")
    private String sexo;
    
    @Column(name = "nivelDeCalor")
    private int nivelDeCalor;



    //Metodos-que-no-se-usan---------------------------------
    public int cantidadSuperponibles() {
        if ( 0 !=this.tipoPrenda.cantidadSuperponibles()) {
            return this.tipoPrenda.cantidadSuperponibles();
        }else{
            return 0;
        }
    }
    //Metodos-privados---------------------------------------
    //Metodos-publicos---------------------------------------
    public boolean tieneEstilo(Estilo estilo){ return this.estilos.contains(estilo); }
    public boolean somosIguales(Prenda prenda){
        return this.hashCode() == prenda.hashCode();
    }
    public boolean disponible(){
       return this.disponibilidad;
    }
    public void bloquearse(){
        this.disponibilidad = false;
    }
    public void desbloquearse(){
        this.disponibilidad = true;
    }
    @Override
    public int hashCode(){
        return Objects.hash(colorPrimario,colorSecundario, tipoPrenda, nombrePrenda, material); //X
    }
    public boolean esSuperponible(Prenda prenda){
        return this.tipoPrenda.esSuperponible(prenda.getTipoDePrenda());
    }

    //Getters-y-Setters----------------------------------------
    public int getNivelDeCalor(){
        return this.nivelDeCalor;
    }
    public String getSexo(){ return sexo; }
    public String tipo(){
        return this.tipoPrenda.tipo();
    }
    public Color getColorPrimario(){
        return this.colorPrimario; //X
    }
    public  Color getColorSecundario(){
       /* if (this.colores.size() == 1) {
            throw new SoloTieneUnColor(); //X
        }*/
        return this.colorSecundario; //X
    }
    public TipoPrenda getTipoDePrenda() {
        return this.tipoPrenda;
    }
    public String getCategoria() {
        return this.tipoPrenda.categoria();
    }
    public String getNombrePrenda(){
        return nombrePrenda;
    }
    public Material getMaterial(){
        return material;
    }
    public Fotografo getFotografo() {
        return fotografo;
    }
    public List<Estilo> getEstilo(){
        return estilos;
    }
    public long getId_prenda() {
		return id_prenda;
	}

	public TipoPrenda getTipoPrenda() {
		return tipoPrenda;
	}
	public List<Estilo> getEstilos() {
		return estilos;
	}
	public Boolean getDisponibilidad() {
		return disponibilidad;
	}
	public void setId_prenda(long id_prenda) {
		this.id_prenda = id_prenda;
	}
	

	public void setColorPrimario(Color color) {
		this.colorPrimario= color;
	}
	public void setColorSecundario(Color color) {
		this.colorSecundario= color;
	}
	public void setTipoPrenda(TipoPrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}
	public void setNombrePrenda(String nombrePrenda) {
		this.nombrePrenda = nombrePrenda;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public void setEstilos(List<Estilo> estilos) {
		this.estilos = estilos;
	}
	public void setEstilo(Estilo estilo) {
		this.estilos.add(estilo);
	}
	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	 public void setNivelDeCalor(int nivelDeCalor) {
			this.nivelDeCalor = nivelDeCalor;
		}
	 
	
	 
	 
	//Constructores---------------------------------------------
    public Prenda(){}
    public Prenda(TipoPrenda tipoPrenda, String nombrePrenda, ArrayList<Color> listaColores, Material material) {
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colorPrimario = listaColores.get(0);
        this.colorSecundario= listaColores.get(1);
        this.material = material;
        this.disponibilidad = true;
        this.estilos = new ArrayList<Estilo>(Arrays.asList(Estilo.NORMAL));
        this.tipoPrenda.perteneceMaterial(material);
        this.fotografo= new Fotografo();
        if(colorPrimario==null){
            throw new AlMenosUnColor();
        }
    }
	public Prenda(TipoPrenda tipoPrenda, String nombrePrenda, Color color1, Color color2, Material material){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colorPrimario = color1;
        this.colorSecundario=color2;
        this.material = material;
        this.disponibilidad = true;
        this.estilos = new ArrayList<Estilo>(Arrays.asList(Estilo.NORMAL));
        this.tipoPrenda.perteneceMaterial(material);
        this.fotografo= new Fotografo();
        if(colorPrimario==null){
            throw new AlMenosUnColor();
        }
    }
    Prenda(TipoPrenda tipoPrenda, String nombrePrenda, Color color1,Color color2, Material material, Estilo estilo){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colorPrimario = color1;
        this.colorSecundario=color2;
        this.material = material;
        this.disponibilidad = true;
        this.estilos = new ArrayList<Estilo>();
        this.estilos.add(estilo);
        this.fotografo= new Fotografo();
        this.tipoPrenda.perteneceMaterial(material);
        if(colorPrimario==null && colorSecundario ==null){
            throw new AlMenosUnColor();
        }
    }
    public Prenda(TipoPrenda tipoPrenda, String nombrePrenda, Color color1, Material material){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colorPrimario = color1;
  
        this.material = material;
        this.disponibilidad = true;
        this.estilos = new ArrayList<Estilo>();
    
        this.fotografo= new Fotografo();
        this.tipoPrenda.perteneceMaterial(material);
        if(colorPrimario==null && colorSecundario ==null){
            throw new AlMenosUnColor();
        }
    }
    public Prenda(TipoPrenda remeraTop, String remeraDePandas, ArrayList<Color> blancoYNegro, Material lino, Estilo normal){

            this.disponibilidad = true;
            this.estilos = new ArrayList<Estilo>();
            this.fotografo= new Fotografo();
           
        }
}
