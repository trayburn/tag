package org.improving.tag.database;

import java.util.List;

import javax.persistence.EntityManager;

import org.improving.tag.Adversary;
import org.improving.tag.Location;
import org.springframework.dao.DataAccessException;
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
            
            for (Location location : locations) {
                if (location.getAdversaryId() != null) {
                	Adversary adversary = em.find( Adversary.class, location.getAdversaryId() );
                    location.setAdversary(adversary);
                    System.out.println("Set adversary " + adversary.getName() + " to location " + location.getName() );
                }
			}
//            em.getTransaction().commit();
//            em.close();
            
            return locations;
        } catch (DataAccessException e) {
        	e.printStackTrace();
            System.out.println("Exception in JDBC: " + e.getMessage());
            return null;
        }
    }
}
