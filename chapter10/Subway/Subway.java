package chapter10.Subway;

import java.util.*;

public class Subway {

    private List<Station> stations;
    private List<Connection> connections;
    private Map<Station, List<Station>> network;

    public Subway() {
        this.stations = new LinkedList<Station>();
        this.connections = new LinkedList<Connection>();
        this.network = new HashMap<Station, List<Station>>();
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

    public Connection addConnection(String station1Name, String station2Name, String lineName) {

        if((this.hasStation(station1Name)) && (this.hasStation(station2Name))) {
            Station station1 = new Station(station1Name);
            Station station2 = new Station(station2Name);
            Connection connection = new Connection(station1, station2, lineName);
            connections.add(connection);
            connections.add(new Connection(station2, station1, connection.getLineName()));

            addToNetwork(station1, station2);
            addToNetwork(station2, station1);
            return connection;

        } else {
            throw new RuntimeException("Invalid connection!");
        }
    }

    private void addToNetwork(Station station1, Station station2) {
        if(network.keySet().contains(station1)) {
            List<Station> connectingStations = (List<Station>)network.get(station1);
            if(!connectingStations.contains(station2)) connectingStations.add(station2);
        } else {
            List<Station> connectiongStations = new LinkedList<Station>();
            connectiongStations.add(station2);
            network.put(station1, connectiongStations);
        }
    }

    public List<Connection> getDirections(String startStationName, String endStationName) {
        if (!this.hasStation(startStationName) || !this.hasStation(endStationName)) {
            throw new RuntimeException("Stations entered do not exist on this subway.");
        }

        // Dijkstra's algorithm - shortest path between two nodes on a graph.
        Station start = new Station(startStationName);
        Station end = new Station(endStationName);
        List<Connection> route = new LinkedList<Connection>();
        List<Station> reachableStations = new LinkedList<Station>();
        Map<Station, Station> previousStations = new HashMap<Station, Station>();
        
        // This first part of the code handles the case when the end station is just one connection 
        // away from the starting station.
        List<Station> neighbors = (List<Station>)network.get(start);
        for (Iterator<Station> i = neighbors.iterator(); i.hasNext();) {
            Station station = (Station)i.next();
            if(station.equals(end)) {
                route.add(getConnection(start, end));
                return route;
            } else {
                reachableStations.add(station);
                previousStations.put(station, start);
            }
        }

        List<Station> nextStations = new LinkedList<Station>();
        nextStations.addAll(neighbors);
        Station currentStation = start;

        // These loops begin to iterate through each set of stations reachable by the starting station, 
        // and tries to find the least number of stations possible to connect the starting point and 
        // the destination.

        searchLoop:
        for (int i=1; i < stations.size(); i++) {
            List<Station> tmpNextStations = new LinkedList<Station>();
            for (Iterator<Station> j = nextStations.iterator(); j.hasNext();) {
                Station station = (Station)j.next();
                reachableStations.add(station);
                currentStation = station;
                List<Station> currentNeighbors = (List<Station>)network.get(currentStation);
                for(Iterator<Station> k = currentNeighbors.iterator(); k.hasNext();) {
                    Station neighbor = (Station)k.next();
                    if(neighbor.equals(end)) {
                        reachableStations.add(neighbor);
                        previousStations.put(neighbor, currentStation);
                        break searchLoop;
                    } else if (!reachableStations.contains(neighbor)) {
                        reachableStations.add(neighbor);
                        tmpNextStations.add(neighbor);
                        previousStations.put(neighbor, currentStation);
                    }
                }
            }
            nextStations = tmpNextStations;
        }

        // We've found the path by now
        boolean keepLooping = true;
        Station keyStation = end;
        Station station;

        // Once we’ve got a path, we just “unwind” the path,
        // and create a List of connections to get from the starting station to the destination station.
        while (keepLooping) {
            station = (Station)previousStations.get(keyStation);
            route.add(0, getConnection(station, keyStation));
            if(start.equals(station)) keepLooping = false;
            keyStation = station;
        }
        return route;
    }

    // utility method that takes two stations, and looks for a connection between them(on any line)
    private Connection getConnection(Station station1, Station station2) {
        for (Iterator<Connection> i = connections.iterator(); i.hasNext();) {
            Connection connection = (Connection)i.next();
            Station one = connection.getStation1();
            Station two = connection.getStation2();
            if( (station1.equals(one)) && (station2.equals(two))) return connection;
        }
        return null;
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
