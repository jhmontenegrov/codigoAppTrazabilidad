<%@page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="errorPage.jsp" %>
<%@page import ="beans.Conexion" %>
<%@page import="java.sql.Statement"%>
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
    resultaLog = sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='" + idUsuario + "' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='lista_usuario.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado3;
        Statement sentencia3;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia3 = (Statement) con.createStatement();

        resultado3 = sentencia3.executeQuery("SELECT usu.Check_bloquear_usuario, usu.UsuarioID, pu.Nom_perfilusuario, usu.Nombre, usu.Apellido, c.Ciudad_nombre FROM usuario usu, ciudad c, perfil_usuario pu WHERE UsuarioID!='" + idUsuario + "' AND c.CiudadID=usu.CiudadID and pu.Perfil_usuarioID=usu.Perfil_usuarioID");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <table style="width: 100%">
            <tr>
                <th>CC</th>
                <th>Nombre</th>
                <th>Tipo de usuario</th>
                <th>Ciudad</th>
                <th>Activación</th>
            </tr>
            <% while (resultado3.next()) {
                    Integer idEs = resultado3.getInt("usu.UsuarioID");
                    String nombre = resultado3.getString("usu.Nombre") + "   " + resultado3.getString("usu.Apellido");
                    String ciudad = resultado3.getString("c.Ciudad_nombre");
                    Boolean activo = resultado3.getBoolean("usu.Check_bloquear_usuario");
                    String activ = null;
                    if (activo.equals(false)) {
                        activ = "Activo";
                    } else if (activo.equals(true)) {
                        activ = "Desactivado";
                    }
                    String perfil = resultado3.getString("pu.Nom_perfilusuario");
            %>
            <tr>
                <td><%=idEs%></td>
                <td><%=nombre%></td>
                <td><%=perfil%></td>
                <td><%=ciudad%></td>
                <td><%=activ%></td>
                <td>
                    <table>
                        <form name="form13" method="POST" action="EliminarUsuarioCtrl">
                            <td>
                                <input type="hidden" name="idUS" value="<%=idEs%>"/>
                                <select name="activ_usuario" required>
                                    <option value="<%=activo%>"><%=activ%></option>
                                    <option value="false">Activo</option>
                                    <option value="true">Desactivado</option>
                                </select>
                            </td>
                            <td>
                                <button title="Actualizar">Actualizar</button>
                            </td>
                        </form>
                    </table>
                </td>
                <td >
                    <form name="form14" method="POST" action="actualizarUsuario.jsp">
                        <input type="hidden" name="idU" value="<%=idEs%>"/>
                        <button title="Actualizar">Ver detalle</button>
                    </form>
                </td>
                <%}
                    }%>
            </tr>
        </table>
    </body>
</html>
