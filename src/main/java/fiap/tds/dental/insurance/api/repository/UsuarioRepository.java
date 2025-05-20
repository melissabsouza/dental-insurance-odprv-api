package fiap.tds.dental.insurance.api.repository;


import fiap.tds.dental.insurance.api.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
