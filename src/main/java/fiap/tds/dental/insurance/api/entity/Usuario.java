package fiap.tds.dental.insurance.api.entity;

import fiap.tds.dental.insurance.api.enums.StatusUsuario;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String id;

    @NotBlank(message = "O email é obrigatório")
    @Indexed(unique = true)
    private String email;

    @Size(min = 5, message = "A senha deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A senha não pode ser vazia")
    @NotNull(message = "senha não pode ser nula")
    private String senha;

    private StatusUsuario status;
}
