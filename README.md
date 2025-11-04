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
