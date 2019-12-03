package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.ElMaterialNoPerteneceALaPrenda;
import javax.persistence.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;


@Entity
@Table(name="tipoPrenda")
public class TipoPrenda {
	
	@Id
	@GeneratedValue
	private long id_tipoPrenda;
	
	@Column(name = "tipo")
    private String tipo;
	
	@Enumerated
	@Column(name="id_categoria")
    private Categoria categoria;
	
	@ElementCollection(targetClass = Material.class)
    @CollectionTable(name="materiales", joinColumns=@JoinColumn(name="id_tipoPrenda"))
    @Column(name="material")
	@Enumerated
    private Set<Material> materiales;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fotografo")
    private Fotografo fotografo;
	
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name="superponible", joinColumns={@JoinColumn(name="id_tipoPrenda")}, inverseJoinColumns={@JoinColumn(name="id_superponible")})
    private Set<TipoPrenda> superponibles;

    //Metodos-privados---------------------------------------------------------
    //Metodos-publicos---------------------------------------------------------
    public Fotografo getFotografo() { return fotografo; }
    @Override
    public int hashCode(){
        return Objects.hash(this.tipo, this.categoria, this.materiales, this.superponibles);
    }
    public boolean mismoTipoDePrenda(TipoPrenda tipoPrenda){
        return this.hashCode() == tipoPrenda.hashCode();
    }
    public boolean esSuperponible(TipoPrenda prenda){
        return superponibles.contains(prenda);
    }
    public Boolean perteneceMaterial(Material material) {
        if(!this.materiales().contains(material)) {
            throw new ElMaterialNoPerteneceALaPrenda();
        }
        return true;
    }

    //Getters-y-Setters---------------------------------------------------------
    public Categoria getCategoria() {
        return categoria;
    }
    public String categoria(){
        return this.categoria.getCategoria();
    }
    public String tipo(){
        return this.tipo;
    }
    public Set<Material> materiales() {
        return materiales;
    }
    public int cantidadSuperponibles() {
        return  this.superponibles.size();
    }

    public long getId_tipoPrenda() {
		return id_tipoPrenda;
	}
	public String getTipo() {
		return tipo;
	}
	public Set<Material> getMateriales() {
		return materiales;
	}
	public Set<TipoPrenda> getSuperponibles() {
		return superponibles;
	}
	public void setId_tipoPrenda(long id_tipoPrenda) {
		this.id_tipoPrenda = id_tipoPrenda;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void setMateriales(Set<Material> materiales) {
		this.materiales = materiales;
	}
	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}
	public void setSuperponibles(Set<TipoPrenda> superponibles) {
		this.superponibles = superponibles;
	}
    //Constructores-------------------------------------------------------------
    public TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales){
        this.tipo = tipo;
        this.materiales = materiales;
        this.categoria = categoria;
        this.superponibles = new HashSet<TipoPrenda>();
    }
    TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales,Set<TipoPrenda> superponibles){
        this.tipo = tipo;
        this.materiales = materiales;
        this.categoria = categoria;
        this.superponibles = superponibles;
    }
    TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales,Set<TipoPrenda> superponibles,String pathImg){
        this.tipo = tipo;
        this.materiales = materiales;
        this.categoria = categoria;
        this.superponibles = superponibles;
    }
    public TipoPrenda() {
    	
    	this.superponibles = new HashSet<TipoPrenda>();
    	
    }

}
