import time
import urllib
from firebase import firebase
import face_recognition

fb = firebase.FirebaseApplication('https://auto-d6b09.firebaseio.com/', None)

biden_image = face_recognition.load_image_file("biden.jpg")
obama_image = face_recognition.load_image_file("obama.jpg")

biden_face_encoding = face_recognition.face_encodings(biden_image)[0]
obama_face_encoding = face_recognition.face_encodings(obama_image)[0]

known_faces = [
    biden_face_encoding,
    obama_face_encoding
]

known_name = [
	"biden",
	"obama"
]

while true:
	r = fb.get('/imgReco', None)
	if r['check'] == 1:

		url = "http://chinmayhomeauto.000webhostapp.com/uploads/obama.jpg"
		urllib.urlretrieve(url, "test.jpg")

		unknown_image = face_recognition.load_image_file("test.jpg")
		unknown_face_locations = face_recognition.face_locations(unknown_image)

		i=0
		flag = 0

		while i < len(unknown_face_locations):
			unknown_face_encoding = face_recognition.face_encodings(unknown_image)[i]
			result = face_recognition.compare_faces(known_faces, unknown_face_encoding)

			if result:
				name = known_name[i]
				url = "https://us-central1-auto-d6b09.cloudfunctions.net/storeName"
				data = {'name': name}
				r = requests.post(url, data=data)
				flag = 1
				print r.text
				break

			i += 1

		if flag != 1:
			url = "https://us-central1-auto-d6b09.cloudfunctions.net/storeImgURL"
			data = {'img': r['url']}
			r = requests.post(url, data=data)
			print r.text

		result = fb.post('/',{'imgReco/check':0})
		print result

	time.sleep(1000)