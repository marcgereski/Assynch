package com.company.direct.gw;


import com.company.direct.domain.in.AbstractTsObject;

public interface TsClient {
    void onEvent(AbstractTsObject object);
}
