let events = [];
let arr = [];

const eventName=document.querySelector("#eventName");
const eventDate=document.querySelector("#eventDate");
const buttonAdd=document.querySelector("#bAdd"); 
const eventsContainer = document.querySelector("#eventsContainer");

const json=load();

try{
    arr = JSON.parse(json);
}catch(error){
    arr=[];
}

events = [...arr];

renderEvents();

document.querySelector("form").addEventListener("submit", (e)=>{
    e.preventDefault();
    addEvent();
});

buttonAdd.addEventListener("click",(e)=>{
    e.preventDefault();
    addEvent();
})

function addEvent(){
    if(eventName.value=="" || eventDate.value==""){
        return;
    }

    if(dateDif(eventDate.value)<0){
        return;
    }

    const newEvent={
        id: (Math.random()*100).toString(36).slice(3),
        name: eventName.value,
        date: eventDate.value
    }

    events.unshift(newEvent);
    save(JSON.stringify(events));
    eventName.value="";
    renderEvents();
}

function dateDif(d){
    const date1=new Date(d);
    const date2 = new Date();
    const difference = date1.getTime() - date2.getTime();
    const days = Math.ceil(difference/(1000*3600*24));
    return days;
}

function renderEvents(){
    const eventsHTML=events.map(event=>{
        return `
            <div class="event">
                <div class="days">
                    <span class="days-number">${dateDif(event.date)}</span>
                    <span class="days-text">dias</span>
                </div>
                <div class="event-name">${event.name}</div>
                <div class="event-date">${event.date}</div>
                <div class="actions" data-id="${event.id}">
                    <button class="bDelete">Eliminar</button>
                </div>
            </div>
        `;
    });
    eventsContainer.innerHTML=eventsHTML.join("");
}

function save(data){
    localStorage.setItem("items",data);
}

function load(data){
    localStorage.getItem("items",data);
}