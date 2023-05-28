# Arkanoid
**The game**
Arkanoid is a block breaker arcade game.
The player controls the paddle which prevents a ball from falling from the playing field, and attempts to bounce the ball against a number of bricks. The ball striking a brick causes the brick to disappear. When all the bricks are gone, the player advances to the next level, where another pattern of bricks appears.
You can pause the game with the 'p' button, that will display a screen with the message "paused -- press space to continue". Once the space buttom is pressed the game will be resumed.
There are four levels which can be played, each of them being a football scenario (doesn't affect the gameplay, just a fan's design:)).

**Implementation**
The classes can be logically divided into several groups:

levels - all stages (currectenly 4, you can add)
animations - all screens (End, Pause, Countdown, etc) and the animation runner
geometry - all required geometry: Velocity, Line, Point, Rectangle
hit_listeners - BallRemover, BlockRemover, Counter, etc
sprites - Ball, Paddle, Block, Background, etc. run - GameFlow, GameEnvironment, and our main Class: RunTheGame

In the implementation of the code we can find various design patterns: Observer, Decorator, Template and more.
In addition, the main basics of OOP principles are kept: abstraction, encapsuliation, inheritance, polymorphism and more.
