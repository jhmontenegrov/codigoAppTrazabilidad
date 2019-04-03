
<%   
    request.setAttribute("idUsuario", null);
%>
<!doctype html>
<html>
    <head>
        <title>Inicar sesion</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta http-equiv="Expires" content="0"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <link rel="stylesheet" href="css/main.css" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
            {
                if (history.forward(1))
                    location.replace(history.forward(1));
            }
        </script>
    </head>

    <body>
    <center>
        <div id="main">
            <div class="inner">
                <!-- Header -->
                <header id="header">
                    <a href="index.jsp" class="logo"><strong>Sistema de Trazabilidad</strong></a>
                </header>
            </div>
            <br>
            <section>   	   			
                <div>
                    <table width="26%">
                        <td>
                            <form id="form1" method="POST" action="AltaLoginEmpleados">
                                <label for="u_login">Usuario: </label>
                                <input type="text" name="u_login" id="u_login" required pattern="[a-z0-9]{3,8}"
                                       title="Por favor, valide la información ingresada, recuerde que debe llenar el campo con letras en minúscula y números"><br>
                                <label for="p_login">Password:</label>
                                <input type="password" name="p_login" id="p_login" required>
                                <br>
                                <button title="Iniciar sesion">iniciar sesion</button>
                            </form>
                        </td>
                    </table>
                </div>
            </section>
        </div>
    </center>
</body>
</html>
