import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import threadsafe.app.SpringBootApp;
import threadsafe.exemplo5.entity.bancoa.ClienteA;
import threadsafe.exemplo5.repository.bancoa.ClienteARepository;
import threadsafe.exemplo5.repository.bancob.ClienteBRepository;
import threadsafe.exemplo5.service.TransferService;

@SpringBootTest(classes = {SpringBootApp.class})
public class TransferServiceTest {

	@Autowired
	private ClienteARepository repoA;

	@Autowired
	private ClienteBRepository repoB;

	@Autowired
	private TransferService service;

	// Cria 1000 registros no banco A
	@BeforeEach
	void setup() {
		repoA.deleteAll();
		repoB.deleteAll();

		for (int i = 1; i <= 1000; i++) {
			ClienteA c = new ClienteA();
			c.setId((long) i);
			c.setNome("Cliente " + i);
			c.setEmail("cliente" + i + "@teste.com");
			repoA.save(c);
		}
	}

	@Test
	void testarComThreadSafe() throws Exception {
		System.out.println("▶️ Testando com thread safety...");

		service.transferirClientes();
		Thread.sleep(3000);

		long count = repoB.count();
		System.out.println("Total esperado: 1000");
		System.out.println("Total inserido: " + count);
		Assertions.assertEquals(1000, count);
	}

}
