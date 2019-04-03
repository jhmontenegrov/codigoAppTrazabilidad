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
    resultaLog = sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='" + idUsuario + "' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='listaDevoluciones.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado;
        Statement sentencia;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("select * from devolucion dv, inventario inv, almacen al, establecimiento est WHERE dv.InventarioID=inv.InventarioID AND inv.AlmacenID=al.AlmacenID "
                + "AND al.EstablecimientoID=est.EstablecimientoID");
%>
<html>
    <head>
        <title>Sistema de trazabilidad</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <link rel="stylesheet" href="css/main.css" />
    </head>
    <body>

        <table style="width: 100%">
            <tr>
                <th>ID</th>
                <th>Fecha de de Devolución</th>
                <th>Ubicación</th>
                <th>Inventario</th>
            </tr>
            <% while (resultado.next()) {
                    Integer spId = resultado.getInt("dv.DevolucionID");
                    Date fecha = resultado.getDate("dv.Fech_devolucion");
                    String ubicacion = resultado.getString("est.Ubicaciontienda");
                    String inventario = resultado.getString("inv.Observacion");
            %>
            <tr>
                <td><%=spId%></td>
                <td><%=fecha%></td>
                <td><%=ubicacion%></td>
                <td><%=inventario%></td>
                <td><a href=<%= "DevolucionPDF?Id=" + resultado.getInt("dv.DevolucionID")%> >Generar PDF</a></td>
                <td><a href=<%= "actualizarDevolucion.jsp?Id=" + resultado.getInt("dv.DevolucionID")%>>Cambiar Estado</a></td>
                <%}
                    }%>
            </tr>
        </table>
    </body>
</html>