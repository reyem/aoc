package com.reyem.aoc24;

import java.io.*;
import java.util.*;

public class Day6 {

    private static final String PUZZLE_INPUT = "day6/input.txt";

    private static boolean PRINT = true;


    public static void main(String[] args) {
        PRINT = false;
        char[][] map = getPuzzleInput(PUZZLE_INPUT);
        Map map1 = new Map(map);
        Guard guard = solvePuzzle1(map1);
        int puzzle1 = guard.distinctPositions;
        System.out.println("Puzzle 1: " + puzzle1);

        int puzzle2 = guard.positionForEndlessLoop;
        System.out.println("Puzzle 2: " + puzzle2);

    }

    public static void printMap(char[][] map) {
        if(!PRINT) return;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    public static char[][] copyMap(char[][] map) {
        char[][] newMap = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            newMap[i] = Arrays.copyOf(map[i], map[i].length);
        }
        return newMap;
    }


    public static char[][] getPuzzleInput(String file) {
        BufferedReader input = new BufferedReader(new InputStreamReader(Day6.class.getClassLoader().getResourceAsStream(file)));
        ArrayList<char[]> rows = new ArrayList<>();
        try (input) {
            String line = input.readLine();
            do {
                char[] mapRow = line.toCharArray();
                rows.add(mapRow);
                line = input.readLine();
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows.toArray(new char[0][]);
    }


    public static Guard solvePuzzle1(Map map) {
        Guard guard = map.guard;
        int distinctPositions = guard.moveAsLongAsOnMap(map);
        return guard;

    }

    public static int solvePuzzle2(Map map) {
        Guard guard = map.guard;
        int distinctPositions = guard.moveAsLongAsOnMap(map);
        int nrOfLoopPos = guard.positionForEndlessLoop;
        return nrOfLoopPos;

    }

    public static class Map {

        public static char VERTICAL_VISIT = '|';
        public static char HORIZONTAL_VISIT = '-';
        public static char TURN_VISIT = '+';

        public java.util.Map<String, Obstacle> obstacles = new HashMap<>();

        public Guard guard;

        public char[][] map;

        public Map(char[][] map) {
            this.map = map;
            parseObstacles();
        }

        public Map(Map toCopy) {
            this.map = copyMap(toCopy.map);
            for (String obstacleKey : toCopy.obstacles.keySet()) {
                obstacles.put(obstacleKey, new Obstacle(toCopy.obstacles.get(obstacleKey)));
            }
            this.guard = new Guard(toCopy.guard);
        }

        public void addTestObstacle(int r, int c) {
            obstacles.put(Obstacle.generatKey(r, c), new Obstacle(r, c));
            map[r][c] = Obstacle.TEST_OBSTACLE;
        }

        private void parseObstacles() {
            for (int r = 0; r < map.length; r++) {
                for (int c = 0; c < map[r].length; c++) {
                    if (Obstacle.isObstacle(map[r][c])) {
                        Obstacle obstacle = new Obstacle(r, c);
                        obstacles.put(Obstacle.generatKey(r, c), obstacle);
                    } else if (Guard.isGuard(map[r][c])) {
                        Guard.DIRECTION dir = Guard.DIRECTION.match(map[r][c]);
                        if (dir != null) {
                            guard = new Guard(r, c, dir);
                        }
                    }
                }
            }
        }

        public boolean visited(int r, int c) {
            return map[r][c] == HORIZONTAL_VISIT || map[r][c] == VERTICAL_VISIT || map[r][c] == TURN_VISIT || Guard.DIRECTION.match(map[r][c]) != null;
        }

        public void turned(int row, int column) {
            map[row][column] = TURN_VISIT;
        }

        public void horizontal(int row, int column) {
            if(!guard.isBackOnStartPos()) map[row][column] = HORIZONTAL_VISIT;
        }

        public void vertical(int row, int column) {
            if(!guard.isBackOnStartPos()) map[row][column] = VERTICAL_VISIT;
        }

        public boolean isObstacle(int r, int c) {
            return Obstacle.isObstacle(map[r][c]);
        }

        public boolean visitedObstacleBefore(int row, int column, Guard.DIRECTION direction) {
            Obstacle obstacle = obstacles.get(Obstacle.generatKey(row, column));
            switch (direction) {
                case LEFT:
                    return obstacle.hitFromRight;
                case UP:
                    return obstacle.hitFromBottom;
                case DOWN:
                    return obstacle.hitFromTop;
                case RIGHT:
                    return obstacle.hitFromLeft;
            }
            return false;
        }

        public void visitObstacle(int row, int column, Guard.DIRECTION direction) {
            Obstacle obstacle = obstacles.get(Obstacle.generatKey(row, column));
            switch (direction) {
                case LEFT:
                    obstacle.hitFromRight = true;
                    break;
                case UP:
                    obstacle.hitFromBottom = true;
                    break;
                case DOWN:
                    obstacle.hitFromTop = true;
                    break;
                case RIGHT:
                    obstacle.hitFromLeft = true;
                    break;
            }
        }
    }

    public static class Obstacle {
        public static final char OBSTACLE = '#';
        public static final char TEST_OBSTACLE = 'O';

        public int column;

        public int row;

        public boolean hitFromTop = false;
        public boolean hitFromBottom = false;
        public boolean hitFromLeft = false;
        public boolean hitFromRight = false;

        public Obstacle(int column, int row) {
            this.column = column;
            this.row = row;
        }

        public Obstacle(Obstacle obstacle) {
            this.column = obstacle.column;
            this.row = obstacle.row;
            this.hitFromTop = obstacle.hitFromTop;
            this.hitFromBottom = obstacle.hitFromBottom;
            this.hitFromLeft = obstacle.hitFromLeft;
            this.hitFromRight = obstacle.hitFromRight;

        }

        public static boolean isObstacle(char c) {
            return c == OBSTACLE || c == TEST_OBSTACLE;
        }

        public static String generatKey(int row, int column) {
            return row + "_" + column;
        }
    }

    public static class Guard {

        public int distinctPositions = 1;

        private int hitMultipleTimes = 0;

        public int positionForEndlessLoop = 0;

        public static enum DIRECTION {
            UP('^'),
            DOWN('v'),
            LEFT('<'),
            RIGHT('>');

            public final char symbol;
            DIRECTION(char symbol) {
                this.symbol = symbol;
            }

            public static DIRECTION match(char symbol) {
                if (symbol == LEFT.symbol) {
                    return LEFT;
                }else if (symbol == RIGHT.symbol) {
                    return RIGHT;
                }else if (symbol == UP.symbol) {
                    return UP;
                }else if (symbol == DOWN.symbol) {
                    return DOWN;
                }
                return null;
            }

            public static DIRECTION turnRight(DIRECTION other) {
                switch (other) {
                    case DOWN: return LEFT;
                    case UP: return RIGHT;
                    case LEFT: return UP;
                    case RIGHT: return DOWN;
                }
                return null;
            }

        };

        public int row;
        public int column;

        public int startRow;
        public int startColumn;

        boolean turnedToStartPosOrSecondVisitOfObstacle = false;


        public DIRECTION direction;
        public DIRECTION startDirection;

        public Guard(Guard toCopy) {
            this.row = toCopy.row;
            this.column = toCopy.column;;
            this.startRow = toCopy.startRow;
            this.startColumn = toCopy.startColumn;
            this.direction = toCopy.direction;
            this.startDirection = toCopy.startDirection;
        }

        public Guard(int row, int column, DIRECTION direction) {
            this.row = row;
            this.column = column;
            this.startColumn = column;
            this.startRow = row;
            this.direction = direction;
            this.startDirection = direction;
        }

        public static boolean isGuard(char c) {
            return c == DIRECTION.UP.symbol || c == DIRECTION.DOWN.symbol || c == DIRECTION.LEFT.symbol || c == DIRECTION.RIGHT.symbol;
        }

        public boolean testOnCopy(Map map) {
            //if(map.guard.isStartPosition(row, column)) return false;
            Map newMap = new Map(map);
            if(direction == DIRECTION.UP) {
                if(this.row > 0) {
                    newMap.addTestObstacle(row -1, column);
                }else {
                    return false;
                }
            }else if(direction == DIRECTION.DOWN) {
                if(this.row < newMap.map.length - 1) {
                    newMap.addTestObstacle(row +1, column);
                }else {
                    return false;
                }

            }else if (direction == DIRECTION.LEFT) {
                if(this.column > 0) {
                    newMap.addTestObstacle(row, column -1);
                }else {
                    return false;
                }

            }else if(direction == DIRECTION.RIGHT) {
                if(this.column < newMap.map[row].length -1) {
                    newMap.addTestObstacle(row, column + 1);
                }else {
                    return false;
                }

            }
            Guard dummy = newMap.guard;
            //dummy.direction = DIRECTION.turnRight(dummy.direction);
            //dummy.startDirection = dummy.direction;

            boolean onMap = true;
            while(onMap && !dummy.turnedToStartPosOrSecondVisitOfObstacle) {
                onMap = dummy.move(newMap, false);
            }
            if(dummy.turnedToStartPosOrSecondVisitOfObstacle) {
                printMap(newMap.map);
                return true;
            }else {
                return false;
            }
        }



        /**
         * returns true still on map.
         * @param map
         * @return
         */
        public int moveAsLongAsOnMap(Map map){
            boolean onMap = true;
            do {
                onMap = move(map, true);
            }while (onMap);
            return distinctPositions;
        }

        public boolean move(Map map, boolean doTest) {
            boolean result = false;
            switch (direction) {
                case UP: result = moveUP(map); break;
                case DOWN: result =  moveDOWN(map);break;
                case LEFT: result =  moveLEFT(map);break;
                case RIGHT: result =  moveRIGHT(map);break;
            }
            if(doTest && testOnCopy(map)) {
                positionForEndlessLoop++;
            };
            return result;
        }

        public boolean moveUP(Map map){
            if(row == 0) {
                markAndCount(map, false);
                return false;
            }
            if(map.isObstacle(row-1,column)){
                if(map.visitedObstacleBefore(row-1,column, direction)) {
                    turnedToStartPosOrSecondVisitOfObstacle = true;
                }else {
                    map.visitObstacle(row-1,column, direction);
                }
                direction = DIRECTION.RIGHT;
                if(this.isBackOnStartPos()) {
                    turnedToStartPosOrSecondVisitOfObstacle = true;
                }

                markAndCount(map, true);
                return moveRIGHT(map);
            }else {
               markAndCount(map, false);
               row--;
               return true;
            }
        }

        private boolean moveRIGHT(Map map) {
            if(column == map.map[row].length -1) {
                markAndCount(map, false);
                return false;
            }
            if(map.isObstacle(row,column + 1)){
                if(map.visitedObstacleBefore(row,column + 1, direction)) {
                    turnedToStartPosOrSecondVisitOfObstacle = true;
                }else {
                    map.visitObstacle(row, column + 1, direction);
                }
                direction = DIRECTION.DOWN;
                if(this.isBackOnStartPos()) {
                    turnedToStartPosOrSecondVisitOfObstacle = true;
                }
                markAndCount(map, true);
                return moveDOWN(map);
            }else {
                markAndCount(map, false);
                column++;
                return true;
            }
        }

        private boolean moveDOWN(Map map) {
            if(row == map.map.length - 1) {
                markAndCount(map, false);
                return false;
            }
            if(map.isObstacle(row + 1,column)){
                if(map.visitedObstacleBefore(row + 1,column, direction)) {
                    turnedToStartPosOrSecondVisitOfObstacle = true;
                }else {
                    map.visitObstacle(row + 1, column, direction);
                }
                direction = DIRECTION.LEFT;
                if(this.isBackOnStartPos()) {
                    turnedToStartPosOrSecondVisitOfObstacle = true;
                }
                markAndCount(map, true);
                return moveLEFT(map);
            }else {
                markAndCount(map, false);
                row++;
                return true;
            }
        }

        private boolean moveLEFT(Map map) {
            if(column == 0) {
                markAndCount(map, false);
                return false;
            }
            if(map.isObstacle(row,column - 1)){
                if(map.visitedObstacleBefore(row,column -1, direction)) {
                    turnedToStartPosOrSecondVisitOfObstacle = true;
                }else {
                    map.visitObstacle(row, column -1, direction);
                }
                direction = DIRECTION.UP;
                if(this.isBackOnStartPos()) {
                    turnedToStartPosOrSecondVisitOfObstacle = true;
                }
                markAndCount(map, true);
                return moveUP(map);
            }else {
                markAndCount(map, false);
                column--;
                return true;
            }
        }

        private void markAndCount(Map map, boolean turned) {
            if(!map.visited(row, column)) {
                if(turned) {
                    map.turned(row, column);
                }else {
                    if(direction == DIRECTION.UP || direction == DIRECTION.DOWN) {
                        map.vertical(row,column);
                    }else {
                        map.horizontal(row,column);
                    }
                }
                distinctPositions++;
            }else {
                hitMultipleTimes++;
            }
        }

        public boolean isStartPosition(int row, int column) {
            return startRow == row && startColumn == column;
        }

        private boolean isBackOnStartPos() {
            return column == startColumn && row == startRow && direction == startDirection;
        }
    }


}
