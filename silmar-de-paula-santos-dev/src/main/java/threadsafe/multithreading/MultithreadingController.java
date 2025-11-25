package threadsafe.multithreading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import threadsafe.exemplo5.service.AsyncMultihreadService;
import threadsafe.exemplo5.service.MonoThreadService;
import threadsafe.exemplo5.service.Resultado;

@RestController
@RequestMapping(value = "/multithreading")
public class MultithreadingController {

	@Autowired
	private MonoThreadService monoThreadService;

	@Autowired
	private AsyncMultihreadService asyncService;

	@GetMapping("/mono")
	public Resultado mono(@RequestParam(defaultValue = "50") int qtd) {
		return monoThreadService.executarSequencial(qtd);
	}

	@GetMapping("/async")
	public Resultado multi(@RequestParam(defaultValue = "50") int qtd) {
		return asyncService.executarParalelo(qtd);
	}

}
