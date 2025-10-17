# 🧮 App Calculadora de Salário
> Aplicativo Android em **Java** para cálculo de salário líquido com sistema de autenticação.

---

## 📱 Visão Geral

O **App Calculadora de Salário** é um aplicativo Android desenvolvido para **calcular salário líquido** considerando descontos de INSS, IR e benefícios.  
Com uma **interface moderna e intuitiva**, o app permite cadastro de usuários e cálculo detalhado da remuneração líquida.

---

## 🚀 Funcionalidades

✅ **Cadastro e Login** – Sistema completo de autenticação com banco SQLite

✅ **Cálculo Automático** – Calcula INSS, IR e salário-família automaticamente

✅ **Validações** – Verifica todos os campos antes do cálculo

✅ **Interface Moderna** – Design com tema vermelho e preto

✅ **Animações Suaves** – Transições entre telas com efeitos visuais

✅ **Persistência** – Dados salvos localmente no dispositivo

---

## 🛠️ Tecnologias Utilizadas

- **Java** – Linguagem principal
- **Android SDK** – Framework de desenvolvimento
- **SQLite** – Banco de dados local
- **XML** – Criação de layouts e interfaces
- **Material Design** – Componentes de UI modernos
- **ConstraintLayout** – Layouts responsivos
- **Gradle** – Gerenciamento de dependências

---

## ⚙️ Como Executar

**Pré-requisitos:**
- Android Studio (versão mais recente)
- Emulador Android ou dispositivo físico com API 21+

**Passos:**
```bash
# 1. Clone o repositório
git clone https://github.com/EleandersonRosadeMorais/AppCalculadoraSalario

# 2. Acesse a pasta
cd AppCalculadoraSalario

# 3. Abra no Android Studio
"C:\Program Files\Android\Android Studio\bin\studio64.exe" .

# 4. Execute o app
# (Shift + F10 ou Run 'app')

---

## 📂 Estrutura do Projeto

```bash
📦 app
├── 📂 manifests/
│   └── AndroidManifest.xml
├── 📂 java/
│   └── 📂 br.ulbra.appcalculadora/
│       ├── BaseActivity.java
│       ├── SplashActivity.java
│       ├── MainActivity.java
│       ├── LoginActivity.java
│       ├── RegisterActivity.java
│       ├── CalculadoraActivity.java
│       ├── Pessoa.java
│       ├── PessoaDAO.java
│       └── Conexao.java
└── 📂 res/
     ├── 📂 anim/
     ├── 📂 drawable/
     ├── 📂 layout/
     │   ├── activity_splash.xml
     │   ├── activity_main.xml
     │   ├── activity_logar.xml
     │   ├── activity_registrar.xml
     │   └── activity_calculadora.xml
     ├── 📂 mipmap/
     ├── 📂 values/
     └── 📂 xml/
 📂 Gradle Scripts/
```

---


## 💻 Exemplo de Código

```java
// Cálculo do salário líquido
double descontoINSS;
if (salarioBruto <= 1212.00) {
    descontoINSS = salarioBruto * 0.075;
} else if (salarioBruto <= 2427.35) {
    descontoINSS = salarioBruto * 0.09;
} else {
    descontoINSS = salarioBruto * 0.12;
}

double descontoIR;
if (salarioBruto <= 1903.98) {
    descontoIR = 0;
} else if (salarioBruto <= 2826.65) {
    descontoIR = salarioBruto * 0.075;
} else {
    descontoIR = salarioBruto * 0.15;
}

double salarioLiquido = salarioBruto - descontoINSS - descontoIR;
```

---

## 🎯 Objetivo do Projeto

Projeto desenvolvido com fins educacionais, com foco em:
-Práticas de programação Java para Android
-Implementação de sistema de autenticação
-Uso de banco de dados SQLite para persistência
-Cálculos financeiros com regras tributárias brasileiras
-Desenvolvimento de interface moderna com animações
-Organização de código seguindo boas práticas de arquitetura

---

## 👤 Autor

**Eleanderson Rosa de Morais**  
📧 eleandersonmorais@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/eleanderson-rosa-de-morais-9aaab9324/)  
🔗 [GitHub](https://github.com/EleandersonRosadeMorais/)

---

## 💬 Contato

Dúvidas, sugestões ou colaborações?  
Entre em contato por email ou via redes sociais.  
Contribuições são bem-vindas! 🚀
