package com.example.reciclercard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;
    private RecyclerView mReciclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager gridLayoutManager;

    private int counter = 0;
    private boolean isList = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = getAllNames();

        mReciclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 3);
        mAdapter = new MyAdapter(names, R.layout.recicler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                //Toast.makeText(MainActivity.this, name + "-" + position, Toast.LENGTH_SHORT).show();
                deleteName(position);
            }
        });

        mReciclerView.setHasFixedSize(true);
        mReciclerView.setItemAnimator(new DefaultItemAnimator());

        mReciclerView.setLayoutManager(mLayoutManager);
        mReciclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.addItem:
                this.addName(0);
                return true;
            case R.id.viewItem:
                if(isList == true) {
                    mReciclerView.setLayoutManager(gridLayoutManager);
                    isList = false;
                } else {
                    mReciclerView.setLayoutManager(mLayoutManager);
                    isList = true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private List<String> getAllNames() {
        return new ArrayList<String>() {{
            add("Alejandro");
            add("Andres");
            add("Martin");
            add("Lucas");
            add("Ruben");
            add("Jose");
        }};
    }

    private void addName(int position) {

        names.add(position, "New Name " + (++this.counter));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

    private void deleteName (int position) {
        names.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
