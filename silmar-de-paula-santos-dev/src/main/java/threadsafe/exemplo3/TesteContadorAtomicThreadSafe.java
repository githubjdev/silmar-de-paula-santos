package threadsafe.exemplo3;

public class TesteContadorAtomicThreadSafe {
	public static void main(String[] args) throws InterruptedException {
		ContadorAtomic contador = new ContadorAtomic();

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
		t1.join();
		t2.join();

		System.out.println("Valor final: " + contador.getValor() + " -- > O valor correto deveria sempre ser 2.000");
	}
}
