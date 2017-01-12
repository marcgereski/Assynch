package com.company.direct.domain.out;

public class AbstractClientRequest {
    private final long id;

    public AbstractClientRequest(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
