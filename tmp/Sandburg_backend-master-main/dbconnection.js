const mongodb=require("mongodb");
let mongoclient=mongodb.MongoClient;
let db;
const str="mongodb+srv://Helly:Helly%4013@sandburg.95q8o.mongodb.net/<dbname>?retryWrites=true&w=majority";
const mongoconnect=callback => {
    mongoclient.connect(str,{
        useNewUrlParser: true,
        useUnifiedTopology: true,
     }).
     then(
          client=>{console.log("Connected to the Database")
          db=client.db('Sandburg');
          callback();
}).
      catch(err=>{
          console.log("error");
      })
    }
    
   function getdb(){
       if(db)
       return db;
   } 
exports.MongoConnect=mongoconnect;     
exports.getdb=getdb;