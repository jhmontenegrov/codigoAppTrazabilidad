<%@page import="com.mysql.jdbc.Statement"%>
<!doctype html>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="errorPage.jsp" %>
<%@ page import ="beans.Conexion" %>

<%   
//recuperar la sesión
response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Progma", "no-cache");
    response.setDateHeader("Expires", 0);
    HttpSession sesion_cli = request.getSession(true);
    Integer idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");    
    Connection conn = (Connection) Conexion.crearConexion();
    ResultSet resultaLog;
    Statement sent;
    sent = (Statement) conn.createStatement();
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='ingresar_tipo_usuario.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
%>
<html>
    <head>
         <link rel="stylesheet" href="css/main.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script language="javascript">
            function Solo_texto(e) {
                var code;
                if (!e) {
                    var e = window.event;
                }
                if (e.keyCode) {
                    code = e.keyCode;
                } else if (e.which) {
                    code = e.which;
                    var character = String.fromCharCode(code);
                    var AllowRegex = /^[\a-z\s-]$/;
                    if (AllowRegex.test(character)) {
                        return true;
                    }
                }
                return false;
            }
            ;
        </script>
        <title>Nuevo tipo de producto</title>
    </head>
    <body>
        <form id="form1" method="POST" action="Ingresa_tipoPerfilCtr">
            <label for="tipoPer">Nombre del tipo de perfil</label>
            <input type="text" name="tipoPer" id="marcar" required onkeypress="return Solo_texto(event);">
            <button title="Guardar">Guardar</button>
        </form>
    </body><%}%>
</html>