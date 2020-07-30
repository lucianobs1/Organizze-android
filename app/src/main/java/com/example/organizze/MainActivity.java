package com.example.organizze;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
                .canGoBackward(true)
                .canGoForward(false)
                .build()
        );
    }
}