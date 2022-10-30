package com.cookandroid.with.cookie;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Cookie {
    private String ID = "", PW = "", DATE = "";
    private File file;
    public static Cookie cookie = new Cookie();

    private Cookie(){
        file = new File("/data/data/com.cookandroid.with/cookie.txt");
    }

    public static Cookie getCookie() {
        return cookie;
    }

    public void writeCookie(String id, String pw, String date) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);

            writer.write(id + "\r\n");
            writer.write(pw + "\r\n");
            writer.write(date);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readCookie() {
        try {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    ID="";PW="";DATE="";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (file.length() == 0) {
                ID="";PW="";DATE="";
                return;
            }
            Scanner sc = new Scanner(file);
            ID = sc.nextLine();
            PW = sc.nextLine();
            DATE = sc.nextLine();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkCookie() {
        readCookie();
        try {
            if (!file.exists()) {
                file.createNewFile();
                ID = "";PW = "";DATE = "";
                return;
            } else if (DATE.equals("")) {
                ID = "";PW = "";DATE = "";
                return;
            }
            Date cookieDate = new SimpleDateFormat("yyyy-MM-dd").parse(DATE);
            Date today = new Date(System.currentTimeMillis());
            long diffSec = (today.getTime() - cookieDate.getTime()) / 1000;
            long diffDays = diffSec / (24 * 60 * 60);
            if (diffDays > 7) {
                clearCookie();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            clearCookie();
        }
    }

    public void test(){
        readCookie();
        clearCookie();
    }

    public void clearCookie() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getID() {
        return ID;
    }

    public String getPW() {
        return PW;
    }

}