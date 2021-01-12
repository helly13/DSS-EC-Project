const http = require("http");
const express = require("express");
var cors = require('cors')
const multer = require("multer");
const body_parser = require("body-parser");

const app = express();

const mongoconnect = require("./dbconnection").MongoConnect;

app.use(body_parser.urlencoded({ extended: true }));
app.use(body_parser.json());
app.use(function(req, res, next) {
    //allow cross origin requests
    res.setHeader("Access-Control-Allow-Methods", "POST, PUT, OPTIONS, DELETE, GET");
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    res.header("Access-Control-Allow-Credentials", true);
    next();
});


const customer_login_signup_routes=require('./routes/customer_login_signup');
const employee_login_signup_routes=require('./routes/employee_login_signup');
const forget_reset_password=require("./routes/forget_reset_password");
const chef_manage=require("./routes/chef_manage");
//const vieworder=require("./routes/view");


app.use(customer_login_signup_routes);
app.use(employee_login_signup_routes);
app.use(forget_reset_password);
app.use(chef_manage);
//app.use(vieworder);

app.use(cors());
mongoconnect(() => {
    app.listen(3000);
});