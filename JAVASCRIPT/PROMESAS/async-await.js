async function requestHandler(req,res){
    try{
    const user= await user.findById(req.userId);
    const Tasks = await Task.findById(user,taskId);
    task.complete=true;
    res.send("Task complete");
    }catch(error){
        res.send(error);
    }
}