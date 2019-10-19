package org.improving.tag;

import org.improving.tag.items.Item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity( name="location" )
public class Location {

	@Id
	@GeneratedValue
    private int id;
	
	@Column(name="Name")
    private String name = "";
	
	@Column(name="Description")
    private String description = "";

	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name="AdversaryId" )
    private Adversary adversary;

    @OneToMany( mappedBy = "origin", fetch = FetchType.EAGER )
    private List<Exit> exits = new ArrayList<>();
    
    @Transient
    private TreasureChest treasureChest = TreasureChest.NO_TREASURE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Adversary getAdversary() {
        return adversary;
    }

    public void setAdversary(Adversary adversary) {
        this.adversary = adversary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Exit> getExits() {
        return exits;
    }

    public void addExit(Exit exit) {
        this.exits.add(exit);
    }

    public void setTreasureChest(TreasureChest treasureChest) {
        this.treasureChest = treasureChest;
    }

    public Item openTreasureChest() {
        if (TreasureChest.NO_TREASURE.equals(treasureChest)) {
            throw new UnsupportedOperationException();
        }
        Item treasureItem = treasureChest.getItem();
        treasureChest = TreasureChest.NO_TREASURE;
        return treasureItem;
    }

    public TreasureChest getTreasureChest() {
        return treasureChest;
    }
}

