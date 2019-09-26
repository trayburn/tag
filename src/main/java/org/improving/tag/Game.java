package org.improving.tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.improving.tag.commands.Command;
import org.springframework.stereotype.Component;

@Component
public class Game {
    private Date startTime;
    private Date endTime;
    private Command[] commands;
    private InputOutput io;
    private Player p;
    private Location startingLocation;
    private List<Location> locationList = new ArrayList<>();
    private final SaveGameFactory saveFactory;

    public Game(Command[] commands, InputOutput io, SaveGameFactory saveFactory) {
        startingLocation = buildWorld();
        this.commands = commands;
        this.io = io;
        this.p = new Player(startingLocation);
        this.saveFactory = saveFactory;
    }

    public Location getStartingLocation() {
        return startingLocation;
    }

    public Player getPlayer() {
        return p;
    }

    public Date getStartTime() {
        return startTime;
    }

    private void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    private void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void run() {
        this.setStartTime(new Date());

        boolean loop = true;
        while (loop) {
            io.displayPrompt("> ");
            String input = io.receiveInput();

            Command validCommand = getValidCommand(input);
            if (null != validCommand) {
                validCommand.execute(input, this);
            } else if (input.equalsIgnoreCase("exit")) {
                saveFactory.save(this);
                io.displayText("Goodbye.");
                loop = false;
            } else {
                io.displayText("Huh? I don't understand.");
            }
        }
        this.setEndTime(new Date());
    }

    private Command getValidCommand(String input) {
        for (Command command : commands) {
            if (command.isValid(input, this)) {
                return command;
            }
        }
        return null;
    }

    private Location buildWorld() {
        var tdh = new Location();
        tdh.setName("The Deathly Hallows");
        this.locationList.add(tdh);

        var td = new Location();
        td.setName("The Dessert");
        this.locationList.add(td);

        var ta = new Location();
        ta.setName("The Amazon");
        this.locationList.add(ta);

        var tmcs = new Location();
        tmcs.setName("The Mac and Cheese Shop");
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
        tdh.getExits().add(new Exit("Heaven Avenue", tmcs, "h", "ave", "heaven", "ha"));
        tdh.getExits().add(new Exit("The Deathly Brownie", td, "tdb", "deathly", "brownie", "db"));

        td.getExits().add(new Exit("Camel Path", ta, "cp", "c", "camel", "path"));
        td.getExits().add(new Exit("Rocky Road", aict, "rocky", "road", "R", "RR"));
        td.getExits().add(new Exit("The Docks", tap, "TD", "D", "docks", "dock"));

        tr.getExits().add(new Exit("The Scenic Route", tvm, "scenic", "SR", "scenic route", "route"));
        tr.getExits().add(new Exit("The City Walk", tm, "city", "walk", "C", "w", "cw"));

        tm.getExits().add(new Exit("Path to Doom", md, "path", "p", "pd"));
        tm.getExits().add(new Exit("An Escalator of Doom", tvd, "escalator", "e", "ed"));

        md.getExits().add(new Exit("Jump Into Lava", tvd, "Jump", "lava", "J", "L", "JL", "jumplava", "jumpintolava"));
        md.getExits().add(new Exit("The Cab", tm, "Cab", "c", "tc"));

        ta.getExits().add(new Exit("Amaz-ing Moose", tvm, "AM", "Amazing", "Moose", "A", "M"));

        tap.getExits().add(new Exit("Flight 121", tms, "Flight121", "f121", "121"));
        tap.getExits().add(new Exit("Flight to the Mall", tm, "mall", "Fm", "FTTM"));

        tmcs.getExits().add(new Exit("Highway 121", ta, "121", "hwy 121", "h121"));
        tmcs.getExits().add(new Exit("Highway 21", tvd, "21", "h21", "hwy 21", "hwy21"));
        tmcs.getExits().add(new Exit("Paradise Road", tr, "P", "r", "PR", "Paradise", "Paradise Rd", "Road"));

        aict.getExits().add(new Exit("Magic Portal", md, "Magic", "Portal", "MP", "m", "p"));

        tms.getExits().add(new Exit("The Plane", ta, "Plane", "TP", "P"));
        tms.getExits().add(new Exit("The Narrow Trail", md, "Narrow", "N", "NT"));
        tms.getExits().add(new Exit("The Lava Flow", tvd, "Lava", "flow", "L", "F", "LF"));
        tms.getExits().add(new Exit("Bike Trail", tr, "Bike", "B", "BT"));

        tvm.getExits().add(new Exit("The Front Door", ta, "TFD", "F", "D", "FD", "front", "door"));
        tvm.getExits().add(new Exit("The Pudding Slide", tap, "TPS", "P", "S", "PS", "Pudding", "Slide"));


        return tdh;
    }

    public Location getLocationOf(final String intendedLocationName) {
        for (Location location: locationList) {
            if (intendedLocationName.equalsIgnoreCase(location.getName())) {
                return location;
            }
        }

        return null;
    }
}
