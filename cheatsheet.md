# Cheatsheet

## Commands

- `help`: shows all available commands and explains the map symbols
- `north`: move up
- `south`: move down
- `east`: move right
- `west`: move left
- `look`: describes the current location
- `map`: shows the game map
- `status`: shows current position, lives, steps, and inventory
- `quit`: exits the game

## Goal

You are a little black cat separated from your mother after a flood. Your goal is to travel across the map, survive dangerous places, find useful items, and reach your mother at position `(5, 0)` before losing all your lives.

## Map Symbols

- `C`: you, the little cat
- `M`: your mother
- `~`: flooded water
- `.`: normal area

## Map Layout

The game uses a 6 by 6 grid.

- Player starts at `(0, 5)`
- MotherCat is at `(5, 0)`

### Flooded Cells

These cells require the raft before the player can enter them:

- `(3, 1)`
- `(3, 2)`
- `(4, 2)`

### Event Locations

- Trap at `(1, 5)`: a frightened bird attacks the player
- Treat at `(2, 4)`: the player finds a fish
- Info clue at `(1, 1)`: the player hears a faint meow from the northeast
- Raft item at `(4, 5)`: the player finds the raft
- Trap at `(3, 3)`: a sharp branch scratches the player
- Treat at `(4, 4)`: a friendly animal helps the player rest
- Info clue at `(0, 2)`: the scent of the mother feels stronger to the east
- Info clue at `(2, 2)`: the player is warned about dangerous water
- Trap at `(5, 4)`: a sudden wave hits the player
- Treat at `(5, 2)`: the player finds a dry spot and recovers strength
- Info clue at `(4, 1)`: the player can almost hear the mother

## Challenges

The player must survive traps, use clues to move through the map, and find the raft before crossing flooded cells. The player wins by reaching the mother cat at `(5, 0)`. The player loses if their lives reach 0.

## Endings

The game has multiple possible endings based on the player’s choices:

- **Best Ending**: the player reaches the mother in 12 steps or fewer
- **Survival Ending**: the player reaches the mother with 3 or fewer lives left
- **Safe Ending**: the player reaches the mother after a longer journey while still having enough lives