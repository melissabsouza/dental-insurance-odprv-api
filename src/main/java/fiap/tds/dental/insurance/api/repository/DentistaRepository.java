package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    boolean existsByCpf(String cpf);

}
