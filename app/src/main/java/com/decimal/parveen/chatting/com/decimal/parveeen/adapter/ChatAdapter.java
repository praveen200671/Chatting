package com.decimal.parveen.chatting.com.decimal.parveeen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.decimal.parveen.chatting.R;
import com.decimal.parveen.chatting.pojochat.PojoChat;

import java.util.ArrayList;

/**
 * Created by PSAINI on 9/18/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private ArrayList<PojoChat> msgList = new ArrayList<PojoChat>();
    private final static int HEADER_VIEW = 0;
    private final static int CONTENT_VIEW = 1;

    public ChatAdapter(Context context, ArrayList<PojoChat> arr_billList) {
        Context context1 = context;

        msgList = arr_billList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutRes = 0;
        switch (viewType) {
            case HEADER_VIEW:
                layoutRes = R.layout.rowsent;
                break;
            case CONTENT_VIEW:
                layoutRes = R.layout.rowrecieved;
                break;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final PojoChat msg = msgList.get(position);
        holder.tv_msg.setText(msg.getMsg());
         holder.tv_msgtime.setText(msg.getMsgtime());
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (msgList.get(position).getIsSentMsg().equalsIgnoreCase("Y"))
            return 0;
        else
            return 1;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_msg, tv_msgtime;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_msg = itemView.findViewById(R.id.tv_Msg);
            tv_msgtime = itemView.findViewById(R.id.tv_msgtime);
        }

    }
}
