<?php

// if ( isset($_FILE) && !empty($_FILE["img"]) )
// {
// 	try
// 	{	
// 		$img = $_FILE["img"];


// 		$read = fopen("imgNo.txt", "r");
// 		$imgNo = (int) fgets( $read );
// 		fclose( $read );

// 		$imgNo++;

// 		$write = fopen("image$imgNo.jpeg", "w");
// 		fwrite( $write, $img);
// 		fclose( $write );

// 		$read = fopen("imgNo.txt", "w");
// 		fwrite( $read, $imgNo);
// 		fclose( $read );

// 		echo "success";
// 	}
// 	catch ( Exception $e )
// 	{
// 		die("error");
// 	}
// }


// In PHP versions earlier than 4.1.0, $HTTP_POST_FILES should be used instead
// of $_FILES.

$uploaddir = '/storage/ssd1/528/3166528/public_html/uploads/';
$uploadfile = $uploaddir . basename($_FILES['img']['name']);

if ( move_uploaded_file($_FILES['img']['tmp_name'], $uploadfile) )
{
    echo "success";
} else {
    echo "Possible file upload attack!\n";
}



?>