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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import Adapter.NganSach;
import DAO.MyDatabaseHelper;
import java.util.ArrayList;
import java.util.List;

import Adapter.GiaoDich;

public class Ngansach extends AppCompatActivity {

    private ListView listView;
    private ImageButton back;
    private Button moi;
    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;

    private static final int MY_REQUEST_CODE = 1000;

    private final List<NganSach> lns = new ArrayList<>();
    private ArrayAdapter<NganSach> listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngansach);
        this.back=findViewById(R.id.imageButton2);
        this.moi=findViewById(R.id.themmoi);
        this.listView = (ListView) findViewById(R.id.listViewNS);
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<NganSach> list = db.getAllNganSach();
        this.lns.addAll(list);
        Intent intent4 = getIntent();
        this.listViewAdapter = new ArrayAdapter<NganSach>(this,android.R.layout.simple_list_item_2,android.R.id.text1,this.lns);
        this.listView.setAdapter(this.listViewAdapter);
        registerForContextMenu(this.listView);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Ngansach.this,Home.class);
                Ngansach.this.startActivity(myIntent);
            }
        });
        moi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Ngansach.this,ThemNganSach.class);
                Ngansach.this.startActivity(intent3);
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0,MENU_ITEM_VIEW,0,"View Ngan Sach");
        menu.add(0,MENU_ITEM_CREATE,1,"Create Ngan Sach");
        menu.add(0,MENU_ITEM_EDIT,2,"Edit Ngan Sach");
        menu.add(0,MENU_ITEM_DELETE,4,"Delete Ngan Sach");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final NganSach selectNs = (NganSach) this.listView.getItemAtPosition(info.position);
        if(item.getItemId()==MENU_ITEM_VIEW){
            Toast.makeText(getApplicationContext(), (int) selectNs.getTongtien(),Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId()==MENU_ITEM_CREATE){
            Intent intent = new Intent(this,ThemNganSach.class);
            this.startActivityForResult(intent,MY_REQUEST_CODE);
        }
        else if (item.getItemId()==MENU_ITEM_EDIT){
            Intent intent = new Intent(this,ThemNganSach.class);
            intent.putExtra("note",selectNs);
            this.startActivityForResult(intent,MY_REQUEST_CODE);
        }
        else if(item.getItemId()==MENU_ITEM_DELETE){
            new AlertDialog.Builder(this).setMessage(selectNs.getIdngansach()+" Confirm ?").setCancelable(false).setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int id){
                    deleteNs(selectNs);
                }
            }).setNegativeButton("No",null).show();
        }
        else{
            return false;
        }
        return true;
    }
    private void deleteNs(NganSach ns){
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteNS(ns);
        this.lns.remove(ns);
        this.listViewAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE){
            boolean needRefresh = data.getBooleanExtra("needRefresh",true);
            if(needRefresh){
                this.lns.clear();
                MyDatabaseHelper db = new MyDatabaseHelper(this);
                List<NganSach> list = db.getAllNganSach();
                this.lns.addAll(list);
                this.listViewAdapter.notifyDataSetChanged();
            }
        }
    }
}