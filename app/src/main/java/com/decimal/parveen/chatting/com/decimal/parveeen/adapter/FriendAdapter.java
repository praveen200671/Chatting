package com.decimal.parveen.chatting.com.decimal.parveeen.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.decimal.parveen.chatting.R;
import com.decimal.parveen.chatting.callback.FriendListCallback;
import com.decimal.parveen.chatting.pojochat.PojoFriend;

import java.util.ArrayList;

/**
 * Created by PSAINI on 9/20/2017.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private ArrayList<PojoFriend> arrlist = new ArrayList<PojoFriend>();
    FriendListCallback obj;

    Context context;
    public FriendAdapter(Context context, ArrayList<PojoFriend> arrlist, FriendListCallback obj) {
        this.context = context;
        this.arrlist =arrlist;
        this.obj=obj;
    }

    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendrow, parent, false);

        return new FriendAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FriendAdapter.ViewHolder holder, final int position) {
        PojoFriend friend = arrlist.get(position);
        holder.tv_friendname.setText(friend.getFriendname());
        holder.tv_friendid.setText(friend.getFrienduniqueid());
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.onItemClick(arrlist.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrlist.size();
    }



    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_friendname, tv_friendid;
        CardView  card_view;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_friendname = itemView.findViewById(R.id.tv_friendname);
            tv_friendid = itemView.findViewById(R.id.tv_friendid);
            card_view = itemView.findViewById(R.id.card_view);
        }

    }
}
