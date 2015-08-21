package com.mcms.study.rest.interceptor;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.mcms.study.rest.common.exception.SystemException;
import com.mcms.study.rest.model.common.BaseEntity;

public class SystemExceptionMapper implements ExceptionMapper<SystemException> {
    @Override
    public Response toResponse(SystemException e) {
        BaseEntity entity = new BaseEntity();
        entity.setErrorMessage(e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entity).build();
    }
}