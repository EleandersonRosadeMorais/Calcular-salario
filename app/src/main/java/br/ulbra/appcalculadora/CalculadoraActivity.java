package br.ulbra.appcalculadora;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CalculadoraActivity extends AppCompatActivity {

    private EditText edtNomeFuncionario, edtSalarioBruto, edtNumeroFilhos;
    private RadioGroup radioGroupSexo;
    private Button btnCalcular;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        edtNomeFuncionario = findViewById(R.id.edtNome);
        edtSalarioBruto = findViewById(R.id.edtSalario);
        edtNumeroFilhos = findViewById(R.id.edtNumeroFilhos);
        radioGroupSexo = findViewById(R.id.radioGroupSexo);
        btnCalcular = findViewById(R.id.btnCalcular);
        textResultado = findViewById(R.id.textResultado);

        // Recuperar nome do usuário logado, se quiser pré preencher (opcional)
        String nomeUsuario = getIntent().getStringExtra("nomeUsuario");
        if(nomeUsuario != null){
            edtNomeFuncionario.setText(nomeUsuario);
        }

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeFunc = edtNomeFuncionario.getText().toString().trim();
                String salarioStr = edtSalarioBruto.getText().toString().trim();
                String numFilhosStr = edtNumeroFilhos.getText().toString().trim();

                int sexoId = radioGroupSexo.getCheckedRadioButtonId();

                // Validações
                if (nomeFunc.isEmpty() || nomeFunc.length() > 80) {
                    Toast.makeText(CalculadoraActivity.this, "Nome do funcionário inválido (máximo 80 caracteres)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(salarioStr)) {
                    Toast.makeText(CalculadoraActivity.this, "Informe o salário bruto", Toast.LENGTH_SHORT).show();
                    return;
                }

                double salarioBruto;
                try {
                    salarioBruto = Double.parseDouble(salarioStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(CalculadoraActivity.this, "Salário inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (salarioBruto <= 0 || salarioBruto > 1_000_000) {
                    Toast.makeText(CalculadoraActivity.this, "Salário deve ser maior que zero e menor ou igual a 1.000.000", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(numFilhosStr)) {
                    Toast.makeText(CalculadoraActivity.this, "Informe o número de filhos", Toast.LENGTH_SHORT).show();
                    return;
                }

                int numeroFilhos;
                try {
                    numeroFilhos = Integer.parseInt(numFilhosStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(CalculadoraActivity.this, "Número de filhos inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (numeroFilhos < 0) {
                    Toast.makeText(CalculadoraActivity.this, "Número de filhos não pode ser negativo", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sexoId == -1) {
                    Toast.makeText(CalculadoraActivity.this, "Selecione o sexo", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton rbSelecionado = findViewById(sexoId);
                String sexo = rbSelecionado.getText().toString();

                // Cálculo do INSS (taxa flat conforme faixa)
                double descontoINSS;
                if (salarioBruto <= 1212.00) {
                    descontoINSS = salarioBruto * 0.075;
                } else if (salarioBruto <= 2427.35) {
                    descontoINSS = salarioBruto * 0.09;
                } else if (salarioBruto <= 3641.03) {
                    descontoINSS = salarioBruto * 0.12;
                } else if (salarioBruto <= 7087.22) {
                    descontoINSS = salarioBruto * 0.14;
                } else {
                    // Caso ultrapasse teto INSS, desconto é sobre teto máximo
                    descontoINSS = 7087.22 * 0.14;
                }

                // Cálculo do IR (taxa flat conforme faixa)
                double descontoIR;
                if (salarioBruto <= 1903.98) {
                    descontoIR = 0;
                } else if (salarioBruto <= 2826.65) {
                    descontoIR = salarioBruto * 0.075;
                } else if (salarioBruto <= 3751.05) {
                    descontoIR = salarioBruto * 0.15;
                } else if (salarioBruto <= 4664.68) {
                    descontoIR = salarioBruto * 0.225;
                } else {
                    descontoIR = salarioBruto * 0.275;  // Faixa acima de 4664,68 (adicionado para casos maiores)
                }

                // Salário-família
                double salarioFamilia = 0;
                if (salarioBruto <= 1212.00) {
                    salarioFamilia = 56.47 * numeroFilhos;
                }

                // Salário líquido
                double salarioLiquido = salarioBruto - descontoINSS - descontoIR + salarioFamilia;

                // Montar resultado com tratamento Sr. ou Sra.
                String tratamento = sexo.equalsIgnoreCase("Masculino") ? "Sr." : "Sra.";

                StringBuilder resultado = new StringBuilder();
                resultado.append(tratamento).append(" ").append(nomeFunc).append("\n\n");
                resultado.append(String.format("Salário Bruto: R$ %.2f\n", salarioBruto));
                resultado.append("Descontos:\n");
                resultado.append(String.format("- INSS: R$ %.2f\n", descontoINSS));
                resultado.append(String.format("- IR: R$ %.2f\n", descontoIR));
                if (salarioFamilia > 0) {
                    resultado.append(String.format("Salário-Família: R$ %.2f\n", salarioFamilia));
                }
                resultado.append("\n");
                resultado.append(String.format("Salário Líquido: R$ %.2f", salarioLiquido));

                textResultado.setText(resultado.toString());

                // Opcional: se quiser limpar o formulário após cálculo, ou criar botão "Novo Cálculo"
            }
        });}}
