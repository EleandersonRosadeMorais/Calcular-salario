package br.ulbra.appcalculadora;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CalculadoraActivity extends BaseActivity {
    // Tela principal - calcula salário líquido com descontos

    private EditText edtNomeFuncionario, edtSalarioBruto, edtNumeroFilhos;
    private RadioGroup radioGroupSexo;
    private Button btnCalcular;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        // Encontra os componentes na tela
        edtNomeFuncionario = findViewById(R.id.edtNome);
        edtSalarioBruto = findViewById(R.id.edtSalario);
        edtNumeroFilhos = findViewById(R.id.edtNumeroFilhos);
        radioGroupSexo = findViewById(R.id.radioGroupSexo);
        btnCalcular = findViewById(R.id.btnCalcular);
        textResultado = findViewById(R.id.textResultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pega os valores digitados
                String nomeFunc = edtNomeFuncionario.getText().toString().trim();
                String salarioStr = edtSalarioBruto.getText().toString().trim();
                String numFilhosStr = edtNumeroFilhos.getText().toString().trim();

                int sexoId = radioGroupSexo.getCheckedRadioButtonId();

                // Validações dos campos
                if (nomeFunc.isEmpty() || nomeFunc.length() > 80) {
                    Toast.makeText(CalculadoraActivity.this, "Nome inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(salarioStr)) {
                    Toast.makeText(CalculadoraActivity.this, "Informe o salário", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Converte para números
                double salarioBruto = Double.parseDouble(salarioStr);
                int numeroFilhos = Integer.parseInt(numFilhosStr);

                if (sexoId == -1) {
                    Toast.makeText(CalculadoraActivity.this, "Selecione o sexo", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Cálculo do INSS (desconto previdenciário)
                double descontoINSS;
                if (salarioBruto <= 1212.00) {
                    descontoINSS = salarioBruto * 0.075;
                } else if (salarioBruto <= 2427.35) {
                    descontoINSS = salarioBruto * 0.09;
                } else if (salarioBruto <= 3641.03) {
                    descontoINSS = salarioBruto * 0.12;
                } else {
                    descontoINSS = salarioBruto * 0.14;
                }

                // Cálculo do IR (imposto de renda)
                double descontoIR;
                if (salarioBruto <= 1903.98) {
                    descontoIR = 0;
                } else if (salarioBruto <= 2826.65) {
                    descontoIR = salarioBruto * 0.075;
                } else if (salarioBruto <= 3751.05) {
                    descontoIR = salarioBruto * 0.15;
                } else {
                    descontoIR = salarioBruto * 0.275;
                }

                // Salário-família (benefício por filho)
                double salarioFamilia = 0;
                if (salarioBruto <= 1212.00) {
                    salarioFamilia = 56.47 * numeroFilhos;
                }

                // Cálculo final
                double salarioLiquido = salarioBruto - descontoINSS - descontoIR + salarioFamilia;

                // Monta o resultado
                RadioButton rbSelecionado = findViewById(sexoId);
                String sexo = rbSelecionado.getText().toString();
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
            }
        });
    }
}