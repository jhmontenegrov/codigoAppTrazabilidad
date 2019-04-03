<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="controlador.NotificacionVtoCtrl"%>
<!DOCTYPE HTML>
<%@page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import ="beans.Conexion" %>
<%
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Progma", "no-cache");
    response.setDateHeader("Expires", 0);
//recuperar la sesión
    HttpSession sesion_cli = request.getSession(true);
    Integer idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");
    Connection conn = (Connection) Conexion.crearConexion();
    ResultSet resultaLog;
    Statement sent;
    sent = (Statement) conn.createStatement();
    resultaLog = sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='" + idUsuario + "' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='ingresar_almacen.jsp'");
    if (idUsuario == null) {
        request.getRequestDispatcher("/Inicio_sesion.jsp").forward(request, response);
    } else {
        ResultSet resultado;
        Statement sentencia;
        Connection con;
        String nombreUsuario;
        String apellidoUsuario;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM usuario where UsuarioID='" + idUsuario + "'");
%>
<html>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <head>
        <title>Sistema de Trazabilidad</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <meta http-equiv="Expires" content="0"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <link rel="stylesheet" href="css/main.css"/>
        <!-- Scripts -->
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script src='push.js/push.min.js'></script>
        <script language="javascript">
            var timestamp = null;
            var fecha = null;
            function cargar_push() {
                $.ajax({
                    async: true,
                    type: "POST",
                    url: "httpush",
                    data: "timestamp",
                    dataType: "html",
                    success: function (data) {
                        var json = eval("("+data+")");
                        timestamp =json.timestamp;
                        mensaje= json.mensaje;
                        id=json.id;
                        if (timestamp === null) {
                        } else {
                            $.ajax({
                                async: true,
                                type: "POST",
                                url: "NotificacionVtoCtrl",
                                data1: "",
                                dataType: "html",
                                success: function (data1) {
                                    $('#div').html(data1);
                                    if (data1 !== "") {
                                        Push.create("Está cerca de su vencimiento los siguientes Productos:", {
                                            body: data1,
                                            icon: "img/warning.png",
                                            timeout: 8000,
                                            onClick: function () {
                                                self.frames['cargapa'].location.href = 'listaProductoVencido.jsp';
                                                this.close();
                                            }
                                        });
                                    } else {
                                    }
                                }
                            });
                        }
                        setTimeout('cargar_push()', 1000);
                    }

                });
            }
            $(document).ready(function ()
            {
                cargar_push();
            });
        </script>
        <script type="text/javascript">
            {
                if (history.forward(1))
                    location.replace(history.forward(1));
            }
        </script>
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
                                <% while (resultado.next()) {
                                        nombreUsuario = resultado.getString("Nombre");
                                        apellidoUsuario = resultado.getString("Apellido");
                                %>
                                <a href="perfil_usuario.jsp" target="cargapa"><%=nombreUsuario%> <%=apellidoUsuario%></a>
                                <%}
                                %>
                                <br>
                                <a href="CerrarSesionCtrl" >Cerrar sesión</a>
                            </li>
                        </ul>
                    </header>
                    <!-- Section -->
                    <section id="banner">
                        <div class="content" >
                            <iframe name="cargapa" class="cargaP1" src="bienvenida.jsp"></iframe>	
                        </div>
                    </section>							
                </div>
            </div>
            <!-- Sidebar -->
            <div id="sidebar">
                <iframe class="cargaP2" src="menu.jsp"></iframe>
            </div>   
        </div>
    </body>
    <%}

    %>
</html>