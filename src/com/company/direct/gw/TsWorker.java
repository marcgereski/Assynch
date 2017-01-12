package com.company.direct.gw;

import com.company.direct.domain.out.AbstractClientRequest;
import com.company.direct.stub.TsServer;

public class TsWorker implements Runnable{
    private final TsServer server;
    private final long userId;
    private final long refId;
    private final AbstractClientRequest request;

    public TsWorker(TsServer server, long userId, long refId, AbstractClientRequest request) {
        this.userId = userId;
        this.refId = refId;
        this.request = request;
        this.server = server;
    }

    @Override
    public void run() {
        server.send(userId, refId, request);
    }
}
