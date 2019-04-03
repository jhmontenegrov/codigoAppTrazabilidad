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
    resultaLog = sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='" + idUsuario + "' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='listaVentas.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado;
        Statement sentencia;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM venta ve, establecimiento esta WHERE esta.establecimientoID=ve.establecimientoID");
%>
<html>
    <head>
        <title>Sistema de trazabilidad</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="css/main.css" />
    </head>
    <body>

        <table style="width: 100%">
            <tr>
                <th>ID</th>
                <th>Establecimiento</th>
                <th>Fecha venta</th>
                <th>Reporte</th>
            </tr>
            <% while (resultado.next()) {
                    Integer veId = resultado.getInt("ve.VentaID");
                    String estaUb = resultado.getString("esta.Ubicaciontienda");
                    String ubicacion = resultado.getString("ve.Fech_venta");
            %>
            <tr>
                <td><%=veId%></td>
                <td><%=estaUb%></td>
                <td><%=ubicacion%></td>
                <td><a href=<%= "verVentasPDF?Id=" + resultado.getInt("ve.ventaID")%> >Generar PDF</a></td>
                <%}%>
            </tr>
        </table><%}%>
    </body>
</html>
