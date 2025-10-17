package br.ulbra.appcalculadora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PessoaDAO {
    // Data Access Object - operações com banco de dados

    private Conexao conexao;
    private SQLiteDatabase banco;

    public PessoaDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    // Insere nova pessoa no banco
    public long inserir(Pessoa pessoa){
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("email", pessoa.getEmail());
        values.put("senha", pessoa.getSenha());
        return banco.insert("pessoa", null, values);
    }

    // Verifica login - busca por email e senha
    public Pessoa login(String email, String senha){
        Pessoa pessoa = null;
        String sql = "SELECT * FROM pessoa WHERE email = ? AND senha = ?";
        Cursor cursor = banco.rawQuery(sql, new String[]{email, senha});

        if(cursor.moveToFirst()){
            pessoa = new Pessoa();
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setEmail(cursor.getString(2));
            pessoa.setSenha(cursor.getString(3));
        }
        cursor.close();
        return pessoa;
    }
}