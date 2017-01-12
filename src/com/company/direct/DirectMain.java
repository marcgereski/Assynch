package com.company.direct;

import com.company.direct.domain.out.ClientOrder;
import com.company.direct.gw.DirectTsClient;
import com.company.direct.stub.TsServer;

import java.util.ArrayList;
import java.util.List;

import static com.company.direct.gw.DirectTsClient.nextRef;
import static com.company.direct.gw.DirectTsClient.rndInt;

public class DirectMain {
    private final DirectTsClient tsClient;

    public DirectMain(TsServer server) {
        tsClient = new DirectTsClient(server);
    }

    public void startServer() {
        sendOrders(createOrders());
    }

    private void sendOrders(List<ClientOrder> list) {
        list.forEach(order -> {
            System.out.println(order.toString());
            tsClient.sendOrder(1, order);
        });
    }

    private List<ClientOrder> createOrders() {
        List<ClientOrder> list = new ArrayList<>();
        list.add(new ClientOrder(nextRef(), "BAST", 1081.0 + rndInt(10, 100), 1000  + rndInt(0, 1000)));
        list.add(new ClientOrder(nextRef(), "KEGC", 1081.0 + rndInt(10, 100), 1000  + rndInt(0, 1000)));
        list.add(new ClientOrder(nextRef(), "KZTO", 1081.0 + rndInt(10, 100), 1000  + rndInt(0, 1000)));
        list.add(new ClientOrder(nextRef(), "SBER", 1081.0 + rndInt(10, 100), 1000  + rndInt(0, 1000)));
        return list;
    }

    public static void main(String[] args) {
        TsServer server = new TsServer();
        DirectMain main = new DirectMain(server);
        main.startServer();
    }
}
