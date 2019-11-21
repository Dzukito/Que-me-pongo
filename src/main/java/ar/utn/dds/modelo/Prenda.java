package ar.utn.dds.modelo;

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
	
	@ElementCollection(targetClass = Color.class)
    @CollectionTable(name="colores", joinColumns=@JoinColumn(name="id_prenda"))
    @Column(name="color")
	@Enumerated
    private List<Color> colores;
    
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="id_tipoPrenda")
    private TipoPrenda tipoPrenda;
    
    @Column(name = "nombrePrenda")
    private String nombrePrenda;
    
    @Enumerated
    @Column(name="material")
    private Material material;
    
    @ElementCollection(targetClass = Estilo.class)
    @CollectionTable(name="estilos", joinColumns=@JoinColumn(name="id_prenda"))
    @Column(name="estilo")
	@Enumerated
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
        return Objects.hash(colores, tipoPrenda, nombrePrenda, material);
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
        return this.colores.get(0);
    }
    public  Color getColorSecundario(){
        if (this.colores.size() == 1) {
            throw new SoloTieneUnColor();
        }
        return this.colores.get(1);
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

    Prenda(TipoPrenda tipoPrenda, String nombrePrenda, List<Color> colores, Material material){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colores = colores;
        this.material = material;
        this.disponibilidad = true;
        this.estilos = new ArrayList<Estilo>(Arrays.asList(Estilo.NORMAL));
        this.tipoPrenda.perteneceMaterial(material);
        this.fotografo= new Fotografo();
        if(colores.isEmpty()){
            throw new AlMenosUnColor();
        }
    }
    Prenda(TipoPrenda tipoPrenda, String nombrePrenda, List<Color> colores, Material material, Estilo estilo){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colores = colores;
        this.material = material;
        this.disponibilidad = true;
        this.estilos = new ArrayList<Estilo>();
        this.estilos.add(estilo);
        this.fotografo= new Fotografo();
        this.tipoPrenda.perteneceMaterial(material);
        if(colores.isEmpty()){
            throw new AlMenosUnColor();
        }
    }
}
