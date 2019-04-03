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
    resultaLog = sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='" + idUsuario + "' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='listaPedidos.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado;
        Statement sentencia;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM requisicion r");
%>
<html>
    <head>
        <title>Sistema de trazabilidad</title>
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

        <table style="width: 100%">
            <tr>
                <th>ID</th>
                <th>Nombre Producto</th>
                <th>Cantidad</th>
                <th>Reporte</th>
                <th>Cambio de estado</th>
            </tr>
            <% while (resultado.next()) {
                    Integer rId = resultado.getInt("r.RequisicionID");
                    String nombre_producto = resultado.getString("r.nombre_producto");
                    String cantidad = resultado.getString("r.cantidad");
            %>
            <tr>
                <td><%=rId%></td>
                <td><%=nombre_producto%></td>
                <td><%=cantidad%></td>
                <td><a href=<%= "PedidosPDF?Id=" + resultado.getInt("r.RequisicionID")%> >Generar PDF</a></td>
                <td><a href=<%= "actualizarPedido.jsp?Id=" + resultado.getInt("r.RequisicionID")%> >Cambiar Estado</a></td>
                <%}%>
            </tr>
        </table>
    </body><%}%>
</html>