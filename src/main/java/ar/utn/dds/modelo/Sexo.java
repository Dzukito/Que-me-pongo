package ar.utn.dds.modelo;
import javax.persistence.*;

@Entity
@Table(name="sexo")
public enum Sexo {
	FEMENINO("Femenino"),
	MASCULINO("Masculino"),
	UNISEX("Unisex");
		
	@Id
	@GeneratedValue
	private long id_sexo;
		
	@Column(name = "sexo") 
	private final String sexo;

	Sexo(String sexo){
		this.sexo = sexo;
	}
}
