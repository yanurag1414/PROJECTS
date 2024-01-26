let boxes = document.querySelectorAll(".box");
let resbtn = document.querySelector("#res");
let newGameBtn = document.querySelector("#new-btn");
let msgContainer = document.querySelector(".msg-container");
let msg = document.querySelector("#msg");

let turn0 = true;           //playerx,playery
let count = 0;
const winPattern = [
    [0,1,2],
    [0,3,6],
    [0,4,8],
    [1,4,7],
    [2,5,8],
    [2,4,6],
    [3,4,5],
    [6,7,8],

];
boxes.forEach((box) => {
    box.addEventListener("click",() => {
        count = count+1;
        console.log(count);
        console.log("Clicked");
        if(turn0){
            box.innerText = "O";
            turn0 = false;
        }else{
            box.innerText = "X";
            turn0 = true;
        }
        box.disabled = true;

        checkwinner();
    });
});

const disableBoxes = () => {
    for(let box of boxes){
        box.disabled = true;
    }
}
const enableBoxes = () =>{
    for(let box of boxes){
        box.disabled = false;
        box.innerText = "";
    }
}
const showWinner = (winner) => {
    msg.innerText = `Congratulations, Winner is ${winner}`; 
    msgContainer.classList.remove("hide");
    disableBoxes();
}
const gameDraw = () =>{
    msg.innerText = "Match draw!!";
    msgContainer.classList.remove("hide");
    disableBoxes();
}

const checkwinner = () => {
    for(pattern of winPattern){
        let posval1 = boxes[pattern[0]].innerText;
        let posval2 = boxes[pattern[1]].innerText;
        let posval3 = boxes[pattern[2]].innerText;

        if(posval1 != "" && posval2 != "" && posval3 != ""){
            if(posval1 == posval2 && posval2 == posval3){
                console.log("winner",posval1);
                showWinner(posval1);
            }
        }
    }
    if(count===9){
        gameDraw();
    }
    
}

const resetGame = () => {
    turn0 = true;
    enableBoxes();
    msgContainer.classList.add("hide");
    count = 0;
}
newGameBtn.addEventListener("click",resetGame);
resbtn.addEventListener("click",resetGame);