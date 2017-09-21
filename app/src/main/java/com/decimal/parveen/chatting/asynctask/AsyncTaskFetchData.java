package com.decimal.parveen.chatting.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.decimal.parveen.chatting.database.MyChatDatabase;
import com.decimal.parveen.chatting.callback.ResultListener;
import com.decimal.parveen.chatting.pojochat.PojoChat;

import java.util.ArrayList;

/**
 * Created by PSAINI on 9/19/2017.
 */

public class AsyncTaskFetchData extends AsyncTask<String,Void,ArrayList<PojoChat>> {
    private final String lastmessageid;
    ArrayList<PojoChat> arrayList = new ArrayList<>();
    Context context;
    ResultListener resultlistener;
    String friendid="";
    ProgressDialog progressBar ;
    public AsyncTaskFetchData(Context context, String frienduniqueid,String lastmessageid, ResultListener resultlistener)
    {
        this.context = context;
        friendid= frienduniqueid;
        this.lastmessageid=lastmessageid;
        this.resultlistener =resultlistener;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Fetching data ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);//initially progress is 0
        progressBar.setMax(100);//sets the maximum value 100

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.show();
    }
    @Override
    protected ArrayList<PojoChat> doInBackground(String... data) {
        return arrayList = new MyChatDatabase(context).getMSG(friendid,lastmessageid);
    }

    @Override
    protected void onPostExecute(ArrayList<PojoChat> pojoChats) {
        super.onPostExecute(pojoChats);
        dismissdialog();
        resultlistener.onResult(pojoChats);

    }

    private void dismissdialog() {
        if(  progressBar!=null)
        {
            if(progressBar.isShowing())
            progressBar.dismiss();
        }
    }
}
