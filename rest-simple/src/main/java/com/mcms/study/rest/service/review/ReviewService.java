package com.mcms.study.rest.service.review;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mcms.study.rest.model.review.Review;

@Produces("application/json")
public interface ReviewService {

    @GET
    public List<Review> list(@PathParam("bookId") long bookId);

}