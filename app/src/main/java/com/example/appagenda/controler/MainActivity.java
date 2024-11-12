package com.example.appagenda.controler;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.appagenda.model.Agenda;
import com.example.appagenda.model.Usuario;
import com.example.appagenda.model.EntryFragment;
import com.example.appagenda.model.ViewFragment;
import com.example.appagenda.R;


public class MainActivity extends AppCompatActivity {

    private Agenda agenda;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando os objetos
        agenda = new Agenda();
        usuario = new Usuario("Nome Exemplo");

        showFragment(new EntryFragment(), R.id.fragmentContainer1);
        showFragment(new ViewFragment(), R.id.fragmentContainer2);

    }

    private void showFragment(Fragment fragment, int containerId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }
}
