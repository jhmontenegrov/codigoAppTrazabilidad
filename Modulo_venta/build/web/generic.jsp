
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE HTML>
<%@page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import ="Beans.Conexion" %>
<%
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Progma", "no-cache");
    response.setDateHeader("Expires", 0);
//recuperar la sesión
    HttpSession sesion_cli = request.getSession();
    sesion_cli.setAttribute("ven", "nVencido");
    Integer idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
    if (idUsuario == null) {
        request.getRequestDispatcher("/Inicio_sesion.jsp").forward(request, response);
    } else {
%>
<html>
    <head>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
           <title>Sistema de Trazabilidad</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="css/main.css"/>
        <!-- Scripts -->
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
        <script src='push.js/push.min.js'></script>
        
    </head>
    <body style="overflow-y:hidden">

        <!-- Wrapper -->
        <div id="wrapper">

            <!-- Main -->
            <div id="main">
                <div class="inner">

                    <!-- Header -->
                    <header id="header">
                        <strong>Sistema de Trazabilidad</strong>
                        <ul class="icons">
                            <li>
                                <br>
                                <a href="CerrarSesionCtrol">Cerrar sesión</a>
                            </li>
                        </ul>
                    </header>
                    <!-- Section -->
                    <section id="banner">
                        <div class="content" >
                           	<iframe name="cargapa1" class="cargaP1" src="inicio.jsp"></iframe>
                        </div>
                    </section>							
                </div>
            </div>
            <!-- Sidebar 
            <div id="sidebar">
                <iframe class="cargaP2" src="menu.jsp"></iframe>
            </div>   -->
        </div>
    </body>
</html>
<%}%>