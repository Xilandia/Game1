# Game1
First game.

JFrameMain- creates JPanel, loads tiles into buffer, and draws map onto JPanel.

Bugs: No known bugs as of now.


Main- Exists for when I want to run the program without JPanel.

Bugs: Not currently working, probably unnecessary.


Map- Object that holds map data. Currently includes three generation methods that work. Once assets are generated using methods 2 and 3, populates map array with tiles.

Variant 1: randomTerrainGeneration- First version of map generation, creates a random pattern of walls and spaces. Works.

Variant 2: roomTerrainGeneration- Second version of map generation, creates a number of rooms, by creating random rectangles while leaving room around each for various other tiles. Works, but is limited to a low amount of rooms, due to the fact that finding room for newer rectangles requires a lot more computation once there are more of them.

Variant 3: distributedRoomGeneration- Third version of map generation, creates a number of rooms, by declaring nine hexes and generating rooms within each one. Works.

Method: populateMapTiles- Populates map with tiles, by filling the map with voids, before placing all rooms onto the map. Room placement works, but entrance bug may be located here (under investigation).

Bugs: While the map is not bugged, there is a chance that the major entrance bug lies here rather than in the entrance class.


Room- Object that holds room data. Currently includes two constructors, one for ver1 roomGen, and one for ver2 roomGen.

Ver1- Constructor and methods that don't require inputs. Generates a room of (3-13, 3-13), generates entrances, and sets the room's aspect (not currently implemented). Works.

Ver2- Constructor and methods that receive inputs. Generates a room within the grid space allocated to the room, then generates entrances and aspect (not currently implemented). Currently includes various printlns, because method is still being ironed out. Most likely works, but may contain bugs.

Bugs: None known, but ver2 roomGen is relatively new, so may still contain bugs. Don't believe that the entrance bug is here.


Entrance- Object that holds entrance data.

Bugs: Currently heavily bugged, not sure exactly where the bug is located, being worked on now.
