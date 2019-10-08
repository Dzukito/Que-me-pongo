package ar.utn.dds.modelo;

import ar.utn.dds.excepciones.ElMaterialNoPerteneceALaPrenda;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.persistence.*;

@Entity
@Table(name = "TipoPrenda")
public class TipoPrenda {
    @Column(name = "tipo")
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;
    @OneToMany(mappedBy = "TipoPrenda", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Material> materiales;
    @ManyToOne
    @JoinColumn(name = "id_fotografo", referencedColumnName = "id_fotografo")
    private Fotografo fotografo;
    @OneToMany(mappedBy = "TipoPrenda", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
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
