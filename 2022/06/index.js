const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
//const allFileContents = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
var startIdx = -1;
var endIdx = startIdx + 3
var set = new Set();

while(set.size < 4) {
    set.clear();
    startIdx++;
    endIdx++;
    for(var i = startIdx; i <= endIdx; i++){
        set.add(allFileContents.charAt(i));
    }
}

console.log(endIdx + 1);
console.log(allFileContents.substring(startIdx, endIdx + 1));