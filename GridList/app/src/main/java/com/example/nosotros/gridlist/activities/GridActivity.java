package com.example.nosotros.gridlist.activities;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nosotros.gridlist.R;
import com.example.nosotros.gridlist.adapters.MyListAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridView gridview;
    private ListView listview;
    private List<String> fruits;
    private MyListAdapter myAdapter;
    private MyListAdapter listAdapter;
    private MenuItem listItem;
    private MenuItem gridItem;
    private int fruitCtr = 0;
    private boolean isListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        gridview = (GridView) findViewById(R.id.gridview);
        listview = (ListView) findViewById(R.id.listview);


        // Mockup element list
        fruits = new ArrayList<String>();
        fruits.add("Uva");
        fruits.add("Manzana");
        fruits.add("Pera");

        // Default adapter
        //ArrayAdapter<String> defaultAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fruits);

        //Adapter bound
        //listview.setAdapter(defaultAdapter);

        // Item click listener
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridActivity.this, "Clicked " + fruits.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridActivity.this, "Clicked " + fruits.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(this.gridview);
        registerForContextMenu(this.listview);

        myAdapter = new MyListAdapter(this, R.layout.list_item, fruits);
        listAdapter = new MyListAdapter(this, R.layout.list_item, fruits);
        gridview.setAdapter(myAdapter);
        listview.setAdapter(listAdapter);
        isListView = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        gridItem = menu.findItem(R.id.gridview_item);
        listItem = menu.findItem(R.id.listview_item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                this.fruits.add("Banana "+(++fruitCtr));
                this.myAdapter.notifyDataSetChanged();
                this.listAdapter.notifyDataSetChanged();
                return true;
            case R.id.listview_item:

                    if(isListView == false) {
                        gridview.setVisibility(View.INVISIBLE);
                        listItem.setVisible(false);

                        listview.setVisibility(View.VISIBLE);
                        gridItem.setVisible((true));

                        isListView = true;
                    }

                return true;
            case R.id.gridview_item:
                if(isListView == true){
                    listview.setVisibility(View.INVISIBLE);
                    gridItem.setVisible(false);

                    gridview.setVisibility(View.VISIBLE);
                    listItem.setVisible((true));

                    isListView = false;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position));
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_item:
                this.fruits.remove(info.position);
                this.myAdapter.notifyDataSetChanged();
                this.listAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
