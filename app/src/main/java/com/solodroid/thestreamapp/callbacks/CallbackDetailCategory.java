package com.solodroid.thestreamapp.callbacks;

import com.solodroid.thestreamapp.models.Category;
import com.solodroid.thestreamapp.models.Channel;

import java.util.ArrayList;
import java.util.List;

public class CallbackDetailCategory {

    public String status = "";
    public int count = -1;
    public int count_total = -1;
    public int pages = -1;
    public Category category = null;
    public List<Channel> posts = new ArrayList<>();

}
