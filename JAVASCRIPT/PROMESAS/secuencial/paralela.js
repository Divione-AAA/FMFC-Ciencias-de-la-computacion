const {TaskOne , TaskTwo} = require('./tareas');

async function main() {
    
    try{
        const results=await Promise.all(TaskOne(),TaskTwo());
        //se ejecutan a la vez es decir 4 segundos
    }catch(error){
        console.log(error);
    }

}

main();