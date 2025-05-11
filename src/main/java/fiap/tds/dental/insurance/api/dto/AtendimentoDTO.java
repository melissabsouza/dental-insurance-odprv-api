package fiap.tds.dental.insurance.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AtendimentoDTO {
    private Long id;

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

    @NotBlank(message = "O custo não pode ser vazio")
    private float custoEstimado;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp= "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message= "Formato de CPF inválido, use 12345678900")
    private String pacienteCpf;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp= "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message= "Formato de CPF inválido, use 12345678900")
    private String dentistaCpf;

}
