const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

// Create and Deploy Your First Cloud Functions
// https://firebase.google.com/docs/functions/write-firebase-functions

exports.storeImgURL = functions.https.onRequest( (request, response) => {
	if ( request.method == "POST" )
	{
		admin.database().ref('url').set(request.body);
		response.send("success");
	}
	else
	{
		response.send("error");
	}
});

exports.storeName = functions.https.onRequest( (request, response) => {
	if ( request.method == "POST" )
	{
		admin.database().ref('url').set(request.body);
		response.send("success");
	}
	else
	{
		response.send("error");
	}
});