import requests

file = "test.txt"

# sending image to server
url = "http://chinmayhomeauto.000webhostapp.com/save.php"
files = {'img': open(file, 'rb')}
# files = {"img": "hello"}
r = requests.post(url, files=files)
print r.text

# if the image is of known person send that persons name
name = "just a random name"
url = "https://us-central1-auto-d6b09.cloudfunctions.net/storeImgURL"
data = {'name': name}
r = requests.post(url, data=data)
print r.text

# else send the server image url to firbase
url = "https://us-central1-auto-d6b09.cloudfunctions.net/storeImgURL"
data = {'img': "http://chinmayhomeauto.000webhostapp.com/uploads/" + file}
r = requests.post(url, data=data)
print r.text