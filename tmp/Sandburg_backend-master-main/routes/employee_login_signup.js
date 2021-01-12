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

router.get("/emp/:email",(req,res,next)=>{
    const db = getdb();
    db.collection('Employee').find({Email:req.params.email}).toArray((err,data)=>{
        if (err) {
            res.json(err);
        } else {
            console.log(data);
            res.json(data);
        }
    });
});

router.post('/empsignup',(req,res,next)=>{
    const db=getdb();
    // console.log(req.body);
    const name = req.body.name;
    const email = req.body.email;
    const password = req.body.password;
    const mobile= req.body.mobile;
    const address=req.body.address;
    const dob=req.body.dob;
    const gender=req.body.gender;
    const emp_type=req.body.emp_type;
    const img=req.body.img;

    bcrypt.hash(password, 12).then(data => {
        const epassword = data;
        const empdata = {
            "Name": name,
            "Mobile_No": mobile,
            "Email": email,
            "Password": epassword,
            "Address":address,
            "Birthdate":dob,
            "Gender":gender,
            "Employee_type":emp_type,
            "Image":img,
        }

        db.collection('Employee').insertOne(empdata,(err,data)=>{
            if (err) {
                console.log("Error Occured during Employee Insertion");
            } else {
                console.log("Employee Inserted Successfully");
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

router.post("/emplogin", (req, res, next) => {
    console.log(req.body);
    
    const db = getdb();

    const email = req.body.email;
    const password = req.body.password;
    console.log(password);

    const cust = db.collection("Employee");
  

    cust.findOne({ "Email": req.body.email }, (err, data) => {
        if (!data) {
            console.log("no data");
        } else {
            bcrypt.compare(password, data.Password).then(domatch => {
                if (domatch) {
                   console.log(data.Mobile_No);
                   cust.find({Email:email}).toArray((err,empdata)=>{
                    if (err) {
                        res.json(err);
                    } else {
                        console.log(empdata);
                        res.json(empdata);
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