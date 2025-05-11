package fiap.tds.dental.insurance.api.controller;

import fiap.tds.dental.insurance.api.dto.PacienteDTO;
import fiap.tds.dental.insurance.api.service.PacienteService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping
    public String listarPacientes(Model model) {
        List<PacienteDTO> lista = pacienteService.findAll();
        model.addAttribute("pacientes", lista);
        return "pacientes/lista";
    }
}
