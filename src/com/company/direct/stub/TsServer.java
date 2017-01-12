package com.company.direct.stub;

import com.company.direct.domain.in.AbstractTsObject;
import com.company.direct.domain.in.OrderTsObject;
import com.company.direct.domain.out.AbstractClientRequest;
import com.company.direct.domain.out.ClientOrder;
import com.company.direct.gw.TsClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


public class TsServer implements TsCommands {

    private volatile List<TsClient> tsClientList = new CopyOnWriteArrayList<>();
    private volatile Map<Long, ActionStatus> actionMap = new ConcurrentHashMap<>();

    private ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    public void addTsClient(TsClient tsClient) {
        tsClientList.add(tsClient);
    }

    private void fireOnEvent(AbstractTsObject object) {
        tsClientList.forEach(c -> {
            actionMap.put(object.getId(), new ActionStatus(object.getId(), ActionStatus.Status.One));
            c.onEvent(object);
        });
    }

    @Override
    public void send(long userId, long refId, AbstractClientRequest order) {
        if (order instanceof ClientOrder) {
            ClientOrder ord = (ClientOrder) order;
            final OrderTsObject o = new OrderTsObject(refId, ord.getId(), ord.getSymbol(), ord.getPrice(), ord.getVolume());
            service.schedule(() -> fireOnEvent(o), 1, TimeUnit.SECONDS);
        }
    }

    @Override
    public List<ActionStatus> readActions(long fromRefId) {
        List<ActionStatus> list = new ArrayList<>();
        actionMap.entrySet().forEach(m -> {
                    if (m.getKey() == fromRefId) {
                        ActionStatus a = m.getValue();
                        list.add(new ActionStatus(a.getRefId(), a.getStatus()));
                    }
                }
        );
        actionMap.clear();

        return list;
    }
}
