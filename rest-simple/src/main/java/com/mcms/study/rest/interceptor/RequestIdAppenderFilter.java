package com.mcms.study.rest.interceptor;

import static com.mcms.study.rest.common.AppConstants.REQUEST_REF;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class RequestIdAppenderFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add(REQUEST_REF, requestContext.getHeaderString(REQUEST_REF));
    }

}
