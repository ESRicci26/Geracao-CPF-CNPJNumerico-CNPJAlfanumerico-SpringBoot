package com.javaricci.GeracaoCPFCNPJ.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GeradorService {

    private final Random random = new Random();

    public String gerarCPF() {
        List<Integer> cpf = new ArrayList<>();
        for (int i = 0; i < 9; i++) cpf.add(random.nextInt(10));
        cpf.add(gerarDigito(cpf, new int[]{10,9,8,7,6,5,4,3,2}));
        cpf.add(gerarDigito(cpf, new int[]{11,10,9,8,7,6,5,4,3,2}));
        return formatarCPF(cpf);
    }

    public String gerarCNPJ() {
        List<Integer> cnpj = new ArrayList<>();
        for (int i = 0; i < 12; i++) cnpj.add(random.nextInt(10));
        cnpj.add(gerarDigito(cnpj, new int[]{5,4,3,2,9,8,7,6,5,4,3,2}));
        cnpj.add(gerarDigito(cnpj, new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2}));
        return formatarCNPJ(cnpj);
    }

    public String gerarCNPJAlfanumerico() {
        String base36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(base36.charAt(random.nextInt(base36.length())));
        }
        char dv1 = calcularDigitoAlfanumerico(sb.toString(), new int[]{5,4,3,2,9,8,7,6,5,4,3,2});
        char dv2 = calcularDigitoAlfanumerico(sb.toString() + dv1, new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2});
        sb.append(dv1).append(dv2);
        String c = sb.toString();
        return String.format("%s%s.%s%s%s.%s%s%s/%s%s%s%s-%s%s",
                c.charAt(0), c.charAt(1),
                c.charAt(2), c.charAt(3), c.charAt(4),
                c.charAt(5), c.charAt(6), c.charAt(7),
                c.charAt(8), c.charAt(9), c.charAt(10), c.charAt(11),
                c.charAt(12), c.charAt(13));
    }

    private char calcularDigitoAlfanumerico(String entrada, int[] pesos) {
        String base36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int soma = 0;
        for (int i = 0; i < entrada.length(); i++) {
            int valor = base36.indexOf(entrada.charAt(i));
            soma += valor * pesos[i % pesos.length];
        }
        int resto = soma % 36;
        int digito = 36 - resto;
        if (digito == 36) digito = 0;
        return base36.charAt(digito);
    }

    public boolean validarCNPJAlfanumerico(String valor) {
        if (valor == null) return false;
        String base36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String limpo = valor.replaceAll("[^A-Z0-9]", "").toUpperCase();
        if (limpo.length() != 14) return false;
        String base = limpo.substring(0, 12);
        char dv1 = calcularDigitoAlfanumerico(base, new int[]{5,4,3,2,9,8,7,6,5,4,3,2});
        char dv2 = calcularDigitoAlfanumerico(base + dv1, new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2});
        return dv1 == limpo.charAt(12) && dv2 == limpo.charAt(13);
    }

    public boolean validarCPF(String cpf) {
        if (cpf == null) return false;
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\1{10}")) return false;
        try {
            List<Integer> digitos = new ArrayList<>();
            for (char c : cpf.toCharArray()) digitos.add(Character.getNumericValue(c));
            int d1 = gerarDigito(digitos.subList(0, 9), new int[]{10,9,8,7,6,5,4,3,2});
            int d2 = gerarDigito(digitos.subList(0, 10), new int[]{11,10,9,8,7,6,5,4,3,2});
            return d1 == digitos.get(9) && d2 == digitos.get(10);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validarCNPJ(String cnpj) {
        if (cnpj == null) return false;
        cnpj = cnpj.replaceAll("\\D", "");
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;
        try {
            List<Integer> digitos = new ArrayList<>();
            for (char c : cnpj.toCharArray()) digitos.add(Character.getNumericValue(c));
            int d1 = gerarDigito(digitos.subList(0, 12), new int[]{5,4,3,2,9,8,7,6,5,4,3,2});
            int d2 = gerarDigito(digitos.subList(0, 13), new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2});
            return d1 == digitos.get(12) && d2 == digitos.get(13);
        } catch (Exception e) {
            return false;
        }
    }

    private int gerarDigito(List<Integer> numeros, int[] pesos) {
        int soma = 0;
        int limite = Math.min(numeros.size(), pesos.length);
        for (int i = 0; i < limite; i++) soma += numeros.get(i) * pesos[i];
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    private String formatarCPF(List<Integer> cpf) {
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d",
                cpf.get(0), cpf.get(1), cpf.get(2),
                cpf.get(3), cpf.get(4), cpf.get(5),
                cpf.get(6), cpf.get(7), cpf.get(8),
                cpf.get(9), cpf.get(10));
    }

    private String formatarCNPJ(List<Integer> cnpj) {
        return String.format("%d%d.%d%d%d.%d%d%d/%d%d%d%d-%d%d",
                cnpj.get(0), cnpj.get(1),
                cnpj.get(2), cnpj.get(3), cnpj.get(4),
                cnpj.get(5), cnpj.get(6), cnpj.get(7),
                cnpj.get(8), cnpj.get(9), cnpj.get(10), cnpj.get(11),
                cnpj.get(12), cnpj.get(13));
    }
}
