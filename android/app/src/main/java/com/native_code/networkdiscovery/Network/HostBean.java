/*
 * Copyright (C) 2009-2010 Aubort Jean-Baptiste (Rorist)
 * Licensed under GNU's GPL 2, see README
 */

// Inspired by http://connectbot.googlecode.com/svn/trunk/connectbot/src/org/connectbot/bean/HostBean.java
package com.native_code.networkdiscovery.Network;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.native_code.networkdiscovery.Utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;

public class HostBean implements Parcelable {

    public static final String EXTRA = Constant.PKG + ".extra";
    public static final String EXTRA_POSITION = Constant.PKG + ".extra_position";
    public static final String EXTRA_HOST = Constant.PKG + ".extra_host";
    public static final String EXTRA_TIMEOUT = Constant.PKG + ".network.extra_timeout";
    public static final String EXTRA_HOSTNAME = Constant.PKG + ".extra_hostname";
    public static final String EXTRA_BANNERS = Constant.PKG + ".extra_banners";
    public static final String EXTRA_PORTSO = Constant.PKG + ".extra_ports_o";
    public static final String EXTRA_PORTSC = Constant.PKG + ".extra_ports_c";
    public static final String EXTRA_SERVICES = Constant.PKG + ".extra_services";
    public static final int TYPE_GATEWAY = 0;
    public static final int TYPE_COMPUTER = 1;

    public int deviceType = TYPE_COMPUTER;
    public int isAlive = 1;
    public int position = 0;
    public int responseTime = 0; // ms
    public String ipAddress = null;
    public String hostname = null;
    public String hardwareAddress = NetInfo.NOMAC;
    public String nicVendor = "Unknown";
    public String os = "Unknown";
    public HashMap<Integer, String> services = null;
    public HashMap<Integer, String> banners = null;
    public ArrayList<Integer> portsOpen = null;
    public ArrayList<Integer> portsClosed = null;

    public HostBean() {
        // New object
    }

    public HostBean(Parcel in) {
        // Object from parcel
        readFromParcel(in);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(deviceType);
        dest.writeInt(isAlive);
        dest.writeString(ipAddress);
        dest.writeString(hostname);
        dest.writeString(hardwareAddress);
        dest.writeString(nicVendor);
        dest.writeString(os);
        dest.writeInt(responseTime);
        dest.writeInt(position);
        dest.writeMap(services);
        dest.writeMap(banners);
        dest.writeList(portsOpen);
        dest.writeList(portsClosed);
    }

    @SuppressWarnings("unchecked")
    private void readFromParcel(Parcel in) {
        deviceType = in.readInt();
        isAlive = in.readInt();
        ipAddress = in.readString();
        hostname = in.readString();
        hardwareAddress = in.readString();
        nicVendor = in.readString();
        os = in.readString();
        responseTime = in.readInt();
        position = in.readInt();
        services = in.readHashMap(null);
        banners = in.readHashMap(null);
        portsOpen = in.readArrayList(Integer.class.getClassLoader());
        portsClosed = in.readArrayList(Integer.class.getClassLoader());
    }

    @SuppressWarnings("unchecked")
    public static final Creator CREATOR = new Creator() {
        public HostBean createFromParcel(Parcel in) {
            return new HostBean(in);
        }

        public HostBean[] newArray(int size) {
            return new HostBean[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "{ IP Address: " + ipAddress + " ," +
                "Host Name: " + hostname + " ," +
                "MAC Address: " + hardwareAddress + " }";
    }
}
