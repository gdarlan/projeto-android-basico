package br.com.alura.agenda.helper;


import android.widget.EditText;
import android.widget.RatingBar;

import br.com.alura.agenda.R;
import br.com.alura.agenda.modelo.Aluno;
import br.com.alura.agenda.ui.activity.FomularioActivity;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoSite;
    private final EditText campoTelefone;
    private final RatingBar campoNota;

    private Aluno aluno;

    public FormularioHelper(FomularioActivity activity) {

        campoNome = activity.findViewById(R.id.formulario_nome);
        campoEndereco = activity.findViewById(R.id.formulario_endereco);
        campoSite = activity.findViewById(R.id.formulario_site);
        campoTelefone = activity.findViewById(R.id.formulario_telefone);
        campoNota = activity.findViewById(R.id.formulario_nota);
        aluno = new Aluno();
    }

    public Aluno pegaAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setTelefone(campoSite.getText().toString());
        aluno.setNota((double) campoNota.getProgress());
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoNota.setProgress(aluno.getNota().intValue());
        campoSite.setText(aluno.getSite());
        campoTelefone.setText(aluno.getTelefone());
        this.aluno = aluno;
    }
}
