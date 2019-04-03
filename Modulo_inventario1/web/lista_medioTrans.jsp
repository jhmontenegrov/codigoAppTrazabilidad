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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='lista_medioTrans.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
    ResultSet resultado;
    Statement sentencia;
    Connection con;
    con = (Connection) Conexion.crearConexion();
    sentencia = (Statement) con.createStatement();
    resultado = sentencia.executeQuery("SELECT * FROM MediosTransporte mt");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" />
              <title>JSP Page</title>
    </head>
    <body>
        <table style="width: 100%">
             <tr>
                <th>ID</th>
                <th>Marca</th>
                <th>Modelo</th>
                <th>Placa</th>
                <th>Actualizar</th>
            </tr>
            <% while (resultado.next()) {
                    Integer idMt = resultado.getInt("mt.MediostransporteID");
                    String marcaMt = resultado.getString("mt.marca");
                    String modeloMt = resultado.getString("mt.modelo");
                    String placaMt = resultado.getString("mt.placa");
%>
            <tr>
                <td><%=idMt%></td>
                <td><%=marcaMt%></td>
                <td><%=modeloMt%></td>
                <td><%=placaMt%></td>
                <td>
                    <a href=<%= "actualizarMedioTrans.jsp?Id=" + idMt%> >Editar Información</a>
                </td>
                <%}%>
            </tr>
        </table><%}%>
    </body>
</html>
