const fs = require('fs');

const allFileContents = fs.readFileSync('example.txt', 'utf-8');
currentElve = 0;

var tail = [0,0];
var head = [0,0];
var rope = [head, [0,0], [0,0], [0,0], [0,0] ,[0,0] ,[0,0], [0,0], [0,0], tail];
const tailMap = new Map();
markTailPos();
allFileContents.split(/\r?\n/).forEach(line =>  {
    var move = line.match(/([A-Z]) (\d+)/);
    var direction = move[1];
    var amount = +move[2];
    moveRope(direction, amount);   
}
);

console.log(tailMap.size);

function markTailPos() {
    var posName = tail.toString();
    if(tailMap.has(posName)) {
        tailMap.set(posName, tailMap.get(posName) + 1);
    }else {
        tailMap.set(posName, 1);
    }
}

function moveRope(direction, amount){
    if(direction == "R" || direction == "L") {
        // X left to right
        for(var step = 0; step < amount; step++) {
            if(direction == "R") {
                head[0] += 1;
                followTail();
            }else {
                head[0] -= 1;
                followTail();
            }
        }        
    }else if(direction == "U" || direction == "D") {
        // Y bottom to top
        for(var step = 0; step < amount; step++) {
            if(direction == "U") {
                head[1] += 1;
                followTail();
            }else {
                head[1] -= 1;
                followTail();
            }
        }  
    }
}

function followTail() {
    for(var i = 0; i < rope.length -1; i++) {
        follow(rope[i], rope[i+1]);
    }
}

function follow(h, t) {
    var dist = [h[0] - t[0], h[1] - t[1]];
    if(Math.abs(dist[0]) > 1 || Math.abs(dist[1]) > 1) {
        if(Math.abs(dist[0]) > 0) {
            t[0] += dist[0]/Math.abs(dist[0]);
        }
        if(Math.abs(dist[1]) > 0) {
            t[1] += dist[1]/Math.abs(dist[1]); 
        }   
        /*     
        if(dist[0] == 0) {
             if(dist[1] > 0) {
                t[1] += dist[1] -1;
             }else {
                t[1] += dist[1] + 1;
             }             
        }else if(dist[1] == 0) {
            if(dist[0] > 0) {
                t[0] += dist[0] -1;
             }else {
                t[0] += dist[0] + 1;
             }  
        }else {
            if(Math.abs(dist[0]) > Math.abs(dist[1])) {
                t[1] = h[1];
                if(dist[0] > 0) {
                    t[0] += dist[0] -1;
                 }else {
                    t[0] += dist[0] + 1;
                 }  
            }else {
                t[0] = h[0];
                if(dist[1] > 0) {
                    t[1] += dist[1] -1;
                 }else {
                    t[1] += dist[1] + 1;
                 } 
            }
        }*/
        markTailPos();
    }
}