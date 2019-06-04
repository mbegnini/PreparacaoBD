package br.com.preparacaobd;

import android.os.Parcel;
import android.os.Parcelable;

public class Filme implements Parcelable {

    private int id;
    private String titulo;
    private String genero;
    private int ano;

    public Filme(int id, String titulo, String genero, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
    }

    public Filme(String titulo, String genero, int ano) {
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
    }

    public Filme() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titulo);
        dest.writeString(genero);
        dest.writeInt(ano);
    }

    public void readFromParcel(Parcel parcel){
        this.id = parcel.readInt();
        this.titulo = parcel.readString();
        this.genero = parcel.readString();
        this.ano = parcel.readInt();
    }

    public static final Parcelable.Creator<Filme> CREATOR = new Parcelable.Creator<Filme>(){
        @Override
        public Filme createFromParcel (Parcel p){
            Filme f = new Filme();
            f.readFromParcel(p);
            return f;
        }
        @Override
        public Filme[] newArray(int size){
            return new Filme[size];
        }
    };

}
