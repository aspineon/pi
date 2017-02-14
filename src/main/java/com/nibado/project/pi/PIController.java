package com.nibado.project.pi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pi")
public class PIController {
    private final PIService service;

    public PIController(final PIService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public PI get() {
        return PI.of(service.results());
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST, produces = "application/json")
    public void start() {
        service.start();
    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST, produces = "application/json")
    public void stop() {
        service.stop();
    }
}
