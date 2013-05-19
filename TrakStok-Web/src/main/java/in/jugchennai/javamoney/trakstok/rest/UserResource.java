/*
 * Copyright 2013 JUGChennai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.jugchennai.javamoney.trakstok.rest;

import in.jugchennai.javamoney.jpa.service.UserService;
import in.jugchennai.javamoney.jpa.service.entity.TsUsers;
import java.util.Collection;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * REST Web Service for User Service
 * @author kaleeswaran <kaleeswaran14@gmail.com>
 * 
 */
@Path("/user")
@RequestScoped
public class UserResource {

      // Sample GET request
//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
//    public Collection<TsUsers> list() {
//        return UserService.findAll();
//    }
                
    // Sample Service to test the status messages
    @GET
    @Path("/test")
    public Response test() {
        System.out.println("list users test");
        return Response.status(Response.Status.NO_CONTENT).entity("Companies is empty").build(); // when data is empty
//	return Response.status(Response.Status.OK).entity(userList).build(); // for success return
//      return Response.status(200).build(); for success
//      return Response.status(Response.Status.BAD_REQUEST).entity(ERROR_MSG_ID_NOT_EQUAL).build(); // when the specified data is not available
    }
    
    // Need to throw some exceptions, when anyting goes wrong
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Collection<TsUsers> list(@QueryParam("username") String username, @QueryParam("password") String password) {
        if (username != null) {
            return UserService.findByUsername(username);            
        } else if (password != null) {
            return UserService.findByPassword(password);
        } else {
            return UserService.findAll();
        }
    }
    
    @GET
    @Path("/validate/{username}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response list(@PathParam("username") String username) {
        try {
            if (username != null) {
                boolean status = UserService.validateUserName(username);
                if (status) {
                    return Response.status(200).build();   
                }
                return Response.status(Response.Status.NOT_FOUND).entity("username does not exist").build();
            }
            return Response.status(Response.Status.BAD_REQUEST).entity("username is empty").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getLocalizedMessage()).build();
        }

    }
        
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response list(@PathParam("id") int id) {
        // TODO: need to be implemented
        return Response.status(200).build();
    }
    
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public Response add(TsUsers user) {
        boolean status = UserService.addUser(user);
        if (status) {
            return Response.status(200).build();
        }
        return Response.status(Response.Status.PRECONDITION_FAILED).entity("Username is empty").build();
    }
        
    @PUT
    @Consumes (MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@PathParam("id") int id, TsUsers user) {
        // TODO: need to be implemented
        return Response.status(200).build();
    }
            
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        // TODO: need to be implemented
        return Response.status(200).build();
    }
}
