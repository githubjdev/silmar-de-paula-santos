package threadsafe.exemplo2;

public class ContadorThreadSafe {
	private int valor = 0;

	public synchronized void incrementar() {
		valor++;
	}

	public synchronized int getValor() {
		return valor;
	}
}
