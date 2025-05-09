package fiap.tds.dental.insurance.api.controller;

import fiap.tds.dental.insurance.api.dto.ClinicaDTO;
import fiap.tds.dental.insurance.api.dto.EnderecoDTO;
import fiap.tds.dental.insurance.api.dto.UsuarioDTO;
import fiap.tds.dental.insurance.api.service.ClinicaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/clinicas")
public class ClinicaController {
    @Autowired
    private final ClinicaService clinicaService;

    @GetMapping
    public String listarClinicas(Model model){
        List<ClinicaDTO> lista = clinicaService.findAll();
        model.addAttribute("clinicas", lista);
        return "clinicas/lista";
    }

    @GetMapping("/novo")
    public String novoClinica(Model model){
        ClinicaDTO clinicaDTO = new ClinicaDTO();
        clinicaDTO.setUsuario(new UsuarioDTO());
        model.addAttribute("clinica", clinicaDTO);
        return "clinicas/formulario";
    }
    @PostMapping
    public String salvarClinica(@Valid @ModelAttribute("clinica") ClinicaDTO clinica, BindingResult bindingResults, Model model){
        if(bindingResults.hasErrors()){
            bindingResults.getAllErrors().forEach(e-> log.info(e.toString()));
            model.addAttribute("clinica", clinica);
            return "clinicas/formulario";
        }
        clinicaService.salvarClinica(clinica);
        return "redirect:/clinicas";
    }

    @GetMapping("/editar/{id}")
    public String editarClinica(@PathVariable Long id, Model model){
        model.addAttribute("clinica", clinicaService.findById(id));
        return "clinicas/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletarClinica(@PathVariable Long id, Model model){
        clinicaService.deleteById(id);
        return "redirect:/clinicas";
    }
}
