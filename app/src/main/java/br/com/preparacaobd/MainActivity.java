package br.com.preparacaobd;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;

public class MainActivity extends AppCompatActivity {

    private FilmeAdapter adapter;
    private RecyclerView recyclerView;
    private DBHelper mydb;

    private static final int REQUEST_EDIT = 1;
    private static final int REQUEST_INSERT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mydb = new DBHelper(this);

        adapter = new FilmeAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.filmesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    public void inserirFilme(View view) {
        Intent intent = new Intent(this, InsertFilmActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("request_code", REQUEST_INSERT);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_INSERT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_INSERT)
            if (resultCode == Activity.RESULT_OK){
                Filme f = data.getParcelableExtra("filme");
                mydb.insertFilme(f);
                adapter.inserir(f);
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuLoad) {
            adapter.setListaFilmes(mydb.getAllMovies());
        }
        return super.onOptionsItemSelected(item);
    }
}
