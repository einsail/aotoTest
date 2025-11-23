package com.example.framework.page;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class PageActionRegistry {

    private final Map<String, PageActionHandler> handlers = new ConcurrentHashMap<>();

    public void register(PageActionHandler handler) {
        handlers.put(handler.getName(), handler);
    }

    public Optional<PageActionHandler> getHandler(String name) {
        return Optional.ofNullable(handlers.get(name));
    }
}
