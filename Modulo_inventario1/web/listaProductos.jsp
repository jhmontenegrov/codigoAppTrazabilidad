<%@page import="java.util.Base64"%>
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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='listaProductos.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
    ResultSet resultado;
    Statement sentencia;
    Connection con;
    con = (Connection) Conexion.crearConexion();
    sentencia = (Statement) con.createStatement();
    resultado = sentencia.executeQuery("SELECT * FROM producto p, inventario inv where p.ProductoID=inv.ProductoID order by producNo");
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
        <div>
            <form id="form1" method="POST" action="productoPdf">
                <button title="generarPDF" >Generar PDF</button>
            </form>  
        </div>
        <table style="width: 100%">
            <tr>
                <th>Nombre</th>
                <th>Lote</th>
                <th>Zona</th>
                <th>estante</th>
                <th>Reporte</th>
            </tr>
            <% while (resultado.next()) {
                    String nom_prod = resultado.getString("p.nom_producto");
                    String lot_prod = resultado.getString("p.lote");
                    String zona = resultado.getString("inv.zona");
                    String estante = resultado.getString("inv.estante");
            %>
            <tr>
                 <td><%=nom_prod%></td>
                <td><%=lot_prod%></td>
                <td><%=zona%></td>
                <td><%=estante%></td>
                
                <td>
                    <a href=<%= "ReporteProducto?id=" + resultado.getInt("p.producNo")%> >Generar PDF</a>
                </td>
                <%}%>
            </tr>
        </table><%}%>
    </body>
</html>
     