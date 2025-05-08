package fiap.tds.dental.insurance.api.service;

import fiap.tds.dental.insurance.api.dto.ClinicaDTO;
import fiap.tds.dental.insurance.api.dto.EnderecoDTO;
import fiap.tds.dental.insurance.api.dto.TelefoneDTO;
import fiap.tds.dental.insurance.api.dto.UsuarioDTO;
import fiap.tds.dental.insurance.api.entity.Clinica;
import fiap.tds.dental.insurance.api.entity.Endereco;
import fiap.tds.dental.insurance.api.entity.Telefone;
import fiap.tds.dental.insurance.api.entity.Usuario;
import fiap.tds.dental.insurance.api.repository.ClinicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;

    public ClinicaDTO salvarClinica(ClinicaDTO clinicaDTO) {
        Clinica clinica;

        if (clinicaDTO.getId() == null) {
            clinica = toEntity(clinicaDTO);
        } else {
            clinica = clinicaRepository.findById(clinicaDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Clinica não encontrada"));

            clinica.setCnpj(clinicaDTO.getCnpj());
            clinica.setNome(clinicaDTO.getNome());

            attEndereco(clinica.getEndereco(), clinicaDTO.getEndereco());
            attUsuario(clinica.getUsuario(), clinicaDTO.getUsuario());
            attTelefone(clinica.getTelefone(), clinicaDTO.getTelefone());
        }

        clinica = clinicaRepository.save(clinica);
        return toDto(clinica);
    }


    private void attEndereco(Endereco endereco, EnderecoDTO enderecoDTO) {
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setComplemento(enderecoDTO.getComplemento());
    }

    private void attUsuario(Usuario usuario, UsuarioDTO usuarioDTO) {
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setStatus(usuarioDTO.getStatus());
    }

    private void attTelefone(Telefone telefone, TelefoneDTO telefoneDTO) {
        telefone.setTipo(telefoneDTO.getTipo());
        telefone.setNumero(telefoneDTO.getNumero());
    }

    public List<ClinicaDTO> findAll(){
        List<Clinica> list = clinicaRepository.findAll();
        List<ClinicaDTO> dtos = list.stream().map(ClinicaService::toDto).toList();
        return dtos;
    }

    public void deleteById(Long id){
        System.out.println("clinica deletada");
        clinicaRepository.deleteById(id);
    }

    public ClinicaDTO findById(Long id)  {
        Optional<Clinica> byId = clinicaRepository.findById(id);
        if(byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }

    private static Clinica toEntity(ClinicaDTO clinicaDTO) {
        Clinica clinica = new Clinica();
        clinica.setId(clinicaDTO.getId());
        clinica.setCnpj(clinicaDTO.getCnpj());
        clinica.setNome(clinicaDTO.getNome());

        clinica.setEndereco(EnderecoService.toEntity(clinicaDTO.getEndereco()));
        clinica.setUsuario(UsuarioService.toEntity(clinicaDTO.getUsuario()));
        clinica.setTelefone(TelefoneService.toEntity(clinicaDTO.getTelefone()));

//        clinica.setEndereco(clinica.getEndereco());
//        clinica.setUsuario(clinica.getUsuario());
//        clinica.setTelefone(clinica.getTelefone());
        return clinica;
    }

    private static ClinicaDTO toDto(Clinica clinica) {
        ClinicaDTO clinicaDTO = new ClinicaDTO();
        clinicaDTO.setId(clinica.getId());
        clinicaDTO.setCnpj(clinica.getCnpj());
        clinicaDTO.setNome(clinica.getNome());

        clinicaDTO.setEndereco(EnderecoService.toDto(clinica.getEndereco()));
        clinicaDTO.setUsuario(UsuarioService.toDto(clinica.getUsuario()));
        clinicaDTO.setTelefone(TelefoneService.toDto(clinica.getTelefone()));

        return clinicaDTO;
    }

}
