const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
var x = 1;
var cycle = 0;
const testCycles = [20,60,100,140,180,220];
var results = [];
allFileContents.split(/\r?\n/).forEach(line =>  {
    if(line.startsWith("noop")) {
        cycleInc();
    }else if(line.startsWith("addx")) {
        cycleInc();
        cycleInc();
        var num = +line.split(" ")[1];
        x+=num;
    }
}
);

function cycleInc() {
    cycle += 1;
    if(testCycles.includes(cycle)) {
        results.push(x * cycle);
    }
}

console.log(results.reduce((x , y) => x + y));

