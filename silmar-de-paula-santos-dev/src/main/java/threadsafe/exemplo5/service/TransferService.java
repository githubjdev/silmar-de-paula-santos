package threadsafe.exemplo5.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import threadsafe.exemplo5.entity.bancoa.ClienteA;
import threadsafe.exemplo5.entity.bancob.ClienteB;
import threadsafe.exemplo5.repository.bancoa.ClienteARepository;
import threadsafe.exemplo5.repository.bancob.ClienteBRepository;

@Service
public class TransferService {

	//Repostirórios do Banco A e Banco B
	private final ClienteARepository repoA;
	private final ClienteBRepository repoB;

	//Monta um executor de Thread
	private final ExecutorService executor = Executors.newFixedThreadPool(5);
	

	//Use a injeção por contrutor que é mais mais seguro
	public TransferService(ClienteARepository repoA, ClienteBRepository repoB) {
		this.repoA = repoA;
		this.repoB = repoB;
	}

	//Realiza a transferencia de dados do Banco A para o Banco B
	public void transferirClientes() {
		List<ClienteA> clientes = repoA.findAll();

		for (ClienteA cliente : clientes) {
			// Divide as tarefas em múltiplas threads
			executor.submit(() -> transferirUmCliente(cliente));
		}
	}

	/** Synchronized → garante thread safety
	 🧠 Aqui está o ponto-chave:
		Cada cliente é processado em threads separadas;
		O método transferirUmCliente é thread-safe por usar synchronized;
		Nenhum dado compartilhado é modificado fora do controle. 
	 **/
	
	@Async("taskExecutor")
	@Transactional("transactionManagerB")
	private synchronized void transferirUmCliente(ClienteA cliente) {
		
		ClienteB novo = new ClienteB();
		novo.setId(cliente.getId());
		novo.setNome(cliente.getNome());
		novo.setEmail(cliente.getEmail());
		
		repoB.save(novo);
		
		System.out.println("Cliente transferido: " + cliente.getNome());
	}

}
