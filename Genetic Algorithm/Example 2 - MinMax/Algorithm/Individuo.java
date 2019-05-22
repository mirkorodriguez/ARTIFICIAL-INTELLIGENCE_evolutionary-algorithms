
public class Individuo {

	int[] genes = new int[Constantes.LONGITUD_CROMOSOMA];
	int numeroAleatorio;
	double numeroDecodificado;

	Individuo() {
		int rango = Constantes.RANGO[1] - Constantes.RANGO[0];
		numeroAleatorio = Util.generarNumeroAleatorio(0, (int)(rango*Math.pow(10, Constantes.NUMERO_DECIMALES)));
        codificarGenes();
	}

	public void codificarGenes(){
		//Codificar
		String numeroBinario = Integer.toString(numeroAleatorio,2);

		int delta = Constantes.LONGITUD_CROMOSOMA - numeroBinario.length();
		for (int i = 0; i < genes.length; i++) {
			if(i < delta){
				genes[i] = 0;
			}else{
				genes[i] = Character.getNumericValue(numeroBinario.charAt(i - delta));
			}
		}
	}

	public void decodificarGenes(){
		numeroDecodificado = Constantes.RANGO[0] + Math.pow(10, -1*Constantes.NUMERO_DECIMALES)*numeroAleatorio;
	}

	public double f(double x) {
		// Funci�n matem�tica
		//return Math.pow(x, 3) - 3*x + 2;
		//return Math.pow(x, 3) - 6*Math.pow(x,2) + 9*x -3;
		//return Math.sin(x + 2) + 2*Math.cos(2*x);
		//return 2*Math.cos(2*x);
		return Math.sin(x+2);
	}


	public double fitness() {
		this.decodificarGenes();
		return ((double)f(numeroDecodificado));
	}

	public double obtenerNumeroDecodificado(){
		this.decodificarGenes();
		return numeroDecodificado;
	}

	public String obtenerFenotipo(){
		String fenotipo = "";
		for (int i = 0; i < genes.length; i++) {
			fenotipo += genes[i];
		}
		return fenotipo;
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
				genes[i] = (genes[i] == 0) ? 1 : 0;
			}
		}

	}
}
