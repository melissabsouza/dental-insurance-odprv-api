package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Clinica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicaRepository extends MongoRepository<Clinica, String> {
    boolean existsByCnpj(String cnpj);

    Optional<Clinica> findByCnpj(String cnpj);
}