const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
currentElve = 0;
var count = 0;
allFileContents.split(/\r?\n/).forEach(line =>  {
    var pair = line.split(",");
    var left = pair[0].split("-");
    var right = pair[1].split("-");
    if(+left[0] <= +right[0] && +left[1] >= +right[1]) {
        count++;
    }else if(+right[0] <= +left[0] && +right[1] >= +left[1]) {
        count++;    
    }
}
);
console.log(count);