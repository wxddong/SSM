package com.atwhere.p2p.myutil;

public class UUID {

    public static String create() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}
