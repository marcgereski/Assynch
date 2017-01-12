package com.company.direct.gw;

import com.company.direct.domain.out.AbstractClientRequest;
import com.company.direct.stub.ActionStatus;
import com.company.direct.stub.TsServer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Dispatcher {
    private final TsServer server;
    private final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1024);
    private final List<Long> refList = new LinkedList<>();
    private Executor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, queue);

    public Dispatcher(TsServer server) {
        this.server = server;
    }

    public void sendToTs(long userId, long refId, AbstractClientRequest request) {
        executor.execute(new TsWorker(server, userId, refId, request));
    }

    private void requestActionStatus(long refId) {
        refList.add(refId);

        List<ActionStatus> statusList = new ArrayList<>();
        while (statusList.size() < 1) {
            statusList = server.readActions(refList.get(0));
        }
        statusList.forEach(s -> System.out.println("*******\nStatus" + s.getRefId() + " : " + s.getStatus().name()));
    }
}
