package com.decimal.parveen.chatting.pojochat;

/**
 * Created by PSAINI on 9/20/2017.
 */

public class PojoFriend {
    private String id;
    private String friendname;
    private String frienduniqueid;

    public String getFriendname() {
        return friendname;
    }

    public void setFriendname(String friendname) {
        this.friendname = friendname;
    }

    public String getFrienduniqueid() {
        return frienduniqueid;
    }

    public void setFrienduniqueid(String frienduniqueid) {
        this.frienduniqueid = frienduniqueid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
