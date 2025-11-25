package threadsafe.exemplo5.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import threadsafe.exemplo5.entity.bancoa.ClienteA;
import threadsafe.exemplo5.repository.bancoa.ClienteARepository;

@Service
public class MonoThreadService {
	
	@Autowired
	private ClienteARepository clienteARepository;
	
	/* Simula uma determinada quantidade de processo sendo executado em fila */
	/**
	 * O método executarSequencial(int qtd) processa uma quantidade de itens de
	 * forma sequencial, ou seja, um por vez, em uma única thread. Ele registra o
	 * instante inicial, percorre os itens chamando processar(i) para cada um, mede
	 * o tempo total de execução usando Duration.between(...) e retorna um objeto
	 * Resultado contendo a quantidade de itens processados, o tempo gasto e o modo
	 * de execução “sequencial”.
	 * 
	 * @param qtd
	 * @return Resultado
	 */
	public Resultado executarSequencial(int qtd) {

		/* Inicio do processo */
		Instant ini = Instant.now();

		List<String> itens = new ArrayList<>();
		for (int i = 1; i <= qtd; i++) {
			itens.add(processar(i));
		}

		/* Fim do processo */
		long ms = Duration.between(ini, Instant.now()).toMillis();

		return new Resultado(qtd, ms, "sequencial");
	}

	/**
	 * O método processar(int item) simula uma tarefa sendo executada de forma
	 * sequencial (mono thread). Ele exibe no console qual item está sendo
	 * processado, pausa a execução por 100 milissegundos com Thread.sleep(100) para
	 * representar um trabalho demorado e, ao final, retorna uma mensagem "OK:" +
	 * item indicando que o processamento daquele item foi concluído.
	 * 
	 * @param item
	 * @return String
	 */
	private String processar(int item) {
		try {
			/* Simulando algumn processamento de algo */
			System.out.println(item + " -> Realizando processamento Mono Thread");
			
			ClienteA a = new ClienteA();
			a.setEmail(item + "@gmail.com");
			a.setNome("user"+item);
			
			clienteARepository.saveAndFlush(a);
			
			Thread.sleep(100);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return "OK:" + item;
	}

}
