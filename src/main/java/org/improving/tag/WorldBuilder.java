package org.improving.tag;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.improving.tag.database.JPAUtility;
import org.improving.tag.database.LocationDAO;
import org.improving.tag.items.UniqueItems;
import org.springframework.stereotype.Component;

@Component
public class WorldBuilder {
    private List<Location> locationList = new ArrayList<>();

    private final LocationDAO locationDAO;

    public WorldBuilder(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public Location buildWorld() {
        try {
        	locationList = locationDAO.findAll();
            System.out.println("Loaded " + locationList.size() + " locations." );
            
            Location start = locationList.stream().filter( loc -> "The Deathly Hallows".equalsIgnoreCase(loc.getName()) ).findFirst().get();
            return start; 
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public void buildHardCodedWorld() {
    	EntityManager em = JPAUtility.getEntityManager();
    	em.getTransaction().begin();

    	List<Location> locations = em.createQuery( "select loc from org.improving.tag.Location loc" ).getResultList();
    	List<Exit> exits = em.createQuery( "select ex from org.improving.tag.Exit ex" ).getResultList();
    	List<Adversary> adversaries = em.createQuery( "select ad from org.improving.tag.Adversary ad" ).getResultList();
    	
    	System.out.println("Removing locations");
    	for( Location location: locations ) {
    		em.remove( location );
    	}

    	System.out.println("Removing exits");
    	for( Exit exit: exits ) {
    		em.remove( exit );
    	}
    	
    	System.out.println("Removing adversaries");
    	for( Adversary adversary : adversaries ) {
    		em.remove( adversary );
    	}

    	em.getTransaction().commit();
    	System.out.println("Done removing.");

    	em.getTransaction().begin();
    	
        var tdh = new Location();
        tdh.setName("The Deathly Hallows");
        this.locationList.add(tdh);
        
        var adv = new Adversary();
        adv.setName("Sauron");
        adv.setHitPoints(300);

        var koopa = new Adversary();
        koopa.setName("Blue Koopa");
        koopa.setHitPoints(20);
        koopa.setItem(UniqueItems.BLUE_SHELL);

        var td = new Location();
        td.setName("The Dessert");
        this.locationList.add(td);

        var ta = new Location();
        ta.setName("The Amazon");
        this.locationList.add(ta);

        var tmcs = new Location();
        tmcs.setName("The Mac and Cheese Shop");
        tmcs.setTreasureChest(new TreasureChest(UniqueItems.THE_ONE_RING, "A Kraft box"));
        tmcs.setAdversary(koopa);
        this.locationList.add(tmcs);

        var tr = new Location();
        tr.setName("The Reef");
        this.locationList.add(tr);

        var tm = new Location();
        tm.setName("The Mall");
        this.locationList.add(tm);

        var tvm = new Location();
        tvm.setName("The Velvet Moose");
        this.locationList.add(tvm);

        var md = new Location();
        md.setName("Mount Doom");
        this.locationList.add(md);
        md.setAdversary(adv);

        var tvd = new Location();
        tvd.setName("The Volcano of Death");
        this.locationList.add(tvd);

        var tap = new Location();
        tap.setName("The Airport");
        this.locationList.add(tap);

        var aict = new Location();
        aict.setName("An Ice Cream Truck");
        this.locationList.add(aict);

        var tms = new Location();
        tms.setName("The Mountains");
        this.locationList.add(tms);

        //initializing all the exits
        tdh.getExits().add(new Exit("Heaven Avenue", tdh, tmcs, "h", "ave", "heaven", "ha"));
        tdh.getExits().add(new Exit("The Deathly Brownie", tdh, td, "tdb", "deathly", "brownie", "db"));

        td.getExits().add(new Exit("Camel Path", td, ta, "cp", "c", "camel", "path"));
        td.getExits().add(new Exit("Rocky Road", td, aict, "rocky", "road", "R", "RR"));
        td.getExits().add(new Exit("The Docks", td, tap, "TD", "D", "docks", "dock"));

        tr.getExits().add(new Exit("The Scenic Route", tr, tvm, "scenic", "SR", "scenic route", "route"));
        tr.getExits().add(new Exit("The City Walk", tr, tm, "city", "walk", "C", "w", "cw"));

        tm.getExits().add(new Exit("Path to Doom", tm, md, "path", "p", "pd"));
        tm.getExits().add(new Exit("An Escalator of Doom", tm, tvd, "escalator", "e", "ed"));

        md.getExits().add(new Exit("Jump Into Lava", md, tvd, "Jump", "lava", "J", "L", "JL", "jumplava", "jumpintolava"));
        md.getExits().add(new Exit("The Cab", md, tm, "Cab", "c", "tc"));

        ta.getExits().add(new Exit("Amaz-ing Moose", ta, tvm, "AM", "Amazing", "Moose", "A", "M"));

        tap.getExits().add(new Exit("Flight 121", tap, tms, "Flight121", "f121", "121"));
        tap.getExits().add(new Exit("Flight to the Mall", tap, tm, "mall", "Fm", "FTTM"));

        tmcs.getExits().add(new Exit("Highway 121", tmcs, ta, "121", "hwy 121", "h121"));
        tmcs.getExits().add(new Exit("Highway 21", tmcs, tvd, "21", "h21", "hwy 21", "hwy21"));
        tmcs.getExits().add(new Exit("Paradise Road", tmcs, tr, "P", "r", "PR", "Paradise", "Paradise Rd", "Road"));

        aict.getExits().add(new Exit("Magic Portal", aict, md, "Magic", "Portal", "MP", "m", "p"));

        tms.getExits().add(new Exit("The Plane", tms, ta, "Plane", "TP", "P"));
        tms.getExits().add(new Exit("The Narrow Trail", tms, md, "Narrow", "N", "NT"));
        tms.getExits().add(new Exit("The Lava Flow", tms, tvd, "Lava", "flow", "L", "F", "LF"));
        tms.getExits().add(new Exit("Bike Trail", tms, tr, "Bike", "B", "BT"));

        tvm.getExits().add(new Exit("The Front Door", tvm, ta, "TFD", "F", "D", "FD", "front", "door"));
        tvm.getExits().add(new Exit("The Pudding Slide", tvm, tap, "TPS", "P", "S", "PS", "Pudding", "Slide"));

        em.persist( tdh );
        em.persist( adv );
        em.persist( koopa );
        em.persist( td );
        em.persist( ta );
        em.persist( tmcs );
        em.persist( tr );
        em.persist( tm );
        em.persist( tvm );
        em.persist( md );
		em.persist(tvd);
		em.persist(tap);
		em.persist(aict);
		em.persist(tms);
        
        em.getTransaction().commit();
        
        System.out.println( " ** Done with database reload." );
    }

    public List<Location> getLocationList() {
        return locationList;
    }

}
