package com.nguyenhoa.nguyenthihoa_kt2_b2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.VeViewHolder>{
    private List<VeMayBay> list;

    public RecyclerViewAdapter() {
        list=new ArrayList<>();
    }
    public void setVMB(List<VeMayBay> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public VeMayBay getVMB(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public VeViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.ve_card,parent,false);
        return new VeViewHolder(v);
    }
    public void onBindViewHolder(@NonNull VeViewHolder holder, int position) {
        //tat ca la String
        VeMayBay s=list.get(position);
        holder.id.setText(String.valueOf(s.getId()));
        holder.tvName.setText(s.getName());
        holder.tvDate.setText("Ngay bat dau: "+s.getDateStart());
        holder.tvHL.setText("Hanh li: "+String.valueOf(s.isHanhli()));
        holder.tvLocation.setText(s.getLocation());

    }
    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }
    class VeViewHolder extends RecyclerView.ViewHolder
             {
        private TextView tvName, tvLocation, tvDate, tvHL, id;
//        private View itemView;
        public VeViewHolder(@NonNull View v) {
            super(v);
            id = v.findViewById(R.id.txtID);
            tvName=v.findViewById(R.id.txtName);
            tvDate=v.findViewById(R.id.txtdate);
            tvLocation=v.findViewById(R.id.txtlocation);
            tvHL = v.findViewById(R.id.txtkl);
        }

    }
}
