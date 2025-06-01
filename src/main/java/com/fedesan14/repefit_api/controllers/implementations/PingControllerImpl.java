package com.fedesan14.repefit_api.controllers.implementations;

import com.fedesan14.repefit_api.controllers.interfaces.PingController;
import com.fedesan14.repefit_api.services.interfaces.PingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingControllerImpl implements PingController {

    private final PingService pingService;

    public PingControllerImpl(PingService pingService) {
        this.pingService = pingService;
    }

    @Override
    public String ping() {
        return pingService.ping();
    }
}
