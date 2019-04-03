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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='crear_usuario.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado, resultado2;
        Statement sentencia, sentencia2;
        String ciudad, nombre_user;
        int id_ciudad, id_tipo_user;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        sentencia2 = (Statement) con.createStatement();
        resultado2 = sentencia2.executeQuery("SELECT * FROM perfil_usuario");
        resultado = sentencia.executeQuery("SELECT * FROM ciudad");
%>
<html>
    <head>
        <title>Nuevo usuario</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="css/main.css" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        </script>
    </head>
    <body>
        <div id="main">
            <section>   	   			
                <div>
                    <form id="form1" method="POST" action="CrearUsuarioCtrl">
                        <table width="100%">
                            <tr>
                                <td>
                                    <label for="cid">Cédula (Sin tildes o puntos)</label>
                                    <input type="text" name="cid" id="cid" required pattern="[0-9]{8,11}" title="No corresponde a un número de cédula válido"/>
                                </td>                              
                                <td>

                                    <label for="nombre">Nombre</label>
                                    <input type="text" name="nombre" id="nombre" required pattern="[a-z]{3,21}" title="No corresponde a un nombre válido"/>
                                </td>
                                <td>

                                    <label for="apellido">Apellido</label>
                                    <input type="text" name="apellido" id="apellido" required pattern="[a-z]{3,21}" title="No corresponde a un apellido válido"/>
                                </td>                              
                                <td>

                                    <label for="telefono">Telefono</label>
                                    <input type="text" name="telefono" id="telefono" required pattern="[0-9]{7,10}" title="No corresponde a un número de contacto válido"/>
                                </td>
                            </tr>
                            <tr>
                                <td>

                                    <label for="Direccion">Dirección</label>
                                    <input type="text" name="direccion" id="direccion" required pattern="[a-z0-9]{3,31}" title="No corresponde a una dirección válida"/>
                                </td>                              
                                <td>

                                    <label for="user">Usuario</label>
                                    <input type="text" name="user" id="user" required pattern="[a-z0-9]{4,8}" title="El usuario debe ser mínimo de 4 caracteres y máximo de 8"/>
                                </td>                              
                                <td>

                                    <label for="pass">Password</label>
                                    <input type="password" name="pass" id="pass" required pattern="{8,15}" title="El password debe ser mínimo de 8 caracteres y máximo de 15"/>
                                </td>                              
                                <td>

                                    <label for="correo">Email</label>
                                    <input type="email" name="correo" id="correo" required/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="id_ciudad">Ciudad</label>
                                    <select name="id_ciudad" required>
                                        <option value="">--</option>
                                        <% while (resultado.next()) {
                                                id_ciudad = Integer.parseInt(resultado.getString("CiudadID"));
                                                ciudad = resultado.getString("Ciudad_nombre");
                                        %>
                                        <option value=<%=id_ciudad%>><%=ciudad%></option>
                                        <%}%>
                                    </select>
                                </td>                              
                                <td>

                                    <label for="t_usuario">Tipo de usuario</label>
                                    <select name="t_usuario" required>
                                        <option value="">--</option>
                                        <% while (resultado2.next()) {
                                                id_tipo_user = Integer.parseInt(resultado2.getString("perfil_usuarioID"));
                                                nombre_user = resultado2.getString("Nom_perfilusuario");
                                        %>
                                        <option value=<%=id_tipo_user%>><%=nombre_user%></option>
                                        <%}%>
                                    </select>
                                </td>                              
                                <td>
                                    <label for="activ_usuario">Activación de usuario:</label>
                                    <select name="activ_usuario" required>
                                        <option value="">--</option>
                                        <option value="false">Activo</option>
                                        <option value="true">Desactivado</option>
                                    </select>
                                </td>                              
                                <td>
                                    <br/>
                                    <button title="Guardar">Guardar</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </section>
        </div>
    </body>
</html>
<%}%>