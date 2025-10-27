# Tower Defense Game (Java, Swing)

A 2D tower defense game built in Java (Swing) as part of the Eindhoven University of Technology’s Challenge-Based Learning course (2IP90 Programming)

## 🎮 About The Game

Defend your town hall against waves of increasingly difficult enemies! Build strategic defenses using towers and walls, manage your resources wisely, and survive as long as possible. Every 5 waves, powerful boss enemies appear to test your defenses.

### Key Features
- **Wave-based gameplay** with increasing difficulty
- **Strategic building phase** between waves to place towers and walls
- **Active defense** - control your player character to shoot enemies during waves
- **Boss battles** every 5 waves
- **Resource management** - earn gold by defeating enemies
- **Town hall healing system** with dynamic pricing
- **Bomb mechanic** for emergency situations

## 🎯 Gameplay

### Controls
- **WASD** - Move player character
- **Mouse Click** - Shoot at cursor location (during waves)
- **Shift + Click** - Deploy bomb (costs 300 gold)
- **Click on tiles** - Build towers/walls (between waves)

### Game Rules
1. Build and modify defenses **only before waves start**
2. Shoot enemies **only during active waves**
3. Each killed enemy rewards gold
4. If the town hall is destroyed, you lose
5. Enemies spawn from all sides and move toward the town hall

### Building Options
- **Tower** (100 gold) - Automatically attacks enemies within range
- **Wall** (20 gold) - Blocks enemy movement and absorbs damage
- **Destroy** (Free) - Remove your own structures

## 🚀 Installation & Setup

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Git (optional, for cloning)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/MaxCieplinski/CBL-Java-Tower-Defense-Game.git
cd CBL-Java-Tower-Defense-Game
```

Or download and extract the ZIP file from GitHub.

2. Ensure the `sprites` folder is in the project root directory.

### Running the Game

#### Compile
```bash
# Create output directory
mkdir CompiledGame

# Compile all Java files
javac -d CompiledGame Main.java gameclasses/*.java
```

#### Run
```bash
# Run from project root
java -classpath "CompiledGame;." Main
```

**Note for Linux/Mac users:** Use `:` instead of `;` in the classpath:
```bash
java -classpath "CompiledGame:." Main
```

## 🏗️ Technical Architecture

### Core Technologies
- **Java** - Primary programming language
- **Swing UI** - Graphics and user interface
- **Multithreading** - Concurrent game systems (waves, tower attacks, enemy attacks)

### Object-Oriented Design

The project demonstrates advanced OOP principles:

#### Inheritance Hierarchy
```
Entity
├── Player
├── Bullet
└── Enemy
    └── EnemyBoss

GridCell
├── Tower
└── Wall
```

#### Key Classes
- **GamePanel** - Main game loop and rendering (60 FPS)
- **Wave** - Manages enemy spawning and wave progression
- **TowerAttack** - Threaded system for tower targeting
- **EnemyAttack** - Threaded system for enemy collision/damage
- **Map** - Grid-based building system
- **Collider** - Collision detection for all entities

### Design Patterns Used
- **Inheritance** - Entity hierarchy reduces code duplication
- **Composition** - HealthBar, Collider components
- **Observer Pattern** - Menu updates from game state
- **Multithreading** - Independent threads for game systems

## 📚 Learning Outcomes

### Technical Skills Developed
1. **Object-Oriented Programming**
   - Complex class hierarchies and inheritance  
   - Encapsulation and abstraction  
   - Polymorphism for enemy types  

2. **Java Swing & GUI Development**
   - Custom painting and rendering  
   - Event handling (keyboard, mouse)  
   - Dynamic UI updates  

3. **Multithreading & Concurrency**
   - Thread management for game systems  
   - Synchronized collections  
   - Thread-safe operations  

4. **Game Development Fundamentals**
   - Game loop architecture  
   - Collision detection algorithms  
   - State management  

5. **Version Control & Collaboration**
   - Using **Git** and **GitHub** for multi-developer workflow  
   - Branching, merging, and resolving conflicts  
   - Writing clear commit messages and managing pull requests  

### Soft Skills
- **Teamwork** – Coordinated feature development through Git and clear communication  
- **Project Planning** – Feature prioritization and task delegation  
- **Problem Solving** – Debugging complex, interconnected systems  
- **Documentation** – Maintaining clarity and consistency in code and README files  

## 🎨 Game Mechanics

### Economy System
- Starting gold: 100
- Enemy kills: 10 gold (8 gold after wave 3)
- Boss kills: 150 gold
- Town hall healing: 100-1000 gold (scales with wave)

### Difficulty Scaling
- Enemies per wave: `5 + (waveNumber + 1)²`
- Boss spawns: Every 5 waves
- Boss count: `waveNumber / 5`

### Combat Stats
| Unit | Health | Damage | Speed |
|------|--------|--------|-------|
| Player | - | 15 | 4 |
| Enemy | 50 | 10 | 1.0 |
| Boss | 1300 | 35 | 0.32 |
| Tower | 400 | 10 | - |
| Wall | 100 | - | - |
| Town Hall | 500 | - | - |

## 🖼️ Screenshots

*[Screenshots would go here - consider adding gameplay images, menu, wave action, etc.]*

## 🤝 Contributing

This repository was originally developed as part of a **university course project** over the span of **one academic quartile (approximately two months)**.  
It served primarily as a **learning and exploration exercise**, and active development concluded once the coursework was completed.

Although the project may contain **minor bugs or occasional runtime errors**, these generally **do not affect gameplay** and represent the **iterative nature of the learning process**.

Contributions and suggestions are welcome!  
To contribute:
1. Fork the repository  
2. Create a feature branch (`git checkout -b feature/improvement`)  
3. Commit your changes (`git commit -m "Add improvement"`)  
4. Push to your branch (`git push origin feature/improvement`)  
5. Open a Pull Request


## 👥 Authors

- [Max Cieplinski](https://github.com/MaxCieplinski)
- [Alex Deatc](https://github.com/AlexDtc)

## 📄 License

This project is shared publicly for educational and portfolio purposes only. All rights reserved to the original authors.

## 🙏 Acknowledgments

- Eindhoven University of Technology - 2IP90 Programming Course

---

**Course:** 2IP90 Programming  
**Institution:** Eindhoven University of Technology  
**Year:** 2024