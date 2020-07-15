package com.example.ppjoke.model;

import java.util.List;

public class BottomBar {
    public String activeColor;
    public String inActiveColor;
    public List<Tabs> tabs;
    public int selectTab;


    public static class Tabs {
        public int size;
        public boolean enable;
        public int index;
        public String pageUrl;
        public String title;
        public String tintColor;
    }
}
