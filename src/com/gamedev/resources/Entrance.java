package com.gamedev.resources;

import java.util.concurrent.ThreadLocalRandom;

public class Entrance {
    private int side;
    private int location;
    private boolean state;
    /*
    Constructs the entrance.
    Inputs: length and width of the room, whether or not this entrance is one of the base 4, and the entrance's number.
     */
    public Entrance(int roomX, int roomY, boolean baseEntrance, int entranceNumber) {
        setSide(baseEntrance, entranceNumber);
        setLocation(roomX, roomY);
        setState(false);
        //printEntrance(entranceNumber);
    }
    /*
    Test method for entrances.
     */
    public static void main(String[] args) {
        Entrance entrance1 = new Entrance(5,5,false,0);
        entrance1.printEntrance(0);
    }
    /*
    Prints the entrance.
    Input: entrance number.
     */
    public void printEntrance(int entranceNumber) {
        System.out.println("Entrance " + entranceNumber + ": " + side + " " + location);
    }
    /*
    Get entrance side.
     */
    public int getSide() {
        return side;
    }
    /*
    Set entrance side.
    Note: base entrances are given a side based on their number.
    Otherwise, assigns a random side.
    Input: whether or not this is one of the base entrances, entrance number.
     */
    public void setSide(boolean baseEntrance, int entranceNumber) {
        if (baseEntrance) {
            switch (entranceNumber) {
                case 0:
                    this.side = 1;
                    break;
                case 1:
                    this.side = 2;
                    break;
                case 2:
                    this.side = 3;
                    break;
                case 3:
                    this.side = 4;
                    break;
                default:
                    System.out.println("Bad entrance number for base entrance.");
            }
        } else {
            this.side = ThreadLocalRandom.current().nextInt(1,5);
        }
    }
    /*
    Gets the door's location on it's side.
     */
    public int getLocation() {
        return location;
    }
    /*
    Sets the door's location on it's side, based on the already-declared side of the entrance.
    Input: room's size on X and Y.
     */
    public void setLocation(int roomX, int roomY) {
        switch (side) {
            case 1:
            case 3:{
                this.location = ThreadLocalRandom.current().nextInt(0,roomY);
                break;
            }
            case 2:
            case 4:{
                this.location = ThreadLocalRandom.current().nextInt(0,roomX);
                break;
            }
            default:
                System.out.println("INVALID SIDE");
        }
    }
    /*
    Gets the state of the entrance (open or closed).
     */
    public boolean getState(){ return state; }
    /*
    Sets the state of the entrance (open or closed).
    Input: new state (true if open, false if closed).
     */
    public void setState(boolean newState){ state = newState; }
}
