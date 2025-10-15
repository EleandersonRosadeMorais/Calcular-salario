package br.ulbra.appcalculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail, edtSenha;
    private Button btnEntrar;
    private PessoaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnEntrar = findViewById(R.id.btnLogar);

        dao = new PessoaDAO(this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();

                if(email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Preencha email e senha", Toast.LENGTH_SHORT).show();
                    return;
                }

                Pessoa pessoa = dao.login(email, senha);

                if(pessoa != null){
                    Intent intent = new Intent(LoginActivity.this, CalculadoraActivity.class);
                    intent.putExtra("nomeUsuario", pessoa.getNome());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
