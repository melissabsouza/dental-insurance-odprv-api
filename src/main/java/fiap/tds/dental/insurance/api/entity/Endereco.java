package fiap.tds.dental.insurance.api.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "enderecos")
public class Endereco {
    @Id
    private String id;

    @Size(min = 5, message = "A rua deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A rua não pode ser vazia")
    @NotNull(message = "rua não pode ser nula")
    private String rua;


    @NotNull(message = "numero não pode ser nulo")
    private int numero;

    private String complemento;

    @Size(min = 5, message = "O bairro deve ter pelo menos 5 caracteres")
    @NotBlank(message = "O bairro não pode ser vazio")
    @NotNull(message = "O bairro não pode ser nulo")
    private String bairro;

    @Size(min = 5, message = "A cidade deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A cidade não pode ser vazia")
    @NotNull(message = "A cidade não pode ser nula")
    private String cidade;

    @NotNull(message = "estado não pode ser nulo")
    @NotBlank(message = "estado não pode ser nulo")
    private String estado;

    @Pattern(regexp = "\\d{5}-?\\d{3}",
            message = "Use XXXXX-XXX")
    @NotNull(message = "cep não pode ser nulo")
    private String cep;

}
