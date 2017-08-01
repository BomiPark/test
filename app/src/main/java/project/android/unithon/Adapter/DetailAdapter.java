package project.android.unithon.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import project.android.unithon.Holder.ViewHolder;
import project.android.unithon.Model.Item;
import project.android.unithon.R;

/**
 * Created by qkrqh on 2017-07-30.
 */

public class DetailAdapter extends RecyclerView.Adapter<ViewHolder>{

    private Context context;
    private ArrayList<Item> items;
    int item_layout;
    public ViewHolder viewHolder;
    private static int TYPE_FOOTER;

    public DetailAdapter(ArrayList<Item> items, Context context, int item_layout) {
        this.items = items;
        this.context = context;
        this.item_layout=item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if(viewType == 1)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item_layout, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){

        if(position != 8) {
            final Item item = items.get(position);
            Glide.with(context).load(item.getContentImg()) // 메인 사진
                    .override(1400, 600).into(holder.contentImg);
            holder.likeImg.setImageResource(R.drawable.heart);
            holder.content.setText(item.getContent());
        }
    }

    @Override
    public int getItemCount() {

        return items.size()+1;
    }

}