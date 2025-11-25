package threadsafe.exemplo3;

import java.util.concurrent.atomic.AtomicInteger;

public class ContadorAtomic {

	//OBS: A atomicidade garante que uma transação seja tratada 
	// como uma única unidade indivisível de trabalho 
	private AtomicInteger valor = new AtomicInteger(0);

	public void incrementar() {
		valor.incrementAndGet();
	}

	public int getValor() {
		return valor.get();
	}

}
