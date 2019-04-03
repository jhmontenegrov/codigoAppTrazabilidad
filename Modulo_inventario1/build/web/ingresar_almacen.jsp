<%@page import="com.mysql.jdbc.Statement"%>
<!doctype html>
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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='ingresar_almacen.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado, resultado3;
        Statement sentencia, sentencia3;
        String nombre_establecimiento;
        int id_establecimiento;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        sentencia3 = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM ciudad");
        resultado3 = sentencia3.executeQuery("SELECT * FROM establecimiento");
%>
<html>
    <head>
        <title>Ingresar Almacen</title>
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
    <center>
        <div id="main">
            <section>   	   			
                <div>
                    <table width="30%">
                        <th>
                        <td>
                            <form id="form1" method="POST" action="Ingresar_almacenCtrl">
                                <label for="ubica">Ubicación del almacen</label>
                                <input type="text" name="ubica" id="ubica" pattern="[a-z0-9 |#|-]{5,31}" title="Ingrese un valor válido" required>
                                <br>
                                <label for="id_ciudad">Seleccione la ciudad</label>
                                <select name="id_ciudad" required>
                                    <option value="">--</option>
                                    <% while (resultado.next()) {
                                            int id_ciudad = Integer.parseInt(resultado.getString("CiudadID"));
                                            String ciudad = resultado.getString("Ciudad_nombre");
                                            String pais = resultado.getString("Pais");
                                    %>
                                    <option value=<%=id_ciudad%>><%=ciudad%> - <%=pais%></option>
                                    <%}%>
                                </select><br>
                                <label for="id_establecimiento">Seleccione el establecimiento</label>
                                <select name="id_establecimiento" required>
                                    <option value="">--</option>
                                    <% while (resultado3.next()) {
                                            id_establecimiento = Integer.parseInt(resultado3.getString("establecimientoID"));
                                            nombre_establecimiento = resultado3.getString("ubicacionTienda");
                                    %>
                                    <option value=<%=id_establecimiento%>><%=nombre_establecimiento%></option>
                                    <%}%>
                                </select><br>
                                <button title="Guardar" >Guardar</button>
                            </form>
                        </td>
                        </th>
                    </table>
                </div>
            </section>
        </div>
    </center>
</body>
</html>
<%}%>