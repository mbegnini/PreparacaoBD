package br.com.preparacaobd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class InsertFilmActivity extends AppCompatActivity {

    private EditText tituloEditText;
    private EditText generoEditText;
    private EditText anoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_film);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tituloEditText = (EditText) findViewById(R.id.tituloEditText);
        generoEditText = (EditText) findViewById(R.id.generoEditText);
        anoEditText = (EditText) findViewById(R.id.anoEditText);
    }

    public void Insert(View view){
        Filme filme = new Filme();
        filme.setTitulo(tituloEditText.getText().toString());
        filme.setGenero(generoEditText.getText().toString());
        filme.setAno(Integer.valueOf(anoEditText.getText().toString()));

        Intent returnIntent = new Intent();
        Bundle returnBundle = new Bundle();
        returnBundle.putParcelable("filme", filme);
        returnIntent.putExtras(returnBundle);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

}
