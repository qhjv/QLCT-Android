package com.example.conspendemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import DAO.MyDatabaseHelper;
import java.util.ArrayList;
import java.util.List;

import Adapter.GiaoDich;

public class ShowGD extends AppCompatActivity {

    private ListView listView;
    private Button back;
    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;

    private static final int MY_REQUEST_CODE = 1000;

    private final List<GiaoDich> lgd = new ArrayList<>();
    private ArrayAdapter<GiaoDich> listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gd);
        this.back=findViewById(R.id.buttontrove);
        this.listView = (ListView) findViewById(R.id.listView);
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<GiaoDich> list = db.getAllGiaoDich();
        this.lgd.addAll(list);
        Intent intent5 = getIntent();
        this.listViewAdapter = new ArrayAdapter<GiaoDich>(this,android.R.layout.simple_list_item_1,android.R.id.text1,this.lgd);
        this.listView.setAdapter(this.listViewAdapter);
        registerForContextMenu(this.listView);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ShowGD.this,Home.class);
                ShowGD.this.startActivity(myIntent);
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0,MENU_ITEM_VIEW,0,"View Giao Dich");
        menu.add(0,MENU_ITEM_CREATE,1,"Create Giao Dich");
        menu.add(0,MENU_ITEM_EDIT,2,"Edit Giao Dich");
        menu.add(0,MENU_ITEM_DELETE,4,"Delete Giao Dich");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final GiaoDich selectGd = (GiaoDich) this.listView.getItemAtPosition(info.position);
        if(item.getItemId()==MENU_ITEM_VIEW){
            Toast.makeText(getApplicationContext(),selectGd.getTengd(),Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId()==MENU_ITEM_CREATE){
            Intent intent = new Intent(this,ThemGD.class);
            this.startActivityForResult(intent,MY_REQUEST_CODE);
        }
        else if (item.getItemId()==MENU_ITEM_EDIT){
            Intent intent = new Intent(this,ThemGD.class);
            intent.putExtra("note",selectGd);
            this.startActivityForResult(intent,MY_REQUEST_CODE);
        }
        else if(item.getItemId()==MENU_ITEM_DELETE){
            new AlertDialog.Builder(this).setMessage(selectGd.getIdgiaodich()+" Confirm ?").setCancelable(false).setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int id){
                    deleteGd(selectGd);
                }
            }).setNegativeButton("No",null).show();
        }
        else{
            return false;
        }
        return true;
    }
    private void deleteGd(GiaoDich gd){
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteGiaoDich(gd);
        this.lgd.remove(gd);
        this.listViewAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE){
            boolean needRefresh = data.getBooleanExtra("needRefresh",true);
            if(needRefresh){
                this.lgd.clear();
                MyDatabaseHelper db = new MyDatabaseHelper(this);
                List<GiaoDich> list = db.getAllGiaoDich();
                this.lgd.addAll(list);
                this.listViewAdapter.notifyDataSetChanged();
            }
        }
    }
}