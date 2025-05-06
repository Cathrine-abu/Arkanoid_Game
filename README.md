# Arkanoid Game

## Overview
This project is a modern take on the classic **Arkanoid Game**, implemented in Java.  
The objective is to destroy all the blocks by bouncing balls off a paddle that you control. The game introduces **color-based mechanics** and **multi-ball gameplay**, adding layers of strategy and excitement.

---

## Features

-  **Paddle Control**: Move the paddle left and right using arrow keys to keep the ball(s) in play.
-  **Blocks**: Breakable blocks arranged in colorful rows, each with a different function.
-  **Ball Physics**: Realistic bouncing off walls, paddle, and blocks.
-  **Collision Detection**: Smooth and accurate handling of collisions for a responsive game experience.
-  **Color Mechanics**:  
  - The **ball's color changes** to match the color of the last block it hit.  
  - The ball can **only break blocks of a different color** than its own.  
  - Hitting a **white block adds a new ball** to the game.  
  - Hitting a **black block ends the game immediately**.
-  **Score Tracking**: Your score is displayed at the top of the screen.
-  **Game Over Conditions**:
  - You **lose** if you run out of all balls.
  - You **win** if you destroy all valid blocks without hitting a black one.

---

## How to Play

1. Run the program.
2. Control the paddle using the **← Left** and **→ Right** arrow keys.
3. The game starts with **3 balls**.
4. Bounce the balls to:
   - **Destroy blocks** (only if their color is different from the ball).
   - **Change the ball's color** to the color of the block it hits.
   - **Gain extra balls** by hitting **white blocks**.
   - **Avoid black blocks** – touching them ends the game!
5. Keep at least one ball in play or the game ends.

---

## Technologies Used

- **Language**: Java
- **Libraries**: `biuoop` (GUI and animation)
- **Concepts**:
  - OOP: Classes, Inheritance, Polymorphism
  - Game Loop & Animation
  - Observer Pattern
  - Collision Detection
  - Input Handling
  - Sprite Management
  - Custom Game Mechanics

---

## Setup and Usage

### Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/arkanoid_game.git](https://github.com/Cathrine-abu/Arkanoid_Game.git)
```
---

## Compile and Run
```bash
cd arkanoid_game
Option 1: Using javac manually
mkdir bin
javac -cp biuoop-1.4.jar -d bin src/**/*.java Ass5Game.java
java -cp "bin;biuoop-1.4.jar" Ass5Game

Option 2: Use Ant (if build.xml is configured)
ant
java -cp "dist/arkanoid_game.jar;biuoop-1.4.jar" Ass5Game

```
## Project Structure
```bash
arkanoid_game/
│
├── src/                      # Source files organized into packages:
│   ├── game/                 # Main game loop and logic
│   ├── geometry/             # Geometry primitives (points, rectangles, etc.)
│   ├── logic/                # Game flow and input
│   ├── sprites/              # Paddle, ball, blocks
│   └── utils/                # Utility classes
│
├── Ass5Game.java             # Main class
├── biuoop-1.4.jar            # External GUI and animation library
├── build.xml                 # (Optional) Ant build file
├── .gitignore                # Git ignore rules
└── README.md                 # You're reading it
```
