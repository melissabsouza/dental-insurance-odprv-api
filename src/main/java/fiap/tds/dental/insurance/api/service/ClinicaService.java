package fiap.tds.dental.insurance.api.service;

import fiap.tds.dental.insurance.api.dto.ClinicaDTO;
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
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;
    private final UsuarioService usuarioService;

    public ClinicaDTO salvarClinica(ClinicaDTO clinicaDTO) {
        Clinica clinica;

        if (clinicaDTO.getId() == null || clinicaDTO.getId().trim().isEmpty()) {
            clinica = new Clinica();
        } else {
            clinica = clinicaRepository.findById(clinicaDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
        }


        clinica.setCnpj(clinicaDTO.getCnpj());
        clinica.setNome(clinicaDTO.getNome());

        Endereco endereco = enderecoService.toEntity(clinicaDTO.getEndereco());
        Telefone telefone = telefoneService.toEntity(clinicaDTO.getTelefone());
        Usuario usuario = usuarioService.toEntity(clinicaDTO.getUsuario());

        clinica.setEndereco(endereco);
        clinica.setTelefone(telefone);
        clinica.setUsuario(usuario);

        clinica = clinicaRepository.save(clinica);
        return toDto(clinica);
    }



    public List<ClinicaDTO> findAll() {
        List<Clinica> list = clinicaRepository.findAll();
        List<ClinicaDTO> dtos = list.stream().map(this::toDto).toList();
        return dtos;
    }

    public void deleteById(String id) {
        System.out.println("clinica deletada");
        clinicaRepository.deleteById(id);
    }

    public ClinicaDTO findById(String id) {
        Optional<Clinica> byId = clinicaRepository.findById(id);
        if (byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }

    private Clinica toEntity(ClinicaDTO clinicaDTO) {
        Clinica clinica = new Clinica();
        clinica.setId(clinicaDTO.getId());
        clinica.setCnpj(clinicaDTO.getCnpj());
        clinica.setNome(clinicaDTO.getNome());

        clinica.setEndereco(enderecoService.toEntity(clinicaDTO.getEndereco()));
        clinica.setUsuario(usuarioService.toEntity(clinicaDTO.getUsuario()));
        clinica.setTelefone(telefoneService.toEntity(clinicaDTO.getTelefone()));
        return clinica;
    }

    private ClinicaDTO toDto(Clinica clinica) {
        ClinicaDTO clinicaDTO = new ClinicaDTO();
        clinicaDTO.setId(clinica.getId());
        clinicaDTO.setCnpj(clinica.getCnpj());
        clinicaDTO.setNome(clinica.getNome());

        clinicaDTO.setEndereco(enderecoService.toDto(clinica.getEndereco()));
        clinicaDTO.setUsuario(usuarioService.toDto(clinica.getUsuario()));
        clinicaDTO.setTelefone(telefoneService.toDto(clinica.getTelefone()));

        return clinicaDTO;
    }

}
