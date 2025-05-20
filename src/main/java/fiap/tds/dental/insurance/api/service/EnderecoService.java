package fiap.tds.dental.insurance.api.service;


import fiap.tds.dental.insurance.api.dto.EnderecoDTO;
import fiap.tds.dental.insurance.api.entity.Endereco;
import fiap.tds.dental.insurance.api.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;



    public Endereco toEntity(EnderecoDTO dto) {
        if (dto.getId() != null && !dto.getId().isBlank()) {
            Endereco existente = enderecoRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

            existente.setRua(dto.getRua());
            existente.setNumero(dto.getNumero());
            existente.setCep(dto.getCep());
            existente.setBairro(dto.getBairro());
            existente.setCidade(dto.getCidade());
            existente.setEstado(dto.getEstado());
            existente.setComplemento(dto.getComplemento());

            return enderecoRepository.save(existente);
        } else {
            Endereco novo = new Endereco();
            novo.setRua(dto.getRua());
            novo.setNumero(dto.getNumero());
            novo.setCep(dto.getCep());
            novo.setBairro(dto.getBairro());
            novo.setCidade(dto.getCidade());
            novo.setEstado(dto.getEstado());
            novo.setComplemento(dto.getComplemento());

            return enderecoRepository.save(novo);
        }
    }


    public EnderecoDTO toDto(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setRua(endereco.getRua());
        return enderecoDTO;
    }

}
