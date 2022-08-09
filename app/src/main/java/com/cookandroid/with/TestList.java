package com.cookandroid.with;

public class TestList {
    String title;
    String tag;
    String contents;
    String region;

    public TestList(String title, String tag, String contents, String region){
        this.title = title;
        this.tag = tag;
        this.contents = contents;
        this.region = region;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTag(){
        return tag;
    }
    public void setTag(String tag){
        this.tag=tag;
    }
    public String getContents(){
        return contents;
    }
    public void setContents(String contents){
        this.contents=contents;
    }
    public String getRegion(){
        return region;
    }
    public void setRegion(String region){
        this.region=region;
    }
}
