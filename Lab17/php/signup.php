<?php

require "DataBase.php";

$db = new DataBase();

if (isset($_POST['fullname']) && isset($_POST['email']) && isset($_POST['username']) && isset($_POST['password']))
{
    if ($db->dbConnect())
    {
        date_default_timezone_set('Europe/Moscow');
        
        if ($db->signUp("users", $_POST['username'], $_POST['fullname'], $_POST['email'], $_POST['password'], date('Y-m-d H:i:s a', time())))
        {
            echo "Sign Up Success";
        }
        else echo "Sign up Failed";
    }
    else echo "Error: Database connection";
}
else echo "All fields are required";

?>
