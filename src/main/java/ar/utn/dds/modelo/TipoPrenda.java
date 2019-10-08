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
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_categoria",referencedColumnName = "id")
    private Categoria categoria;
	
	@ManyToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="map_tipoPrenda_material", joinColumns={@JoinColumn(name="id_tipoPrenda")}, inverseJoinColumns={@JoinColumn(name="id_material")})
    private Set<Material> materiales;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_fotografo",referencedColumnName = "id")
    private Fotografo fotografo;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="id_superponibles",referencedColumnName= "id")
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

    //Constructores-------------------------------------------------------------
    TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales){
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

}
