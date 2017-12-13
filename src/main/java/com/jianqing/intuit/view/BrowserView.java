package com.jianqing.intuit.view;

/**
 * Created by jianqing_sun on 12/6/17.
 */
public class BrowserView implements View {

    @Override
    public String display(String response) {
        StringBuilder sb = new StringBuilder("Web Response\n");
        sb.append(response);
        sb.append("\n----------------------");
        return sb.toString();
    }
}
