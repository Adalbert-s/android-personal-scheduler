package com.example.appagenda.data;

import com.example.appagenda.database.usuario.UsuarioDBHelper;
import com.example.appagenda.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication with login credentials and retrieves user information.
 */
public class LoginDataSource {

    private UsuarioDBHelper usuarioDBHelper;

    public LoginDataSource(UsuarioDBHelper usuarioDBHelper) {
        this.usuarioDBHelper = usuarioDBHelper;
    }

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // Verifica as credenciais no banco de dados
            boolean isValid = usuarioDBHelper.verificarLogin(username, password);

            if (isValid) {
                // Se o login for bem-sucedido, cria um usuário e retorna
                LoggedInUser user = new LoggedInUser(username, username); // Ajuste conforme necessário
                return new Result.Success<>(user);
            } else {
                // Se o login falhar
                return new Result.Error(new IOException("Invalid username or password"));
            }

        } catch (Exception e) {
            // Retorna erro em caso de exceção
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // Lógica de logout, se necessário
    }
}
