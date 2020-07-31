package com.example.organizze.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze.R;
import com.example.organizze.activity.config.ConfiguracaoFirebase;
import com.example.organizze.activity.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        botaoCadastrar = findViewById(R.id.btnCadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textNome = campoNome.getText().toString();
                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();

                if (textNome.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Preencha o Nome", Toast.LENGTH_SHORT).show();
                }
                if (textEmail.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Preencha o Email", Toast.LENGTH_SHORT).show();
                }
                if (textSenha.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Preencha o Nome", Toast.LENGTH_SHORT).show();
                }

                usuario = new Usuario();
                usuario.setNome(textNome);
                usuario.setEmail(textEmail);
                usuario.setSenha(textSenha);

                cadastrarUsuario();

            }
        });
    }

    public void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword
                (
                        usuario.getEmail(),
                        usuario.getSenha()
                ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful()){
                    Toast.makeText
                            (
                                    CadastroActivity.this,
                                    "Cadastro realizado com sucesso",
                                    Toast.LENGTH_SHORT
                            )
                            .show();
                }
                else{
                    Toast.makeText
                            (
                                    CadastroActivity.this,
                                    "Erro ao cadastrar usuário",
                                    Toast.LENGTH_SHORT
                            )
                            .show();
                }
            }
        });
    }
}