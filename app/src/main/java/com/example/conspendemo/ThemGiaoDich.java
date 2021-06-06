package com.example.conspendemo;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import Adapter.ListChiTieu;

public class ThemGiaoDich extends AppCompatActivity {
    ImageButton buttonBack;
    TextView txtDate;
    EditText editGd,editMon;
    Button btnDate, btnAdd;
    //Khai báo Datasource lưu trữ danh sách giao dịch
    ArrayList<ListChiTieu>arrGd=new ArrayList<ListChiTieu>();
    //Khai báo ArrayAdapter cho ListView
    ArrayAdapter<ListChiTieu>adapter=null;
    ListView lvGd;
    Calendar cal;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);
        getFormWidgets();
        getDefaultInfor();
        addEventFormWidgets();

        Intent intent2 = getIntent();
        this.buttonBack.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Back to previous Activity.
                Intent intent1 = new Intent(ThemGiaoDich.this,Home.class);
                ThemGiaoDich.this.startActivity(intent1);
            }


        });
    }
    public void getFormWidgets()
    {
        txtDate=(TextView)this.findViewById(R.id.txtdate);
        editGd=(EditText)this.findViewById(R.id.editgiaodich);
        editMon=(EditText)this.findViewById(R.id.editsotien);
        btnDate=(Button)this.findViewById(R.id.btndate);
        btnAdd=(Button)this.findViewById(R.id.btnthemgd);
        buttonBack = (ImageButton) this.findViewById(R.id.imageButton);
        lvGd=(ListView)this.findViewById(R.id.lvgiaodich);
        //Gán DataSource vào ArrayAdapter
        adapter=new ArrayAdapter<ListChiTieu>
                (this,
                        android.R.layout.simple_list_item_1,
                        arrGd);
        //gán Adapter vào ListView
        lvGd.setAdapter(adapter);
    }

    public void getDefaultInfor()
    {
        //lấy ngày hiện tại của hệ thống
        cal=Calendar.getInstance();
        SimpleDateFormat dft=null;
        //Định dạng ngày / tháng /năm
        dft=new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        String strDate=dft.format(cal.getTime());
        //hiển thị lên giao diện
        txtDate.setText(strDate);

        editGd.requestFocus();
        //gán cal.getTime() cho ngày hoàn thành và giờ hoàn thành
        date=cal.getTime();
    }
    public void addEventFormWidgets()
    {
        btnDate.setOnClickListener(new MyButtonEvent());
        btnAdd.setOnClickListener(new MyButtonEvent());
        lvGd.setOnItemClickListener(new MyListViewEvent());
        lvGd.setOnItemLongClickListener(new MyListViewEvent());
    }
    private class MyButtonEvent implements OnClickListener
    {
        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.btndate:
                    showDatePickerDialog();
                    break;
                case R.id.btnthemgd:
                    processAddGD();
                    break;
            }
        }

    }
    private class MyListViewEvent implements OnItemClickListener, AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            arrGd.remove(arg2);
            adapter.notifyDataSetChanged();
            return false;

        }

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(ThemGiaoDich.this,
                    arrGd.get(arg2).getMoney(),
                    Toast.LENGTH_LONG).show();

        }


    }
    public void showDatePickerDialog()
    {
        OnDateSetListener callback=new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                txtDate.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                //Lưu vết lại biến ngày hoàn thành
                cal.set(year, monthOfYear, dayOfMonth);
                date=cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s=txtDate.getText()+"";
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                ThemGiaoDich.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }
    public void processAddGD()
    {
        String title=editGd.getText()+"";
        String money=editMon.getText()+"";
        ListChiTieu giaodich=new ListChiTieu(title,money, date);
        arrGd.add(giaodich);
        adapter.notifyDataSetChanged();
        //sau khi cập nhật thì reset dữ liệu và cho focus tới editCV
        editGd.setText("");
        editMon.setText("");
        editGd.requestFocus();
    }
}