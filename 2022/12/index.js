const fs = require('fs');

const allFileContents = fs.readFileSync('input.txt', 'utf-8');

const heightmap = []
var startNode;
var targetNode;
const unvistited = [];
const startNodes = [];
class Node {
    character;
    height;
    row;
    col;
    dist = Number.MAX_SAFE_INTEGER;
    edges;
    visited = false;
    constructor(character, row, col) {
        this.character = character;
        if(character == "S" || character == "E"){
            if(character == "S") {
                this.height = "a".charCodeAt(0);
            }else {
                this.height = "az".charCodeAt(0);
            }            
        }else {
            this.height = character.charCodeAt(0);
        }        
        
        this.row = row;
        this.col = col;
    }    
    setDist = function(dist) {
        this.dist = dist;
    }
    toString = function() {
        return character + "(" + row + "," + col+")";
    }
}

var row = 0;
allFileContents.split(/\r?\n/).forEach(line =>  {
    var rowArr = [];
    heightmap.push(rowArr)
    for(var i = 0; i < line.length; i++) {
        var c = line.charAt(i);        
        rowArr[i] = new Node(c, row, i);
        unvistited.push(rowArr[i]);
        if(c == 'S') {
            startNode = rowArr[i];           
            startNodes.push(rowArr[i]);
        }else if(c == "E") {
            targetNode = rowArr[i];
            targetNode.setDist(0);
        }
        if(c == "a") {
            startNodes.push(rowArr[i]);
        }
    }        
    row++;
}
);
var temp = startNode;
startNode = targetNode;
targetNode = temp;
visit(startNode);
console.log(targetNode.dist);
console.log(startNodes.sort((a, b) => a.dist - b.dist)[0].dist);

function visit(node) {
    edgeNodes = findEdgeNodes(node);
    edgeNodes.forEach(edge => {
        /*if(edge.edges && edge.edges.indexOf(node) >= 0){
            edgeNodes.splice(edgeNodes.indexOf(node), 1);            
        }else{*/
            setEdgeDistance(edge, node.dist + 1)
        /*}*/
    });
    node.edges = edgeNodes;
    node.visited = true;
    unvistited.splice(unvistited.indexOf(node), 1);
    if(unvistited.indexOf(targetNode) < 0 || unvistited.length == 0) {
        return;
    }else {
        current = unvistited.sort((a,b) => a.dist - b.dist)[0];
        visit(current);
    }
    
    
}

function setEdgeDistance(edge, dist) {
    if(edge.dist == Number.MAX_SAFE_INTEGER) {
        edge.dist = dist;
    }else {
        if(edge.dist > dist) {
            edge.dist = dist;
        }
    }
}

function findEdgeNodes(node) {
    edgeNodes = [];
    if(node.col > 0) {
        // left
        left = heightmap[node.row][node.col -1];
        if(node.height - left.height < 2) {
            edgeNodes.push(left);
        }
    }
    if(node.col < heightmap[node.row].length -1) {
        // right
        right = heightmap[node.row][node.col +1];
        if(node.height - right.height < 2) {
            edgeNodes.push(right);
        }
    }
    if(node.row > 0) {
        //top
        top = heightmap[node.row -1][node.col];
        if(node.height - top.height < 2) {
            edgeNodes.push(top);
        }
    }
    if(node.row < heightmap.length -1) {
        // bottom    
        bottom = heightmap[node.row +1][node.col];
        if(node.height - bottom.height < 2) {
            edgeNodes.push(bottom);
        }
    }
    return edgeNodes;    
}