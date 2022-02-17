package chapter10.Subway;

import java.util.*;

public class Subway {

    private List<Station> stations;
    private List<Connection> connections;

    public Subway() {
        this.stations = new LinkedList<Station>();
        this.connections = new LinkedList<Connection>();
    }
    
    public void addStation(String stationName) {
        if(!this.hasStation(stationName)) {
            Station station = new Station(stationName);
            stations.add(station);
        }
    }

    public boolean hasStation(String stationName) {
        return stations.contains(new Station(stationName));
    }

    public void addConnection(String station1Name, String station2Name, String lineName) {

        if((this.hasStation(station1Name)) && (this.hasStation(station2Name))) {
            Station station1 = new Station(station1Name);
            Station station2 = new Station(station2Name);
            Connection connection = new Connection(station1, station2, lineName);
            connections.add(connection);
            connections.add(new Connection(station2, station1, connection.getLineName()));
        } else {
            throw new RuntimeException("Invalid connection!");
        }
    }

    public boolean hasConnection(String station1Name, String station2Name, String lineName) {
        Station station1 = new Station(station1Name);
        Station station2 = new Station(station2Name);

        for(Iterator<Connection> i = connections.iterator(); i.hasNext(); ) {
            Connection connection = (Connection)i.next();
            if(connection.getLineName().equalsIgnoreCase(lineName)) {
                if( (connection.getStation1().equals(station1)) && (connection.getStation2().equals(station2))) return true;
            }
        }
        return false;
    }
}
