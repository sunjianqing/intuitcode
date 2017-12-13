package com.jianqing.intuit;

/**
 * Created by jianqing_sun on 12/6/17.
 */
public class AndriodView implements View {

    @Override
    public String display(String response) {
        StringBuilder sb = new StringBuilder("Android Response\n");
        sb.append(response);
        sb.append("\n----------------------");
        return sb.toString();
    }
}
