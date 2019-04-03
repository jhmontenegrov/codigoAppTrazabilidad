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
    resultaLog = sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='" + idUsuario + "' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='lista_inventario.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado;
        Statement sentencia;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM inventario iv, producto prod, almacen al WHERE iv.ProductoID=prod.ProductoID AND al.AlmacenID=iv.AlmacenID");
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
                <th>Nombre Inventario</th>
                <th>Nombre Producto</th>
                <th>Cantidad</th>
                <th>Almacen</th>
                <th>Reporte</th>
            </tr>
            <% while (resultado.next()) {
                    Integer inId = resultado.getInt("iv.inventarioID");
                    String name = resultado.getString("iv.observacion");
                    String nomb = resultado.getString("prod.Nom_producto");
                    Integer cant = resultado.getInt("iv.cantidad");
                    String nomAl = resultado.getString("al.Ubicacion");
            %>
            <tr>
                <td><%=inId%></td>
                <td><%=name%></td>
                <td><%=nomb%></td>
                <td><%=cant%></td>
                <td><%=nomAl%></td>
                <td>
                    <a href=<%= "detalleInventario.jsp?Id=" + inId%> >Detalle</a>
                </td>
                <%}%>
            </tr>
        </table>
    </body><%}%>
</html>