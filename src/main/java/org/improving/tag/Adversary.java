package org.improving.tag;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import org.improving.tag.items.Item;
import org.improving.tag.items.UniqueItems;

@Entity( name = "adversary" )
public class Adversary {
	@Id
	long id;
	
	@Column( name = "Name" )
    private String name;
	
	@Column( name = "HitPoints" )
    private int hitPoints;
	
	@Column( name = "DamageTaken" )
    private int damageTaken;
	
	@Column( name = "AttackDamage" )
    private int attackDamage;
	
	@Column( name = "DropItem" )
	private String dropItemDb;
	
	@Transient
    private Item dropItem = UniqueItems.NOTHING;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getDamageTaken() {
        return damageTaken;
    }

    public void setDamageTaken(int damageTaken) {
        this.damageTaken = damageTaken;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setItem(Item dropItem) {
        this.dropItem = dropItem;
    }

    public Item getItem() {
        return dropItem;
    }
    
    public String getDropItemDb() {
    	return dropItemDb;
    }
    
    public void setDropItemDb( String dropItemDb ) {
    	this.dropItemDb = dropItemDb;
    }
    
    @PostLoad
    public void postLoad() {
        if (null != dropItemDb) {
            this.setItem(Arrays
                    .stream(UniqueItems.values())
                    .filter(item -> item.getName().equals(dropItemDb))
                    .findFirst()
                    .orElse(null)
            );
        }
    }
}
