package com.company.direct.stub;


public class ActionStatus {
    public static enum Status {
        One, Two, Three
    }
    private final long refId;
    private final Status status;

    public ActionStatus(long refId, Status status) {
        this.refId = refId;
        this.status = status;
    }

    public long getRefId() {
        return refId;
    }

    public Status getStatus() {
        return status;
    }
}
