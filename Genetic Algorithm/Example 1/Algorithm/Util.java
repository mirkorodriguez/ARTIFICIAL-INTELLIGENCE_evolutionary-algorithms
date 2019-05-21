
public class Util {

	public static int generarNumeroAleatorio(int numInicial, int numFinal){
		double randomDouble = Math.random();
		double numAleatorio = numInicial + randomDouble * (numFinal-numInicial);
		return (int)numAleatorio;
	}
}
