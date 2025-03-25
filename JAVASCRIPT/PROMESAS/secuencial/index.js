const {TaskOne , TaskTwo} = require('./tareas');

async function main() {
 
    const v_one=await TaskOne();
    const v_two=await TaskTwo();

}

main();