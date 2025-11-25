package threadsafe.exemplo4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListaThreadSafe {
	public static void main(String[] args) throws InterruptedException {
		
		/*Cria uma copia interna garantindo leitura correta*/
		List<String> lista = new CopyOnWriteArrayList<>(); 
		
		/*Resultado Final fica errado*/
		//List<String> lista2 = new ArrayList<>(); 

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++)
				lista.add("A" + i);
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++)
				lista.add("B" + i);
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println("Tamanho da lista deve ser 2.000 : -> " + lista.size());
	}
}
