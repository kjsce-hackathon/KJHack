import requests

file = "test.txt"

url = "http://chinmayhomeauto.000webhostapp.com/save.php"
files = {'img': open(file, 'rb')}
#files = {"img": "hello"}

r = requests.post(url, files=files)

print r.text

url = "https://us-central1-auto-d6b09.cloudfunctions.net/storeImgURL"
data = {'img': "http://chinmayhomeauto.000webhostapp.com/uploads/" + file}
r = requests.post(url, data=data)

print r.text