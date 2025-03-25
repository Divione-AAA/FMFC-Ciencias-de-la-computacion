const util=require('util');
const sleep= util.promisify(setTimeout);

Module.exports={
    async TaskOne(){
        await sleep(3000);
        return "One Value"
    },
    async TaskTwo(){
        await sleep(2000);
        return "Second Value"
    }
}