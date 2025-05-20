package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Atendimento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AtendimentoRepository extends MongoRepository<Atendimento, String> {
}
