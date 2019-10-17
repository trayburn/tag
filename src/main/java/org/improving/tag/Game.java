package org.improving.tag;

import org.improving.tag.commands.Command;
import org.improving.tag.items.UniqueItems;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Game {
    private Date startTime;
    private Date endTime;
    private final Command[] commands;
    private final InputOutput io;
    private final Player p;
    private final Location startingLocation;
    private final SaveGameFactory saveFactory;
    private final List<Location> locationList;

    private final WorldBuilder worldBuilder;
    
    public Game(Command[] commands, InputOutput io,
            SaveGameFactory saveFactory, WorldBuilder worldBuilder) {
        startingLocation = worldBuilder.buildWorld();
        locationList = worldBuilder.getLocationList();
        this.commands = commands;
        this.io = io;
        this.p = new Player(startingLocation);
        this.saveFactory = saveFactory;
        this.worldBuilder = worldBuilder;
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

        io.displayPrompt("Run(1) or rebuild database (2) > ");
        String input = io.receiveInput();

        if( "1".equals( input ) ) {
	        boolean loop = true;
	        while (loop) {
	            try {
	                io.displayPrompt("> ");
	                input = io.receiveInput();
	
	                Command validCommand = getValidCommand(input);
	                if (null != validCommand) {
	                    validCommand.execute(input, this);
	                } else {
	                    io.displayText("Huh? I don't understand.");
	                }
	            } catch (GameExitException ex) {
	                loop = false;
	            }
	        }
        } else if( "2".equals( input ) ) {
        	worldBuilder.buildHardCodedWorld();
        	System.exit(1);
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

    public Location getLocationOf(final String intendedLocationName) {
        for (Location location : locationList) {
            if (intendedLocationName.equalsIgnoreCase(location.getName())) {
                return location;
            }
        }

        return null;
    }
}
