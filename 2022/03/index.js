const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
currentElve = 0;
const rucksacks = [];
allFileContents.split(/\r?\n/).forEach(line =>  {
    var left = line.substring(0, line.length /2)
    var right = line.substring(line.length /2, line.length);
    var similar = findSimilar(left, right);
    rucksacks.push(score(similar));
});

var sum = rucksacks.reduce((a, b) => a + b, 0);
console.log("sum is " + sum);


function findSimilar(left, right) {
    for (var i = 0; i < left.length; i++) {
        var l = left.charAt(i);
        for (var j = 0; j < right.length; j++) {
            var r = right.charAt(j);
            if(l == r) {
                return l;
            }
        }
    }
    console.log("error no char found");
    return undefined;
}

function score(character) {
    var charCode = character.charCodeAt(0);
    if(character == character.toLowerCase()) {
        return charCode - 96; // ascii a is 97
    }else {
        return charCode - 65 + 27;
    }
}