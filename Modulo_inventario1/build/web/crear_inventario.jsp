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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='crear_inventario.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado;
        Statement sentencia;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT p.productoID, p.nom_producto FROM producto p");
%>
<html>
    <head>
        <title>Crear Inventario</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script language="javascript">
            function Solo_texto(e) {
                var code;
                if (!e) {
                    var e = window.event;
                }
                if (e.keyCode) {
                    code = e.keyCode;
                } else if (e.which) {
                    code = e.which;
                    var character = String.fromCharCode(code);
                    var AllowRegex = /^[\a-z\s-]$/;
                    if (AllowRegex.test(character)) {
                        return true;
                    }
                }
                return false;
            }
            ;
        </script>
        <link rel="stylesheet" href="css/main.css" />
    </head>
    <body>
    <center>
        <div id="main">
            <section>
                <table width="30%">
                    <th>
                    <td>
                        <form id="form1" method="POST" action="Ingresar_inventarioCtrl">
                            <label for="select_producto">seleccionar producto</label>
                            <select name="select_producto" required>
                                <option value="">--</option>
                                <% while (resultado.next()) {
                                        String productoId = resultado.getString("p.productoID");
                                        String nombreP = resultado.getString("p.nom_producto");
                                %>
                                <option value=<%=productoId%>><%=nombreP%></option>
                                <%}%>
                            </select>
                            
                            <button title="Guardar">Guardar</button>
                        </form>
                    </td>
                </th>
                </table>
                <%}%>
            </section>
        </div>
    </center>
</body>
</html>
<%}%>