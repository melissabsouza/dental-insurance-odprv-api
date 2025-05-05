package fiap.tds.dental.insurance.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AtendimentoDTO {
    private Long id;
    private String tipoProcedimento;
    private String descricao;
    private Date dataAtendimento;
    private float custoEstimado;
    private PacienteDTO paciente;
    private DentistaDTO dentista;
}
