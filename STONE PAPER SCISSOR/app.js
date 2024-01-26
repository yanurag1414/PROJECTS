let userScore = 0;
let compScore = 0;

const choices = document.querySelectorAll(".choice");
const msg = document.querySelector("#msg");

const userScorePara = document.querySelector("#user-score");
const compScorePara = document.querySelector("#comp-score");


const genComputerChoice = () => {
    const option = ["rock" , "paper" , "scissors"];
    const ranIndx = Math.floor(Math.random()*3);
    return option[ranIndx];
};


const drawGame = () =>{
    console.log("Game was draw!!")
    msg.innerText = "Game was Draw";
    msg.style.backgroundColor = "#081b31";
};


const showWinner = (userWin,userChoice,compChoice) => {
    if(userWin){
        userScore++;
        userScorePara.innerText = userScore;
        console.log("you win!!!");
        msg.innerText = "You win!!!";
        msg.style.backgroundColor = "green";
    }else{
        compScore++;
        compScorePara.innerText = compScore;
        console.log("You lose");
        msg.innerText = "You lose";
        msg.style.backgroundColor = "red";
    }
}

const playGame = (userChoice) => {
    console.log("user choice = ",userChoice);
    const compChoice = genComputerChoice();
    console.log("Computer choice = ",compChoice);

    if(compChoice === userChoice){
        drawGame();
    }else{
        let userWin = true;
        if(userChoice === "rock"){
            userWin = compChoice === "paper" ? false : true;
        }else if(userChoice==="paper"){
            userWin = compChoice==="scissors" ? false : true;
        }else{
            userWin = compChoice==="rock" ? false:true;
        }
        showWinner(userWin,userChoice,compChoice);
    }
};
choices.forEach((choice) =>{
    console.log(choice);
    choice.addEventListener("click",() => {
        const userChoice = choice.getAttribute("id");
        playGame(userChoice);
    });
});
