const task=[];
let time=0;
let timeBreaker=null;
let timer=null,current=null;

const bAdd=document.querySelector("#bAdd");
const idTask=document.querySelector("#idTask");
const form=document.querySelector("#form");

form.addEventListener('submit', e=>{
    e.preventDefault();
    if(idTask.value!=''){
        createTask(idTask.value);
        idTask.value='';
        renderTasks();
    }
})

function createTask(value){
    const newTask={
        id: (Math.random()*100).toString(36).slice(3),
        completed: false,
        tittle: value
    };
    task.unshift(newTask);
}

function renderTasks(value){
    const html=task.map(task=>{
        return `
            <div class="task">
                <div class="completed">${
                    task.completed 
                    ? `<span class="done">Done</span> ` 
                    : `<button class="start-button data-id="${task.id}">Start</button>` 
                    }</div>
                <div class="tittle">${task.tittle}</div>
            </div>
        `;
    })

    const taskContainer=document.querySelector("#task");
    taskContainer.innerHTML=html.join("");
}