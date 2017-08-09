package com.atwhere.p2p.myutil;

public final class IdUtil {

    public static Short getShortId(Short maxId) {
        return (short)(maxId == null ? 1 : maxId + 1);
    }

    public static Integer getIntegerId(Integer maxid) {
        return maxid == null ? 1 : maxid + 1;
    }
}
