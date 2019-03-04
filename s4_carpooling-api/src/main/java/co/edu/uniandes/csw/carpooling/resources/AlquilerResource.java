/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.AlquilerDTO;
import co.edu.uniandes.csw.carpooling.ejb.AlquilerLogic;
import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author df.penap
 */
@Path("alquileres")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AlquilerResource {
    private static final Logger LOGGER = Logger.getLogger(AlquilerResource.class.getName());
    @Inject
    private AlquilerLogic logic;
    @GET
    @Path("{id: \\d+}")
    public AlquilerDTO getAlquiler(@PathParam("id")Long id)
    {
        AlquilerEntity alquiler = logic.getAlquiler(id);
        if(alquiler==null)
        {
            throw new WebApplicationException("El recurso alquiler id: "+id+" no existe", 404);
        }
        return new AlquilerDTO(alquiler);
    }
    @GET
    public List<AlquilerDTO> getAlquileres()
    {
        List<AlquilerDTO> alquileres = listEntityToDTO(logic.getAlquiler());
        return alquileres;
    }
    @POST
    public AlquilerDTO createAlquiler(AlquilerDTO alquiler) throws BusinessLogicException
    {
        AlquilerEntity entity = alquiler.toEntity();
        entity = logic.createAlquiler(entity);
        return new AlquilerDTO(entity);
    }
    @PUT
    @Path("{id: \\d+}")
    public AlquilerDTO updateAlquiler(@PathParam("id")Long id, AlquilerDTO alquiler)
    {
        AlquilerEntity entity = alquiler.toEntity();
        entity = logic.update(id, entity);
        return new AlquilerDTO(entity);
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAlquiler(@PathParam("id")Long id)
    {
        logic.deleteAlquiler(id);
        
    }
    @POST
    @Path("{idA: \\d+}/{idD: \\d+}/{idAr: \\d+}/{idS: \\d+}")    
    public AlquilerDTO addRelacion(@PathParam("idA")Long idAlquiler,@PathParam("idD")Long idDueno,@PathParam("idAr")Long idArrendatario,
            @PathParam("idS")Long idSeguro) throws BusinessLogicException
    {
        AlquilerEntity entity = logic.addRelacionAlquiler(idAlquiler, idDueno, idArrendatario, idSeguro);
        return new AlquilerDTO(entity);
        
    }
    @PUT
    @Path("{idA: \\d+}/arrendatario/{idAr: \\d+}")
    public AlquilerDTO ReplaceArrendatario(@PathParam("idA")Long idAlquiler,@PathParam("idAr")Long idArrendatario) throws BusinessLogicException
    {
       
       AlquilerEntity entity;
       entity = logic.replaceRelacionArrendatario(idAlquiler, idArrendatario);
       return new AlquilerDTO(entity);
    }
    @PUT
    @Path("{idA: \\d+}/seguro/{idS: \\d+}")
    public AlquilerDTO ReplaceSeguro(@PathParam("idA")Long idAlquiler, @PathParam("idS")Long idSeguro) throws BusinessLogicException
    {
        AlquilerEntity entity = logic.replaceRelacionSeguro(idAlquiler, idSeguro);
        return new AlquilerDTO(entity);
    }
    private List<AlquilerDTO> listEntityToDTO(List<AlquilerEntity> alquiler) {
        List<AlquilerDTO> list = new ArrayList<>();
        for (AlquilerEntity entity : alquiler) {
            list.add(new AlquilerDTO(entity));
        }
        return list;
    }
}
