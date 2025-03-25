const zona = document.querySelector(".zona_de_arrastre")

zona.addEventListener("dragleave",e=>{
    e.preventDefault();
    changeStyle(e.srcElement,"#888");
})

zona.addEventListener("dragover",e=>{
    e.preventDefault();
    changeStyle(e.srcElement,"#444");
})

const changeStyle=(obj,color)=>{
    obj.style.color=color;
    obj.style.border='4px dashed ${color}';
}