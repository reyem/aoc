const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
const monkeys = [];
var currentMonkey;
const rounds = 10000;
var globalTest = 1n;
class Monkey {
    items = [];
    operation;
    test;
    nextMonkeys = {
        true : -1,
        false: -1
    };
    itemsInspected = 0;
    inspect = function(item){
        this.itemsInspected++;
        //return Math.floor(item / 3);
        return item;
    };
    playRound = function() {
        while(this.items.length > 0) {
            var item = this.items.shift();
            item = this.operation(item);
            item = this.inspect(item);            
            monkeys[this.nextMonkeys[item % this.test == 0n]].items.push(item % globalTest);            
        }
    }
}

allFileContents.split(/\r?\n/).forEach(line =>  {
    if(line.length > 0) {
        if(line.match(/Monkey \d+/s)) {
            currentMonkey = new Monkey();
            monkeys.push(currentMonkey);
         }else {
            found = line.match(/(.*): (.*)/s)
            if(found[1] == "  Starting items") {                
                currentMonkey.items = found[2].split(",").map(s => s.trim()).map(s => BigInt(parseInt(s)));
                //console.log("items num: " + currentMonkey.items);
            }else if(found[1] == "  Operation") {
                var body = "var " + found[2].replace("new", "result") + "; return result;"
                var numbers = body.match(/(\d+)/s);
                if(numbers) {
                    body = body.replace(numbers[1].toString(), numbers[1]+"n");
                }
                currentMonkey.operation = Function("old", body);
                //console.log("result for old 55 " + currentMonkey.operation(55));
            }else if(found[1] == "  Test") {                                
                currentMonkey.test = BigInt(+found[2].match(/divisible by (\d+)/s)[1]);
                //console.log(currentMonkey.test);
                globalTest *= currentMonkey.test;
            }else if(found[1] == "    If true") {
                //console.log("true " + found[2]);
                currentMonkey.nextMonkeys[true] = +found[2].match(/throw to monkey (\d+)/s)[1];
            }else if(found[1] == "    If false") {
                //console.log("false " + found[2]);
                currentMonkey.nextMonkeys[false] = +found[2].match(/throw to monkey (\d+)/s)[1];
            }
         }
    }
}
);
for(var i = 0; i < rounds; i++) {    
    console.log("round " + i);
    monkeys.forEach(monkey => monkey.playRound());    
}
monkeys.sort((a,b) => a.itemsInspected - b.itemsInspected);
console.log(monkeys[monkeys.length -1].itemsInspected + " and " + monkeys[monkeys.length -2].itemsInspected );
console.log("monkey business " + monkeys[monkeys.length -1].itemsInspected * monkeys[monkeys.length -2].itemsInspected );
