/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.CiudadEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.persistence.CiudadPersistence;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class TrayectoCiudadOrigenLogic {
    
    @Inject
    private CiudadPersistence ciudadPersistence;

    @Inject
    private TrayectoPersistence trayectoPersistence;
    
    public CiudadEntity addCiudadOrigen(Long idTrayecto, Long ciudadId) {
        CiudadEntity ciudadEntity = ciudadPersistence.find(ciudadId);
        TrayectoEntity trayectoEntity = trayectoPersistence.find(idTrayecto);
        trayectoEntity.setOrigen(ciudadEntity);
        return ciudadEntity;
    }
    
    
    
    public CiudadEntity createCiudadOrigen(Long idTrayecto, CiudadEntity ciudad) {
        CiudadEntity ciudadEntity = ciudadPersistence.create(ciudad);
        TrayectoEntity trayectoEntity = trayectoPersistence.find(idTrayecto);
        trayectoEntity.setOrigen(ciudadEntity);
        return ciudadEntity;
    }
    
}
