<%@page import="beans.Usuario"%>
<%@page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="errorPage.jsp" %>
<%@page import ="beans.Conexion" %>
<%@page import="java.sql.Statement"%>
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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='lista_proveedores.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado;
        Statement sentencia;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM proveedor pr");
%>

<html>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <table style="width: 100%">
            <tr>
                <th>Nombre</th>
                <th>Dirección</th>
                <th>Telefono</th>
                <th>E-mail</th>
                <th>Editar</th>
                
            </tr>
            <% while (resultado.next()) {
                    Integer idPr = resultado.getInt("pr.ProveedorID");
                    String nombreP = resultado.getString("pr.Nom_proveedor");
                    String dir_proveedor = resultado.getString("pr.Dir_proveedor");
                    String tel_proveedor = resultado.getString("pr.Tel_proveedor");
                    String mail_proveedor = resultado.getString("pr.Mail_proveedor");
            %>
            
            <tr>
                <td><%=nombreP%></td>
                <td><%=dir_proveedor%></td>
                <td><%=tel_proveedor%></td>
                <td><%=mail_proveedor%></td>
                <td>
                    <a href=<%= "actualizarProveedor.jsp?Id=" + idPr%> >Editar Información</a>
                </td>
                
                <%}
                    }%>
            </tr>
        </table>
    </body>
</html>

