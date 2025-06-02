package com.fedesan14.repefit_api.controllers.implementations;

import com.fedesan14.repefit_api.controllers.interfaces.PingController;
import com.fedesan14.repefit_api.services.interfaces.PingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PingControllerImpl implements PingController {

    private final PingService pingService;

    @Override
    public String ping() {
        return pingService.ping();
    }
}
