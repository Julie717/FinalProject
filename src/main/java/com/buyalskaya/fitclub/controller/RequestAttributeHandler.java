package com.buyalskaya.fitclub.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Request attribute handler.
 * Uses for store the information from request and
 * provides a possibility to change locale
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class RequestAttributeHandler {
    private Map<String, Object> attributes = new HashMap<>();

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    /**
     * Add attribute.
     *
     * @param attributeName  the attribute name
     * @param attributeValue the attribute value
     */
    public void addAttribute(String attributeName, Object attributeValue) {
        attributes.put(attributeName, attributeValue);
    }

    /**
     * Gets attribute.
     *
     * @param attributeName the attribute name
     * @return the attribute
     */
    public Object getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    /**
     * Remove attributes.
     */
    public void removeAttributes() {
        attributes.clear();
    }

    /**
     * Sets attributes.
     *
     * @param request the request
     */
    public void setAttributes(HttpServletRequest request) {
        Enumeration<String> requestAttributes = request.getAttributeNames();
        while (requestAttributes.hasMoreElements()) {
            String attributeName = requestAttributes.nextElement();
            attributes.put(attributeName, request.getAttribute(attributeName));
        }
    }
}