<?php

if ( isset($_FILE) && !empty($_FILE["img"]) )
{
	try
	{	
		$img = $_FILE["img"];


		$read = fopen("imgNo.txt", "r");
		$imgNo = (int) fgets( $read );
		fclose( $read );

		$imgNo++;

		$write = fopen("image$imgNo.jpeg", "w");
		fwrite( $write, $img);
		fclose( $write );

		$read = fopen("imgNo.txt", "w");
		fwrite( $read, $imgNo);
		fclose( $read );

		echo "success";
	}
	catch ( Exception $e )
	{
		die("error");
	}
}

?>