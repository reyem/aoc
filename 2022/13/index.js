const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');

var left = undefined;
var right = undefined;
var pairIndex = 1;
var result = 0;
var part2 = [ [[2]], [[6]] ];
allFileContents.split(/\r?\n/).forEach(line =>  {
    if(line == "") {
        left = undefined;
        right = undefined;
        pairIndex++;
    }else if(left == undefined) {
        left = eval(line);   
        part2.push(left);     
    }else {
        right = eval(line);
        part2.push(right);
    }
    if(left != undefined && right != undefined && compare(left,right) < 0) {
        result += pairIndex;
    }
}
);
console.log(result);
part2 = part2.sort(compare);
var firstIndex = 0;
var secondIndex = 0;
part2.forEach((entry, i) => {
    console.log(JSON.stringify(entry))
    if(compare(entry, [[2]] ) == 0) {        
        firstIndex = i + 1;        
    }
    if(compare(entry, [[6]] ) == 0) {
        secondIndex = i + 1;        
    }
});

console.log("decoder key " + firstIndex * secondIndex);

function compare(left, right) {
    if(typeof left == 'number') {
        if(typeof right == 'number') {
            return left - right;
        }else {
            return compare([left], right);
        }
    }else {
        if(typeof right == 'number') {
            return compare(left, [right]);
        }else {
            if(Array.isArray(left) && Array.isArray(right)){                  
                var i = 0;                
                while(i < left.length && i < right.length) {
                    var l = left[i];
                    var r = right[i]
                    var c = compare(l, r);
                    if(c != 0) { // if not same (length)
                        return c;
                    } ;                    
                    i++;
                }                                      
                return left.length - right.length;
            }else {
                console.log("error left " + typeof left + " right " + typeof right );
            }
        }
    }
}