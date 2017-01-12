package com.company.direct.domain.in;


public class AbstractTsObject {
    private final long id;

    public AbstractTsObject(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
