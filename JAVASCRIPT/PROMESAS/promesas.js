function requestHandler(req, res){
    User.findById(req.UserId)
        .then(function(user){
            return Task.findById(user,TaskId)
        })
        .then(function(task){
            task.complete=true;
            return task.save();
        })
        .then(function(){
            res.send("Task complete");
        })
        .catch(function(error){
            res.send(error);
        })
}