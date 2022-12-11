const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
currentElve = 0;
const rucksacks = [];
var group = []
allFileContents.split(/\r?\n/).forEach(line =>  {
    group.push(line);
    if(group.length == 3) {    
        var similar = findSimilar(group);
        rucksacks.push(score(similar));
        group = [];
    }
});

var sum = rucksacks.reduce((a, b) => a + b, 0);
console.log("sum is " + sum);


function findSimilar(group) {
    
    for (var i = 0; i < group[0].length; i++) {
        var first = group[0].charAt(i);
        for (var j = 0; j < group[1].length; j++) {
            var second = group[1].charAt(j);
            if(first == second) {
                for (var k = 0; k < group[2].length; k++) {
                    var third = group[2].charAt(k);
                    if(second == third) {
                        return first;
                    }
                }
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