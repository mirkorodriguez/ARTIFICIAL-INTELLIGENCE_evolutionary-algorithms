import java.util.ArrayList;

public class AppPrincipal {

	public static void main(String[] args) {

		// I. POBLACION INICIAL de n Individuos
		Individuo[] poblacion = new Individuo[Constantes.POBLACION_TOTAL];

		for (int i = 0; i < poblacion.length; i++) {
			poblacion[i] = new Individuo();
		}

		int numeroGeneracion = 0;

		do{
			numeroGeneracion++;

			//II. EVALUAR SOLUCION
			for (int i = 0; i < poblacion.length; i++) {
				System.out.println("Gen [" + numeroGeneracion + "] | Individuo: [" + i + "]: " + poblacion[i].obtenerFenotipo() + " | fitness: " + poblacion[i].fitness());
					if(evaluarObjetivo(poblacion[i])){
						System.exit(0);
					}
			}


			//II. SELECCION
			ArrayList<Individuo> padresPool = new ArrayList<Individuo>();
			for (int i = 0; i < poblacion.length; i++) {
				int n = (int)(poblacion[i].fitness() * 100);
				for (int j = 0; j < n; j++) {
					padresPool.add(poblacion[i]);
				}
			}

			//III. REPRODUCCION
			for (int i = 0; i < poblacion.length; i++) {
				int a = (int)(Util.generarNumeroAleatorio(0,padresPool.size()-1));
				int b = (int)(Util.generarNumeroAleatorio(0,padresPool.size()-1));
				Individuo padreA = padresPool.get(a);
				Individuo padreB = padresPool.get(b);

				//CRUCE (CROSSOVER)
				Individuo hijo = padreA.cruzar(padreB);
				//System.out.println("Cruce: " + hijo.obtenerFenotipo());

				//MUTACION (MUTATION)
				hijo.mutar(Constantes.RATE_MUTACION);
				//System.out.println("Mutado: " + hijo.obtenerFenotipo());

				//IV. REEMPLAZO/DESCARTE
				poblacion[i] = hijo;
			}

		}while(true);

	}

	public static boolean evaluarObjetivo(Individuo individuo){
		if(individuo.fitness() == 1.0){
			System.out.println("\nOBJETIVO ENCONTRADO: " + individuo.obtenerFenotipo());
			return true;
		}
		return false;
	}

}
