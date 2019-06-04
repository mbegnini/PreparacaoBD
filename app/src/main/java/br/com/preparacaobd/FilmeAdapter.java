package br.com.preparacaobd;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter {

    private List<Filme> listaFilmes;

    public FilmeAdapter(){
        listaFilmes = new ArrayList<Filme>();
    }

    public List<Filme> getListaFilmes() {
        return listaFilmes;
    }

    public void setListaFilmes(List<Filme> novaListaFilmes) {
        while(getItemCount()>0)
            remover(0);

        for (Filme filme: novaListaFilmes)
            inserir(filme);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filme_row, viewGroup, false);
        FilmeViewHolder holder = new FilmeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FilmeViewHolder holder = (FilmeViewHolder) viewHolder;
        holder.tituloTextView.setText(listaFilmes.get(i).getTitulo());
        holder.generoTextView.setText(listaFilmes.get(i).getGenero());
        holder.anoTextView.setText(String.valueOf(listaFilmes.get(i).getAno()));
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public void inserir(Filme filme){
        listaFilmes.add(filme);
        notifyItemInserted(getItemCount());
    }

    public void update(Filme filme, int position){
        listaFilmes.get(position).setTitulo(filme.getTitulo());
        listaFilmes.get(position).setGenero(filme.getGenero());
        listaFilmes.get(position).setAno(filme.getAno());
        notifyItemChanged(position);
    }

    public void remover(int position){
        listaFilmes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,this.getItemCount());
    }

    public static class FilmeViewHolder extends RecyclerView.ViewHolder {

        TextView tituloTextView;
        TextView generoTextView;
        TextView anoTextView;

        public FilmeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
            tituloTextView = (TextView) itemView.findViewById(R.id.tituloTextView);
            generoTextView = (TextView) itemView.findViewById(R.id.generoTextView);
            anoTextView = (TextView) itemView.findViewById(R.id.anoTextView);
        }

    }
}
