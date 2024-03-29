package br.com.preparacaobd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AulaBD.db";
    public static final String FILMES_TABLE_NAME = "filmes";
    public static final String FILMES_COLUMN_ID = "id";
    public static final String FILMES_COLUMN_TITULO = "titulo";
    public static final String FILMES_COLUMN_GENERO = "genero";
    public static final String FILMES_COLUMN_ANO = "ano";

    public DBHelper(Context context){
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FILMES_TABLE_NAME
                + "("+FILMES_COLUMN_ID + " integer primary key, "
                + FILMES_COLUMN_TITULO + " text, "
                + FILMES_COLUMN_GENERO + " text, "
                + FILMES_COLUMN_ANO + " integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO
        db.execSQL("DROP TABLE IF EXISTS "+FILMES_TABLE_NAME);
        onCreate(db);
    }

    public long insertFilme(Filme filme){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILMES_COLUMN_TITULO, filme.getTitulo());
        contentValues.put(FILMES_COLUMN_GENERO, filme.getGenero());
        contentValues.put(FILMES_COLUMN_ANO, filme.getAno());
        long i = db.insert(FILMES_TABLE_NAME, null, contentValues);
        db.close();
        return i;
    }

    public Filme getFilme(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ FILMES_TABLE_NAME +" where "
                + FILMES_COLUMN_ID +"="+id+"", null );
        res.moveToFirst();
        Filme filme = new Filme(res.getInt(res.getColumnIndex(FILMES_COLUMN_ID)),
                res.getString(res.getColumnIndex(FILMES_COLUMN_TITULO)),
                res.getString(res.getColumnIndex(FILMES_COLUMN_GENERO)),
                res.getInt(res.getColumnIndex(FILMES_COLUMN_ANO)));
        db.close();
        return filme;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FILMES_TABLE_NAME);
        db.close();
        return numRows;
    }

    public int updateFilme(Filme filme){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILMES_COLUMN_TITULO, filme.getTitulo());
        contentValues.put(FILMES_COLUMN_GENERO, filme.getGenero());
        contentValues.put(FILMES_COLUMN_ANO, filme.getAno());
        db.close();
        return db.update(FILMES_TABLE_NAME, contentValues, FILMES_COLUMN_ID+" = ?", new String[] {Integer.toString(filme.getId())} );
    }

    public Integer deleteFilme (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i =  db.delete(FILMES_TABLE_NAME,
                FILMES_COLUMN_ID+" = ?",
                new String[] { Integer.toString(id) });
        db.close();
        return i;
    }

    public ArrayList<Filme> getAllMovies(){
        ArrayList<Filme> filmes = new ArrayList<Filme>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+FILMES_TABLE_NAME, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            filmes.add(new Filme(res.getInt(res.getColumnIndex(FILMES_COLUMN_ID)),
                            res.getString(res.getColumnIndex(FILMES_COLUMN_TITULO)),
                            res.getString(res.getColumnIndex(FILMES_COLUMN_GENERO)),
                            res.getInt(res.getColumnIndex(FILMES_COLUMN_ANO))));
            res.moveToNext();
        }
        db.close();
        return filmes;
    }
}
