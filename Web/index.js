const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.storeImgURL = functions.https.onRequest( (request, response) => {
	if ( request.method === "POST" )
	{
		admin.database().ref('url').update(request.body);
		response.send("success");
	}
	else
		response.send("error");
});

exports.storeName = functions.https.onRequest( (request, response) => {
	if ( request.method === "POST" )
	{
		admin.database().ref('url').update(request.body);
		response.send("success");
	}
	else
		response.send("error");
});

// changed the database set method to update method
// 

// needs to be done from the app side
// reset the value of the img and name data once the notifiction uis read
// or delete the node.