package fiap.tds.dental.insurance.api.service;


import fiap.tds.dental.insurance.api.dto.EnderecoDTO;
import fiap.tds.dental.insurance.api.entity.Endereco;
import fiap.tds.dental.insurance.api.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO salvarEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = toEntity(enderecoDTO);

        if(enderecoDTO.getId() == null){
            endereco = enderecoRepository.save(endereco);
        }else{
            EnderecoDTO byId = this.findById(enderecoDTO.getId());
            byId.setNumero(enderecoDTO.getNumero());
            byId.setBairro(enderecoDTO.getBairro());
            byId.setCep(enderecoDTO.getCep());
            byId.setComplemento(enderecoDTO.getComplemento());
            byId.setEstado(enderecoDTO.getEstado());
            byId.setCidade(enderecoDTO.getCidade());
            byId.setRua(enderecoDTO.getRua());

            endereco = enderecoRepository.save(toEntity(byId));
        }
        return toDto(endereco);
    }

    public List<EnderecoDTO> findAll(){
        List<Endereco> list = enderecoRepository.findAll();
        List<EnderecoDTO> dtos = list.stream().map(EnderecoService::toDto).toList();
        return dtos;
    }

    public void deleteById(Long id){
        System.out.println("endereco deletado");
        enderecoRepository.deleteById(id);
    }

    public EnderecoDTO findById(Long id){
        Optional<Endereco> byId = enderecoRepository.findById(id);
        if(byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }



    public static Endereco toEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDTO.getId());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setRua(enderecoDTO.getRua());
        return endereco;
    }

    public static EnderecoDTO toDto(Endereco endereco) {
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
