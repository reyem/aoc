const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');

const forrest = new Array();
const visible = new Array();

allFileContents.split(/\r?\n/).forEach(line =>  {
    forrest.push(Array.prototype.map.call(line, c => parseInt(c)));
});

// edge trees = 99 * 2 + 97 *2
forrest.forEach( (row, rIndex) => {
    if(rIndex == 0 || rIndex == forrest.length -1) {
        visible.push(...row);
    }else {
        row.forEach((tree, cIndex) => {
            if(cIndex == 0 || cIndex == row.length -1) {
                visible.push(tree);
            }else {
                var top = bottom = left= right = true;
                for(var l = 0; l < cIndex; l++) {
                    if(row[l] >= tree) {
                        // hidden from left
                        left = false;
                    }
                }
                for(var r = row.length -1 ; r > cIndex; r-- ) {
                    if(row[r] >= tree) {
                        // hidden from right;
                        right = false;
                    }
                }
                for(var t = 0; t < rIndex; t++) {
                    if(forrest[t][cIndex] >= tree) {
                        // hidden from top
                        top = false;
                    }
                }
                for(var b = forrest.length -1; b > rIndex; b--) {
                    if(forrest[b][cIndex] >= tree) {
                        // hidden from bottom
                        bottom = false;
                    }
                }
                if(top || bottom || left || right) {
                    visible.push(tree);
                }
            }
        });
    }
}
);

console.log(forrest.length);
console.log(visible.length);
console.log(visible);