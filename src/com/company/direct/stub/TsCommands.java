package com.company.direct.stub;


import com.company.direct.domain.out.AbstractClientRequest;

import java.util.List;

public interface TsCommands {
    void send(long userId, long orderId, AbstractClientRequest order);
    List<ActionStatus> readActions(long fromRefId);
}
