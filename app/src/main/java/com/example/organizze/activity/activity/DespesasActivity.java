package com.example.organizze.activity.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze.R;
import com.example.organizze.activity.config.ConfiguracaoFirebase;
import com.example.organizze.activity.helper.Base64Custom;
import com.example.organizze.activity.helper.DateCustom;
import com.example.organizze.activity.model.Movimentacao;
import com.example.organizze.activity.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);


        campoValor = findViewById(R.id.editValor);
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);

//        Preenche o campo data com o date atual
        campoData.setText(DateCustom.dataAtual());

        recuperarDespesaTotal();

    }

    public void salvarDespesa(View view){

        try {
            if(validarCamposDespesa()) {

                movimentacao = new Movimentacao();
                String data = campoData.getText().toString();
                Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());

                movimentacao.setValor(valorRecuperado);
                movimentacao.setCategoria(campoCategoria.getText().toString());
                movimentacao.setDescricao(campoDescricao.getText().toString());
                movimentacao.setData(data);
                movimentacao.setTipo("d");

                Double despesaAtualizada = despesaTotal + valorRecuperado;

                atualizarDespesa(despesaAtualizada);

                movimentacao.Salvar(data);

               finish();
            }
        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public Boolean validarCamposDespesa(){

        String textValor = campoValor.getText().toString();
        String textData = campoData.getText().toString();
        String textCategoria = campoCategoria.getText().toString();
        String textDescricao = campoDescricao.getText().toString();

        try {
           if(textValor.isEmpty()){
               throw new Exception("Preencha Valor");
           }

            if(textData.isEmpty()){
              throw new Exception("Preencha a Data");
            }

            if(textCategoria.isEmpty()){
                throw new Exception("Preencha Categoria");
            }

            if (textDescricao.isEmpty()){
                throw new Exception("Preencha a descrição");
            }

            return true;
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage() , Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void atualizarDespesa(Double despesa){

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.child("despesaTotal").setValue(despesa);

    }

    public void recuperarDespesaTotal(){

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);

        DatabaseReference usuarioRef = firebaseRef
                .child("usuarios")
                .child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}