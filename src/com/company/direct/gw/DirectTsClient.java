package com.company.direct.gw;

import com.company.direct.domain.in.AbstractTsObject;
import com.company.direct.domain.in.OrderTsObject;
import com.company.direct.domain.out.ClientOrder;
import com.company.direct.stub.TsServer;

import java.util.Random;


public class DirectTsClient implements TsClient{
    private final TsServer tsServer;
    private final static Random rnd = new Random(1);
    private final Dispatcher dispatcher;

    public DirectTsClient(TsServer tsServer) {
        this.tsServer = tsServer;
        tsServer.addTsClient(this);
        dispatcher = new Dispatcher(tsServer);
    }

    public static long nextRef() {
        return rnd.nextInt(10000000);
    }

    public static double rndInt(int min, int max) {
        return rnd.nextInt(max) + 1 + min;
    }

    public void sendOrder(long userId, ClientOrder order) {
        dispatcher.sendToTs(userId, nextRef(), order);
    }

    @Override
    public void onEvent(AbstractTsObject object) {
        if (object instanceof OrderTsObject) {
            OrderTsObject order = (OrderTsObject)object;
            System.out.println(order);
        }
    }
}
