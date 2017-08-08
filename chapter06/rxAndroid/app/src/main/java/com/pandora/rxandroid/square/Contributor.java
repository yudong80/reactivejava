package com.pandora.rxandroid.square;

/**
 * Created by jungjoon on 2017. 2. 24..
 */

public class Contributor {
    String login;
    String url;
    int id;

    @Override
    public String toString() {
        return "login : " + login + "id : " + id + "url : " + url;
    }
}