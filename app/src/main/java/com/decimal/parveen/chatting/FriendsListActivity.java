package com.decimal.parveen.chatting;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.decimal.parveen.chatting.callback.FriendListCallback;
import com.decimal.parveen.chatting.com.decimal.parveeen.adapter.ChatAdapter;
import com.decimal.parveen.chatting.com.decimal.parveeen.adapter.FriendAdapter;
import com.decimal.parveen.chatting.database.MyChatDatabase;
import com.decimal.parveen.chatting.pojochat.PojoFriend;
import com.decimal.parveen.chatting.utils.DividerItemDecoration;

import java.util.ArrayList;

public class FriendsListActivity extends AppCompatActivity implements FriendListCallback {

    RecyclerView rc_friends;
    final private int REQUEST_CODE=10000;
    private MyChatDatabase db;
    private ArrayList<PojoFriend> arraylist;
    private FriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.friendlist));
        getViews();
        initialize();
        getFriendsList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddFriendActivity();
            }
        });
    }

    private void initialize() {
        db= new MyChatDatabase(this);
    }

    private void getFriendsList() {
        arraylist = db.getFriendsList();
        setAdapter();
    }

    private void setAdapter() {
         adapter =new FriendAdapter(this,arraylist,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rc_friends.setLayoutManager(mLayoutManager);
        rc_friends.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
        rc_friends.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        rc_friends.setAdapter(adapter);
        rc_friends.setVerticalScrollbarPosition(arraylist.size());
//        rc_friends.setAdapter(adapter);
    }

    private void openAddFriendActivity() {
        Intent intent = new Intent(this,AddFriendActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    private void getViews() {
        rc_friends = (RecyclerView)findViewById(R.id.rc_friends);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE)
        {
            getFriendsList();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(PojoFriend obj) {

        startChatActivity(obj);

    }

    private void startChatActivity(PojoFriend obj) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(getString(R.string.key_id),obj.getId());
        intent.putExtra(getString(R.string.key_friendname),obj.getFriendname());
        intent.putExtra(getString(R.string.key_frienduniqueid),obj.getFrienduniqueid());
        startActivity(intent);
    }
}
