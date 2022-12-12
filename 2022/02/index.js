const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
const results = [];
const results2 = [];

allFileContents.split(/\r?\n/).forEach(line =>  {
    var matches = line.match(/([ABC]) ([XYZ])/);
    var elve = matches[1];
    var me = matches[2];
    results.push(play(elve, me)); 
    results2.push(play2(elve, me));    
});

const summed = results.reduce( (acc, current) => [acc[0]+current[0], acc[1]+current[1]], [0,0]);
console.log("me : " + summed[0], " elve: " + summed[1]);
const summed2 = results2.reduce( (acc, current) => [acc[0]+current[0], acc[1]+current[1]], [0,0]);
console.log("me : " + summed2[0], " elve: " + summed2[1]);


function play(elve, me) {
    // A rock, B Paper, C Scissors
    // X rock, Y Paper, Z Scissors
    if( elve == 'A') { // rock
        if(me == 'X') { // rock
            // me: draw 3 + 1
            // elve: draw 3 + 1
            return [3+1, 3+1];
        }else if (me == "Y"){ // paper
            // me; win 6 + 2
            // elve: lost 0 + 1
            return [6+2,0+1];
        }else if (me == "Z") { // scissor
            // me: lost 0 + 3
            // elve: win 6 + 1
            return [0+3,6+1];
        }        
    } else if (elve == 'B') { // Paper
        if(me == 'X') { // rock
            // me; lost 0 + 1
            // elve: win 6 + 2
            return [0+1,6+2];
        }else if (me == "Y"){ // paper
            // me: draw 3 + 2
            // elve: draw 3 + 2
            return [3+2,3+2];
        }else if (me == "Z") { // Scissor
            // me: win 6 + 3
            // elve: lost 0 + 2
            return [6+3,0+2];
        }   
    }else if (elve == 'C') { // scissor
        if(me == 'X') { // rock
            // me: win 6 + 1
            // elve: lost 0 + 3
            return [6+1, 0+3];
        }else if (me == "Y"){ // paper
            // me: lost 0 + 2
            // elve: won 6 + 3
            return [0+2,6+3];
        }else if (me == "Z") {
             // me: draw 3 + 3
            // elve: draw 3 + 3
            return [3+3,3+3];
        }   
        console.log("error elve: " + elve + "me: " + me );
        return [0,0];
    }
}

function play2(elve, me) {
    // A rock, B Paper, C Scissors
    // X loose, Y draw, Z win
    if( elve == 'A') { // rock
        if(me == 'X') { // loose - scissor
            // me: loose 0 + 3
            // elve: win 6 + 1
            return [0+3, 6+1];
        }else if (me == "Y"){ // draw - rock
            // me; draw 3 + 1
            // elve: draw 3 + 1
            return [3+1,3+1];
        }else if (me == "Z") { // win - paper
            // me: win 6 + 2
            // elve: loos 0 + 1
            return [6+2,0+1];
        }        
    } else if (elve == 'B') { // Paper
        if(me == 'X') { // loose - rock
            // me; loose 0 + 1
            // elve: win 6 + 2
            return [0+1,6+2];
        }else if (me == "Y"){ // draw - paper
            // me: draw 3 + 2
            // elve: draw 3 + 2
            return [3+2,3+2];
        }else if (me == "Z") { // win - scissor
            // me: win 6 + 3
            // elve: lost 0 + 2
            return [6+3,0+2];
        }   
    }else if (elve == 'C') { // scissor
        if(me == 'X') { // loose paper
            // me: loose 0 + 2
            // elve: win 6 + 3
            return [0+2, 6+3];
        }else if (me == "Y"){ // draw scissor
            // me: draw 3 + 3
            // elve: draw 0 + 3
            return [3+3,3+3];
        }else if (me == "Z") { // win stone
             // me: win 6 + 1
            // elve: loos 0 + 3
            return [6+1,0+3];
        }   
        console.log("error elve: " + elve + "me: " + me );
        return [0,0];
    }
}

