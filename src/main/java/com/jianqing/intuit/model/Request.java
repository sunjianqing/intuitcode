package com.jianqing.intuit.model;

import com.jianqing.intuit.view.ClientType;

/**
 * Created by jianqing_sun on 12/11/17.
 */
public class Request {
    public ClientType type;
    public String body;

    public Request(ClientType type, String body) {
        this.type = type;
        this.body = body;
    }
}
