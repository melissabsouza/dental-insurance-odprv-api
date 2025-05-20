package fiap.tds.dental.insurance.api.repository;


import fiap.tds.dental.insurance.api.entity.Telefone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TelefoneRepository extends MongoRepository<Telefone, String> {
}
