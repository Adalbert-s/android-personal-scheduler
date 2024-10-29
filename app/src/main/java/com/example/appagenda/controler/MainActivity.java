package com.example.appagenda.controler;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appagenda.model.Agenda;
import com.example.appagenda.model.Usuario;
import com.example.appagenda.R;

public class MainActivity extends AppCompatActivity {

    private Agenda agenda;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // O arquivo XML associado

        // Inicializando os objetos
        agenda = new Agenda();
        usuario = new Usuario("Nome Exemplo");

        // Ligação com os elementos da interface
        EditText inputNome = findViewById(R.id.inputNome);
        Button btnAdicionarCompromisso = findViewById(R.id.btnAdicionarCompromisso);

        // Exemplo de evento de clique para o botão
        btnAdicionarCompromisso.setOnClickListener(view -> {
            String nomeUsuario = inputNome.getText().toString();
            usuario.setNome(nomeUsuario);
            // Aqui você pode adicionar mais lógica para compromissos
        });
    }
}
