package threadsafe.exemplo5.entity.bancob;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente_b")
public class ClienteB {
	@Id
	private Long id;
	private String nome;
	private String email;
}
