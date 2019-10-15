package org.improving.tag.database;

import java.util.List;

import javax.persistence.EntityManager;

import org.improving.tag.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationDAO {

    public LocationDAO() {
    }

    public List<Location> findAll() {
        try {
        	EntityManager em = JPAUtility.getEntityManager();
//        	em.getTransaction().begin();
            List<Location> locations = em.createQuery( "SELECT loc FROM org.improving.tag.Location loc" ).getResultList();

            // For debugging only.
            for (Location location : locations) {
            	if( location.getAdversary() != null ) {
            		System.out.println(" Location " + location.getName() + " has an adversary");
            	}
            }
//            em.getTransaction().commit();
//            em.close();
            
            return locations;
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Exception in loading locations: " + e.getMessage());
            return null;
        }
    }
}
