package fiap.tds.dental.insurance.api.service;

import fiap.tds.dental.insurance.api.dto.DentistaDTO;
import fiap.tds.dental.insurance.api.entity.Dentista;
import fiap.tds.dental.insurance.api.entity.Endereco;
import fiap.tds.dental.insurance.api.entity.Telefone;
import fiap.tds.dental.insurance.api.repository.ClinicaRepository;
import fiap.tds.dental.insurance.api.repository.DentistaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DentistaService {
    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;

    public DentistaDTO salvarDentista(DentistaDTO dentistaDTO) {
        Dentista dentista;

        if (dentistaDTO.getId() == null || dentistaDTO.getId().isBlank()) {
            dentista = new Dentista();

            if (dentistaRepository.existsByCpf(dentistaDTO.getCpf())) {
                throw new RuntimeException("Já existe um dentista com esse CPF");
            }
        } else {
            dentista = dentistaRepository.findById(dentistaDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

            if (!dentistaDTO.getCpf().equals(dentista.getCpf()) && dentistaRepository.existsByCpf(dentistaDTO.getCpf())) {
                throw new RuntimeException("Já existe um dentista com este CPF");
            }
        }

        dentista.setNome(dentistaDTO.getNome());
        dentista.setCpf(dentistaDTO.getCpf());
        dentista.setCro(dentistaDTO.getCro());
        dentista.setEspecialidade(dentistaDTO.getEspecialidade());
        dentista.setEmail(dentistaDTO.getEmail());
        dentista.setDataContratacao(dentistaDTO.getDataContratacao());

        if (dentistaDTO.getClinicaCnpj() != null) {
            boolean clinicaExiste = clinicaRepository.existsByCnpj(dentistaDTO.getClinicaCnpj());
            if (!clinicaExiste) {
                throw new RuntimeException("Clinica não encontrada com cnpj: " + dentistaDTO.getClinicaCnpj());
            }
            dentista.setClinicaCnpj(dentistaDTO.getClinicaCnpj());
        }

        Endereco endereco = enderecoService.toEntity(dentistaDTO.getEndereco());
        Telefone telefone = telefoneService.toEntity(dentistaDTO.getTelefone());

        dentista.setEndereco(endereco);
        dentista.setTelefone(telefone);

        dentista = dentistaRepository.save(dentista);
        return toDto(dentista);
    }


    public List<DentistaDTO> findAll() {
        List<Dentista> dentistas = dentistaRepository.findAll();
        List<DentistaDTO> dtos = dentistas.stream().map(this::toDto).toList();
        return dtos;
    }

    public void deleteById(String id) {
        dentistaRepository.deleteById(id);
    }

    public DentistaDTO findById(String id) {
        Optional<Dentista> byId = dentistaRepository.findById(id);
        if (byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }


    private DentistaDTO toDto(Dentista dentista) {
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setId(dentista.getId());
        dentistaDTO.setNome(dentista.getNome());
        dentistaDTO.setCpf(dentista.getCpf());
        dentistaDTO.setCro(dentista.getCro());
        dentistaDTO.setEspecialidade(dentista.getEspecialidade());
        dentistaDTO.setEmail(dentista.getEmail());
        dentistaDTO.setDataContratacao(dentista.getDataContratacao());
        dentistaDTO.setClinicaCnpj(dentista.getClinicaCnpj());

        dentistaDTO.setEndereco(enderecoService.toDto(dentista.getEndereco()));
        dentistaDTO.setTelefone(telefoneService.toDto(dentista.getTelefone()));


        return dentistaDTO;
    }
}
