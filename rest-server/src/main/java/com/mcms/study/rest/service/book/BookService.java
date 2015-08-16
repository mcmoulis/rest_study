package com.mcms.study.rest.service.book;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mcms.study.rest.model.book.Book;

@Path("/book")
@Produces("application/json")
public interface BookService {

    @GET
    public List<Book> list();

    @POST
    public void create(Book book);

    @GET
    @Path("/{bookId}")
    public Book read(@PathParam("bookId") long bookId);

    @PUT
    public void update(Book book);

    @DELETE
    @Path("/{bookId}")
    public void delete(@PathParam("bookId") long bookId);

}