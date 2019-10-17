package org.improving.tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

@Entity( name="exits" )
public class Exit {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column( name="Name" )
    private String name;
	
	@ManyToOne
	@JoinColumn( name="DestinationId" )
    private Location destination;
	
    // Have to add for the bidirectional nature of this relationship
	// In general, @OneToMany has a @ManyToOne on the other end (most times)
    @ManyToOne
    @JoinColumn( name="OriginId" )
    private Location origin;

    public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	@Column( name="Aliases")
    private String csvOfAliases;
    
    @Transient
    private List<String> aliases = new ArrayList<>();

    public Exit() { }

    public Exit(String name, Location origin, Location destination, String...aliases) {
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.aliases.addAll(Arrays.asList(aliases));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, destination);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Exit) {
            Exit exit = (Exit) obj;
            return this.getName().equals(exit.getName()) &&
                    this.getDestination().equals(exit.getDestination());
        }
        return super.equals(obj);
    }
    
    @PostLoad
	public void postLoad() {
		if (null != csvOfAliases) {
			Arrays.stream(csvOfAliases.replace(" ", "").split(","))
				.forEach(alias -> this.addAlias(alias));
		}
	}
    
    @PrePersist
    public void prePersist() {
    	if( aliases.isEmpty() ) {
    		csvOfAliases = "";
    	} else {
    		csvOfAliases = String.join( ",", aliases );
    	}
    }
}
