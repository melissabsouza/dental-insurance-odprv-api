package fiap.tds.dental.insurance.api.repository;


import fiap.tds.dental.insurance.api.entity.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends MongoRepository<Paciente, String> {
    boolean existsByCpf(String cpf);

    Optional<Paciente> findByCpf(String cpf);
}
