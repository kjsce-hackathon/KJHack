<?php

$uploaddir = '/storage/ssd1/528/3166528/public_html/uploads/';
$uploadfile = $uploaddir . basename($_FILES['img']['name']);

if ( move_uploaded_file($_FILES['img']['tmp_name'], $uploadfile) )
    echo "success";
else
    echo "Possible file upload attack!\n";

// also thinking of deleting the image file once it is shown and of now further use
// delete.php is that file, later if i get time
?>