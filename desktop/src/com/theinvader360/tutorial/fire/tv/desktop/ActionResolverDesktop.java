package com.theinvader360.tutorial.fire.tv.desktop;

import com.theinvader360.tutorial.fire.tv.ActionResolver;

public class ActionResolverDesktop implements ActionResolver {

    @Override
    public void openUri(String uri) {
        System.out.println("Open URI: " + uri);
    }

}
