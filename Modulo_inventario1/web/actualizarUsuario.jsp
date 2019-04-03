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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='crear_usuario.jsp'");
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
        sentencia2 = (Statement) con.createStatement();
        Integer idU = Integer.parseInt(request.getParameter("idU"));
        resultado2 = sentencia2.executeQuery("SELECT * FROM usuario usu, ciudad c where usuarioID='" + idU + "' AND c.ciudadID=usu.ciudadID ");
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
    </head>
    <body>
        <div id="main">
            <section>   	   			
                <div>
                    <table >
                        <form id="form1" method="POST" action="ActualizarInfoUsuariosCtrol">
                            <% while (resultado2.next()) {
                                    String nombreUser = (resultado2.getString("usu.nombre"));
                                    String apellidoUser = (resultado2.getString("usu.apellido"));
                                    String telUser = (resultado2.getString("usu.Tel_usuario"));
                                    String direccionUser = (resultado2.getString("usu.direccion"));
                                    String emailUser = (resultado2.getString("usu.email"));
                                    String ciudadUser = (resultado2.getString("usu.ciudadID"));
                                    String ciudadnomUser = resultado2.getString("Ciudad_nombre");
                            %>
                            <tr>
                                <td>
                                    <input type="hidden" name="idU" value="<%=idU%>"/>
                                    <label for="pass">Cambiar Password:</label>
                                    <input type="password" name="pass" id="pass" pattern="{8,15}" title="El password debe ser mínimo de 8 caracteres y máximo de 15"/>
                                </td>
                                <td>
                                    <label for="nombreAC">Nombre</label>
                                    <input type="text" name="nombreAC" id="nombreAC" value="<%=nombreUser%>" disabled>
                                </td>                              
                                <td>
                                    <label for="apellidoAC">Apellido</label>
                                    <input type="text" name="apellidoAC" value="<%=apellidoUser%>" disabled>
                                </td>                              
                                <td>
                                    <label for="telefonoAC">Telefono</label>
                                    <input type="text" name="telefonoAC" id="telefonoAC" value="<%=telUser%>" disabled>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="DireccionAC">Dirección</label>
                                    <input type="text" name="direccionAC" id="direccionAC" value="<%=direccionUser%>" disabled>
                                </td>
                                <td>
                                    <label for="correoAC">Email</label>
                                    <input type="email" name="correoAC" id="correoAC" value="<%=emailUser%>" disabled>
                                </td>                              
                                <td>
                                    <label for="id_ciudadAC">Ciudad</label>
                                    <select name="id_ciudadAC" disabled>
                                        <option value="<%=ciudadUser%>"><%=ciudadnomUser%></option>
                                        <% }
                                            while (resultado.next()) {
                                                id_ciudad = Integer.parseInt(resultado.getString("CiudadID"));
                                                ciudad = resultado.getString("Ciudad_nombre");
                                        %>
                                        <option value=<%=id_ciudad%> ><%=ciudad%></option>
                                        <%}%>
                                    </select>
                                </td>                              
                                <td><br>
                                    <button title="Guardar">Guardar</button>
                                </td>
                        </form>

                    </table>
                </div>
            </section>
        </div>
    </body><%}%>
</html>
