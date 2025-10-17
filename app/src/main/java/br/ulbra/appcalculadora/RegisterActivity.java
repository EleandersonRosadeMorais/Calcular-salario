package br.ulbra.appcalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends BaseActivity {
    // Tela de cadastro - cria novo usuário

    private EditText edtNome, edtEmail, edtSenha;
    private Button btnRegistrar;
    private PessoaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        dao = new PessoaDAO(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pega dados do formulário
                String nome = edtNome.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();

                // Validações
                if(nome.isEmpty() || nome.length() > 80) {
                    Toast.makeText(RegisterActivity.this, "Nome inválido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Email obrigatório", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(senha.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Senha muito curta", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Cria novo usuário e salva no banco
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(nome);
                pessoa.setEmail(email);
                pessoa.setSenha(senha);

                long id = dao.inserir(pessoa);

                if(id > 0){
                    Toast.makeText(RegisterActivity.this, "Cadastro realizado!", Toast.LENGTH_LONG).show();
                    finish(); // Volta para tela anterior
                } else {
                    Toast.makeText(RegisterActivity.this, "Erro ao cadastrar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}