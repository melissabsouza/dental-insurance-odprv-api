package fiap.tds.dental.insurance.api.entity;

import fiap.tds.dental.insurance.api.enums.TipoTelefone;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "telefones")
public class Telefone {
    @Id
    private String id;

    @Pattern(regexp = "\\d{2}\\s?(9\\d{4}-?\\d{4}|\\d{4}-?\\d{4})",
            message = "Use XX 9XXXX-XXXX, XX 9XXXX XXXX ou XX 9XXXXXXXX")
    private String numero;

    @NotNull(message = "O tipo de telefone não pode ser nulo")
    private TipoTelefone tipo;
}
