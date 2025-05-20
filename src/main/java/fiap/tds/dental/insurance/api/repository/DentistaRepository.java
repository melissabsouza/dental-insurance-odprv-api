package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Dentista;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DentistaRepository extends MongoRepository<Dentista, String> {
    boolean existsByCpf(String cpf);

    Optional<Dentista> findByCpf(String cpf);

}
