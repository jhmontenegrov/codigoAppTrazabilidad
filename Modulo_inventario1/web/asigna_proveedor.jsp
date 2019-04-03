<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="errorPage.jsp" %>
<%@ page import ="beans.Conexion" %>
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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='asigna_proveedor.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado, resultado2;
        Statement sentencia, sentencia2;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        sentencia2 = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM inventario iv");
        resultado2 = sentencia2.executeQuery("SELECT * FROM proveedor pr");
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
        <form name="form2" method="post" action="Asigna_proveedorCtrl">
            <table  style="width: 100%">
                <tr>
                    <td>
                        <label for="select_inventario">Seleccione Inventario</label>
                        <select name="select_inventario" required>
                            <option value="">--</option>
                            <% while (resultado.next()) {
                                    int inventarioId = resultado.getInt("iv.inventarioID");
                                    String nombreInv = resultado.getString("iv.observacion");
                            %>
                            <option value=<%=inventarioId%>> <%=nombreInv%> </option>
                            <%}%>
                        </select>
                        <br><br><br><br><br><br><br><br>
                        <button title="Guardar">Guardar</button>
                    </td>
                    <td>
                        <table style="width: 100%">
                            <% while (resultado2.next()) {
                                    String nom_prov = resultado2.getString("pr.Nom_proveedor");
                                    Integer id_prov = Integer.parseInt(resultado2.getString("pr.proveedorID"));
                            %>
                            <tr>
                                <td>
                                    <input type="radio" name="select_prov" value=<%=id_prov%> />
                                </td>
                                <td><%=nom_prov%></td>
                                <%}
                                    }%>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
