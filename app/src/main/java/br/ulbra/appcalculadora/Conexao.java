package br.ulbra.appcalculadora;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pessoa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(80)," +
                "email VARCHAR(80) UNIQUE," +
                "senha VARCHAR(80))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso precise atualizar banco no futuro
    }
}
