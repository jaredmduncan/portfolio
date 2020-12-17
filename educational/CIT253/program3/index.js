var express = require('express');
var app = express();

var path = require('path');

var handlebars = require('express-handlebars');

var bodyparser = require('body-parser');
app.use(bodyparser.urlencoded({ extended: true }));

app.engine('handlebars', handlebars({defaultLayout: 'main'}));
app.set('view engine', 'handlebars');

app.use(express.static('public'));

var schedule = [
{flight: 1212, origin: "SDF 7:00am", destination: "MIA 9:50am"},
{flight: 4505, origin: "SDF 7:20am", destination: "LAS 8:30am"},
{flight: 2212, origin: "SDF 10:00am", destination: "MIA 12:50pm"},
{flight: 5505, origin: "SDF 11:20am", destination: "LAS 12:30pm"}
];

app.get('/', function(req, res) {
	res.render('landing', {
		page_title : "Welcome"
	});
});

app.get('/flights', function(req, res) {
	res.render('order', {
		page_title : "Flights",
		flights : schedule
	});
});

app.post('/form', function(req, res) {
	console.log(req.body);
	var fullName = req.body.fullName;
	var flightNumber = req.body.flightNumber;
	var seatingPreference = req.body.seatingPreference;
	var mealPreference = req.body.mealPreference;
	data = { fullName : fullName, flightNumber : flightNumber, seatingPreference : seatingPreference, mealPreference : mealPreference };
	res.render('summary', data);
});


app.use(function (req, res) {
	res.status(404).send("Oof! Page not found.");
});

app.listen(3000, function() {
	console.log('Starting on port 3000. Ctrl+C to quit.')
});