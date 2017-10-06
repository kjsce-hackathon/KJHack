import face_recognition

# Load the jpg files into numpy arrays
trump_image = face_recognition.load_image_file("trump.jpg")
obama_image = face_recognition.load_image_file("obama.jpg")
unknown_image = face_recognition.load_image_file("t.jpg")

# Get the face encodings for each face in each image file
# Since there could be more than one face in each image, it returns a list of encordings.
# But since I know each image only has one face, I only care about the first encoding in each image, so I grab index 0.
trump_face_encoding = face_recognition.face_encodings(trump_image)[0]
obama_face_encoding = face_recognition.face_encodings(obama_image)[0]
unknown_face_encodingO = face_recognition.face_encodings(unknown_image)
unknown_face_encodingT = face_recognition.face_encodings(unknown_image)

#getting length (no of persons) in the picture
#print len(face_recognition.face_encodings(unknown_image))


# results is an array of True/False telling if the unknown face matched anyone in the known_faces array
result = face_recognition.compare_faces(trump_face_encoding, unknown_face_encodingT)
print("Is the unknown face a picture of Trump? {}".format(result))
result = face_recognition.compare_faces(obama_face_encoding, unknown_face_encodingO)
print("Is the unknown face a picture of Obama? {}".format(result[0]))

# now this is the real fr.py