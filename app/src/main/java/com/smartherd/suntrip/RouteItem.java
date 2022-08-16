package com.smartherd.suntrip;

public class RouteItem {
    private String title;
    private String content;

    public RouteItem(String title_, String content_)
    {
        title = title_;
        content = content_;
    }

    public String getTitle()
    {
        return title;
    }

    public String getContent()
    {
        return content;
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
