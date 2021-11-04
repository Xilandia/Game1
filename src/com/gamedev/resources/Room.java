package com.gamedev.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Room {

    private int roomX;
    private int roomY;
    private int roomXCoor;
    private int roomYCoor;
    private int roomAspect;
    List<Entrance> entrances = new ArrayList<>();
    /*
    Constructs a room. For ver1.
     */
    public Room() {
        generateRoomSize();
        generateEntrances();
        setRoomAspect();
    }
    /*
    Constructs a room. For ver2.
    Inputs: left top corner x, left top corner y, available length, available width.
    */
    public Room(int xCoor, int yCoor, int availableX, int availableY) {
        System.out.println(xCoor);
        System.out.println(yCoor);
        System.out.println(availableX);
        System.out.println(availableY);
        generateRoomSize(availableX,availableY);
        generateRoomPosition(xCoor,yCoor,availableX,availableY);
        generateEntrances();
        setRoomAspect();
        printRoom();
    }
    /*
    Test method for rooms.
     */
    public static void main(String[] args) {
        Room room1 = new Room();
        room1.printRoom();
    }
    /*
    Generates the length and width of the room. For ver1.
     */
    private void generateRoomSize() {
        roomX = ThreadLocalRandom.current().nextInt(3, 13);
        roomY = ThreadLocalRandom.current().nextInt(3, 13);
    }
    /*
    Generates the length and width of the room. For ver2.
    Inputs: available width, available length.
    */
    private void generateRoomSize(int availableX, int availableY) {
        /*System.out.println(availableX);
        System.out.println(availableY);*/
        roomX = ThreadLocalRandom.current().nextInt((int)(availableX*0.3), (int)(availableX*0.7));
        roomY = ThreadLocalRandom.current().nextInt((int)(availableY*0.3), (int)(availableY*0.7));
    }
    /*
    Places the room inside the quadrant.
    Inputs: initial X and Y coordinates; available X and Y spaces in quadrant.
     */
    private void generateRoomPosition(int xCoor, int yCoor, int availableX, int availableY) {
        /*System.out.println(xCoor);
        System.out.println(yCoor);
        System.out.println(availableX);
        System.out.println(availableY);
        System.out.println(roomX);
        System.out.println(roomY);*/
        roomXCoor = ThreadLocalRandom.current().nextInt(xCoor + 2, xCoor + availableX - roomX - 2);
        roomYCoor = ThreadLocalRandom.current().nextInt(yCoor + 2, yCoor + availableY - roomY - 2);
    }
    /*
    Generates the room's entrances.
     */
    private void generateEntrances() {
        int numAdditionalEntrances = ThreadLocalRandom.current().nextInt(1,5);
        for (int a = 0; a < 4; a++) {
            entrances.add(new Entrance(roomX,roomY,true,a));
        }
        for (int b = 0; b < numAdditionalEntrances ; b++) {
            entrances.add(new Entrance(roomX,roomY,false,0));
        }
    }
    /*
    Checks to make sure that two rooms (this room and another room)
    do not intersect.
    Returns: true if the rooms intersect, false if they don't.
     */
    public boolean checkRoomCoorMismatch(Room otherRoom) {
        int thisLeftXCoor = this.roomXCoor - 3;
        int thisTopYCoor = this.roomYCoor - 3;
        int thisRightXCoor = this.roomXCoor + this.roomX + 3;
        int thisBottomYCoor = this.roomYCoor + this.roomY + 3;
        int otherLeftXCoor = otherRoom.getRoomXCoor() - 3;
        int otherTopYCoor = otherRoom.getRoomYCoor() - 3;
        int otherRightXCoor = otherRoom.getRoomXCoor() + otherRoom.getRoomX() + 3;
        int otherBottomYCoor = otherRoom.getRoomYCoor() + otherRoom.getRoomY() + 3;
        if (otherLeftXCoor >= thisLeftXCoor && otherLeftXCoor <= thisRightXCoor) {
            if (otherTopYCoor >= thisTopYCoor && otherTopYCoor <= thisBottomYCoor) {
                return true;
            }
        }
        if (otherRightXCoor >= thisLeftXCoor && otherRightXCoor <= thisRightXCoor) {
            if (otherTopYCoor >= thisTopYCoor && otherTopYCoor <= thisBottomYCoor) {
                return true;
            }
        }
        if (otherLeftXCoor >= thisLeftXCoor && otherLeftXCoor <= thisRightXCoor) {
            if (otherBottomYCoor >= thisTopYCoor && otherBottomYCoor <= thisBottomYCoor) {
                return true;
            }
        }
        if (otherRightXCoor >= thisLeftXCoor && otherRightXCoor <= thisRightXCoor) {
            if (otherBottomYCoor >= thisTopYCoor && otherBottomYCoor <= thisBottomYCoor) {
                return true;
            }
        }

        if (thisLeftXCoor >= otherLeftXCoor && thisLeftXCoor <= otherRightXCoor) {
            if (thisTopYCoor >= otherTopYCoor && thisTopYCoor <= otherBottomYCoor) {
                return true;
            }
        }
        if (thisRightXCoor >= otherLeftXCoor && thisRightXCoor <= otherRightXCoor) {
            if (thisTopYCoor >= otherTopYCoor && thisTopYCoor <= otherBottomYCoor) {
                return true;
            }
        }
        if (thisLeftXCoor >= otherLeftXCoor && thisLeftXCoor <= otherRightXCoor) {
            if (thisBottomYCoor >= otherTopYCoor && thisBottomYCoor <= otherBottomYCoor) {
                return true;
            }
        }
        if (thisRightXCoor >= otherLeftXCoor && thisRightXCoor <= otherRightXCoor) {
            if (thisBottomYCoor >= otherTopYCoor && thisBottomYCoor <= otherBottomYCoor) {
                return true;
            }
        }

        System.out.println("Final Result: " + thisLeftXCoor + ", " + thisRightXCoor + ", " + thisTopYCoor + ", " + thisBottomYCoor + ", " + otherLeftXCoor + ", " + otherRightXCoor + ", " + otherTopYCoor + ", " + otherBottomYCoor + ".");
        return false;
    }
    /*
    Gets the room's aspect.
     */
    public int getRoomAspect() {
        return roomAspect;
    }
    /*
    Sets the random aspect of the room. (unique thing)
     */
    private void setRoomAspect() {
        this.roomAspect = ThreadLocalRandom.current().nextInt(1,1001);
    }
    /*
    Gets the width of the room.
     */
    public int getRoomX() {
        return roomX;
    }
    /*
    Gets the length of the room.
     */
    public int getRoomY() {
        return roomY;
    }
    /*
    Gets the room's X coordinate (left).
     */
    public int getRoomXCoor() {
        return roomXCoor;
    }
    /*
    Sets the room's X coordinate (left).
    Input: new X coordinate.
     */
    public void setRoomXCoor(int roomXCoor) {
        this.roomXCoor = roomXCoor;
    }
    /*
    Gets the room's Y coordinate (top).
     */
    public int getRoomYCoor() {
        return roomYCoor;
    }
    /*
    Sets the room's Y coordinate (top).
    Input: new Y coordinate.
     */
    public void setRoomYCoor(int roomYCoor) {
        this.roomYCoor = roomYCoor;
    }
    /*
    Prints the room.
     */
    public void printRoom() {
        System.out.println("Room Coordinates: (" + roomXCoor + ", " + roomYCoor + ")");
        System.out.println("Room Size: " + roomX + " by " + roomY);
        /*System.out.println("Entrances:");
        for (int i = 0; i < entrances.size();i++) {
            entrances.get(i).printEntrance(i+1);
        }*/
    }

}

