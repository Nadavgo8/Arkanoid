# Arkanoid
**The game** 

Arkanoid is a block breaker arcade game.
The player controls the paddle which prevents a ball from falling from the playing field, and attempts to bounce the ball against a number of bricks. The ball striking a brick causes the brick to disappear. When all the bricks are gone, the player advances to the next level, where another pattern of bricks appears.
You can pause the game with the 'p' button, that will display a screen with the message "paused -- press space to continue". Once the space buttom is pressed the game will be resumed.
There are four levels which can be played, each of them being a football scenario (doesn't affect the gameplay, just a fan's design:)).

**Implementation**

The classes can be logically divided into several groups:

Levels 
Animations - all screens (End, Pause, Countdown, etc) and the animation runner
Geometry - Velocity, Line, Point, Rectangle
Hit_listeners - BallRemover, BlockRemover, Counter, etc
Sprites - Ball, Paddle, Block, Background, etc. run - GameFlow, GameEnvironment, and
the main class - Ass6Game which runs it all.

The main principles of OOP were used -  abstraction, encapsuliation, inheritance, polymorphism and etc.
The design patterns used were Observer, Decorater, and etc.


**How to run**
First of all, make sure you have ant, it'll make the whole process much simpler.
Once that is done, open your terminal from the project's directory and enter:
"ant compile"
"ant run"

**Have fun and play!**

