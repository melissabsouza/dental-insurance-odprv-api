package fiap.tds.dental.insurance.api.service;

import fiap.tds.dental.insurance.api.dto.AtendimentoDTO;
import fiap.tds.dental.insurance.api.entity.Atendimento;
import fiap.tds.dental.insurance.api.repository.AtendimentoRepository;
import fiap.tds.dental.insurance.api.repository.DentistaRepository;
import fiap.tds.dental.insurance.api.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AtendimentoService {
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    public AtendimentoDTO salvarAtendimento(AtendimentoDTO atendimentoDTO) {
        Atendimento atendimento;

        if (atendimentoDTO.getId() == null || atendimentoDTO.getId().isBlank()) {
            atendimento = new Atendimento();
        } else {
            atendimento = atendimentoRepository.findById(atendimentoDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));
        }

        atendimento.setDataAtendimento(atendimentoDTO.getDataAtendimento());
        atendimento.setDescricao(atendimentoDTO.getDescricao());
        atendimento.setTipoProcedimento(atendimentoDTO.getTipoProcedimento());
        atendimento.setCustoEstimado(atendimentoDTO.getCustoEstimado());

        if (atendimentoDTO.getPacienteCpf() != null) {
            pacienteRepository.findByCpf(atendimentoDTO.getPacienteCpf())
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado com cpf: " + atendimentoDTO.getPacienteCpf()));
            atendimento.setPacienteCpf(atendimentoDTO.getPacienteCpf());
        }

        if (atendimentoDTO.getDentistaCpf() != null) {
            dentistaRepository.findByCpf(atendimentoDTO.getDentistaCpf())
                    .orElseThrow(() -> new RuntimeException("Dentista não encontrado com cpf: " + atendimentoDTO.getDentistaCpf()));
            atendimento.setDentistaCpf(atendimentoDTO.getDentistaCpf());
        }

        atendimento = atendimentoRepository.save(atendimento);
        return toDto(atendimento);
    }

    public List<AtendimentoDTO> findAll() {
        List<Atendimento> atendimentos = atendimentoRepository.findAll();
        List<AtendimentoDTO> dtos = atendimentos.stream().map(this::toDto).toList();
        return dtos;
    }

    public void deleteById(String id) {
        atendimentoRepository.deleteById(id);
    }

    public AtendimentoDTO findById(String id) {
        Optional<Atendimento> byId = atendimentoRepository.findById(id);
        if (byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }

    private AtendimentoDTO toDto(Atendimento atendimento) {
        AtendimentoDTO atendimentoDTO = new AtendimentoDTO();

        atendimentoDTO.setId(atendimento.getId());
        atendimentoDTO.setDataAtendimento(atendimento.getDataAtendimento());
        atendimentoDTO.setDescricao(atendimento.getDescricao());
        atendimentoDTO.setTipoProcedimento(atendimento.getTipoProcedimento());
        atendimentoDTO.setCustoEstimado(atendimento.getCustoEstimado());

        if (atendimento.getPacienteCpf() != null) {
            atendimentoDTO.setPacienteCpf(atendimento.getPacienteCpf());
        }

        if (atendimento.getDentistaCpf() != null) {
            atendimentoDTO.setDentistaCpf(atendimento.getDentistaCpf());
        }

        return atendimentoDTO;
    }
}
