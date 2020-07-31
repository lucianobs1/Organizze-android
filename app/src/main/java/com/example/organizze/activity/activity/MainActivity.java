package com.example.organizze.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organizze.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        CarregarSlides();
    }

    public void CarregarSlides(){

        //Configurações de exibição de botoões
        setButtonBackVisible(false);
        setButtonNextVisible(false);


        this.addSlide(new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .fragment(R.layout.intro_1)
                .build()
        );

        this.addSlide(new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .fragment(R.layout.intro_2)
                .build()
        );

        this.addSlide(new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .fragment(R.layout.intro_3)
                .build()
        );

        this.addSlide(new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .fragment(R.layout.intro_4)
                .build()
        );

        this.addSlide(new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .fragment(R.layout.intro_cadastro)
                .canGoBackward(true)
                .canGoForward(false)
                .build()
        );
    }

    public void btnEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btnCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}