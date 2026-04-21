# Cheatsheet

## Commands

The game accepts commands in either uppercase or lowercase, but the official command names are written in uppercase.

### Main Commands

- `NORTH`: move up
- `SOUTH`: move down
- `EAST`: move right
- `WEST`: move left
- `LOOK`: describes the current location without triggering a new event
- `RESTART`: restarts the game from the beginning
- `END`: exits the game

### Optional Check Commands

These commands are still available, but the map and quick status are already shown automatically after each successful move.

- `MAP`: shows the current game map again
- `STATUS`: shows full current position, lives, steps, and inventory
- `HELP`: shows all available commands and explains the map symbols
- `QUIT`: also exits the game

## Goal

You are a little black cat separated from your mother after a flood. Your goal is to travel across the map, survive dangerous places, find the raft, cross the flooded river, and reach your mother before losing all your lives.

Your mother is hidden on the map, somewhere across the flooded river. Use clues to figure out where to go.

## Map Symbols

- `C`: you, the little cat
- `~`: flooded water
- `.`: normal area

The mother cat is not shown on the map.

## Map Layout

The game uses an 8 by 8 grid.

- Player starts at `(0, 7)`
- MotherCat is hidden at `(7, 0)`
- The flooded river is the full column where `x = 4`
- The raft is at `(2, 6)`

After each successful move, the updated map is automatically printed so the player can see their current location.

## Flooded River

The flooded river blocks the left side of the map from the right side. The player cannot enter flooded cells without the raft.

If the player tries to enter the river without the raft, the game gives a direction hint about where the raft may be.

## Event Effects

Events are hidden on the map and are triggered when the player moves onto a cell with an unused event.

- Trap events remove 2 lives
- Treat events add 2 lives
- Info events give clues without changing lives
- Item events add useful items to the player's inventory
- The raft allows the player to cross flooded cells

The `LOOK` command only describes the current location. It does not trigger a new event by itself.

## Important Event Locations

### Left Side of the River

- Trap at `(1, 7)`: a frightened bird attacks the player
- Trap at `(2, 7)`: broken glass cuts the player
- Trap at `(3, 7)`: a loose plank flips under the player
- Treat at `(1, 6)`: the player finds a dry box and rests
- Raft item at `(2, 6)`: the player finds the raft
- Info clue at `(3, 6)`: the raft may help the player cross the river
- Trap at `(0, 5)`: sharp debris blocks the way
- Trap at `(1, 5)`: mud pulls at the player's paws
- Info clue at `(2, 5)`: a faint meow comes from the northeast
- Treat at `(3, 5)`: the player finds a small fish
- Trap at `(0, 4)`: a hidden hole makes the player stumble
- Info clue at `(1, 4)`: the shortest path may not be the safest path
- Trap at `(2, 4)`: a broken fence scratches the player
- Trap at `(3, 4)`: a sudden wave pushes debris into the path
- Treat at `(1, 3)`: a friendly animal lets the player rest
- Trap at `(2, 3)`: the ground collapses
- Info clue at `(3, 3)`: the mother’s smell grows stronger across the river

### Flooded River

- Trap at `(4, 7)`: the current pulls against the raft
- Trap at `(4, 6)`: a hidden branch hits the raft
- Info clue at `(4, 5)`: the raft shakes but stays above water
- Trap at `(4, 4)`: cold water slows the player down
- Treat at `(4, 3)`: the player finds a calm patch of water
- Trap at `(4, 2)`: a strong current almost pulls the player under
- Info clue at `(4, 1)`: the player is across the worst of the river

### Right Side of the River

- Trap at `(5, 7)`: a wave knocks the player against a wall
- Trap at `(6, 7)`: sharp stones hurt the player
- Treat at `(7, 7)`: the player finds a warm, dry corner
- Trap at `(5, 6)`: wet ground slips under the player
- Info clue at `(6, 6)`: the mother’s scent is stronger toward the north
- Trap at `(7, 6)`: a falling branch almost traps the player
- Treat at `(5, 5)`: the player rests on a safe ledge
- Trap at `(6, 5)`: a broken board snaps
- Info clue at `(7, 5)`: a soft meow comes from above
- Trap at `(5, 4)`: a dog barks and scares the player
- Trap at `(6, 4)`: metal scraps cut the player
- Info clue at `(7, 4)`: the meow is closer
- Treat at `(5, 3)`: the player finds a dry spot
- Trap at `(6, 3)`: a roof tile falls
- Info clue at `(7, 3)`: the mother’s scent is very strong
- Trap at `(5, 2)`: a puddle hides sharp debris
- Info clue at `(6, 2)`: the sound is coming from the northeast
- Trap at `(7, 2)`: the player slips on wet stone steps
- Trap at `(5, 1)`: a gust pushes the player into a branch
- Info clue at `(6, 1)`: the mother is very close
- Trap at `(7, 1)`: a final wave crashes near the shore

## Challenges

The player must survive traps, find the raft, cross the flooded river, and use clues to locate the hidden mother cat. The game is designed so that walking directly toward the goal can be dangerous. The safest route may require exploring, reading clues, and avoiding repeated trap-heavy paths.

The player loses if their lives reach 0.

## Restarting or Ending the Game

The player can restart or end the game in two ways:

- During the game, type `RESTART` to restart
- During the game, type `END` or `QUIT` to exit
- After winning or losing, type `RESTART` to play again
- After winning or losing, type `END` to finish

## Endings

The game has multiple possible endings based on the player’s choices:

- **Best Ending**: the player reaches the mother in 16 steps or fewer
- **Survival Ending**: the player reaches the mother with 3 or fewer lives left
- **Safe Ending**: the player reaches the mother after a longer journey while still having enough lives