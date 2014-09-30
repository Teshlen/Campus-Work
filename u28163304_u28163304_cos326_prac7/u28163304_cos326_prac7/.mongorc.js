//QUESTION 1

function show()
{
		return db.Cartesian.find();
}

function t1q1()
{
	db.createCollection("Cartesian");
	db.createCollection("zipcodes");
	db.createCollection("facebook");
	db.Cartesian.ensureIndex({x:1,y:-1},{unique:true});
	return "task1 Q1 Done";
		
}

function t1q2()
{
		insertPoints("prac7db","Cartesian",5,5);
		return "points have been inserted";
}

function t1q3()
{
	updateYVals("prac7db","Cartesian",2,10);
	return "updated Y Vals";
	}

function t1q4()
	{
		removeIfYless("prac7db", "Cartesian", 3);
		return "removed files";
	}

//task2
	
//mongoimport --db prac7db --collection zipcodes --file zipcodes.json
	
	//task 3
//mongoimport --db facebookdata --collection facebook --jsonArray --file FacebookData.json
	
function t2()
{
	db.createCollection("zipcodes");
return true;	
}
	
function t2q1()
{
	return allStatesPopulation("prac7db","zipcodes");
}

function t2q2()
{
		return oneStatePopulation("prac7db","zipcodes", "DE");
}

function t2q3()
{
	return allStatesPopulationMR("prac7db","zipcodes");
}

function t2q4()
{
	return placesInGrid("prac7db","zipcodes",-72.908793,-72.622739,42.070206,47.070206);
}
	


//actual functions................................................................................................................................	
//task 1 functions

function insertPoints(dbName, colName, xcount, ycount)
{
	var collection = db.getSiblingDB(dbName).getCollection(colName);
	for (q = 1; q <= xcount || q <= ycount; q++) 
	{
		
		collection.insert({'x' : (q <= xcount) ? q : "",'y' : (q <= ycount) ? q : ""});
	}
	
}

function updateYVals(dbName, colName, threshold, step)
{
	var col = db.getSiblingDB(dbName).getCollection(colName);
	
	col.update(
		{'y':{$gt:parseInt(threshold)}},
		{$inc:{'y':parseInt(step)}},
		{multi:true}
	)
	
	return true;
}

function removeIfYless(dbName, colName, threshold)
{
	var col = db.getSiblingDB(dbName).getCollection(colName);
	
	col.remove(
		{'y':{$lt:parseInt(threshold)}},
		{multi:true}
	)
	
	return true;
}

//task 2 functions

function allStatesPopulation(dbName, colName)
{
		var collection = db.getSiblingDB(dbName).getCollection(colName);
		var i =  collection.aggregate (			
		{
			$group: {
				_id: "$state", population : {$sum: "$pop"}
				}
			

		},	{ $sort: { "_id" : 1 } }		
		
		)
		while ( i.hasNext() ) printjson (i.next() )
		
		
}


function oneStatePopulation(dbName, colName, stateName)
{
	var collection = db.getSiblingDB(dbName).getCollection(colName);
		var y =  collection.aggregate (			
		{
			$match : {state: stateName} },
			{$group: {
				_id: "$state", population : {$sum: "$pop"}
				}
			

		},	{ $sort: { "_id" : 1 } }		
		
		)
		while ( y.hasNext() ) printjson (y.next() )
}


function allStatesPopulationMR(dbName, colName)
{
	var collection = db.getSiblingDB(dbName).getCollection(colName);
	
	var f1 = function() { 
		emit (this.state,this.pop);
		};
	
	var f2 = function(stateID, values) {
		return Array.sum(values);
		};
	
	collection.mapReduce
	(
		f1,f2,{out : "states_population"}
	)
	var e=  db.states_population.find();
		while ( e.hasNext() ) printjson (e.next() )
		
}


function placesInGrid( dbName, colName,lat1,lat2,lon1,lon2)
{
	var collection = db.getSiblingDB(dbName).getCollection(colName);
	var col = collection.find(
		{"loc.0" : {$gt:lat1, $lt:lat2}, 
				"loc.1": {$gt:lon1, $lt:lon2}}, 
				{pop : 0}
	);
	while ( col.hasNext() ) printjson (col.next() )
}












