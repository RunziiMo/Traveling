package com.runzii.traveling.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.runzii.traveling.CheeseDetailActivity;
import com.runzii.traveling.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    List<PoiInfo> contents;
    String s = "\"http://api.map.baidu.com/panorama?width=512&height=256&location=\" + location + \"&fov=180&ak=kbAaU8kmIniyXGtKS2QeaRLv\"";

    public TestRecyclerViewAdapter(List<PoiInfo> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ImageView iv = (ImageView) holder.itemView.findViewById(R.id.image_view);
        final TextView tv = (TextView) holder.itemView.findViewById(R.id.text_view);
        String location = contents.get(position).location.longitude + "," + contents.get(position).location.latitude;
        final String url = "http://api.map.baidu.com/panorama?width=768&height=384&poiid=" + contents.get(position).uid + "&fov=180&ak=kbAaU8kmIniyXGtKS2QeaRLv";
        String sr = "http://image.baidu.com/i?tn=baiduimagejson&width=&height=&word=" + contents.get(position).name + "rn=15&pn=0";
//        HttpUtils.getStreetViewImage(sr, new HttpCallBack() {
//            @Override
//            public void onSuccess(String response) {
//
//            }
//
//            @Override
//            public void onFailed(String failedInfo) {
//                Toasts.show(failedInfo);
//            }
//        });
        Picasso.with(holder.itemView.getContext())
                .load(url)
                .centerCrop().fit().into(iv);
        tv.setText(contents.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.getContext().startActivity(new Intent(iv.getContext(), CheeseDetailActivity.class)
                        .putExtra("imageurl", url)
                        .putExtra("name", contents.get(position).name).putExtra("address",contents.get(position).address));
            }
        });
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }
    }
}