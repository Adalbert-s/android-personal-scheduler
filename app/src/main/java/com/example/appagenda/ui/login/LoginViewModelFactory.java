package com.example.appagenda.ui.login;


import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.appagenda.data.LoginDataSource;
import com.example.appagenda.data.LoginRepository;
import com.example.appagenda.database.usuario.UsuarioDBHelper;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public LoginViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // Passando o UsuarioDBHelper para o LoginRepository
        UsuarioDBHelper usuarioDBHelper = new UsuarioDBHelper(context);
        LoginRepository loginRepository = LoginRepository.getInstance(new LoginDataSource(usuarioDBHelper));
        return (T) new LoginViewModel(loginRepository);
    }
}
