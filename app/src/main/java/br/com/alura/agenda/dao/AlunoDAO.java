package br.com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AdapterListUpdateCallback;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

    private static final String AGENDA = "Agenda";
    private static final String NOME = "nome";
    private static final String ENDERECO = "endereco";
    private static final String TELEFONE = "telefone";
    private static final String SITE = "site";
    private static final String NOTA = "nota";
    private static final String TABELA_ALUNOS = "Alunos";
    private static final String ID = "id";

    public AlunoDAO(@Nullable Context context) {
        super(context, AGENDA, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, " +
                "endereco TEXT, telefone TEXT, site TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoVelha, int versaoNova) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(aluno);

        db.insert(TABELA_ALUNOS, null, dados);
    }

    private ContentValues pegaDadosDoAluno(Aluno aluno) {
        ContentValues dados = new ContentValues();

        dados.put(NOME, aluno.getNome());
        dados.put(ENDERECO, aluno.getEndereco());
        dados.put(TELEFONE, aluno.getTelefone());
        dados.put(SITE, aluno.getSite());
        dados.put(NOTA, aluno.getNota());
        return dados;
    }

    public List<Aluno> buscaAlunos() {
        String sql = "SELECT * FROM Alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        ArrayList<Aluno> alunos = new ArrayList<>();

        while (c.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setNome(c.getString(c.getColumnIndex(NOME)));
            aluno.setNota(c.getDouble(c.getColumnIndex(NOTA)));
            aluno.setEndereco(c.getString(c.getColumnIndex(ENDERECO)));
            aluno.setTelefone(c.getString(c.getColumnIndex(TELEFONE)));
            aluno.setSite(c.getString(c.getColumnIndex(SITE)));
            aluno.setId(c.getLong(c.getColumnIndex(ID)));

            alunos.add(aluno);
        }
        c.close();
        return alunos;
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = pegaParametro(aluno);
        db.delete(TABELA_ALUNOS, "id = ?", params);
    }

    private String[] pegaParametro(Aluno aluno) {
        return new String[]{aluno.getId().toString()};
    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDoAluno(aluno);
        String[] params = pegaParametro(aluno);
        db.update(TABELA_ALUNOS,dados,"id = ?",params);

    }
}
