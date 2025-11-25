package threadsafe.exemplo5.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import threadsafe.exemplo5.entity.bancoa.ClienteA;
import threadsafe.exemplo5.repository.bancoa.ClienteARepository;

@Service
public class AsyncMultihreadService {
	
	@Autowired
	private ClienteARepository clienteARepository;

	/**
	 * O método executarParalelo(int qtd) executa várias tarefas em paralelo e mede
	 * o tempo total do processamento. Ele cria uma lista de CompletableFuture
	 * chamando processarAsync(i) para cada item, o que dispara várias execuções
	 * assíncronas simultaneamente. Depois usa CompletableFuture.allOf(...).join()
	 * para aguardar todas as tarefas terminarem, calcula o tempo decorrido e
	 * retorna um objeto Resultado com a quantidade de itens processados, o tempo
	 * total e o modo de execução (“async-paralelo”).
	 * 
	 * @param qtd
	 * @return Resultado
	 */
	public Resultado executarParalelo(int qtd) {

		Instant ini = Instant.now();

		List<CompletableFuture<String>> futures = new ArrayList<>(qtd);
		for (int i = 1; i <= qtd; i++) {
			futures.add(processarAsync(i));
		}

		// Aguarda todos terminarem
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
		
		

		long ms = Duration.between(ini, Instant.now()).toMillis();
		return new Resultado(qtd, ms, "async-paralelo");

	}

	/**
	 * Esse método processarAsync(int item) executa o processamento de forma
	 * assíncrona e paralela usando o executor nomeado "appExecutor". Ele cria uma
	 * tarefa assíncrona com CompletableFuture.supplyAsync(), que chama o método
	 * processar(item) em uma thread separada, sem bloquear a execução principal. O
	 * retorno CompletableFuture<String> permite acompanhar o resultado da tarefa
	 * quando ela for concluída.
	 * 
	 * @param item
	 * @return CompletableFuture
	 */
	@Async("appExecutor")
	private CompletableFuture<String> processarAsync(int item) {
		return CompletableFuture.supplyAsync(() -> processar(item));
	}

	/**
	 * O método processar(int item) simula o processamento de uma tarefa em
	 * paralelo, exibindo no console qual item está sendo executado. Ele pausa a
	 * thread por 100 ms com Thread.sleep(100) para representar um trabalho
	 * demorado. Por fim, retorna uma mensagem indicando o item concluído e o nome
	 * da thread que o processou.
	 * 
	 * @param item
	 * @return String
	 */
	private String processar(int item) {
			System.out.println(item + " -> Realizando processamento Async Multi Thread");
			
			ClienteA a = new ClienteA();
			a.setEmail(item + "@gmail.com");
			a.setNome("user"+item);
			
			clienteARepository.saveAndFlush(a);
			
		return "OK:" + item + " - " + Thread.currentThread().getName();
	}

}
