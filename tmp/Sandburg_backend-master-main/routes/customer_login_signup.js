const app = require("express");
const getdb = require("../dbconnection").getdb;
const bcrypt = require("bcryptjs");
const nodemailer = require('nodemailer');
const transport = nodemailer.createTransport({
    service: "gmail",
    auth: {
        user: "thakkerrpiyu@gmail.com",
        pass: "Bhenthok"
    }
});


const router = app.Router();

router.get("/cust/:email",(req,res,next)=>{
    const db = getdb();
    db.collection('Customer').find({Email:req.params.email}).toArray((err,data)=>{
        if (err) {
            res.json(err);
        } else {
            console.log(data);
            res.json(data);
        }
    });
});

router.post('/custsignup',(req,res,next)=>{
    const db=getdb();
    // console.log(req.body);
    const name = req.body.name;
    const email = req.body.email;
    const password = req.body.password;
    const mobile= req.body.mobile;

    bcrypt.hash(password, 12).then(data => {
        const epassword = data;
        const custdata = {
            "Name": name,
            "Mobile_No": mobile,
            "Email": email,
            "Password": epassword,
        }

        db.collection('Customer').insertOne(custdata,(err,data)=>{
            if (err) {
                console.log("Error Occured during Customer Insertion");
            } else {
                console.log("Customer Inserted Successfully");
                res.json(data);
                transport.sendMail({
                    to: email,
                    from: "thakkerrpiyu@gmail.com",
                    subject: "Registration Successful",
                    text: "Thanks for Registering in Sandburg"

                });
            }
        });
    });   
});

router.post("/custlogin", (req, res, next) => {
    console.log(req.body);
    
    const db = getdb();

    const email = req.body.email;
    const password = req.body.password;
    console.log(password);

    const cust = db.collection("Customer");
  

    cust.findOne({ "Email": req.body.email }, (err, data) => {
        if (!data) {
            console.log("no data");
            // res.json(not_found);
            // return res.redirect('/login');
        } else {
            bcrypt.compare(password, data.Password).then(domatch => {
                if (domatch) {
                   console.log(data.Mobile_No);
                   cust.find({Email:email}).toArray((err,cdata)=>{
                    if (err) {
                        res.json(err);
                    } else {
                        console.log(cdata);
                        res.json(cdata);
                    }
                });
                  
                } else {
                    console.log("Invalid Username or Passsword");      
                }
            }).catch(err => {
                console.log(err);
            });
        }

    });

});



module.exports = router;