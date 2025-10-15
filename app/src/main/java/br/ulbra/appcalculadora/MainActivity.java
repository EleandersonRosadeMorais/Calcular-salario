package br.ulbra.appcalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
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
                String nome = edtNome.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();

                if(nome.isEmpty() || nome.length() > 80) {
                    Toast.makeText(MainActivity.this, "Nome não pode estar vazio e deve ter até 80 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Email é obrigatório", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(senha.length() < 6) {
                    Toast.makeText(MainActivity.this, "Senha deve ter no mínimo 6 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                Pessoa pessoa = new Pessoa();
                pessoa.setNome(nome);
                pessoa.setEmail(email);
                pessoa.setSenha(senha);

                long id = dao.inserir(pessoa);

                if(id > 0){
                    Toast.makeText(MainActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                    finish(); // volta para tela de login após cadastro
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao cadastrar. Verifique os dados.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
