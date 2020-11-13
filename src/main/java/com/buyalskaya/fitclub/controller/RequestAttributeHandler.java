package com.buyalskaya.fitclub.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestAttributeHandler {
    private Map<String, Object> attributes = new HashMap<>();

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public void addAttribute(String attributeName, Object attributeValue) {
        attributes.put(attributeName, attributeValue);
    }

    public Object getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    public void removeAttributes() {
        attributes.clear();
    }

    public void setAttributes(HttpServletRequest request) {
        Enumeration<String> requestAttributes = request.getAttributeNames();
        while (requestAttributes.hasMoreElements()) {
            String attributeName = requestAttributes.nextElement();
            attributes.put(attributeName, request.getAttribute(attributeName));
        }
    }
}