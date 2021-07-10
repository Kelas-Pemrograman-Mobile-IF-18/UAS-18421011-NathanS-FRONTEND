package com.nathans.steam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nathans.steam.Model.ModelWallet;
import com.nathans.steam.R;
import com.nathans.steam.Server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterWallet extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelWallet> item;

    public AdapterWallet(Activity activity, List<ModelWallet> item) {
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
            convertView = inflater.inflate(R.layout.content_wallet, null);


        TextView type        = (TextView) convertView.findViewById(R.id.walletType);
        TextView price       = (TextView) convertView.findViewById(R.id.walletPrc);
        ImageView pics       = (ImageView) convertView.findViewById(R.id.pics);

        type.setText(item.get(position).getWalletType());
        price.setText("Rp. " + item.get(position).getWalletPrc());
        Picasso.get().load(BaseURL.baseUrl + "pics/" + item.get(position).getPics())
                .resize(40, 40)
                .centerCrop()
                .into(pics);
        return convertView;
    }
}
