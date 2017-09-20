package com.decimal.parveen.chatting.callback;

import com.decimal.parveen.chatting.pojochat.PojoChat;

import java.util.ArrayList;

/**
 * Created by PSAINI on 9/19/2017.
 */

public interface ResultListener {
    public void onResult(ArrayList<PojoChat> arrayList);
}
