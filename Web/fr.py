import requests

url = "http://chinmayhomeauto.000webhostapp.com/save.php"
#files = {'file': open('report.xls', 'rb')}
files = {"img": "hello"}

r = requests.post(url, files=files)

print r.text