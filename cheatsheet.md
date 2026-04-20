# Cheatsheet

## Commands

- `help`: shows all available commands and explains the map symbols
- `north`: move up
- `south`: move down
- `east`: move right
- `west`: move left
- `look`: describes the current location without triggering a new event
- `map`: shows the current game map
- `status`: shows current position, lives, steps, and inventory
- `restart`: restarts the game from the beginning
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

After each successful move, the updated map is automatically printed so the player can see their current location.

## Flooded Cells

These cells require the raft before the player can enter them:

- `(3, 1)`
- `(3, 2)`
- `(4, 2)`

If the player tries to enter one of these cells without the raft, the move is blocked.

## Event Locations

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

## Event Effects

Events are triggered when the player moves onto a cell that contains an unused event.

- Trap events remove 2 lives
- Treat events add 2 lives
- Info events give clues without changing lives
- Item events add useful items to the player's inventory
- The raft allows the player to cross flooded cells

The `look` command only describes the current location. It does not trigger a new event by itself.

## Challenges

The player must survive traps, use clues to move through the map, and find the raft before crossing flooded cells. Some routes are blocked until the player has the raft. The player wins by reaching the mother cat at `(5, 0)`. The player loses if their lives reach 0.

## Restarting the Game

The player can restart in two ways:

- Type `restart` during the game
- After winning or losing, type `yes` or `y` when asked whether to play again

## Endings

The game has multiple possible endings based on the player’s choices:

- **Best Ending**: the player reaches the mother in 12 steps or fewer
- **Survival Ending**: the player reaches the mother with 3 or fewer lives left
- **Safe Ending**: the player reaches the mother after a longer journey while still having enough lives