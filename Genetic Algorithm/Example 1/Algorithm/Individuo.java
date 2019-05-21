public class Individuo {
	
	char[] genes = new char[Constantes.OBJETIVO.length()];
	String objetivo = Constantes.OBJETIVO;
	
	Individuo() {
		for (int i = 0; i < genes.length; i++) {
			int numAleatorio = Util.generarNumeroAleatorio(32,128);
			genes[i] = (char)numAleatorio;
		}
	}
	
	public String obtenerFenotipo(){
		return new String(genes);
	}
	
	public double fitness() { 
		int score = 0;
		for (int i = 0; i < genes.length; i++) {
			if (genes[i] == objetivo.charAt(i)) { 
				score++;
			}
		}	
	 return ((double)score/(double)objetivo.length());
	}

	public Individuo cruzar(Individuo individuo) {
		Individuo hijo = new Individuo();
		int puntoMedio = (int)(Util.generarNumeroAleatorio(0,genes.length-1));
		for (int i = 0; i < genes.length; i++) {
		if (i > puntoMedio) {
			hijo.genes[i] = genes[i];
		}else 
			hijo.genes[i] = individuo.genes[i];
		}
		return hijo;
	}
	
	public void mutar(double mutationRate) {
		for (int i = 0; i < genes.length; i++) {
			double numAleatorioRate = Math.random();
			if (numAleatorioRate < mutationRate) {
				if (genes[i] != objetivo.charAt(i)){
					genes[i] = (char) Util.generarNumeroAleatorio(32,128);					
				}
			}
		}
		
	}
}
