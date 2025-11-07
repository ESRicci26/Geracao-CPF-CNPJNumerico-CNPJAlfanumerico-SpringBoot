package com.javaricci.GeracaoCPFCNPJ.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaricci.GeracaoCPFCNPJ.Service.GeradorService;

@Controller
@RequestMapping("/cadastros/gerarcpfcnpj")
public class GeradorController {

    private final GeradorService geradorService;
    private final Logger logger = LoggerFactory.getLogger(GeradorController.class);

    public GeradorController(GeradorService geradorService) {
        this.geradorService = geradorService;
    }

    @GetMapping("/")
    public String index() {
        return "/cadastros/gerarcpfcnpj/index";
    }

    @GetMapping("/gerarCPF")
    public String gerarCPF(Model model) {
        String cpf = geradorService.gerarCPF();
        model.addAttribute("documento", cpf);
        return "/cadastros/gerarcpfcnpj/index";
    }

    @GetMapping("/gerarCNPJ")
    public String gerarCNPJ(Model model) {
        String cnpj = geradorService.gerarCNPJ();
        model.addAttribute("documento", cnpj);
        return "/cadastros/gerarcpfcnpj/index";
    }

    @GetMapping("/gerarCNPJAlfa")
    public String gerarCNPJAlfa(Model model) {
        String cnpjAlfa = geradorService.gerarCNPJAlfanumerico();
        model.addAttribute("documento", cnpjAlfa);
        return "/cadastros/gerarcpfcnpj/index";
    }

    @GetMapping("/validarCPF")
    public String validarCPF(@RequestParam("valor") String valor, Model model) {
        boolean valido = geradorService.validarCPF(valor);
        model.addAttribute("mensagem", valido ? "✅ CPF válido!" : "❌ CPF inválido!");
        return "/cadastros/gerarcpfcnpj/index";
    }

    @GetMapping("/validarCNPJ")
    public String validarCNPJ(@RequestParam("valor") String valor, Model model) {
        boolean valido = geradorService.validarCNPJ(valor);
        model.addAttribute("mensagem", valido ? "✅ CNPJ válido!" : "❌ CNPJ inválido!");
        return "/cadastros/gerarcpfcnpj/index";
    }

    @GetMapping("/validarCNPJAlfa")
    public String validarCNPJAlfa(@RequestParam("valor") String valor, Model model) {
        boolean valido = geradorService.validarCNPJAlfanumerico(valor);
        model.addAttribute("mensagem", valido ? "✅ CNPJ Alfanumérico válido!" : "❌ CNPJ Alfanumérico inválido!");
        return "/cadastros/gerarcpfcnpj/index";
    }
}

/*
package com.javaricci.GeracaoCPFCNPJ.Controller;

import com.javaricci.GeracaoCPFCNPJ.Service.GeradorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeradorController {

    private final GeradorService geradorService;
    private final Logger logger = LoggerFactory.getLogger(GeradorController.class);

    public GeradorController(GeradorService geradorService) {
        this.geradorService = geradorService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/gerarCPF")
    public String gerarCPF(Model model) {
        try {
            String cpf = geradorService.gerarCPF();
            model.addAttribute("documento", cpf);
            return "index";
        } catch (Exception ex) {
            logger.error("Erro ao gerar CPF", ex);
            model.addAttribute("erro", "Erro ao gerar CPF: " + ex.getMessage());
            return "index";
        }
    }

    @GetMapping("/gerarCNPJ")
    public String gerarCNPJ(Model model) {
        try {
            String cnpj = geradorService.gerarCNPJ();
            model.addAttribute("documento", cnpj);
            return "index";
        } catch (Exception ex) {
            logger.error("Erro ao gerar CNPJ", ex);
            model.addAttribute("erro", "Erro ao gerar CNPJ: " + ex.getMessage());
            return "index";
        }
    }

    @GetMapping("/gerarCNPJAlfa")
    public String gerarCNPJAlfanumerico(Model model) {
        try {
            String cnpjAlfa = geradorService.gerarCNPJAlfanumerico();
            model.addAttribute("documento", cnpjAlfa);
            return "index";
        } catch (Exception ex) {
            logger.error("Erro ao gerar CNPJ Alfanumérico", ex);
            model.addAttribute("erro", "Erro ao gerar CNPJ Alfanumérico: " + ex.getMessage());
            return "index";
        }
    }
}
*/