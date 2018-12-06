package com.example.pegados.appdesafios.activity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.pegados.appdesafios.R;
import com.example.pegados.appdesafios.adapter.DesafiosAdapter;
import com.example.pegados.appdesafios.data.Desafio;
import com.example.pegados.appdesafios.data.DesafioDAO;

import java.io.Serializable;
import java.util.List;

public class ListDesafios extends ListActivity implements AppCompatCallback{

    private AppCompatDelegate delegate;


    private DesafioDAO desafioDAO;
    private DesafiosAdapter adapter;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        delegate = AppCompatDelegate.create(this, this);
        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_list_desafios);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Para o layout preencher toda tela do cel (remover a barra de tit.)
        delegate.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);

        //delegate.getSupportActionBar().addOnMenuVisibilityListener((ActionBar.OnMenuVisibilityListener) this);

        //Toolbar toolbar = findViewById(R.id.my_toolbar);
        //delegate.setSupportActionBar(toolbar);

        adapter = new DesafiosAdapter(this);
        setListAdapter(adapter);


        //getListView().setOnItemLongClickListener(this);

        desafioDAO = DesafioDAO.getInstance(this);

        updateList();

    }

    private void updateList(){
        List<Desafio> desafios = desafioDAO.list();
        adapter.setItems(desafios);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_desafio, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_desafio){
            Intent intent = new Intent(getApplicationContext(), EditDesafio.class);
            startActivityForResult(intent, 100);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100){
            updateList();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(getApplicationContext(), EditDesafio.class);

        intent.putExtra("desafio", (Serializable) adapter.getItem(position));
        startActivityForResult(intent, 100);
    }

    @Override
    public void onSupportActionModeStarted(ActionMode actionMode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode actionMode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
}
