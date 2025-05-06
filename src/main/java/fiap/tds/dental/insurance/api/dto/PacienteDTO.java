package fiap.tds.dental.insurance.api.dto;

import fiap.tds.dental.insurance.api.enums.TipoGenero;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
@Data
public class PacienteDTO {

    private Long id;

    @NotBlank(message = "Cpf é obrigatório")
    @NotNull(message = "Cpf não pode ser nulo")
    @Pattern(regexp= "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$",
            message= "Formato de CPF inválido, use 12345678900")
    @Column(name = "cpf_paciente", unique = true, nullable = false)
    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    @NotNull(message = "Nome não pode ser nulo")
    @Size(min=3, message = "Nome deve ter pelo menos 3 caracteres")
    private String nome;

    @NotNull(message = "Data não pode ser nula")
    private Date dataNascimento;

    @NotNull(message = "gênero não pode ser nulo")
    private TipoGenero genero;

    private ClinicaDTO clinica;
    private EnderecoDTO endereco;
    private TelefoneDTO telefone;
}
