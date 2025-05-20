package fiap.tds.dental.insurance.api.repository;

import fiap.tds.dental.insurance.api.entity.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnderecoRepository extends MongoRepository<Endereco, String> {
}
