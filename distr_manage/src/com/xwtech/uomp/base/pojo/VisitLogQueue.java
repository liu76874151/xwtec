package com.xwtech.uomp.base.pojo;

import java.util.LinkedList;

public class VisitLogQueue extends LinkedList {
    private static VisitLogQueue queue = null;

    private VisitLogQueue() {

    }

    public static VisitLogQueue getInstance() {
        if (null == queue) {
            queue = new VisitLogQueue();
        }

        return queue;
    }

}
