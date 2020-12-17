var express = require('express');
var app = express();
var bodyparser = require('body-parser');
app.use(bodyparser.json());
app.use(bodyparser.urlencoded({ extended: true }));
app.use(express.static('public'));      // index.html served from public folder

var Student = require('./modules/Student.js');

app.get('/getStudents', function(req, res) {
    Student.find(function(err, students) {
        if (err) {
            res.send(err);
        } else if (!students) {
            res.send("No students found.");
        } else {
            res.send(students);
        }
    });
});

app.get('/getStudents', function(req, res) {
    Student.find(function(err, students) {
        if (err) {
            res.send(err);
        } else if (!students) {
            res.send("No students found.");
        } else {
            res.send(students);
        }
    });
});

app.get('/getStudent', function(req, res) {
    Student.findOne({sid: req.query.sid}, function(err, student) {
        if (err) {
            res.send(err);
        } else if (!student) {
            res.send("No student found.");
        } else {
            res.send(student);
        }
    });
});

app.post('/postStudent', function(req, res){
    var newStudent = new Student ({
        sid: req.body.sid,
        last_name: req.body.last_name,
        first_name: req.body.first_name,
        major: req.body.major,
        midterm: 0,
        final: 0
    });
    newStudent.save(function(err) {
        if (err) {
            res.send(err);
        }
        else {
            res.send("Student added.");
        }
    });
});

app.post('/editStudent', function(req, res) {
    console.log(req.body)
    Student.findOne({sid: req.body.sid}, function(err, found) {
        if (err) {
            res.send(err);
        } else if (!found) {
            res.send('Student not found');
        } else {
            found.major = req.body.major;
            found.midterm = parseFloat(req.body.midterm);
            found.final = parseFloat(req.body.final)
            found.save(function (err) {
                if (err) {
                    res.send('Error saving student');
                }
            });
            res.send('Student updated');
        }
    });
});

app.use(function (req, res) {
    res.status(404).send("Sorry, no such page!")
});

app.listen(3000, function () {
    console.log('App started on http://localhost:3000, press Ctrl-C to terminate.' );
});