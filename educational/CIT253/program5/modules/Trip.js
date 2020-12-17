var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/travel');

var Schema = mongoose.Schema;

var tripsSchema = new Schema(
	{
		date: {type: String, required: true, unique: true},
		city: {type: String, required: true},
		miles: {type: Number, required: true},
		gallons: {type: Number, required: true}
	}
);

tripsSchema.virtual('mpg').get(function() {
	return this.miles/this.gallons;
});

module.exports = mongoose.model('Trip', tripsSchema);