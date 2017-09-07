package com.example.nosotros.gridlist.models;

import android.graphics.drawable.Icon;

/**
 * Created by nosotros on 11/03/2017.
 */

public class Fruits {
    private String name;
    private String Origin;
    private Icon icon;

    public Fruits() {
    }

    public Fruits(String name, String origin, Icon icon) {
        this.name = name;
        Origin = origin;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
