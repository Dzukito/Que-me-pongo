package ar.utn.dds.spark.utils;

import java.util.List;
import java.util.stream.Collectors;

import ar.utn.dds.modelo.clases.Atuendo;
import ar.utn.dds.modelo.clases.CalificacionAtuendo;
import ar.utn.dds.modelo.clases.Usuario;
import ar.utn.dds.modelo.ropa.Prenda;


public class AtuendoCalifVista {
	Long id_atuendo;
	long usuarioid;
	int calificacion1;
	int calificacion2;
	int calificacion3;
	int calificacion4;
	int calificacion5;
	List<Prenda> prendas;

	public AtuendoCalifVista(Atuendo atuendo, Usuario usuario) {
		this.prendas = atuendo.getPrendas();
		this.id_atuendo = atuendo.getId_atuendo();
		this.usuarioid = usuario.getId_usuario();
		
		List<CalificacionAtuendo> calificacionesFinal=atuendo.getCalificaciones().stream().filter(c1->c1.getAtuendo().getId_atuendo()== this.id_atuendo && c1.getUsuario().getId_usuario()==this.usuarioid).collect(Collectors.toList());
		if(!calificacionesFinal.isEmpty()) {
			CalificacionAtuendo calificacionFinal = calificacionesFinal.get(0);
			if (calificacionFinal.getCalificacion()==1) {
				this.setCalificacion1(calificacionFinal.getCalificacion());
			}
			if (calificacionFinal.getCalificacion()==2) {
				this.setCalificacion2(calificacionFinal.getCalificacion());
			}
			if (calificacionFinal.getCalificacion()==3) {
				this.setCalificacion3(calificacionFinal.getCalificacion());
			}
			if (calificacionFinal.getCalificacion()==4) {
				this.setCalificacion4(calificacionFinal.getCalificacion());
			}
			if (calificacionFinal.getCalificacion()==5) {
				this.setCalificacion5(calificacionFinal.getCalificacion());
			}
		}
	}

	public Long getId_atuendo() {
		return id_atuendo;
	}

	public void setId_atuendo(Long id_atuendo) {
		this.id_atuendo = id_atuendo;
	}

	public long getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(long usuarioid) {
		this.usuarioid = usuarioid;
	}

	public int getCalificacion1() {
		return calificacion1;
	}

	public void setCalificacion1(int calificacion1) {
		this.calificacion1 = calificacion1;
	}

	public int getCalificacion2() {
		return calificacion2;
	}

	public void setCalificacion2(int calificacion2) {
		this.calificacion2 = calificacion2;
	}

	public int getCalificacion3() {
		return calificacion3;
	}

	public void setCalificacion3(int calificacion3) {
		this.calificacion3 = calificacion3;
	}

	public int getCalificacion4() {
		return calificacion4;
	}

	public void setCalificacion4(int calificacion4) {
		this.calificacion4 = calificacion4;
	}

	public int getCalificacion5() {
		return calificacion5;
	}

	public void setCalificacion5(int calificacion5) {
		this.calificacion5 = calificacion5;
	}

	public List<Prenda> getPrendas() {
		return prendas;
	}

	public void setPrendas(List<Prenda> prendas) {
		this.prendas = prendas;
	}
	

}
