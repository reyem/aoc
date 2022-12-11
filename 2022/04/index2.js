const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
currentElve = 0;
var count = 0;
allFileContents.split(/\r?\n/).forEach(line =>  {
    var pair = line.split(",");

    // 1-8, 6-10
    var left = pair[0].split("-");
    var right = pair[1].split("-");

    // 7
    var leftBound = Math.max(+left[0], +right[0]);
    
    // 7
    var rightBound = Math.min(+left[1], +right[1]);

    // 8 - 6 + 1 = 3
    // 6-8 => 6,7,8
    if(leftBound <= rightBound) {
        count++;
    }
}
);
console.log(count);