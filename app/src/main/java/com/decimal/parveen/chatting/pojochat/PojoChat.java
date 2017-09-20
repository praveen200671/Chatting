package com.decimal.parveen.chatting.pojochat;


/**
 * Created by PSAINI on 9/18/2017.
 */

public class PojoChat {

    private String id="";
    private String friendid ="";
    private String msg ="";
    private String isSentMsg="Y";
    private String msgtime="";

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(String msgtime) {
        this.msgtime = msgtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsSentMsg() {
        return isSentMsg;
    }

    public void setIsSentMsg(String isSentMsg) {
        this.isSentMsg = isSentMsg;
    }
}
