#ZombieSimulator2001

This project was done for Intro To Computer Science III (Spring Quarter of 2014), taught by Jeff Edgington (jedgingt@du.edu)

Created by Dan.Cruwys@du.edu, Paul.Heinen@du.edu, and David.Garcia@du.edu. 

Our team name was Team Jeff Edgington, named after (obviously) our professor, who is the "the man".

The name "Zombie Simulator 2001" is a play off games like "Ant Sim" and "Sim City 2000".

Goal
---------------
The idea here was to simulate a zombie take over of a randomly generated city. At this point in our coding career, we were not well versed in both generation and AI. We ended up using the A star algorithm to do pathfinding. It is poorly implemented but also our first attempt at AI. Zombies tend to move towards humans, BUT also tend to group together in packs. This implementation creates a few issues were sometimes Zombies and Humans both make incorrect moves. This both because of conflicting goals, as well as a strange refresh rate for finding new moves. 

**There are a few differant unit types in this game:**

* **Uninfected**: Basic human type, shown in purple. Can move and trys to avoid zombies.
* **Infected**: Basic zombie type, shown in green. Can move and trys to go towards humans as well group in hordes.
* **Medic**: Extended human type, shown in orange. When attacked has a chance to cure the attacker.
* **Police**: Extended human type, shown in blue. Able to sprint when in danger, and has ~10 bullets to fire and kill zombies.

**The game has a soft ending:** When all humans, OR all zombies have been killed. Humans win about 1 in every 1000 games. This generally happens right at the begining when the random zombie spawns are near Police and Medic unit types.

Images and Resources
---------------------
Dr. Nathan Sturtevant (sturtevant@cs.du.edu) was a huge help with getting the AI implemented, he directed us to the A star algorithm.  This knowledge plus some wikipedia articles was the basis for our basic implementation. You can see more of the AI work at DU on http://movingai.com/.

![Zombie Simulator 2001](http://i.imgur.com/SCvcmYg.gif)

It runs smoothly in game, this gif is just choppy :)

How To Run
----------------------
Clone this repo and run the jar! It should all be prepackaged. If you have any issues please email Dan.Cruwys@du.edu.
The code should also be compilable on most computers, but it's only been tested on MacOS 10.9/10.10. 





