
public class Util {

	public static int generarNumeroAleatorio(int numInicial, int numFinal){
		double randomDouble = Math.random();
		double numAleatorio = numInicial + randomDouble * (numFinal-numInicial);
		return (int)numAleatorio;
	}
	
	public static double normalizar(double valor, Individuo[] poblacion) {
		double min = minimo(poblacion).fitness();
		double max = maximo(poblacion).fitness();
        return (valor - min) / (max - min);
    }
	
	public static Individuo minimo(Individuo[] poblacion) {
		double min = 99999.0;
		int minIndex = 0;
		for (int i = 0; i < poblacion.length; i++) {
			if( poblacion[i].fitness() < min ){
				min = poblacion[i].fitness();
				minIndex = i;
			}
		}
        return poblacion[minIndex];
    }

	public static Individuo maximo(Individuo[] poblacion) {
		double max = -99999.0; 
		int maxIndex = 0;
		for (int i = 0; i < poblacion.length; i++) {
			if( poblacion[i].fitness() > max ){
				max = poblacion[i].fitness();
				maxIndex = i;
			}
		}
        return poblacion[maxIndex];
    }

}
