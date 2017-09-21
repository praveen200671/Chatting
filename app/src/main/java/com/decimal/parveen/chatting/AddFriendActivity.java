package com.decimal.parveen.chatting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.decimal.parveen.chatting.database.MyChatDatabase;
import com.decimal.parveen.chatting.pojochat.PojoFriend;
import com.decimal.parveen.chatting.utils.UtilClass;
//import android.widget.Button;

public class AddFriendActivity extends AppCompatActivity implements View.OnClickListener{

    MyChatDatabase db;
    private Button btn_save;
    private EditText et_friendname,et_friendid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        setTitle(getString(R.string.Addfriend));
        getViews();
        initialize();
        setClickListener();
    }

    private void setClickListener() {
        btn_save.setOnClickListener(this);
    }

    private void initialize() {
        db = new MyChatDatabase(this);
    }

    private void getViews() {
        btn_save = (Button)findViewById(R.id.btn_save);
        et_friendname = (EditText) findViewById(R.id.et_friendname);
        et_friendid = (EditText)findViewById(R.id.et_friendid);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_save:
                if(validate())
                {
                    try {
                        PojoFriend friend = new PojoFriend();
                        friend.setFriendname(et_friendname.getText().toString().trim());
                        friend.setFrienduniqueid(et_friendid.getText().toString().trim());
                        db.addFriend(friend);
                        UtilClass.showToast(this,getString(R.string.savedsuccess));
                        Intent intent = new Intent(this,FriendsListActivity.class);
                        setResult(10000,intent);
                        finish();
                    } catch (Exception e) {
                        UtilClass.showToast(this,getString(R.string.oopsmsg));
                    }
                }
        }
    }

    private boolean validate() {

        // We can validate dupilcate friend id in database.
        if(et_friendname.getText().toString().trim().length()==0)
        {
            UtilClass.showToast(this,getString(R.string.enterfriend_name));
            return false;
        }
        if(et_friendid.getText().toString().trim().length()==0)
        {
            UtilClass.showToast(this,getString(R.string.enterfriend_id));
            return false;
        }

        if(et_friendid.getText().toString().trim().length()<0)
        {
            UtilClass.showToast(this,getString(R.string.entercorrectfriend_id));
            return false;
        }

        try {
            int no =Integer.parseInt(et_friendid.getText().toString().trim());
            if((no+"").length()<10)
            {
                UtilClass.showToast(this,getString(R.string.entercorrectfriend_id));
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if(validateforDuplicate())
        {
            UtilClass.showToast(this,getString(R.string.useralreadyexist));
            return false;
        }

        return true;
    }

    private boolean validateforDuplicate() {
        if(db.getFriend(et_friendid.getText().toString().trim()).size()>0)
        {
            return true;
        }
        return false;
    }
}
