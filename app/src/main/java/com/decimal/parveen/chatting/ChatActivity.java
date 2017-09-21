package com.decimal.parveen.chatting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.decimal.parveen.chatting.database.MyChatDatabase;
import com.decimal.parveen.chatting.asynctask.AsyncTaskFetchData;
import com.decimal.parveen.chatting.callback.ResultListener;
import com.decimal.parveen.chatting.com.decimal.parveeen.adapter.ChatAdapter;
import com.decimal.parveen.chatting.pojochat.PojoChat;
import com.decimal.parveen.chatting.utils.UtilClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ChatActivity extends AppCompatActivity implements ResultListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private TextView tv_send;
    private EditText et_msg;
    MyChatDatabase db;
    ArrayList<PojoChat> arraylist;
    ChatAdapter chatadapter;
    PojoChat recievedmsg;
    String[] arr =new String[]{"Auto generated.\n Hi! Friend ", "Auto generated.\nHow are you?","Auto generated.\nI miss you a lot.","Auto generated.\n Have you completed you graduation.","Auto generated.\n This is good news.","Auto generated.\n Nice to talk to you."};
    String id="",friendname="",frienduniqueid="";
    private String lastmessageid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        getValuefromBundle();
        initialization();
        getDatafromDatabase(lastmessageid);
        setClickListener();
        setTitle(friendname);


    }

    private void getValuefromBundle() {
        Bundle  bundle =getIntent().getExtras();
        id = bundle.getString(getString(R.string.key_id));
        friendname = bundle.getString(getString(R.string.key_friendname));
        frienduniqueid = bundle.getString(getString(R.string.key_frienduniqueid));

    }

    private void initialization() {
        db = new MyChatDatabase(this);
        arraylist = new ArrayList<>();
        recievedmsg =new PojoChat();

    }

    private PojoChat setDataforRecievedMsg() {
        try {
            Random rn = new Random();
            int nextno =rn.nextInt()+1;
            recievedmsg.setMsg(arr[ (Math.abs(nextno)%6)]);
            recievedmsg.setMsgtime((UtilClass.getCurrentTime().toString()));
            recievedmsg.setIsSentMsg("N");
            recievedmsg.setFriendid(id);
            db.addMessage(recievedmsg);
            return recievedmsg;
        } catch (Exception e) {
            e.printStackTrace();
            return recievedmsg;
        }

    }

    private void setClickListener() {
        tv_send.setOnClickListener(this);
    }

    private void getDatafromDatabase(String lastmessageid) {
        this.lastmessageid =lastmessageid;
        AsyncTaskFetchData asynctsk = new AsyncTaskFetchData(this,id, lastmessageid, this);
        asynctsk.execute();

    }

    private void getViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        et_msg = (EditText) findViewById(R.id.et_msg);
        tv_send = (TextView) findViewById(R.id.tv_send);
    }


    @Override
    public void onResult(ArrayList<PojoChat> arrayList) {
        try {
            if(chatadapter!=null)
            {
                for(PojoChat pojo:arrayList)
                this.arraylist.add(pojo);
            }
            else
            {
                this.arraylist =arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            setAdapter();
    }

    private void setAdapter() {
        chatadapter = new ChatAdapter(this, arraylist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setStackFromEnd(true);
        recyclerView.setAdapter(chatadapter);
//        recyclerView.smoothScrollToPosition(chatadapter.getItemCount());
        recyclerView.scrollToPosition(chatadapter.getItemCount()-1);
        chatadapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                sendMessage();
                break;
        }
    }

    private void sendMessage() {
        if (validate()) {
            PojoChat msg = new PojoChat();
            msg.setIsSentMsg("Y");
            msg.setMsg(et_msg.getText().toString());
            msg.setMsgtime((UtilClass.getCurrentTime().toString()));
            msg.setFriendid(id);

            // save sent message.......
            db.addMessage(msg);
            setDataforRecievedMsg();
            if(chatadapter.getItemCount()>0)
            {
                getDatafromDatabase(arraylist.get(chatadapter.getItemCount()-1).getId());
            }else
            {
                getDatafromDatabase("");
            }


//            recyclerView.smoothScrollToPosition(arraylist.size()-2);

            et_msg.setText("");


        }
    }

    private boolean validate() {
        if (et_msg.getText().toString().length() > 0)
            return true;
        return false;
    }
}
