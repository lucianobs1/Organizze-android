package com.example.organizze.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizze.R;
import com.example.organizze.activity.model.Movimentacao;

import java.util.List;

public class AdapterMovimentacao extends RecyclerView.Adapter<AdapterMovimentacao.Myholder> {

    List<Movimentacao> movimentacoes;
    Context context;

    public AdapterMovimentacao(List<Movimentacao> movimentacoes, Context context){
        this.movimentacoes = movimentacoes;
        this.context = context;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemLista =
               LayoutInflater.from(parent.getContext())
               .inflate(R.layout.adapter_movimentacao, parent, false);

       return new Myholder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        Movimentacao movimentacao = movimentacoes.get(position);
        holder.titulo.setText(movimentacao.getDescricao());
        holder.valor.setText(String.valueOf(movimentacao.getValor()));
        holder.categoria.setText(movimentacao.getCategoria());

        if(movimentacao.getTipo() == "d" || movimentacao.getTipo().equals("d")){
            holder.valor.setTextColor(context.getResources().getColor(R.color.colorAccentDespesa));
            holder.valor.setText("-" + movimentacao.getValor());
        }

        if(movimentacao.getTipo() == "d" || movimentacao.getTipo().equals("d")){
            holder.valor.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.valor.setText("-" + movimentacao.getValor());
        }
    }

    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    public class Myholder extends RecyclerView.ViewHolder{

        TextView titulo, valor, categoria;

        public Myholder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.txtTitulo);
            valor = itemView.findViewById(R.id.txtValor);
            categoria = itemView.findViewById(R.id.txtCategoria);
        }
    }
}
