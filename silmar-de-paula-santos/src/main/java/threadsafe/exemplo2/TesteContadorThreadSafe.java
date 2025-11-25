package threadsafe.exemplo2;

public class TesteContadorThreadSafe {
	public static void main(String[] args) throws InterruptedException {
		
		ContadorThreadSafe contador = new ContadorThreadSafe();

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++)
				contador.incrementar();
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++)
				contador.incrementar();
		});

		t1.start();
		t2.start();
		t1.join(); // Aguarda a finalização desta thread.
		t2.join(); // Aguarda a finalização desta thread.

		/* O VALOR CORRETO DEVERIA SER 2.000 */
		System.out.println("Valor final: " + contador.getValor() + " -- > O valor correto deveria sempre ser 2.000");
	}
}
