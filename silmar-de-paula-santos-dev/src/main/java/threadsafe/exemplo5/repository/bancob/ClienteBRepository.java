package threadsafe.exemplo5.repository.bancob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import threadsafe.exemplo5.entity.bancob.ClienteB;

@Repository
public interface ClienteBRepository extends JpaRepository<ClienteB, Long> {
}