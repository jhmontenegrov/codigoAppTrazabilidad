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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='ingresar_establecimiento.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
    ResultSet resultado1;
    Statement sentencia1;
    Connection con;
    con = (Connection) Conexion.crearConexion();
    sentencia1 = (Statement) con.createStatement();
    resultado1 = sentencia1.executeQuery("SELECT * FROM ciudad");
%>
<html>
    <head>
        <title>Ingresar establecimiento</title>
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
    <center>
        <div id="main">
            <section>   	   			
                <div>
                    <table width="30%">
                        <td>
                            <form id="form1" method="POST" action="Ingresar_establecimientoCtrl">
                                <label for="ubi_tienda">Ubicacion establecimiento</label>
                                <input type="text" name="ubi_tienda" id="ubi_tienda" required pattern="[a-z0-9 |-]{4,32}" title="Ingrese un valor válido">
                                <br>
                                <label for="id_ciudad">Seleccione la ciudad</label>
                                <select name="id_ciudad" required>
                                    <option value="">--</option>
                                <% while (resultado1.next()){
                                    int id_ciudad = resultado1.getInt("CiudadID");
                                    String ciudad = resultado1.getString("Ciudad_nombre");
                                    String pais = resultado1.getString("Pais");
                                %>
                                    <option value=<%=id_ciudad%>><%=ciudad%> - <%=pais%></option>
                                <%}%>
                                </select><br>
                                <button title="Guardar" >Guardar</button>
                            </form>
                        </td>
                    </table>
                </div>
            </section>
        </div>
    </center>
</body>
</html>
<%}%>
