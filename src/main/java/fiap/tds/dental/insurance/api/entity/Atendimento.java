package fiap.tds.dental.insurance.api.entity;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
@Document(collection = "atendimentos")
public class Atendimento {

    @Id
    private String id;

    @Size(min = 3, message = "O procedimento deve ter pelo menos 5 caracteres")
    @NotBlank(message = "O procedimento não pode ser vazio")
    @NotNull(message = "O procedimento não pode ser nulo")
    private String tipoProcedimento;

    @Size(min = 3, message = "A descrição deve ter pelo menos 5 caracteres")
    @NotBlank(message = "A descrição não pode ser vazio")
    @NotNull(message = "A descrição não pode ser nulo")
    private String descricao;

    @NotNull(message = "Data de atendimento não pode ser nula")
    private LocalDateTime dataAtendimento;

    @NotNull(message = "O custo não pode ser vazio")
    @Positive(message = "O custo deve ser maior que zero")
    private BigDecimal custoEstimado;

    @NotNull(message = "O cpf não pode ser vazio")
    @Valid
    private String pacienteCpf;

    @NotNull(message = "O cpf não pode ser vazio")
    @Valid
    private String dentistaCpf;
}
