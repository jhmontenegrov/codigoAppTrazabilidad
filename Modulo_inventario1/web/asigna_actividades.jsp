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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='asigna_actividades.jsp'");
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
        resultado = sentencia.executeQuery("SELECT * FROM usuario u, perfil_usuario pu where u.Perfil_usuarioID=pu.Perfil_usuarioID;");
        resultado2 = sentencia2.executeQuery("SELECT * FROM actividades_perfil_usuario");
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
        <form name="form2" method="post" action="Asigna_actividadesCtrl">
            <table  style="width: 100%">
                <tr>
                    <td>
                        <label for="select_user">Seleccione Usuario</label>
                        <select name="select_user" required>
                            <option value="">--</option>
                            <% while (resultado.next()) {
                                    Integer usuarioID = Integer.parseInt(resultado.getString("u.usuarioID"));
                                    String nombre = resultado.getString("u.nombre");
                                    String apellido = resultado.getString("u.apellido");
                                    String cargo = resultado.getString("pu.Nom_perfilusuario");
                            %>
                            <option value=<%=usuarioID%>><%=nombre%> <%=apellido%> - <%=cargo%></option>
                            <%}%>
                        </select>
                        <br><br><br><br><br><br><br><br>
                        <button title="Guardar">Guardar</button>
                    </td>
                    <td>
                        <table style="width: 100%">
                            <% while (resultado2.next()) {
                                    String nom_activ = resultado2.getString("Actividades");
                                    Integer id_activ = Integer.parseInt(resultado2.getString("actividades_perfil_usuarioID"));
                            %>
                            <tr>
                                <td>
                                    <input type="radio" name="select_activ" value=<%=id_activ%> />
                                </td>
                                <td><%=nom_activ%></td>
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
