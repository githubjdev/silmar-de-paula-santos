package threadsafe.exemplo5.entity.bancoa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente_a")
@SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente", allocationSize = 1, initialValue = 1)
public class ClienteA {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
	private Long id;
	private String nome;
	private String email;

}
