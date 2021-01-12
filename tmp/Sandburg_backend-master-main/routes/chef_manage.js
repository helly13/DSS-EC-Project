const app = require("express");
const getdb = require("../dbconnection").getdb;
const bcrypt = require("bcryptjs");

const router = app.Router();

router.get("/view",(req,res,next)=>{
    const db = getdb();
    db.collection('Order').find().toArray((err,data)=>{
        if (err) {
            res.json(err);
        } else {
            console.log(data);
            res.json(data);
        }
    });
});

router.get("/view1",(req,res,next)=>{
    const db = getdb();
    db.collection('Menu').find().toArray((err,data)=>{
        if (err) {
            res.json(err);
        } else {
            console.log(data);
            res.json(data);
        }
    });
});

router.post("/chef_manage",(req,res,next)=>{

    const db = getdb();
    var tab;
    tab = req.body.tab;
    const tableno = {Table_no : tab};
    db.collection('Order').updateOne(tableno,{$set:{Status:0}},(err)=>{
        if (err) {
            res.json(err);
        } else {
            console.log("Success");
            status = "Updated";
            res.json(status);
        }
    });

});

router.put("/menu_update", (req, res, next) => {
    const db = getdb();   
    var id1;
    id1 = req.body.id1;

    var price1;
    price1 = req.body.price1;
   // const price2 = {Price : price1};
    var itemimg;
    itemimg = req.body.itemimg;

    var typefood;
    typefood = req.body.typefood;

    var catid1 = req.body.catid1;
    var catname1 = req.body.catname1;

    const catdata = {
        "Category_Id" : catid1,
        "Category_Name" : catname1
    };
    

   const id2 = { Item_name : id1 };
   //db.collection('Menu').fin
   
    db.collection('Menu').updateOne( id2, { $set: { "Price" : price1, "Item_Image" : itemimg,"Status" : "5", "Type_of_Food" : typefood}}, (err) => {
        if (err) {
            console.log("Error occured during adding to waiting list");
            console.log(err);
        } else {
            // res.json(data1);
            console.log("Waiting added");
            status = "Updted";
            res.json(status);
        }
    });
});




router.post('/menuinsert', (req, res, next) => {
    const db = getdb();
    // console.log(req.body);

    const catname = req.body.catname;
    const catid = req.body.catid;
    const itemname = req.body.itemname;
    const price = req.body.price;
    const itemimage = req.body.itemimage;
    const stat = req.body.stat;
    const foodtype = req.body.foodtype;

    const custdata = {

        "Item_name": itemname,
        "Price": price,
        "Item_Image": itemimage,
        "Status": "1",
        "Type_of_Food": foodtype,
        "Category": [{ "Category_Name": catname, "Category_Id": catid }]

    }

    db.collection('Menu').insertOne(custdata, (err, data) => {
        if (err) {
            console.log("Error Occured during Menu Insertion");
            console.log(err);
        } else {
            console.log("menuitem Inserted Successfully");
         //   res.json(data);

        }
    });
    // });
});

router.delete("/menu_delete",(req,res,next) => {
    const db = getdb();   
    var name;
    name = req.body.name;
    const name1 = {Item_name : name};

    db.collection('Menu').deleteOne(name1 , (err) => {
        if (err) {
            console.log("Error occured during deleting menu");
            console.log(err);
        } else {
            // res.json(data1);
            console.log("Deleted");
            status = "Deleted";
            res.json(status);
        }


    });

});


router.delete("/order_delete",(req,res,next) => {
    const db = getdb();   
    var name1;
    name1 = req.body.name1;
    const name2 = {_id : name1};

    db.collection('Order').deleteOne(name2 , (err) => {
        if (err) {
            console.log("Error occured during deleting order");
            console.log(err);
        } else {
            // res.json(data1);
            console.log("Rejected");
            status = "Rejected";
            res.json(status);
        }


    });

});

module.exports = router;