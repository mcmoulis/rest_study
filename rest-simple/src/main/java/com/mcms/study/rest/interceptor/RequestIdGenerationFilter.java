package com.mcms.study.rest.interceptor;

import static com.mcms.study.rest.common.AppConstants.REQUEST_REF;

import java.io.IOException;
import java.util.UUID;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class RequestIdGenerationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getHeaderString(REQUEST_REF) == null) {
            requestContext.getHeaders().add(REQUEST_REF, UUID.randomUUID().toString());
        }
    }

}
