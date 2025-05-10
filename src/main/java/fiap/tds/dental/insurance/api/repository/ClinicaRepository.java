package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
    boolean existsByCnpj(String cnpj);

}
