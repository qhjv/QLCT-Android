package Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conspendemo.R;

import java.util.ArrayList;

public class ListNganSach extends RecyclerView.Adapter<ListNganSach.test> {

    private ArrayList<NganSach> list;
    private Context context;

    public ListNganSach(ArrayList<NganSach> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public test onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.card, parent, false);
        return new test(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNganSach.test holder, int position) {
        NganSach a=list.get(position);
        holder.txt1.setText(a.getId()+"");
        holder.txt2.setText(a.getMuchi()+"");
        holder.txt3.setText(a.getThoigian()+" ngày");
    }

    @Override
    public int getItemCount() {
        if(list.size()>0){
            return list.size();
        }
        return 0;
    }

    public class test extends RecyclerView.ViewHolder {
        private TextView txt1, txt2, txt3;
        public test(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.txtstt);
            txt2=itemView.findViewById(R.id.txtmc);
            txt3=itemView.findViewById(R.id.txttg);
        }
    }
}
