package ar.utn.dds.modelo;

public class Sensibilidad {
	private int parteSuperior=0;
	private int parteInferior=0;
	private int manos=0;
	
	Sensibilidad(int pS, int pI,int mns){
		this.parteSuperior=pS;
		this.parteInferior=pI;
		this.manos=mns;
	}
	
	public void setFriolentoSuperior() {
		this.parteSuperior--;
	}
	public void setFriolentoInferior() {
		this.parteInferior--;
	}
	public void setFriolentoManos() {
		this.manos--;
	}
	public void setCalurosoSuperior() {
		this.parteSuperior++;
	}
	public void setCalurosoInferior() {
		this.parteInferior++;
	}
	public void setCalurosoManos() {
		this.manos++;
	}
	
	public boolean satisfaceSuperior(int nivelCalor) {
		return (parteInferior>0 && nivelCalor-1<=parteInferior) || (parteInferior<0 && nivelCalor>=1-parteInferior ) || parteInferior==0;
		
	}
	public boolean satisfaceInferior(int nivelCalor) {
		return (parteInferior>0 && nivelCalor-1<=parteInferior) || (parteInferior<0 && nivelCalor>=1-parteInferior) || parteInferior==0;
	}
	public boolean satisfaceManos(int nivelCalor) {
		
		return (manos>0 && nivelCalor-1<=parteInferior)|| (manos<0 && nivelCalor>=1-parteInferior)|| manos==0;
	}
	
	//1-friolento= 1-(-frialdad)= 1+frialdad

	
}
