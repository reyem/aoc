const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
currentElve = 0;
const elves = [{ calories : 0}];
allFileContents.split(/\r?\n/).forEach(line =>  {
    if(line.trim() === "") {
        let newElve = { calories : 0};
        elves.push(newElve);
        currentElve++;
    }else {
        elves[currentElve].calories += +line;
    }
});

elves.sort((a,b) => { return a.calories - b.calories});
console.log(elves[elves.length -1].calories);
console.log(elves[elves.length -1].calories + elves[elves.length -2].calories + elves[elves.length -3].calories);
