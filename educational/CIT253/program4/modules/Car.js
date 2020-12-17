var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/cars4sale');

var Schema = mongoose.Schema;

var carsSchema = new Schema(
	{
		cid: {type: Number, required: true, unique: true},
		year: {type: Number, required: true},
		make: {type: String, required: true},
		model: {type: String, required: true},
		miles: {type: Number, required: true},
		price: {type: Number, required: true},
		dealer_id: {type: String, required: true},
	}
);

module.exports = mongoose.model('Car', carsSchema);