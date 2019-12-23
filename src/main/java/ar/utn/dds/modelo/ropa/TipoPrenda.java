package ar.utn.dds.modelo.ropa;

import ar.utn.dds.excepciones.ElMaterialNoPerteneceALaPrenda;
import ar.utn.dds.modelo.clases.Fotografo;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name="tipoPrenda")
public class TipoPrenda {
	
	@Id
	@GeneratedValue
	private long id_tipoPrenda;
	
	@Column(name = "tipo")
    private String tipo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="id_categoria")
    private Categoria categoria;
	
	@ElementCollection(targetClass = Material.class)
    @CollectionTable(name="materiales", joinColumns=@JoinColumn(name="id_tipoPrenda"))
    @Column(nullable=false,name="material")
    @Enumerated(EnumType.STRING)
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
 //   @Override
//    public int hashCode(){
//        return Objects.hash(this.tipo, this.categoria, this.materiales, this.superponibles);
//    }
    
    public boolean mismoTipoDePrenda(TipoPrenda tipoPrenda){
    	return this.categoria==this.categoria;
      //  return this.hashCode() == tipoPrenda.hashCode();
    }
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id_tipoPrenda ^ (id_tipoPrenda >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TipoPrenda other = (TipoPrenda) obj;
		if (id_tipoPrenda != other.id_tipoPrenda) {
			return false;
		}
		return true;
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
    public TipoPrenda(Categoria categoria, String tipo, Set<Material> materiales, Set<TipoPrenda> superponibles, String pathImg){
        this.tipo = tipo;
        this.materiales = materiales;
        this.categoria = categoria;
        this.superponibles = superponibles;
    }
    public TipoPrenda() {
    	
    	this.superponibles = new HashSet<TipoPrenda>();
    	
    }

}
