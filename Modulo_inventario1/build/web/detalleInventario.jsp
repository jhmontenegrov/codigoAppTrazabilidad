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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='lista_inventario.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado;
        Statement sentencia;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        Integer idInveta = Integer.parseInt(request.getParameter("Id"));
        resultado = sentencia.executeQuery("SELECT * FROM inventario iv, producto prod, almacen al WHERE iv.InventarioID='" + idInveta + "' AND iv.ProductoID=prod.ProductoID AND al.AlmacenID=iv.AlmacenID");
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/main.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle inventario</title>
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
        <div id="main">
            <section>
                <table width="30%">
                    <th>
                    <td>
                        <% while (resultado.next()) {
                                String nombreP = resultado.getString("prod.nom_producto");
                                Integer cantidadI = resultado.getInt("iv.cantidad");
                                String almacenI = resultado.getString("al.Ubicacion");
                                String observacion = resultado.getString("iv.Observacion");
                        %>
                        <form id="form12" method="POST" action="lista_inventario.jsp">

                            <label for="productoInfo">Producto:</label>
                            <input type="text" name="productoInfo" value="<%=nombreP%>" disabled="disable"/>
                            <br>
                            <label for="cantidadP">Cantidad:</label>
                            <input type="text" name="cantidadP" id="cantidadP" value="<%=cantidadI%>" disabled="disable"/>
                            <br>
                            <label for="almacen">Almacen:</label>
                            <input type="text" name="almacen" value="<%=almacenI%>" disabled="disable"/>
                            <br>
                            <label for="inventario">Nombre Inventario</label>
                            <input type="text" name="inventario" id="inventario" value="<%=observacion%>" disabled="disable"/>
                            <br>
                            <button title="volver">Volver</button>
                        </form>
                        <%}%>
                    </td>
                    </th>
                </table>
                <%}%>
            </section>
        </div>
    </body>
</html>