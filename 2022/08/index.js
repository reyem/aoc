const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');

const forrest = new Array();
const scores = new Array();

allFileContents.split(/\r?\n/).forEach(line =>  {
    forrest.push(Array.prototype.map.call(line, c => parseInt(c)));
});

// edge trees = 99 * 2 + 97 *2
forrest.forEach( (row, rIndex) => {
    if(rIndex == 0 || rIndex == forrest.length -1) {
        //visible.push(...row);
    }else {
        row.forEach((tree, cIndex) => {
            if(cIndex == 0 || cIndex == row.length -1) {
                //visible.push(tree);
            }else {
                var lScore = rScore = tScore = bScore = 0;                                    
                for(var l = cIndex -1; l >= 0; l--) {
                    if(row[l] <= tree) {
                        lScore++;
                        if(row[l] == tree) {
                            break;
                        }
                    }
                }
                for(var r = cIndex +1; r < row.length; r++) {
                    if(row[r] <= tree) {
                        rScore++;
                        if(row[r] == tree) {
                            break;
                        }
                    }
                }
                for(var t = rIndex -1; t >= 0; t--) {
                    if(forrest[t][cIndex] <= tree) {
                        tScore++;
                        if(forrest[t][cIndex] == tree) {
                            break;
                        }
                    }
                }
                for(var b = rIndex +1; b < forrest.length; b++) {
                    if(forrest[b][cIndex] <= tree) {
                        bScore++;
                        if(forrest[b][cIndex] == tree) {
                            break;
                        }
                    }
                }
                scores.push(lScore * rScore * bScore * tScore);           
            }
        });
    }
}
);

console.log(forrest.length);
console.log(scores.sort( (a, b) => b-a));
console.log(scores[0]);