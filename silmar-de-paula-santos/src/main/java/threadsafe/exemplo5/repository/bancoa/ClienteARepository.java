package threadsafe.exemplo5.repository.bancoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import threadsafe.exemplo5.entity.bancoa.ClienteA;

@Repository
public interface ClienteARepository extends JpaRepository<ClienteA, Long> {
}
