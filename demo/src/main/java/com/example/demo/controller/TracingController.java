package com.example.demo.controller;

import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.propagation.Propagator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TracingController {
    private final Tracer tracer;
    private final Propagator propagator;

    public TracingController(Tracer tracer, Propagator propagator) {
        this.tracer = tracer;
        this.propagator = propagator;
    }

    // 기본응답 API
    @GetMapping("/hello")
    public String hello() {
        return "Hello World,with zipkin";
    }

    //트레이싱이 적용된 API(지연)
    @GetMapping("/slow")
    public String slowApi() throws InterruptedException {
        Span newSpan = tracer.nextSpan().name("slow-api").start();
        try (Tracer.SpanInScope ws = tracer.withSpan(newSpan)) {
            Thread.sleep(3000); // 3초 딜레이
            return "This was slow...";
        } finally {
            newSpan.end();
        }
    }
}
