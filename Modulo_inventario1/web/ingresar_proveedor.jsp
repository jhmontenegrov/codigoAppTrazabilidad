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
    resultaLog=sent.executeQuery("SELECT UsuarioID FROM usuario WHERE UsuarioID='"+idUsuario+"'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado1;
        Statement sentencia1;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia1 = (Statement) con.createStatement();
        resultado1 = sentencia1.executeQuery("SELECT * FROM ciudad ci");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <title>Ingresar Proveedor</title>
    </head>
    <body>
        <form id="form1" method="POST" action="Ingresar_proveedorCtrl">
            <table style="width: 100%">
                <tr>
                    <td>
                        <label for="nombreProv">Nombre de proveedor</label>
                        <input type="text" name="nombreProv" required pattern="[a-z ]{3,31}" title="Solo ingrese letras sin tildes"/>
                    </td>
                    <td>
                        <label for="direccionProv">Dirección de proveedor</label>
                        <input type="text" name="direccionProv" required pattern="[a-z0-9 |-]{3,31}" title="No corresponde a una dirección válida"/>
                    </td>
                    <td>
                        <label for="id_ciudadAC">Ciudad de proveedor</label>
                        <select name="id_ciudadAC" required>
                            <option value="">--</option>
                            <% while (resultado1.next()) {
                                    Integer id_ciudad = resultado1.getInt("ci.CiudadID");
                                    String ciudad = resultado1.getString("ci.Ciudad_nombre");
                                    String ciudadPais = resultado1.getString("ci.Pais");
                            %>
                            <option value=<%=id_ciudad%> ><%=ciudad%> - <%=ciudadPais%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="telProv">telefono de proveedor:</label>
                        <input type="text" name="telProv" required pattern="[0-9]{7,10}" title="Ingrese un número de contacto valido"/>
                    </td>
                    <td>
                        <label for="mailProv">E-mail de proveedor:</label>
                        <input type="email" name="mailProv" required/>
                    </td>
                    <td>
                        <br>
                        <button title="Guardar">Guardar</button>               
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
<%}%>