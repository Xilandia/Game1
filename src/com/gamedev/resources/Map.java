package com.gamedev.resources;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Map {

    public int size = 0;
    String[][] mapLayout;
    List<Room> rooms = new ArrayList<>();
    boolean generated = false;
    List<Integer[]> availableRoomLocations = new ArrayList<>();
    int xBoundary1 = 0;
    int xBoundary2 = 0;
    int yBoundary1 = 0;
    int yBoundary2 = 0;
    /*
    Builds the map.
    Inputs: map size (currently all maps are squares); number of rooms the
    map should include; type of generation for the program to use.
     */
    public Map(int initSize, int numRooms, int mapType) {
        size = initSize;
        mapLayout = new String[size][size];
        switch (mapType) {
            case 1:
                randomTerrainGeneration();
                break;
            case 2:
                roomTerrainGeneration(numRooms);
                break;
            case 3:
                distributedRoomGeneration(numRooms);
            case 4:
                bossRoomTerrainGeneration();
                break;
        }
        System.out.println("Map Constructed: " + size + " by " + size);
    }
    /*
    Test method for maps.
     */
    public static void main(String[] args) {
        Map map1 = new Map(40,2,3);
        //map1.printMap();
    }
    /*
    Variant 1 of map gen: Obstacle course.
    This method generates a map that contains a random combination of
    spaces and walls (- and X).
     */
    private void randomTerrainGeneration() {
        List<Integer> randomNumbers = new ArrayList<>();
        List<Integer> filledPositions = new ArrayList<>();
        int min = size/2;
        int max = min + size/4+1;
        System.out.println("MinMax: " + min + " " + max);
        int total = size;
        int rand;
        int rand1;
        int start = 0;
        int end = 0;
        if (generated) {
            System.out.println("GENERATED");
            return;
        } else {
            for (int a = 0; a < size; a++) {
                total = size;
                for (int c = 0; c < size; c++) {
                    randomNumbers.add(c);
                }
                rand = ThreadLocalRandom.current().nextInt(min, max);
                System.out.println(rand);
                for (int d = 0; d < rand; d++) {
                    rand1 = ThreadLocalRandom.current().nextInt(0, total);
                    filledPositions.add(randomNumbers.get(rand1));
                    randomNumbers.remove(rand1);
                    total--;
                }
                System.out.println(filledPositions);
                System.out.println(randomNumbers);
                for (int b = 0; b < size; b++) {
                    if (filledPositions.contains(b)){
                        mapLayout[b][a] = "-";
                    } else {
                        mapLayout[b][a] = "X";
                    }
                    if (a==0) {
                        mapLayout[filledPositions.get(0)][a] = "S";
                        start = filledPositions.get(0);
                    }
                    if (a==size-1) {
                        mapLayout[filledPositions.get(0)][a] = "E";
                        end = filledPositions.get(0);
                    }
                }
                randomNumbers.clear();
                filledPositions.clear();
            }
            System.out.println(start + " " + end);
            generated = true;
        }
    }
    /*
    Variant 2 of map gen: ver1 room generation.
    Input: number of rooms to generate.
    This method works by:
    1) Paint the map black (place void everywhere).
    2) Populate the array list of rooms with the amount required.
    3) Place the rooms onto the map one at a time. If the room being placed intersects
    with a different one, then re-generate the room's location.
    4) Paint the rooms onto the map, by labelling each tile based on the rooms.
    5) Paint entrances onto the map.
     */
    private void roomTerrainGeneration(int numRooms) {
        /*for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                mapLayout[a][b] = "V";
            }
        }*/
        for (int c = 0; c < numRooms; c++) {
            rooms.add(new Room());
        }
        for (int d = 0; d < numRooms; d++) {
            placeRoomInMap(d);
            switch (d) {
                case 0:
                    System.out.println("D = 0");
                    break;
                case 1:
                    System.out.println("D = 1");
                    while (rooms.get(0).checkRoomCoorMismatch(rooms.get(1))) {
                        placeRoomInMap(1);
                    }
                    break;
                case 2:
                    System.out.println("D = 2");
                    while (rooms.get(0).checkRoomCoorMismatch(rooms.get(2)) || rooms.get(1).checkRoomCoorMismatch(rooms.get(2))) {
                        placeRoomInMap(2);
                    }
                    break;
                case 3:
                    System.out.println("D = 3");
                    while (rooms.get(0).checkRoomCoorMismatch(rooms.get(3)) || rooms.get(1).checkRoomCoorMismatch(rooms.get(3)) || rooms.get(2).checkRoomCoorMismatch(rooms.get(3))) {
                        placeRoomInMap(3);
                    }
                    break;
                case 4:
                    System.out.println("D = 4");
                    while (rooms.get(0).checkRoomCoorMismatch(rooms.get(4)) || rooms.get(1).checkRoomCoorMismatch(rooms.get(4)) || rooms.get(2).checkRoomCoorMismatch(rooms.get(4)) || rooms.get(3).checkRoomCoorMismatch(rooms.get(4))) {
                        placeRoomInMap(4);
                    }
                    break;
                case 5:
                    System.out.println("D = 5");
                    while (rooms.get(0).checkRoomCoorMismatch(rooms.get(5)) || rooms.get(1).checkRoomCoorMismatch(rooms.get(5)) || rooms.get(2).checkRoomCoorMismatch(rooms.get(5)) || rooms.get(3).checkRoomCoorMismatch(rooms.get(5)) || rooms.get(4).checkRoomCoorMismatch(rooms.get(5))) {
                        placeRoomInMap(5);
                    }
                    break;
                case 6:
                    System.out.println("D = 6");
                    while (rooms.get(0).checkRoomCoorMismatch(rooms.get(6)) || rooms.get(1).checkRoomCoorMismatch(rooms.get(6)) || rooms.get(2).checkRoomCoorMismatch(rooms.get(6)) || rooms.get(3).checkRoomCoorMismatch(rooms.get(6)) || rooms.get(4).checkRoomCoorMismatch(rooms.get(6)) || rooms.get(5).checkRoomCoorMismatch(rooms.get(6))) {
                        placeRoomInMap(6);
                    }
                    break;
                case 7:
                    System.out.println("D = 7");
                    while (rooms.get(0).checkRoomCoorMismatch(rooms.get(7)) || rooms.get(1).checkRoomCoorMismatch(rooms.get(7)) || rooms.get(2).checkRoomCoorMismatch(rooms.get(7)) || rooms.get(3).checkRoomCoorMismatch(rooms.get(7)) || rooms.get(4).checkRoomCoorMismatch(rooms.get(7)) || rooms.get(5).checkRoomCoorMismatch(rooms.get(7)) || rooms.get(6).checkRoomCoorMismatch(rooms.get(7))) {
                        placeRoomInMap(7);
                    }
                    break;
                case 8:
                    System.out.println("D = 8");
                    while (rooms.get(0).checkRoomCoorMismatch(rooms.get(8)) || rooms.get(1).checkRoomCoorMismatch(rooms.get(8)) || rooms.get(2).checkRoomCoorMismatch(rooms.get(8)) || rooms.get(3).checkRoomCoorMismatch(rooms.get(8)) || rooms.get(4).checkRoomCoorMismatch(rooms.get(8)) || rooms.get(5).checkRoomCoorMismatch(rooms.get(8)) || rooms.get(6).checkRoomCoorMismatch(rooms.get(8)) || rooms.get(7).checkRoomCoorMismatch(rooms.get(8))) {
                        placeRoomInMap(8);
                    }
                    break;
                default:
                    System.out.println("Not yet implemented");
            }
        }
        populateMapTiles(numRooms, true);
    }
    /*
    Variant 3 of map gen: ver2 room generation
    */
    private void distributedRoomGeneration(int numRooms){
        xBoundary1 = ThreadLocalRandom.current().nextInt((int)(size*0.3), (int)(size*0.4));
        xBoundary2 = ThreadLocalRandom.current().nextInt((int)(size*0.7), (int)(size*0.8));
        yBoundary1 = ThreadLocalRandom.current().nextInt((int)(size*0.3), (int)(size*0.4));
        yBoundary2 = ThreadLocalRandom.current().nextInt((int)(size*0.7), (int)(size*0.8));
        availableRoomLocations.add(new Integer[]{0, 0, xBoundary1, yBoundary1});
        availableRoomLocations.add(new Integer[]{xBoundary1, 0, xBoundary2-xBoundary1, yBoundary1});
        availableRoomLocations.add(new Integer[]{xBoundary2, 0, size-xBoundary2, yBoundary1});
        availableRoomLocations.add(new Integer[]{0, yBoundary1, xBoundary1, yBoundary2-yBoundary1});
        availableRoomLocations.add(new Integer[]{xBoundary1, yBoundary1, xBoundary2-xBoundary1, yBoundary2-yBoundary1});
        availableRoomLocations.add(new Integer[]{xBoundary2, yBoundary1, size-xBoundary2, yBoundary2-yBoundary1});
        availableRoomLocations.add(new Integer[]{0, yBoundary2, xBoundary1, size-yBoundary2});
        availableRoomLocations.add(new Integer[]{xBoundary1, yBoundary2, xBoundary2-xBoundary1, size-yBoundary2});
        availableRoomLocations.add(new Integer[]{xBoundary2, yBoundary2, size-xBoundary2, size-yBoundary2});

        for (int a = 0; a < numRooms; a++){
            int chosenIndex = (int)(Math.random()*availableRoomLocations.size());
            Integer[] roomStats = availableRoomLocations.get(chosenIndex);
            System.out.println(chosenIndex);
            availableRoomLocations.remove(chosenIndex);
            rooms.add(new Room(roomStats[0],roomStats[1],roomStats[2],roomStats[3]));
        }

        System.out.println("Boundaries:");
        System.out.println(xBoundary1);
        System.out.println(xBoundary2);
        System.out.println(yBoundary1);
        System.out.println(yBoundary2);
        populateMapTiles(numRooms, true);
    }
    /*
    Placeholder: variant 4 of map gen: boss floors.
    */
    private void bossRoomTerrainGeneration() {
        // TODO Auto-generated method stub

    }
    /*
    Populates the map, once all the assets are generated.
     */
    private void populateMapTiles(int numRooms, boolean entrances) {
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                mapLayout[a][b] = "V";
            }
        }
        for (int e = 0; e < size; e++) {
            for (int f = 0; f < size; f++) {
                for (int g = 0; g < numRooms; g++) {
                    if (e  > rooms.get(g).getRoomXCoor() && e < rooms.get(g).getRoomXCoor() + rooms.get(g).getRoomX()) {
                        if (f  > rooms.get(g).getRoomYCoor() && f < rooms.get(g).getRoomYCoor() + rooms.get(g).getRoomY()) {
                            if (!mapLayout[f][e].contentEquals("V")) {
                                mapLayout[f][e] = "!";
                            } else {
                                mapLayout[f][e] = "F";
                            }
                        }
                    }
                    if (e  > rooms.get(g).getRoomXCoor()-1 && e < rooms.get(g).getRoomXCoor() + rooms.get(g).getRoomX()+1) {
                        if (f  > rooms.get(g).getRoomYCoor()-1 && f < rooms.get(g).getRoomYCoor() + rooms.get(g).getRoomY()+1) {
                            if (!(e  > rooms.get(g).getRoomXCoor() && e < rooms.get(g).getRoomXCoor() + rooms.get(g).getRoomX()) || !(f  > rooms.get(g).getRoomYCoor() && f < rooms.get(g).getRoomYCoor() + rooms.get(g).getRoomY())) {
                                if (!mapLayout[f][e].contentEquals("V")) {
                                    mapLayout[f][e] = "!";
                                } else {
                                    mapLayout[f][e] = "W";
                                }
                            }
                        }
                    }
                    if (e  > rooms.get(g).getRoomXCoor()-2 && e < rooms.get(g).getRoomXCoor() + rooms.get(g).getRoomX()+2) {
                        if (f  > rooms.get(g).getRoomYCoor()-2 && f < rooms.get(g).getRoomYCoor() + rooms.get(g).getRoomY()+2) {
                            if (!(e  > rooms.get(g).getRoomXCoor()-1 && e < rooms.get(g).getRoomXCoor() + rooms.get(g).getRoomX()+1) || !(f  > rooms.get(g).getRoomYCoor()-1 && f < rooms.get(g).getRoomYCoor() + rooms.get(g).getRoomY()+1)) {
                                if (!mapLayout[f][e].contentEquals("V")) {
                                    mapLayout[f][e] = "!";
                                } else {
                                    mapLayout[f][e] = "P";
                                }
                            }
                        }
                    }
                }
            }
        }
        if (entrances) {
            for (int j = 0; j < rooms.size(); j++) {
                for (int k = 0; k < rooms.get(j).entrances.size(); k++) {
                    switch (rooms.get(j).entrances.get(k).getSide()) {
                        case 1:
                            mapLayout[rooms.get(j).getRoomYCoor() - 1][rooms.get(j).entrances.get(k).getLocation() + rooms.get(j).getRoomXCoor()] = "N";
                            break;
                        case 2:
                            mapLayout[rooms.get(j).entrances.get(k).getLocation() + rooms.get(j).getRoomYCoor()][rooms.get(j).getRoomX() + rooms.get(j).getRoomXCoor() + 1] = "E";
                            break;
                        case 3:
                            mapLayout[rooms.get(j).getRoomY() + rooms.get(j).getRoomYCoor() + 1][rooms.get(j).entrances.get(k).getLocation() + rooms.get(j).getRoomXCoor()] = "S";
                            break;
                        case 4:
                            mapLayout[rooms.get(j).entrances.get(k).getLocation() + rooms.get(j).getRoomYCoor()][rooms.get(j).getRoomXCoor() - 1] = "T";
                            break;
                        default:
                            System.out.println("BAD SIDE - ENTRANCE PRINTING");
                    }
                }
            }
        }
    }
    /*
    Gives the room it's location.
    Input: number of the room currently being placed.
     */
    private void placeRoomInMap(int roomNum) {
        int xCoor = 0;
        int yCoor = 0;
        int xMin = 3;
        int xMax = size - 3;
        int yMin = 3;
        int yMax = size - 3;
        xCoor = ThreadLocalRandom.current().nextInt(xMin, xMax - rooms.get(roomNum).getRoomX());
        yCoor = ThreadLocalRandom.current().nextInt(yMin, yMax - rooms.get(roomNum).getRoomY());
        rooms.get(roomNum).setRoomXCoor(xCoor);
        rooms.get(roomNum).setRoomYCoor(yCoor);
    }
    /*
    Returns the value of a tile.
    Input: x and y value.
    Output: tile value.
     */
    public String getTileValue(int x, int y){
        return mapLayout[x][y];
    }
    /*
    Prints the char value of each tile on the map.
     */
    public void printMap() {
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                System.out.print(mapLayout[a][b] + " ");
            }
            System.out.println(" " + (a+1));
        }
    }

}

