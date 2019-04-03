<%@page import="java.sql.Statement"%>
<!doctype html>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="errorPage.jsp" %>
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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='ingresar_ciudad.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
%>
<html>
    <head>
        <title>Ingresar Ciudad</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="css/main.css" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
        <center>
            <div id="main">
                <section>   	   			
                    <div>
                        <table width="30%">
                            <td>
                                <form id="form1" method="POST" action="Ingresa_ciudadCtrl">
                                    <label for="name_ciudad">Nombre ciudad</label><br>
                                    <input type="text" name="name_ciudad" id="name_ciudad" required pattern="[a-z]{3,11}" title="Ingrese un valor válido"><br>
                                    <label for="name_ciudad">Nombre País</label><br>
                                    <input type="text" name="name_pais" id="name_pais" required pattern="[A-Za-z]{3,11}" title="Ingrese un valor válido"><br>
                                    <button title="Guardar">Guardar</button>
                                </form>
                            </td>
                        </table>
                    </div>
                </section>
            </div>
        </center>
    </body>
</html>
<%}%>