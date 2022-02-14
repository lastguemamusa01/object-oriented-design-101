package chapter9.unit;

import java.util.*;
import java.util.Map.Entry;

public class UnitGroup {

    private Map<Integer, Unit> units;

    public UnitGroup(List<Object> unitList) {
        units = new HashMap<Integer, Unit>();

        for(Iterator<Object> i = unitList.iterator(); i.hasNext(); ) {
            Unit unit = (Unit)i.next();
            units.put(unit.getId(), unit);
        }
    }

    public UnitGroup() { 
        this(new LinkedList<Object>());
    }

    public void addUnit(Unit unit) {
        units.put(unit.getId(), unit);
    }

    public void removeUnit(int id) {
        units.remove(id);
    }

    public void removeUnit(Unit unit) {
        removeUnit(unit.getId());
    }

    public Unit getUnit(int id) {
        return (Unit)units.get(id);
    }

    public List<Object> getUnits() {
        List<Object> unitList = new LinkedList<Object>();
        for(Iterator<Entry<Integer, Unit>> i = units.entrySet().iterator(); i.hasNext(); ) {
            Unit unit = (Unit)i.next();
            unitList.add(unit);
        }
        return unitList;
    }    
}
