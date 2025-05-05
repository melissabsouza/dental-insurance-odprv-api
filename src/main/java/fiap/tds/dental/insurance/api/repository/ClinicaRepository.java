package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, String> {
}
