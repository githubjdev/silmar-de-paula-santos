package threadsafe.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication(scanBasePackages = "threadsafe.*")
@EntityScan(basePackages = "threadsafe.exemplo5.entity.*")
@EnableAsync
public class SpringBootApp {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5); // Tamanho do pool de threads
		executor.setMaxPoolSize(10); // Número máximo de threads
		executor.setQueueCapacity(25); // Capacidade da fila
		executor.setThreadNamePrefix("taskExecutor");
		executor.initialize();
		return executor;
	}

}
