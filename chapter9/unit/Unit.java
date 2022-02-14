package chapter9.unit;

import java.util.*;

public class Unit {
    
    private String type;
    private int id;
    private String name;
    private List<Weapon> weapons;
    private Map<String, Object> properties;

    public Unit(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addWeapon(Weapon weapon) {
        if(weapons == null) weapons = new LinkedList<Weapon>();
        weapons.add(weapon);
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setProperty(String property, Object value) {
        if (properties == null) properties = new HashMap<String, Object>();
        properties.put(property, value);
    }

    public Object getProperty(String property) {
        if (properties == null) throw new RuntimeException("No properties for this Unit.");
        
        Object value = properties.get(property);

        if(value == null) {
            throw new RuntimeException("You're screwing up! No property value.");
        } else  {
            return value;
        }       
    }    
}
