import java.util.ArrayList;

public class AppPrincipal {

	public static void main(String[] args) {

		//POBLACION INICIAL de n Individuos
		Individuo[] poblacion = new Individuo[Constantes.POBLACION_TOTAL];
		
		for (int i = 0; i < poblacion.length; i++) {
			poblacion[i] = new Individuo();
		}
		
		int numeroGeneracion = 0;
		
		do{
			numeroGeneracion++;
			
			//EVALUAR SOLUCION
			for (int i = 0; i < poblacion.length; i++) {
				System.out.print("\nGen [" + numeroGeneracion + "] ");
				System.out.print(" | Individuo: [" + i + "]: " + poblacion[i].obtenerFenotipo());
				System.out.print(" | Valor: " + poblacion[i].obtenerNumeroDecodificado());
				System.out.print(" | fitness: " + poblacion[i].fitness());
			}
			
			//SELECCION DE PADRES
			ArrayList<Individuo> padresPool = new ArrayList<Individuo>();
			for (int i = 0; i < poblacion.length; i++) {
				double valorNormalizado = Util.normalizar(poblacion[i].fitness(),poblacion);
				//System.out.println("valorNormalizado: " + valorNormalizado);

				//Minimizar
				if (Constantes.MAXMIN.equals("MIN")){
					valorNormalizado = 1 - valorNormalizado;
					//System.out.println("Minimizar, valorNormalizado: " + valorNormalizado);
				}
					
				int n = (int)(valorNormalizado * 100);
				for (int j = 0; j < n; j++) {
					padresPool.add(poblacion[i]);
				}
			}
			
			//REPRODUCCION
			for (int i = 0; i < poblacion.length; i++) {
				int a = (int)(Util.generarNumeroAleatorio(0,padresPool.size()-1));
				int b = (int)(Util.generarNumeroAleatorio(0,padresPool.size()-1));
				Individuo padreA = padresPool.get(a);
				Individuo padreB = padresPool.get(b);
	
				//CRUCE - CROSSOVER
				Individuo hijo = padreA.cruzar(padreB);
				//System.out.println("Cruce: " + hijo.obtenerFenotipo());
	
				//MUTACION - MUTATION
				hijo.mutar(Constantes.RATE_MUTACION);
				//System.out.println("Mutado: " + hijo.obtenerFenotipo());
				
				poblacion[i] = hijo;
			}
		}while(Constantes.ITERACIONES_A_SIMULAR != numeroGeneracion);
		
		if (Constantes.MAXMIN.equals("MIN")){
			System.out.println("\n\nMinimo: x=" + Util.minimo(poblacion).obtenerNumeroDecodificado());
		}else if (Constantes.MAXMIN.equals("MAX")){
			System.out.println("\n\nMaximo: x=" + Util.maximo(poblacion).obtenerNumeroDecodificado());
		}

	}

}
