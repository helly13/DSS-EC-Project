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
var OTP;
var EMAIL;
const router = app.Router();

router.post("/forgetpassword", (req, res, next) => {
    const min = 100;
    const max = 1000000;
    OTP = Math.floor(Math.random() * (+max - +min)) + +min;
    EMAIL = req.body.email;
    transport.sendMail({
        to: EMAIL,
        from: "thakkerrpiyu@gmail.com",
        subject: "OTP for Resetting the Password",
        text: "Your One Time Password:-" + OTP
    });
    console.log(OTP);
    res.json();
});

router.post("/resetpassword", (req, res, next) => {
    var status;
    var type=-1;
    const otp = req.body.otp;
    const password = req.body.password;
    const confirm_password = req.body.confirm_password;
    db=getdb();

    db.collection('Employee').find({Email:EMAIL}).toArray((err,data)=>{
        if(data)
        {
            type=1;
        }
    });
    
    db.collection('Customer').find({email:EMAIL}).toArray((err,data)=>{
        if(data)
        {
            type=0;
        }
    });


    console.log(type);
    if (otp == OTP && password == confirm_password) {

        bcrypt.hash(password, 12, (err, data) => {
            console.log(data);
            const db = getdb();
            const password = data;
            const updateon = { Email: EMAIL };
            const newval = { $set: { Password: password } };
            if(type==0)
            {

                db.collection("Customer").updateOne(updateon, newval, (err) => {
                    if (err) {
                        console.log("Error");
                    } else {
                        status="customer done";
                        console.log("Reset");
                        res.json(status);
                        // return res.redirect("/login");
                    }
                });
            }
            else if(type==1)
            {
                db.collection("Employee").updateOne(updateon, newval, (err) => {
                    if (err) {
                        console.log("Error");
                    } else {
                        status="employee done";
                        console.log("Reset");
                        res.json(status);
                        // return res.redirect("/login");
                    }
                });
            }
            else
            {
                console.log("no mail found");
                status="no mail found";
                res.json(status);
            }
            
        });
    } else {
        res.json(null);
        console.log(otp + " " + password);

    }


});


module.exports = router;