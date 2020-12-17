var express = require("express");
var app = express();

var path = require("path");

var handlebars = require("express-handlebars");

var Trip = require("./modules/Trip.js")

var bodyparser = require("body-parser");
app.use(bodyparser.urlencoded({ extended: true }));

app.engine("handlebars", handlebars({defaultLayout: "main"}));
app.set("view engine", "handlebars");

app.use(express.static("public"));

app.get("/", function(req, res) {
	Trip.find(function(err, result) {
		res.render("showTripsPage", {
			page_title : "Welcome",
			trips : result
		});
	});
});

app.get("/edit", function(req, res) {
	Trip.findOne({date: req.query.date}, function(err, result) {
		res.render("editTripPage", {
			page_title : "Edit Trip",
			trip : result
		});
	});
});

app.post("/editForm", function(req, res) {
	Trip.findOne( {date: req.query.date}, function(err, found) {
		if (err) {
			res.render("resultPage", {
				page_title : "Error",
				message : err
			});
		} else if (!found) {
			res.render("resultPage", {
				page_title : "Error",
				message : "Trip not found."
			});
		} else {
			console.log(req.body)
			var updated = false;
			if (isFinite(req.body.miles) && req.body.miles !="") {
				found.miles = req.body.miles;
				updated = true;
			}
			if (isFinite(req.body.gallons) && req.body.gallons !="") {
				found.gallons = req.body.gallons;
				updated = true;
			}
			if (!updated) {
				res.render("resultPage", {
					page_title : "Error",
					message : "No data supplied in query."
				});
				return;
			}
			found.save(function (err) {
				if (err) {
					res.render("resultPage", {
						page_title : "Edit Trip Results",
						message : err
					});
				}
				else {
					res.render("resultPage", {
						page_title : "Edit Trip Results",
						message : "Trip updated successfully."
					});
				}
			});
		}
	});
});

app.get("/add", function(req, res) {
	res.render("addTripPage", {
		page_title : "Add New Trip"
	});
});

app.post("/addForm", function(req, res) {
	var newTrip = new Trip({
		date: req.body.date,
		city: req.body.city,
		miles: req.body.miles,
		gallons: req.body.gallons
	});
	newTrip.save(function(err) {
		if (err) {
			res.render("resultPage", {
				page_title: "Error",
				message: err
			});
		} else {
			res.render("resultPage", {
				page_title: "New Trip Results",
				message: "New trip added successfully."
			});
		}
	});
});

app.get("/get", function(req, res) {
	Trip.find().distinct('city', function(err, result) {
		console.log(result)
		res.render("getTripsPage", {
			page_title : "Get Trips By City",
			cities : result
		});
	});
});

app.get("/getForm", function(req, res) {
	Trip.find({city : req.query.city}, function(err, result) {
		res.render("showTripsPage", {
			page_title : "Get Trips By City",
			trips : result
		});
	});
});

app.use(function (req, res) {
	res.status(404).send("Oof! Page not found.");
});

app.listen(3000, function() {
	console.log("Starting on port 3000. Ctrl+C to quit.")
});