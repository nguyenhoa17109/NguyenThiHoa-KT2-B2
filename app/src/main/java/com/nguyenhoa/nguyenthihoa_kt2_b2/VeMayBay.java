package com.nguyenhoa.nguyenthihoa_kt2_b2;

import java.io.Serializable;

public class VeMayBay implements Serializable {
    private int id;
    private String name;
    private String location;
    private String dateStart;
    private boolean hanhli;

    public VeMayBay() {
    }

    public VeMayBay(String name, String location, String dateStart, boolean hanhli) {
        this.name = name;
        this.location = location;
        this.dateStart = dateStart;
        this.hanhli = hanhli;
    }

    public VeMayBay(int id, String name, String location, String dateStart, boolean hanhli) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.dateStart = dateStart;
        this.hanhli = hanhli;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public boolean isHanhli() {
        return hanhli;
    }

    public void setHanhli(boolean hanhli) {
        this.hanhli = hanhli;
    }
}
