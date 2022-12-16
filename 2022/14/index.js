const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
var rocks = [];
var maxX = 0;
var minX = 999999999999;
var maxY = 0;
allFileContents.split(/\r?\n/).forEach(line =>  {
    var path = line.split("->")
                      .map(point => point.split(",")
                          .map(
                        str => {
                            String.prototype.trim(str);
                            return parseInt(str);
                        }
                         ));
    path.forEach(point => {
        if(point[0] > maxX) {
            maxX = point[0];
        }
        if(point[0] < minX ) {
            minX = point[0];
        }
        if(point[1] > maxY) {
            maxY = point[1];
        }
    })
    rocks.push(path);
}
);
minX--;
//maxX++;
maxY = maxY + 1;
minX = 500 - maxY;
maxX = 500 + maxY;

const scene = [[]];

for(var px = 0; px <= maxX - minX; px++) {    
    for(var py = 0; py <= maxY; py++) {
        if(scene[py] == undefined) {
            scene[py] = [];
        }
        scene[py][px] = ".";
    }
}

rocks.forEach(path => {
    for(var pi = 1; pi < path.length; pi++) {
        draw(path[pi -1], path[pi]);
    }   
});


function draw(start, end) {
    if(start[1] == end[1]) {
        var xs = (Math.min(end[0],start[0]) - minX);
        var xe = (Math.max(end[0],start[0]) - minX);
        var y = start[1];
        for(var ix = xs; ix <= xe; ix++) {
            scene[y][ix] = "#";
        }
    }else if (start[0] == end[0]){
        var ys = Math.min(start[1], end[1]);
        var ye = Math.max(start[1],end[1]);
        var x = (start[0] - minX)
        for(var iy = ys; iy <= ye; iy++) {
            scene[iy][x] = "#";
        }
    }else{
        console.log("ERROR: cannot draw line from " + start + " to " + end );
    }
}

console.log("minX " + minX + " maxX " + maxX);
var result = floatSand(500);
scene.forEach(line => console.log(line.slice(100,300).join("")));
console.log(result);

function floatSand(startX) {
    var count = 0;
    var startY = 0;
    while(float(startX - minX, startY)) {
        count++;
    }
    return count;
}

function float(x, y){
    if(y == maxY){
        scene[y][x] = "o";
        return true;
    }
    if(scene[y + 1][x] == '.') {
        return float(x, y + 1);
    }else {        
        if(scene[y + 1][x - 1] == ".") {
            return float(x - 1, y + 1);
        }else if(scene[y + 1][x + 1] == "."){
            return float(x + 1, y + 1);
        }else {
            if(x == 500 - minX && y == 0 && scene[y][x] == "o") {
                return false;
            }
            if (scene[y + 1][x] == "#" || scene[y + 1][x] == "o") {
                scene[y][x] = "o";
                return true;
            }else {
                return true;
            }            
        }
    }
}
