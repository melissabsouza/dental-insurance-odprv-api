package fiap.tds.dental.insurance.api.service;


import fiap.tds.dental.insurance.api.dto.TelefoneDTO;
import fiap.tds.dental.insurance.api.entity.Telefone;
import fiap.tds.dental.insurance.api.repository.TelefoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TelefoneService {
    @Autowired
    private final TelefoneRepository telefoneRepository;


    public Telefone toEntity(TelefoneDTO dto) {
        if (dto.getId() != null && !dto.getId().isBlank()) {
            Telefone existente = telefoneRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));
            existente.setNumero(dto.getNumero());
            existente.setTipo(dto.getTipo());
            return existente;
        }

        Telefone novo = new Telefone();
        novo.setNumero(dto.getNumero());
        novo.setTipo(dto.getTipo());
        return telefoneRepository.save(novo);
    }

    public TelefoneDTO toDto(Telefone telefone) {
        TelefoneDTO telefoneDTO = new TelefoneDTO();
        telefoneDTO.setId(telefone.getId());
        telefoneDTO.setNumero(telefone.getNumero());
        telefoneDTO.setTipo(telefone.getTipo());
        return telefoneDTO;

    }
}
