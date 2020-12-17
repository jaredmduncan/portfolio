var express = require('express');
var app = express();

var path = require('path');

var handlebars = require('express-handlebars');

var Car = require('./modules/Car.js')

var bodyparser = require('body-parser');
app.use(bodyparser.urlencoded({ extended: true }));

app.engine('handlebars', handlebars({defaultLayout: 'main'}));
app.set('view engine', 'handlebars');

app.use(express.static('public'));

app.post('/showAll', function(req, res) {
	Car.find( function(err, found) {
		if (err) {
			res.type('html').status(500);
			res.send('Error: ' + err);
		} else {
			for (var i = 0; i < found.length; i++) {
				res.write("<p>{ " + found[i].cid + ", " + found[i].year + ", " + found[i].make + ", " + found[i].model + ", " + found[i].miles + ", " + found[i].price + ", " + found[i].dealer_id + " }</p>");
			}
			res.end();
		}
	})
});

app.post('/addCar', function(req, res) {
	console.log(req.body);
	var newCar = new Car(
		{
			cid: req.body.cid,
			year: req.body.year,
			make: req.body.make,
			model: req.body.model,
			miles: req.body.miles,
			price: req.body.price,
			dealer_id: req.body.dealer_id
		}
	);
	newCar.save( function(err) {
		if (err) {
			res.send('Error: ' + err);
		}
		else {
			res.send("New Item successfully added to db");
		}
		res.end();
	});
});

app.post('/findCar', function(req, res) {
	console.log(req.body);
	Car.find( {cid: req.body.cid}, function(err, found) {
		if (err) {
			res.send('Error: ' + err);
		} else {
			for (var i = 0; i < found.length; i++) {
				res.write("<p>{ " + found[i].cid + ", " + found[i].year + ", " + found[i].make + ", " + found[i].model + ", " + found[i].miles + ", " + found[i].price + ", " + found[i].dealer_id + " }</p>");
			}
			res.end();
		}
	})
});

app.post('/updateCar', function(req, res) {
	console.log(req.body);
	Car.findOne( {cid: req.body.cid}, function(err, found) {
		if (err) {
			res.send(err);
		} else if (!found) {
			res.send('Car not found');
		} else {
			found.miles = req.body.miles;
			found.price = req.body.price;
			found.save(function (err) {
				if (err) {
					res.send('Error saving car');
				}
			});
			res.send('Car updated');
		}
	});
});

app.post('/deleteCar', function(req, res) {
	console.log(req.body);
	Car.findOneAndRemove({cid: req.body.cid}, function(err, found) {
		if (err) {
			res.send(err);
		} else if (!found) {
			res.send('No car found');
		} else {
			res.send('Car deleted');
		}
	});
});

app.use(function (req, res) {
	res.status(404).send("Oof! Page not found.");
});

app.listen(3000, function() {
	console.log('Starting on port 3000. Ctrl+C to quit.')
});