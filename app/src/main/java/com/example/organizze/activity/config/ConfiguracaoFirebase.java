package com.example.organizze.activity.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autenticacao;

    //retorna a instancia do firebase
    public static FirebaseAuth getFirebaseAutenticacao(){
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;
    }
}
