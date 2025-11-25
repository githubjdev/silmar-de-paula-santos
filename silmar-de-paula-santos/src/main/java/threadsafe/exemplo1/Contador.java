package threadsafe.exemplo1;

public class Contador {

	  private int valor = 0;

	    public void incrementar() {
	        valor++; // não é operação atômica ou ThreadSafe!
	    }

	    public int getValor() {
	        return valor;
	    }
}
