const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');

var stacksReg = /(\[([A-Z])\])+/g;
var instrReg = /move (\d+) from (\d) to (\d)/;
var match;
var stacks = [[],[],[],[],[],[],[],[],[]];
var instructionsMode = false;
allFileContents.split(/\r?\n/).forEach(line =>  {        
    if(instructionsMode) {
        var instrArr = line.match(instrReg);
        var number = +instrArr[1];
        var from = +instrArr[2] -1;
        var to = +instrArr[3] -1;
        var intermediate = [];
        for(var i = 0; i < number; i++) {
            intermediate.push(stacks[from].pop());           
        }
        for(var i = 0; i < number; i++) {            
            stacks[to].push(intermediate.pop());
        }

    }else {
        while(match = stacksReg.exec(line)) {
            var stack = +(match.index / 4);
            var create = match[2];
            stacks[stack].unshift(create);
        }
        if(line.trim() == "") {
            instructionsMode = true;        
        }
    }
});
var result = "";
stacks.forEach(  stack => result += stack.pop() );
console.log(result);