package fiap.tds.dental.insurance.api.entity;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@ToString
@Document(collection = "dentistas")
public class Dentista {

    @Id
    private String id;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message = "Formato de CPF inválido, use 12345678900")
    @Indexed(unique = true)
    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    private String nome;

    @NotBlank(message = "CRO é obrigatório")
    @NotNull(message = "CRO não pode ser nulo")
    @Size(min = 5, message = "CRO deve ter pelo menos 5 caracteres")
    private String cro;

    @NotBlank(message = "Especialidade é obrigatória")
    @NotNull(message = "Especialidade não pode ser nula")
    @Size(min = 3, message = "Especialidade deve ter pelo menos 10 caracteres")
    private String especialidade;

    @NotBlank(message = "O email é obrigatório")
    @Indexed(unique = true)
    private String email;

    @NotNull(message = "Data não pode ser nula")
    private LocalDate dataContratacao;

    @NotNull(message = "Clinica não pode ser nula")
    private String clinicaCnpj;

    @NotNull(message = "endereco não pode ser nulo")
    @Valid
    private Endereco endereco;

    @NotNull(message = "telefone não pode ser nulo")
    @Valid
    private Telefone telefone;


}
