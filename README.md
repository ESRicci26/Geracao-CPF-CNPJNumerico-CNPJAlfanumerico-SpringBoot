# 🧮 Gerador de CPF, CNPJ e CNPJ Alfanumérico  
Aplicação Java Spring Boot com Thymeleaf para gerar **CPF**, **CNPJ** e **CNPJ Alfanumérico** automaticamente.  
Interface simples, responsiva e funcional utilizando **Bootstrap 5**.

---

## 📌 Funcionalidades

- ✅ Geração automática de **CPF válido**
- ✅ Geração automática de **CNPJ válido**
- ✅ Geração de **CNPJ alfanumérico** (com letras e números no formato de CNPJ)
- ✅ Exibição do resultado no navegador via **Thymeleaf**
- ✅ Layout simples e moderno com **Bootstrap**
- ✅ Tratamento de erros no backend com retorno amigável na interface

---

## 🧠 Estrutura do Projeto

```
GeracaoCPFCNPJ/
├── src/
│   ├── main/
│   │   ├── java/com/javaricci/GeracaoCPFCNPJ/
│   │   │   ├── Controller/
│   │   │   │   └── GeradorController.java
│   │   │   └── Service/
│   │   │       └── GeradorService.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── index.html
│   │       └── application.properties
│   └── test/
│       └── ...
├── pom.xml
└── README.md
```

---

## ⚙️ Requisitos

| Ferramenta | Versão Recomendada |
|-------------|--------------------|
| Java        | 11 ou superior     |
| Maven       | 3.6+               |
| Spring Boot | 2.7.x ou 3.x       |

---

## 🚀 Como Executar o Projeto

1. **Clonar o repositório**
   ```bash
   git clone https://github.com/ESRicci26/GeradorCPFCNPJ.git
   cd GeradorCPFCNPJ
   ```

2. **Compilar e executar com Maven**
   ```bash
   mvn spring-boot:run
   ```

3. **Acessar no navegador**
   ```
   http://localhost:8080
   ```

---

## 🧩 Endpoints Disponíveis

| Rota | Descrição | Exemplo |
|------|------------|---------|
| `/` | Página inicial com botões | - |
| `/gerarCPF` | Gera um CPF válido e exibe no textarea | `123.456.789-09` |
| `/gerarCNPJ` | Gera um CNPJ válido e exibe no textarea | `12.345.678/0001-95` |
| `/gerarCNPJAlfa` | Gera um CNPJ alfanumérico | `AB1.2C3.D4E/5678-F9` |

---

## 🧱 Estrutura de Código

### **GeradorService.java**
Camada de lógica que contém os métodos responsáveis pela geração dos números.

```java
public String gerarCPF()
public String gerarCNPJ()
public String gerarCNPJAlfanumerico()
```

### **GeradorController.java**
Camada de controle responsável por mapear as rotas HTTP e enviar os resultados à view.

```java
@GetMapping("/gerarCPF")
@GetMapping("/gerarCNPJ")
@GetMapping("/gerarCNPJAlfa")
```

### **index.html**
Interface com três botões principais e um campo `textarea` para exibir o resultado:

- **Gerar CPF**
- **Gerar CNPJ**
- **Gerar CNPJ Alfanumérico**

---

## 🧠 Lógica do CNPJ Alfanumérico

O método `gerarCNPJAlfanumerico()` gera **14 caracteres** contendo letras (A-Z) e números (0-9), mantendo a **máscara de CNPJ tradicional**:

```
AA.BBB.CCC/DDDD-EE
```

> Exemplo: `AB1.2C3.D4E/5678-F9`

---

## 🎨 Interface (Thymeleaf + Bootstrap)

A página `index.html` utiliza **Bootstrap 5** para responsividade e **Thymeleaf** para renderizar os valores do backend.

```html
<textarea th:text="${documento != null ? documento : ''}" readonly></textarea>
```

---

## 🧰 Tecnologias Utilizadas

- ☕ **Java 11+**
- 🌱 **Spring Boot (Web, Thymeleaf)**
- 🎨 **Thymeleaf**
- 🧩 **Bootstrap 5**
- 🧠 **Maven**

---

## 🧑‍💻 Autor

**Edilson Salvador Ricci**  
📍 Desenvolvedor Java | Spring Boot | Thymeleaf  
🔗 [LinkedIn](https://www.linkedin.com/in/edilson-salvador-ricci-82b771b7/)  

---

## 🪪 Licença

Este projeto é licenciado sob a **MIT License** — sinta-se à vontade para usar e modificar.

```
MIT License © 2025 Edilson Salvador Ricci
```

---

## 💡 Sugestões Futuras

- Exportar CPF/CNPJ gerados em arquivo `.txt` ou `.csv`
- Adicionar opção de copiar automaticamente o resultado
- Implementar geração em lote (ex: 100 CPFs por vez)
- Adicionar API REST (retorno JSON)
- Integração com Front-end React

---

# 📘 Especificação Técnica — CNPJ Alfanumérico Base 36 - Implementada dia 06/11/2025

## 1. Introdução

O **CNPJ Alfanumérico** é uma evolução do modelo atual de identificação cadastral de pessoas jurídicas no Brasil.
Ele foi proposto para uso em **fundos de investimento, veículos de propósito específico (SPVs), tokenização de ativos e infraestrutura de mercado financeiro**, com previsão de adoção oficial em **julho de 2026**.

Atualmente, o **CNPJ tradicional** é composto por 14 dígitos numéricos (base 10).  
O novo formato utiliza **base 36 (0–9 + A–Z)**, expandindo exponencialmente o espaço de combinações possíveis.

Este documento descreve a estrutura, o algoritmo de cálculo e as regras de validação do **CNPJ Alfanumérico Base 36** implementado no projeto Java Spring Boot/Thymeleaf.

---

## 2. Estrutura do Identificador

O formato visual permanece idêntico ao CNPJ tradicional:

```
AA.AAA.AAA/AAAA-AA
```

| Posição | Significado | Tipo | Exemplo |
|----------|--------------|------|----------|
| 1–8 | Raiz da entidade (empresa, fundo, veículo, etc.) | Alfanumérico (A–Z, 0–9) | `H9.59R.U2E` |
| 9–12 | Código da filial ou instância | Alfanumérico (A–Z, 0–9) | `ZXL4` |
| 13–14 | Dígitos verificadores (DV1 e DV2) | Alfanumérico calculado | `42` |

Exemplo completo:

```
H9.59R.U2E/ZXL4-42
```

---

## 3. Fundamento Matemático

O cálculo dos **dígitos verificadores (DV1 e DV2)** segue o mesmo conceito do CNPJ decimal, adaptado para **base 36**.

### 3.1. Conversão de caracteres

Cada caractere é convertido para um valor inteiro:

| Caractere | Valor |
|------------|--------|
| 0–9 | 0–9 |
| A–Z | 10–35 |

### 3.2. Cálculo do DV1

1. Considere os 12 primeiros caracteres (sem os DVs).  
2. Multiplique cada valor pelo **peso correspondente**:

```
Pesos DV1 = [5,4,3,2,9,8,7,6,5,4,3,2]
```

3. Calcule a soma ponderada:  
   `S = Σ(valor[i] × peso[i])`
4. Calcule o resto da divisão:  
   `R = S mod 36`
5. Determine o dígito verificador:  
   `DV1 = (36 - R) mod 36`

### 3.3. Cálculo do DV2

1. Considere os 12 caracteres + DV1.  
2. Multiplique pelos pesos:

```
Pesos DV2 = [6,5,4,3,2,9,8,7,6,5,4,3,2]
```

3. Calcule o novo resto e DV2:  
   `R = S mod 36`  
   `DV2 = (36 - R) mod 36`

### 3.4. Montagem final

O CNPJ Alfanumérico completo é então formado como:

```
CNPJ36 = Base(12) + DV1 + DV2
```

e formatado visualmente conforme o padrão `AA.AAA.AAA/AAAA-AA`.

---

## 4. Comparativo entre CNPJ Decimal e Alfanumérico

| Característica | CNPJ Decimal (RFB) | CNPJ Alfanumérico Base 36 |
|----------------|--------------------|----------------------------|
| Base numérica | 10 | 36 |
| Tamanho total | 14 dígitos | 14 caracteres (A–Z, 0–9) |
| Máscara | XX.XXX.XXX/XXXX-XX | AA.AAA.AAA/AAAA-AA |
| Espaço de combinações | 10¹⁴ (100 trilhões) | 36¹⁴ (78 bilhões de vezes maior) |
| Uso oficial | Sim (RFB) | Previsto para 2026 (mercado financeiro) |
| Dígitos verificadores | Módulo 11 | Módulo 36 |
| Aplicações | Empresas, filiais | Fundos, SPVs, tokenização, fintechs |

---

## 5. Algoritmo Java (resumo)

```java
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
```

---

## 6. Recomendações de Implementação

1. **Separar CNPJs reais de CNPJs base-36** no banco de dados (campo `TipoDocumento`).  
2. **Validar sempre com base no algoritmo completo** — nunca apenas pelo formato.  
3. **Documentar os DVs gerados** para fins de auditoria.  
4. **Evitar colisões** entre CNPJs reais e alfanuméricos (prefixos diferentes são recomendados).  
5. **Manter logs de geração e uso** para compliance e rastreabilidade.  
6. **Usar letras maiúsculas sempre** — o algoritmo é *case-insensitive*, mas o padrão é maiúsculo.

---

## 7. Benefícios do Novo Formato

- Expansão exponencial de combinações possíveis.  
- Compatibilidade com formato CNPJ existente.  
- Facilidade de integração em sistemas legados.  
- Identificação exclusiva para fundos e entidades digitais.  
- Eliminação de conflitos de numeração entre filiais.  
- Potencial internacionalização (base 36 é padrão global).

---

## 8. Previsão de Adoção

| Fase | Período | Status |
|-------|-----------|---------|
| Estudo técnico (RFB/B3/BACEN) | 2024–2025 | Em andamento |
| Sandbox regulatório | 2025–início de 2026 | Em testes piloto |
| Adoção oficial (mercado financeiro) | Julho/2026 | Previsto |
| Ampliação para outros setores | 2027+ | Planejado |

---

## 9. Créditos Técnicos

**Autor:** Edilson Salvador Ricci  
**Projeto:** Hipermercados Ricci — Módulo de Geração de Documentos  
**Tecnologia:** Java 11, Spring Boot, JDBC, Thymeleaf, SQLite  
**Última revisão:** Novembro/2025

---

© 2025 — Documento técnico de referência para implementação do CNPJ Alfanumérico Base 36 em sistemas corporativos e financeiros.
