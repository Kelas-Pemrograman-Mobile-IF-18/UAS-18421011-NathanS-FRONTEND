package com.nathans.steam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.nathans.steam.Model.ModelCart;
import com.nathans.steam.R;
import java.util.List;

public class AdapterCart extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelCart> item;

    public AdapterCart(Activity activity, List<ModelCart> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_cart, null);


        TextView game        = (TextView) convertView.findViewById(R.id.namaGame);
        TextView price       = (TextView) convertView.findViewById(R.id.harga);

        game.setText(item.get(position).getNamaGame());
        price.setText(item.get(position).getHarga());
        return convertView;
    }
}
