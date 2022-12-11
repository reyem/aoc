const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
var x = 1;
var cycle = 0;
const testCycles = [20,60,100,140,180,220];
var results = [];
var sprite = [x -1, x, x+1];
var crt = "";
allFileContents.split(/\r?\n/).forEach(line =>  {
    if(line.startsWith("noop")) {
        cycleInc();
    }else if(line.startsWith("addx")) {
        cycleInc();
        cycleInc();
        var num = +line.split(" ")[1];
        x+=num;
        setSprite();
    }
}
);

function cycleInc() {
    cycle += 1;
    draw();    
    if(testCycles.includes(cycle)) {
        results.push(x * cycle);
    }
}

function draw() {
    if(sprite.includes((cycle -1) % 40)) {
        crt += "#";
    }else {
        crt += ".";
    }
    if(cycle % 40 == 0) {
        crt +="\n";
    }
}

function setSprite() {
    sprite = [x -1, x, x+1];
}

console.log(results.reduce((x , y) => x + y));
console.log("CRT:");
console.log(crt);

