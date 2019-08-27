package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.AlMenosUnColor;
import ar.utn.dds.excepciones.SoloTieneUnColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//Para las imagenes:

//

public class Prenda{
    private ArrayList<Color> colores;
    private TipoPrenda tipoPrenda;
    private String nombrePrenda;
    private Material material;
    private Estilo estilo;
    private Boolean disponibilidad;
    private ArrayList<String> imagen;
    private Sexo sexo;
    private int nivelDeCalor;

    public boolean masculino(String sexo){ return this.sexo.masculino(sexo); }
    public boolean femenino(String sexo){ return this.sexo.femenino(sexo); }
    public Sexo sexo(){ return sexo; }
    public int nivelDeCalor(){
        return this.nivelDeCalor;
    }
    public boolean somosIguales(Prenda prenda){
        return this.hashCode() == prenda.hashCode();
    }
    public Estilo getEstilo(){
        return estilo;
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
    Prenda(TipoPrenda tipoPrenda, String nombrePrenda, ArrayList<Color> colores, Material material){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colores = colores;
        this.material = material;
        this.disponibilidad = true;
        this.estilo = Estilo.NORMAL;
        this.tipoPrenda.perteneceMaterial(material);
        this.imagen= new ArrayList<String>();
        if(colores.isEmpty()){
            throw new AlMenosUnColor();
        }
    }
    Prenda(TipoPrenda tipoPrenda, String nombrePrenda, ArrayList<Color> colores, Material material, Estilo estilo){
        this.tipoPrenda = tipoPrenda;
        this.nombrePrenda = nombrePrenda;
        this.colores = colores;
        this.material = material;
        this.disponibilidad = true;
        this.estilo = estilo;
        this.imagen= new ArrayList<String>();
        this.tipoPrenda.perteneceMaterial(material);
        if(colores.isEmpty()){
            throw new AlMenosUnColor();
        }
    }
   
    public String tipo(){
        return this.tipoPrenda.tipo();
    }
    public String getNombrePrenda(){
        return nombrePrenda;
    }
    public Material getMaterial(){
        return material;
    }
    public Color colorPrimario(){
        return this.colores.get(0);
    }
    public  Color colorSecundario(){
        if (this.colores.size() == 1) {
            throw new SoloTieneUnColor();
        }
        return this.colores.get(1);
    }    
    @Override
    public int hashCode(){
        return Objects.hash(colores, tipoPrenda, nombrePrenda, material);
    }
    public String categoria() {
        return this.tipoPrenda.categoria();
    }
    public boolean esSuperponible(Prenda prenda){
       return this.tipoPrenda.esSuperponible(prenda.tipoDePrenda());
    }
    public TipoPrenda tipoDePrenda() {
        return this.tipoPrenda;
    }

   //----Imagen---------------------
    
    public String unaImagen(int path){ //devuelve una imagen
        return this.imagen.get(path);
    }
    
    public List<String> imagenes(){ //devuelve todas las imagenes
        return this.imagen.stream().collect(Collectors.toList());
    }
    
    public void cargarImagen(String path, Fotografo normalizador) {
    	String destino= "Imagenes/"+path.replace('/', '-').replaceAll(".jpg","")+".jpg";//nombre para la imagen en la carpeta donde se almacena
    	//normalizarUnaImagen(path,destino, 600,600,"jpg");
    	normalizador.normalizarUnaImagen(path, destino, 600, 600, ".jpg"); //el adminImg normaliza la imagen 
    	this.imagen.add(destino); 
    	
        
    }

    public int cantidadSuperponibles() {
        if ( 0 !=this.tipoPrenda.cantidadSuperponibles()) {
            return this.tipoPrenda.cantidadSuperponibles();
        }else{
            return 0;
        }
    }
}
