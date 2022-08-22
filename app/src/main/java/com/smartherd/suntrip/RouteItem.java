package com.smartherd.suntrip;

import java.util.Stack;

public class RouteItem {
    private String title;
    private String content;
    private String cdnPhoto;

    public RouteItem(String title_, String content_, String cdnPhoto_)
    {
        title = title_;
        content = content_;
        cdnPhoto = cdnPhoto_;
    }

    public String getTitle()
    {
        return title;
    }

    public String getContent()
    {
        return content;
    }

    public String getImageCdn()
    {
        return cdnPhoto;
    }


    public void setTitle(String title_)
    {
        title = title_;
    }

    public void setContent(String content_)
    {
        content = content_;
    }

}
