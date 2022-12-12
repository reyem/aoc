const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');
const fsSize = 70000000;
const requiredSpace = 30000000;
const treeRoot = {name : "/",
              size : 0,
              subdirs : new Map(),
              files: new Map(),
              parent : undefined
            };
var currentDir = {name : "/",
                    size : 0,
                    subdirs : new Map([["/", treeRoot]]),
                    files: {}
                    };
var listMode = false;
allFileContents.split(/\r?\n/).forEach(line =>  {
    if(line.startsWith('$')) {
        var parts = line.split(" ");
        if(parts[1] == "ls") {
            listMode = true;
        }else if(parts[1] == "cd") {
            if(parts[2] == "..") {
                currentDir = currentDir.parent;
            }else {
                currentDir = currentDir.subdirs.get(parts[2]);
            }            
            listMode = false;
        }else{
            console.log("error, but got line " + line);
        }
    }else if(listMode) {
        var parts = line.split(" ");
        if(parts[0] == "dir") {
            currentDir.subdirs.set(parts[1],{ name : parts[1], size: 0, subdirs : new Map, files: new Map, parent: currentDir });
        }else {
            currentDir.files.set([parts[1]],{ name : parts[1], size: +parts[0], parent: currentDir });
        }
    }else {
        console.log("error, not list mode but got line " + line);
    }
});

var collectDirs = [];
calcDirSize(treeRoot);

console.log(collectDirs.reduce( (sum, dir) => { return sum + dir.size}, 0));
var freeSpace = fsSize - treeRoot.size;
var spaceToFree = requiredSpace - freeSpace;
console.log("need to free up " + spaceToFree);

collectDirectories(treeRoot, collectDirs, spaceToFree);

collectDirs.sort(function(a, b) { return a.size - b.size});

console.log("delete directory " + collectDirs[0].name +  " of size " + collectDirs[0].size);

function collectDirectories(dir, collectDirs, spaceToFree) {
    if(dir.size >= spaceToFree) {
        collectDirs.push(dir);
    }
    if(dir.subdirs != undefined && dir.subdirs.size > 0) {
        dir.subdirs.forEach( subdir => collectDirectories(subdir, collectDirs, spaceToFree));
    }
}

function calcDirSize( dir ) {
    if(dir.files != undefined && dir.files.size > 0) {
        var totalFiles = 0;
        dir.files.forEach((file) => {
            totalFiles += file.size;
          });
        dir.size = totalFiles;
    }
    
    if(dir.subdirs != undefined && dir.subdirs.size > 0) {
        dir.subdirs.forEach(dir => calcDirSize(dir));
        var totalDirs = 0;
        dir.subdirs.forEach((subdir) => {
            totalDirs += subdir.size;
          });
        dir.size += totalDirs;
    }     
}