<%@page import="java.sql.Statement"%>
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
    ResultSet resultado, resultado2;
    Statement sentencia, sentencia2;
    String ciudad;
    int id_ciudad;
    Connection con;
    con = (Connection) Conexion.crearConexion();
    sentencia = (Statement) con.createStatement();
    sentencia2= (Statement) con.createStatement();
    resultado2 = sentencia2.executeQuery("SELECT * FROM usuario usu, ciudad c where usuarioID='"+idUsuario+"' AND c.ciudadID=usu.ciudadID ");
    resultado = sentencia.executeQuery("SELECT * FROM ciudad");
%>
<html>
    <head>
        <title>Perfil usuario</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="css/main.css" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script language="javascript">
            function numValida(f) {
                var code;
                if (!f) {
                    var f = window.event;
                }
                if (f.keyCode) {
                    code = f.keyCode;
                } else if (f.which) {
                    code = f.which;
                    var character = String.fromCharCode(code);
                    var AllowRegex = /^[\0-9\s-]$/;
                    if (AllowRegex.test(character)) {
                        return true;
                    }
                }
                return false;
            };
        </script>
    </head>
    <body>
        <div id="main">
            <section>   	   			
                <div>
                    <table width="30%">
                        <th>
                        <td>
                            <form id="form1" method="POST" action="ActualizarInfoPerfiCtrol">
                                <% while (resultado2.next()){
                                    String nombreUser =(resultado2.getString("usu.nombre"));
                                    String apellidoUser=(resultado2.getString("usu.apellido"));
                                    String telUser=(resultado2.getString("usu.Tel_usuario"));
                                    String direccionUser=(resultado2.getString("usu.direccion"));
                                    String emailUser=(resultado2.getString("usu.email"));
                                    String ciudadUser=(resultado2.getString("usu.ciudadID"));
                                    String ciudadnomUser = resultado2.getString("Ciudad_nombre");
                                 %>
                                <br>
                                <label for="nombreAC">Nombre</label>
                                <input type="text" name="nombreAC" id="nombreAC" value="<%=nombreUser%>" disabled>
                                <br>
                                <label for="apellidoAC">Apellido</label>
                                <input type="text" name="apellidoAC" value="<%=apellidoUser%>" disabled>
                                <br>
                                <label for="telefonoAC">Telefono</label>
                                <input type="text" name="telefonoAC" id="telefonoAC" value="<%=telUser%>" required onkeypress="return numValidar(event);">
                                <br>
                                <label for="DireccionAC">Dirección</label>
                                <input type="text" name="direccionAC" id="direccionAC" value="<%=direccionUser%>" required>
                                <br>
                                <label for="correoAC">Email</label>
                                <input type="email" name="correoAC" id="correoAC" value="<%=emailUser%>" disabled>
                                <br>
                                <label for="id_ciudadAC">Ciudad</label>
                                <select name="id_ciudadAC">
                                    <option value="<%=ciudadUser%>"><%=ciudadnomUser%></option>
                                <% }while (resultado.next()){
                                    id_ciudad = Integer.parseInt(resultado.getString("CiudadID"));
                                    ciudad = resultado.getString("Ciudad_nombre");
                                %>
                                    <option value=<%=id_ciudad%> ><%=ciudad%></option>
                                <%}%>
                                </select>
                                <br>
                                <br>
                                <button title="actualizar">Actualizar</button>
                            </form>
                        </td>
                        </th>
                    </table>
                </div>
            </section>
        </div>
    </body><%}%>
</html>
