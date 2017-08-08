package com.example.haidarafif.crudretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HaidarAfif on 29/07/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter(Context context, List<Result> results) {

        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Result result = results.get(position);
        holder.textViewNPM.setText(result.getNpm());
        holder.textViewNama.setText(result.getNama());
        holder.textViewKelas.setText(result.getKelas());
        holder.textViewSesi.setText(result.getSesi());

        Picasso.with(context)
                .load("http://192.168.1.20/crud_android/foto/"+result.getFoto())
                .placeholder(R.drawable.user)   // optional
                .error(R.drawable.user)
                .into(holder.imgMahasiswa);
    }

    @Override
    public int getItemCount() {

        return results.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textNPM)
        TextView textViewNPM;
        @BindView(R.id.textNama) TextView textViewNama;
        @BindView(R.id.textKelas) TextView textViewKelas;
        @BindView(R.id.textSesi) TextView textViewSesi;
        @BindView(R.id.imgMahasiswa) ImageView imgMahasiswa;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            String npm = textViewNPM.getText().toString();
            String nama = textViewNama.getText().toString();
            String kelas = textViewKelas.getText().toString();
            String sesi = textViewSesi.getText().toString();

            Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("npm", npm);
            i.putExtra("nama", nama);
            i.putExtra("kelas", kelas);
            i.putExtra("sesi", sesi);
            context.startActivity(i);

        }
    }
}
