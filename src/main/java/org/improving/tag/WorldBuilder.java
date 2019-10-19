package org.improving.tag;

import java.util.ArrayList;
import java.util.List;

import org.improving.tag.database.AdversaryRepository;
import org.improving.tag.database.ExitRepository;
import org.improving.tag.database.LocationRepository;
import org.improving.tag.items.UniqueItems;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class WorldBuilder {
    private List<Location> locationList = new ArrayList<>();

    private final LocationRepository locationRepository;
    private final ExitRepository exitRepository;
    private final AdversaryRepository adversaryRepository;

    public WorldBuilder( LocationRepository locationRepository,
    					 ExitRepository exitRepository,
    					 AdversaryRepository adversaryRepository ) {
        this.locationRepository = locationRepository;
        this.exitRepository = exitRepository;
        this.adversaryRepository = adversaryRepository;
    }

    public Location buildWorld() {
        try {
        	locationList = locationRepository.findAll();
        	
            System.out.println("Loaded " + locationList.size() + " locations." );
            
            Location start = locationList.stream().filter( loc -> "The Deathly Hallows".equalsIgnoreCase(loc.getName()) ).findFirst().get();
            return start; 
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    @Transactional
    public void deleteDatabaseWorld() {

//    	List<Location> locations = locationRepository.findAll();
//    	List<Exit> exits = exitRepository.findAll();
//    	List<Adversary> adversaries = adversaryRepository.findAll();
//    	
//    	System.out.println("Removing locations");
//    	for( Location location: locations ) {
//    		locationRepository.delete( location );
//    	}
//
//    	System.out.println("Removing exits");
//    	for( Exit exit: exits ) {
//    		exitRepository.delete( exit );
//    	}
//    	
//    	System.out.println("Removing adversaries");
//    	for( Adversary adversary : adversaries ) {
//    		adversaryRepository.delete( adversary );
//    	}

    	// easiest
    	exitRepository.deleteAll();
    	locationRepository.deleteAll();
    	adversaryRepository.deleteAll();
    	
    	System.out.println("Done removing.");
    }

    @Transactional
    public void buildDatabaseWorld() {
    	System.out.println("Starting build.");
    	
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

        locationRepository.save( tdh );
        adversaryRepository.save( adv );
        adversaryRepository.save( koopa );
        locationRepository.save( td );
        locationRepository.save( ta );
        locationRepository.save( tmcs );
        locationRepository.save( tr );
        locationRepository.save( tm );
        locationRepository.save( tvm );
        locationRepository.save( md );
		locationRepository.save(tvd);
		locationRepository.save(tap);
		locationRepository.save(aict);
		locationRepository.save(tms);
        
        System.out.println( " ** Done with database reload." );
    }

    public List<Location> getLocationList() {
        return locationList;
    }

}
