package com.fedesan14.repefit_api.services.implementations;

import com.fedesan14.repefit_api.services.interfaces.PingService;
import org.springframework.stereotype.Service;

@Service
public class PingServiceImpl implements PingService {
    @Override
    public String ping() {
        return "pong";
    }
}
