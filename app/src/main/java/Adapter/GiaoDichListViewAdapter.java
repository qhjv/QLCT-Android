package Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.conspendemo.R;

import java.util.ArrayList;

public class GiaoDichListViewAdapter extends BaseAdapter {
    final ArrayList<GiaoDich> listGiaodich;
    GiaoDichListViewAdapter(ArrayList<GiaoDich> listGiaodich){
        this.listGiaodich = listGiaodich;
    }
    @Override
    public int getCount(){
        return listGiaodich.size();
    }
    @Override
    //pos = position
    public Object getItem(int pos){
        return listGiaodich.get(pos);
    }
    @Override
    public long getItemId(int pos){
        return listGiaodich.get(pos).giaodichID;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        View viewGiaodich;
        if(convertView==null){
            viewGiaodich = View.inflate(parent.getContext(),R.layout.activity_show_gd,null);
        } else viewGiaodich = convertView;
        GiaoDich giaodich = (GiaoDich) getItem(pos);
        ((TextView) viewGiaodich.findViewById(R.id.gdidtext)).setText(String.format("ID = %",giaodich.giaodichID));
        ((TextView) viewGiaodich.findViewById(R.id.gdnametext)).setText(String.format("Ten giao dich = %",giaodich.name));
        ((TextView) viewGiaodich.findViewById(R.id.gdpricetext)).setText(String.format("Gia tri = %",giaodich.price));
        return viewGiaodich;
    }
}

